package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import dao.*;
import entity.User;

import util.Dbobj;
import util.JdbcUtils;
public class UserDaoImpl implements UserDao {

	public void regist(User user) {
		// TODO Auto-generated method stub
		//将数据存入数据库中
		String sql="insert into SCOTT.USERS(USERNAME,PASSWORD,NICKNAME,email,state,code) values"
				+ "('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getNickname()+"','"+user.getEmail()+"',"
				+ "'"+user.getState()+"','"+user.getCode()+"')";
		
		JdbcUtils jdbcutils=new JdbcUtils();
		jdbcutils.executeUpdate(sql);
	
		
	}

	public User findByCode(String code) {
		// TODO Auto-generated method stub
		User user=new User();
		String sql="select * from SCOTT.USERS where CODE='"+code+"'";
		   Dbobj dj=new Dbobj();
		   JdbcUtils jdbcu=new JdbcUtils();
		   dj=jdbcu.executeQuery(sql);
		   ResultSet rs=dj.getRs(); 
		   System.out.println(""+sql);		   
		   try {
			while(rs.next())
				{
					user.setUid(Integer.parseInt(""+rs.getString("ID")));
					user.setUsername(""+rs.getString("USERNAME"));
					user.setPassword(""+rs.getString("PASSWORD"));
					user.setNickname(""+rs.getString("NICKNAME"));
					user.setEmail(""+rs.getString("EMAIL"));
					user.setCode(""+rs.getString("CODE"));
					user.setState(Integer.parseInt(""+rs.getString("STATE")));
				}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		   System.out.println("userdaoimpl"+user.toString());
		
		return user;
	}

	public void update(User user) {
		// TODO Auto-generated method stub
		String sql="update  SCOTT.USERS set USERNAME='"+user.getUsername()+"',PASSWORD='"+user.getPassword()+"',NICKNAME='"+user.getNickname()+"',EMAIL='"+user.getEmail()+"',STATE='"+user.getState()+"',CODE='"+user.getCode()+"' where ID='"+user.getUid()+"'";
		
		JdbcUtils jdbcutils=new JdbcUtils();
		jdbcutils.executeUpdate(sql);
		
	}
	public void updatePassword(User user) {
		// TODO Auto-generated method stub
		String sql="update  SCOTT.USERS set PASSWORD='"+user.getPassword()+"' where USERNAME='"+user.getUsername()+"'";
		
		JdbcUtils jdbcutils=new JdbcUtils();
		jdbcutils.executeUpdate(sql);
		
	}
	public String findEmail(String email) {
		// TODO Auto-generated method stub
		String sql="select email from SCOTT.USERS  where  USERNAME='"+email+"'";
		
		 Dbobj dj=new Dbobj();
		   JdbcUtils jdbcu=new JdbcUtils();
		   dj=jdbcu.executeQuery(sql);
		   ResultSet rs=dj.getRs(); 
		   System.out.println(""+sql);	
		   String t="";
		   try {
			while(rs.next())
				{
					t=rs.getString(1);
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
	public User findUser(User user) {
		User newuser=new User();
		String sql = "select *FROM SCOTT.USERS where USERNAME=? and PASSWORD=?";
		Dbobj dj=new Dbobj();
		   JdbcUtils jdbcu=new JdbcUtils();
		   Connection conn=JdbcUtils.getConnection();
		   try {
			   PreparedStatement stmt = conn.prepareStatement(sql);
			   String name = user.getUsername();
				String password = user.getPassword();
				stmt.setString(1, name);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next())
				{
				newuser.setUsername(rs.getString("USERNAME"));
				newuser.setPassword(rs.getString("PASSWORD"));
				}
				 
		
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return newuser;
		
	}
	public boolean findbyUser(String username) {
		// TODO Auto-generated method stub
		String sql="select username from SCOTT.USERS  where  USERNAME='"+username+"'";
		
		 Dbobj dj=new Dbobj();
		   JdbcUtils jdbcu=new JdbcUtils();
		   dj=jdbcu.executeQuery(sql);
		   ResultSet rs=dj.getRs(); 
		   System.out.println(""+sql);	
		   String t="";
		   try {
			while(rs.next())
				{
					t=rs.getString(1);
				}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 if (t.isEmpty()) {
			 return false;
		 }else{
			 return true; 
		 }
		
		
		
	}
public static void main(String[] args) {
	UserDao userDao=new UserDaoImpl();
	String usern="e";
	boolean t=userDao.findbyUser(usern);
	System.out.println(t);
}
}
