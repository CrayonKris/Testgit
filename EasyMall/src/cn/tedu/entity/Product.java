package cn.tedu.entity;

public class Product {
	private String id;
	private String name;
	private double price;
	private String category;
	private int pnum;
	private String description;
	private String imgurl;
	
	//ֻҪID��ͬ��hashCode �϶�һ��
	@Override
	public int hashCode() {
		return id==null?0:id.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this==obj) {//�жϵ�ַ
			return true;
		}
		if (obj==null) {
			return false;
		}
		//�ж�obj�Ƿ�ΪProduct����
		if (!(obj instanceof Product)) {
			return false;
		}
		//obj����Product��Ķ���
		Product other =(Product)obj;
		if (id!=null&&id.equals(other.getId())) {
			//˵��������ƷID��ͬ
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
