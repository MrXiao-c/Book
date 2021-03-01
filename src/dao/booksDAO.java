package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.books;
import util.JdbcUtils;
import util.Pager;

// 书籍的业务逻辑类
public class booksDAO {

	// 获得所有的书籍信息
	public Pager<books> getAllItems(books searchModel, int pageNum, int pageSize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<books> list = new ArrayList<books>(); // 书籍集合
		Pager<books> result = null;
		String bookName = searchModel.getName();
		List<Object> paramList = new ArrayList<Object>();
		System.out.println("dao" + bookName);
		try {
			conn = JdbcUtils.getConnection();
			StringBuilder sql = new StringBuilder("select * from SCOTT.BOOKS where 1=1"); // SQL语句
			StringBuilder countSql = new StringBuilder("select count(ID) as totalRecord from SCOTT.BOOKS where 1=1");
			if (bookName != null && !bookName.equals("")) {
				sql.append(" and NAME like ?");
				countSql.append(" and NAME like ?");
				paramList.add("%" + bookName + "%");
			}
			int fromIndex = pageSize * (pageNum - 1) + 1;
			// mysql使用limit关键字实现分页
			// sql.append(" limit "+ fromIndex+","+pageSize);
			// oracle实现分页
			sql.insert(0, "select *FROM( select s.*,rownum rn from (");
			sql.append(") s where rownum<=" + (pageSize * pageNum) + ") where rn>=" + fromIndex);
			System.out.println(sql);

			JdbcUtils dbConn = new JdbcUtils();
			List<books> booksList = new ArrayList<books>();
			List<Map<String, Object>> countResult = dbConn.findResult(countSql.toString(), paramList);
			Map<String, Object> countMap = countResult.get(0);
			System.out.println(countMap);
			int totalRecord = ((Number) countMap.get("TOTALRECORD")).intValue();
			List<Map<String, Object>> booksResult = dbConn.findResult(sql.toString(), paramList);
			if (booksResult != null) {
				for (Map<String, Object> map : booksResult) {
					books s = new books(map);
					booksList.add(s);
				}
			}
			int totalPage = totalRecord / pageSize;
			if (totalRecord % pageSize != 0) {
				totalPage = totalPage + 1;
			}
			result = new Pager<>(pageSize, pageNum, totalRecord, totalPage, booksList);

			System.out.println(list);
			System.out.println("booklist=" + booksList);
			System.out.println("result=" + result.toString());
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

	// 根据书籍编号获得书籍资料
	public books getItemsById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from SCOTT.BOOKS where ID=?"; // SQL语句
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				books item = new books();
				item.setId(rs.getInt("ID"));
				item.setName(rs.getString("NAME"));
				item.setCity(rs.getString("AUTHOR"));
				item.setNumber(rs.getInt("NUM"));
				item.setPrice(rs.getInt("PRICE"));
				item.setPicture(rs.getString("PICTURE"));
				System.out.println(item);
				return item;
			} else {
				return null;
			}

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

	// 获取最近浏览的前五条书籍信息
	public ArrayList<books> getViewList(String list) {
		System.out.println("list:" + list);
		ArrayList<books> bookslist = new ArrayList<books>();
		int iCount = 5; // 每次返回前五条记录
		if (list != null && list.length() > 0) {
			String[] arr = list.split("#");
			System.out.println("arr.length=" + arr.length);
			// 如果书籍记录大于等于5条
			if (arr.length >= 5) {
				for (int i = arr.length - 1; i >= arr.length - iCount; i--) {
					bookslist.add(getItemsById(Integer.parseInt(arr[i])));
				}
			} else {
				for (int i = arr.length - 1; i >= 0; i--) {
					bookslist.add(getItemsById(Integer.parseInt(arr[i])));
				}
			}
			return bookslist;
		} else {
			return null;
		}

	}
}