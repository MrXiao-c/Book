package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Carts;
import entity.books;
import util.Dbobj;
import util.JdbcUtils;

public class CartService {
	
	public void addgoods(int bookid,String name,int prices,int buynum,int totalprice,String picture) {
		String sql="declare begin insert  into CARTS(BOOKID,NAME,PRICE,BUYNUM,TOTALPRICE,PICTURE) values"
				+ "('"+bookid+"','"+name+"','"+prices+"','"+buynum+"','"+totalprice+"','"+picture+"');end;";
		
		 System.out.println(""+sql);
		JdbcUtils jdbcutils=new JdbcUtils();
		jdbcutils.executeUpdate(sql);
	}
	public void updategoods(int bookid,int num) {
		String sql="update CARTS set buynum=buynum+'"+num+"' where bookid='"+bookid+"'";
		
		 System.out.println(""+sql);
		JdbcUtils jdbcutils=new JdbcUtils();
		jdbcutils.executeUpdate(sql);
	}
	public List findgoods(int bookid) {
		String sql="select *from CARTS  where  bookid="+bookid;
		List list= new ArrayList();
		 Dbobj dj=new Dbobj();
		   JdbcUtils jdbcu=new JdbcUtils();
		   dj=jdbcu.executeQuery(sql);
		   ResultSet rs=dj.getRs(); 
		   System.out.println(""+sql);	
		   try {
				while(rs.next())
					{
					Carts carts=new Carts();
					
					carts.setName(""+rs.getString("NAME"));
					carts.setPrice(rs.getInt("PRICE"));
					carts.setBuynum(rs.getInt("BUYNUM"));
					carts.setTotalPrice(rs.getInt("TOTALPRICE"));
					carts.setPicture(rs.getString("PICTURE"));
					list.add(carts);
					
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
	public List showgoods() {
		String sql="select *from cart_view";
		 List list= new ArrayList();
		 Dbobj dj=new Dbobj();
		   JdbcUtils jdbcu=new JdbcUtils();
		   dj=jdbcu.executeQuery(sql);
		   ResultSet rs=dj.getRs(); 
		   System.out.println(""+sql);
		   
		   try {
				while(rs.next())
					{
					Carts carts=new Carts();
					carts.setId(rs.getInt("ID"));
					carts.setName(""+rs.getString("NAME"));
					carts.setPrice(rs.getInt("PRICE"));
					carts.setBuynum(rs.getInt("BUYNUM"));
					carts.setTotalPrice(rs.getInt("TOTALPRICE"));
					carts.setPicture(rs.getString("PICTURE"));
					carts.setBookid(rs.getInt("BOOKID"));
					list.add(carts);
					
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
	public boolean deletegoods(int id) {
		String sql="delete from CARTS where id="+id;
		
		 System.out.println(""+sql);
		JdbcUtils jdbcutils=new JdbcUtils();
		jdbcutils.executeUpdate(sql);
		return true;
	}
	
	public double totalPrice() {
		String sql="select sum(TOTALPRICE) from CARTS";
		
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
	
	public static void main(String[] args) {
		CartService cartService=new CartService();
		List list=cartService.findgoods(12);
		System.out.println(!list.isEmpty());
	}
}
