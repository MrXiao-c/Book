package service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.sql.*;

import entity.Order;
import entity.books;
import util.Dbobj;
import util.JdbcUtils;
import util.Pager;


public class OrderService {
	
	public void addOrder(int bookid,String name,int price,int buynum,String picture) {
		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
		int id = 0;
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
        String buyDate=year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
		System.out.println(buyDate);
		 try (Connection conn = JdbcUtils.getConnection();
	    
	    Statement stmt = conn.createStatement()) {
			 //事务处理，订单生成失败则回滚
            conn.setAutoCommit(false);
            try {
            	String sql="insert into ORDERS(NAME,PRICE,BUYNUM,PICTURE,BOOKID) values"
            			+ " ('"+name+"','"+price+"','"+buynum+"','"+picture+"','"+bookid+"')";
            	int totalprice=buynum*price;
            	System.out.println(totalprice);
            	 stmt.execute(sql,new String[] {"id"});
            	 ResultSet rs = stmt.getGeneratedKeys();
     			if(rs.next()){
     				id = rs.getInt(1);
     			}
     			System.out.println("ID："+id);
            	String sqlo="insert into ORDERSDETAIL(BOOKID,NAME,PRICE,BUYNUM,TOTALPRICE,PICTURE,BUYDATE,ORDERID) "
            			+ "values ('"+bookid+"','"+name+"','"+price+"','"+buynum+"','"+totalprice+"','"+picture+"',"
            					+ "to_date('"+buyDate+"','yyyy-mm-dd hh24:mi:ss'),'"+id+"')";
              
                
                stmt.execute(sqlo);
                String sqloa="update books set num=num-'"+buynum+"' where id='"+bookid+"'";
                
                stmt.execute(sqloa);
                
            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback(); // rollback
                throw e;
            }
            conn.commit(); // commit
		 } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error occurred!");
	        }

        } 
     
		
	public void deleteorder(int id) {
		 
			String sql="delete from ORDERS where ID="+id;
			
			 System.out.println(""+sql);
			 Dbobj dj=new Dbobj();
			   JdbcUtils jdbcu=new JdbcUtils();
			   jdbcu.executeUpdate(sql);
		
	}
     
  
		
		
	
	
	public int findOrder(int id) {
		String sql="select *from ORDER  where  ID="+id;
		
		 Dbobj dj=new Dbobj();
		   JdbcUtils jdbcu=new JdbcUtils();
		   dj=jdbcu.executeQuery(sql);
		   ResultSet rs=dj.getRs(); 
		   System.out.println(""+sql);	
		   int t=0;
		   try {
			while(rs.next())
				{
					t=rs.getInt(1);
				}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		   
		
		
		return t;
	
		
	}
	public List showOrder() {
		 List list = new ArrayList();
		String sql="select  *from orders";
		
		 Dbobj dj=new Dbobj();
		   JdbcUtils jdbcu=new JdbcUtils();
		   dj=jdbcu.executeQuery(sql);
		   ResultSet rs=dj.getRs(); 
		   System.out.println(""+sql);
		  
		   try {
				while(rs.next())
					{
					 Order order=new Order();
					order.setBookid(rs.getInt("BOOKID"));
					order.setId(rs.getInt("ID"));	
					order.setName(""+rs.getString("NAME"));
					order.setPrice(rs.getInt("PRICE"));
					order.setBuynum(rs.getInt("BUYNUM"));
					order.setPicture(rs.getString("PICTURE"));
					list.add(order);
					}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			  
			
			return list;
	}
	
	
	public Pager<Order> getAllOrders(Order searchModel,int pageNum, int pageSize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Order> list = new ArrayList<Order>(); // 书籍集合
		Pager<Order> result = null;
		String stuName = searchModel.getName();
		List<Object> paramList = new ArrayList<Object>();
		System.out.println("service"+stuName);
		try {
			conn = JdbcUtils.getConnection();
			StringBuilder sql = new StringBuilder("select * from SCOTT.ORDERS where 1=1"); // SQL语句
			StringBuilder countSql = new StringBuilder("select count(ID) as totalRecord from SCOTT.ORDERS where 1=1");
			if(stuName !=null&&!stuName.equals("")){
				sql.append(" and NAME like ?");
				countSql.append(" and NAME like ?");
				paramList.add("%"+stuName+"%");
			}
			int fromIndex = pageSize * (pageNum - 1)+1;
			//mysql使用limit关键字实现分页
			//sql.append(" limit "+ fromIndex+","+pageSize);
			//oracle实现分页
			sql.insert(0, "select *FROM( select s.*,rownum rn from (");
			sql.append(") s where rownum<="+(pageSize*pageNum)+") where rn>="+fromIndex);
			System.out.println(sql);
			
			JdbcUtils dbConn=new JdbcUtils();
			List<Order> ordersList = new ArrayList<Order>();
			List<Map<String,Object>> countResult = dbConn.findResult(countSql.toString(), paramList);
			Map<String,Object> countMap = countResult.get(0);
			System.out.println(countMap);
			int totalRecord = ((Number)countMap.get("TOTALRECORD")).intValue();
			List<Map<String,Object>> ordersResult = dbConn.findResult(sql.toString(), paramList);
			if(ordersResult!=null){
				for(Map<String,Object> map:ordersResult){
					Order s = new Order(map);
					ordersList.add(s);
				}
			}
			int totalPage = totalRecord / pageSize;
			if (totalRecord % pageSize != 0) {
				totalPage = totalPage + 1;
			}
			result = new Pager<>(pageSize, pageNum, totalRecord, totalPage, ordersList);

			
			
			
			System.out.println(list);
			System.out.println(result);
			return result; // 返回集合
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		}
	public static void main(String[] args) {
		OrderService orderService=new OrderService();
		List list=new ArrayList<>();
		list=orderService.showOrder();
		System.out.println(list);

		
		
	}
}
