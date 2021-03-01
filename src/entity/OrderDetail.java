package entity;

import java.sql.Date;

public class OrderDetail {
	private int id;
	private int bookid;
	private String name;
	private int price;
	private int buynum;
	private int totalprice;
	private String picture;
	private String date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
public String toString() {
		
		return "["+this.getId()+","+this.getBookid()+","+this.getName()+","+this.getPrice()+","+this.getBuynum()+","+this.getPicture()+","+this.getDate()+"]";
	}
}
