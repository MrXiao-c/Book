package entity;

import java.util.Map;

public class books {

	private int id; // 书籍编号
	private String name; // 书籍名称
	private String author; // 作者
	private int price; // 价格
	private int number; // 库存
	private String picture; // 书籍图片
	private int buynum;
	

	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	public int getId() {
		return id;
	}
	public books() {
		
	}

	public books(int id, String name, String author, int price, int number, String picture) {

		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.number = number;
		this.picture = picture;
	}
	public books(Map<String, Object> map) {
		Object oid = map.get("ID");
		Object oprice = map.get("PRICE");
		Object onumber = map.get("NUM");
		Object oname = map.get("NAME");
		Object opicture = map.get("PICTURE");
		Object oauthor = map.get("AUTHOR");

		this.id = Integer.parseInt(oid.toString());
		this.name = oname.toString();
		this.author = oauthor.toString();
		this.price = Integer.parseInt(oprice.toString());
		this.number = Integer.parseInt(onumber.toString());
		this.picture = opicture.toString();
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setCity(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
public String toString() {
	
	return "items [书籍编号："+this.getId()+"书籍名称："+this.getName()+"价格："+this.getPrice()+"]";
}
@Override
public boolean equals(Object obj) {
	// TODO Auto-generated method stub
	if(this==obj) {
		return true;
	}
	if(obj instanceof books) {
		books books=(books) obj;
		if(this.getId()==books.getId()&&this.getName().equals(books.getName())) {
			return true;
		}
		else {
			return false;
		}
	}
	else {
		return false;
	}
	
}
@Override
public int hashCode() {
	// TODO Auto-generated method stub
	return this.getId()+this.getName().hashCode();
}
}