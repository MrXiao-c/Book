package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import util.Dbobj;




public class JdbcUtils {
	public static Connection conn;

	// sql语句的执行对象

private PreparedStatement pstmt;

	// 返回的结果集合

private ResultSet resultSet;	
static Properties pros = null;
	
	
   


public static Connection getConnection()

{



	

try

{
	pros = new Properties();
	pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
	// 加载驱动
	Class.forName(pros.getProperty("mysqlDriver"));
	// 连接数据库
	conn = DriverManager.getConnection(pros.getProperty("mysqlURL"), pros.getProperty("mysqlUser"), pros.getProperty("mysqlPwd"));
	
	
	
	
}

catch(Exception e)

{

e.printStackTrace();

}

return conn;

}

    
    public static void release(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();   //throw new
                rs=null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
                st=null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn=null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

   

  

 
    public Dbobj executeQuery(String sql) {	
	  Dbobj dj=new Dbobj();
	  
       Connection conn = null;
       Statement stmt=null;
       PreparedStatement pstmt = null;
        ResultSet rs = null;
      
      try{
				
                               conn =JdbcUtils.getConnection();
			       stmt=conn.createStatement();
				rs=stmt.executeQuery(sql);
				
		dj.setConn(conn);
		dj.setPstmt(pstmt);
		dj.setRs(rs);
		dj.setStmt(stmt);
				
			}catch(SQLException ex){
				System.err.println(ex.getMessage());
			}
			
               
   
      
           return dj;
		}



  
  
    public void executeUpdate(String sql)
	    {
	    	
    	
    	
	    	 Connection conn = null;
       Statement stmt=null;
      
     
	    	try
	    	{  
                     conn = JdbcUtils.getConnection();
			       stmt=conn.createStatement();
	    		
	    		stmt.executeUpdate(sql);
	    		stmt.close();
	    		conn.close();
	    		
	    		conn=null;
	    		stmt=null;
	    		
	    		
	    	
	    	}
	    	catch(SQLException ex)
	    	{
	    		System.err.println(ex.getMessage());
	    		
	    		
	    	}
	    
	    	
	    }
    public boolean updateByPreparedStatement(String sql, List<?> params) throws SQLException {

		boolean flag = false;

		int result = -1;// 表示用户执行添加删除和修改的时候所影响数据库的行数

		pstmt = conn.prepareStatement(sql);

		int index = 1;

		// 填充sql语句中的占位符

		if (params != null && !params.isEmpty()) {

			for (int i = 0; i < params.size(); i++) {

				pstmt.setObject(index++, params.get(i));

			}

		}

		result = pstmt.executeUpdate();

		flag = result > 0 ? true : false;

		return flag;

	}

	 /**

     * 执行查询操作

     * 

     * @param sql

     *            sql语句

     * @param params

     *            执行参数

     * @return

     * @throws SQLException

     */

	public List<Map<String, Object>> findResult(String sql, List<?> params) throws SQLException {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		int index = 1;

		pstmt = conn.prepareStatement(sql);

		if (params != null && !params.isEmpty()) {

			for (int i = 0; i < params.size(); i++) {

				pstmt.setObject(index++, params.get(i));

			}

		}

		resultSet = pstmt.executeQuery();

		// getMetaData()获取结果集的所有字段的描述

		ResultSetMetaData metaData = resultSet.getMetaData();

		// 得到数据集的列数

		int cols_len = metaData.getColumnCount();

		while (resultSet.next()) {

			Map<String, Object> map = new HashMap<String, Object>();

			for (int i = 0; i < cols_len; i++) {

				String cols_name = metaData.getColumnName(i + 1);

				Object cols_value = resultSet.getObject(cols_name);

				if (cols_value == null) {

					cols_value = "";

				}

				map.put(cols_name, cols_value);

			}

			list.add(map);

		}

		return list;

	}
  

}