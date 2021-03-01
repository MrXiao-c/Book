package entity;

import java.util.Map;

public class Order {
	private int id;
	private int bookid;
	private int price;
	private int buynum;
	private String picture;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookid() {
		return bookid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
public Order() {
		
	}
	public Order(Map<String, Object> map) {
		Object oid = map.get("ID");
		Object oprice = map.get("PRICE");
		Object onumber = map.get("BUYNUM");
		Object oname = map.get("NAME");
		Object opicture = map.get("PICTURE");
		Object obookid = map.get("BOOKID");

		this.id = Integer.parseInt(oid.toString());
		this.name = oname.toString();
		this.bookid = Integer.parseInt(obookid.toString());
		this.price = Integer.parseInt(oprice.toString());
		this.buynum = Integer.parseInt(onumber.toString());
		this.picture = opicture.toString();
	}
public String toString() {
		
		return "["+this.getId()+","+this.getBookid()+","+this.getName()+","+this.getPrice()+","+this.getBuynum()+","+this.getPicture()+"]";
	}
}
