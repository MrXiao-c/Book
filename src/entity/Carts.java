package entity;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



import dao.booksDAO;
import service.CartService;

public class Carts {
//  ������кܶ����Ʒ���������ǿ�����һ��Map�������
//  ��Ʒ����
   
    private int id;
    private double totalPrice;
    private int buynum;
    private String picture;
    private String name;
    private int price;
    private int bookid;

    public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice*this.buynum;
	}

	
    
    public String toString() {
    	
    	return "carts [�鼮��ţ�"+this.getId()+"�鼮���ƣ�"+this.getName()+"�۸�"+this.getPrice()+"]";
    }
}

