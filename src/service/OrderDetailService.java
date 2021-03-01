package service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Order;
import entity.OrderDetail;
import util.Dbobj;
import util.JdbcUtils;

public class OrderDetailService {
	public OrderDetail showOrderDetail(int bookid) {
		 List list = new ArrayList();
		String sql="select  ID,BOOKID,NAME,PRICE,BUYNUM,TOTALPRICE,PICTURE,"
				+ "to_char(BUYDATE,'yyyy-MM-dd HH24:mi:ss') as BUYDATE from ordersdetail where BOOKID="+bookid;
		
		 Dbobj dj=new Dbobj();
		   JdbcUtils jdbcu=new JdbcUtils();
		   dj=jdbcu.executeQuery(sql);
		   ResultSet rs=dj.getRs(); 
		   System.out.println(""+sql);
		   OrderDetail orderDetail=new OrderDetail();
		   try {
				if(rs.next())
					{
					
					orderDetail.setBookid(rs.getInt("BOOKID"));
					orderDetail.setId(rs.getInt("ID"));	
					orderDetail.setName(""+rs.getString("NAME"));
					orderDetail.setPrice(rs.getInt("PRICE"));
					orderDetail.setBuynum(rs.getInt("BUYNUM"));
					orderDetail.setTotalprice(rs.getInt("TOTALPRICE"));
					orderDetail.setPicture(rs.getString("PICTURE"));
					orderDetail.setDate(rs.getString("BUYDATE"));
					
					}
				else {
					return null;
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return orderDetail;
	}	
		
			public void addOrderDetail(int bookid,String name,int price,int buynum,int totalprice,String picture,Date buyDate) {
				String sql="insert into ORDERS(NAME,PRICE,BUYNUM,PICTURE,BOOKID) values ('"+bookid+"'','"+name+"','"+price+"','"+buynum+"','"+totalprice+"''"+picture+"','"+buyDate+"')";
				
				 System.out.println(""+sql);
				JdbcUtils jdbcutils=new JdbcUtils();
				jdbcutils.executeUpdate(sql);
			
		
}	
			
	
	public static void main(String[] args) {
		OrderDetailService orderDetailService=new OrderDetailService();
		System.out.println(orderDetailService.showOrderDetail(1));
	}

}
