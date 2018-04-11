package cn.tedu.entity;

public class Product {
	private String id;
	private String name;
	private double price;
	private String category;
	private int pnum;
	private String description;
	private String imgurl;
	
	//只要ID相同，hashCode 肯定一样
	@Override
	public int hashCode() {
		return id==null?0:id.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this==obj) {//判断地址
			return true;
		}
		if (obj==null) {
			return false;
		}
		//判断obj是否为Product对象
		if (!(obj instanceof Product)) {
			return false;
		}
		//obj不是Product类的对象
		Product other =(Product)obj;
		if (id!=null&&id.equals(other.getId())) {
			//说明两个商品ID相同
			return true;
		}
		return false;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
