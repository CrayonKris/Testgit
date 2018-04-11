package cn.tarena.ht.pojo;

public class Role extends BaseEntity {
	private String name;
	private String roleId;
	private String remarks;
	private Integer orderNo;
	private boolean checked;
	
	public boolean isChecked(){
		return checked;
	}
	public void setChecked(boolean checked){
		this.checked=checked;
	}
	public String getId() {
		return roleId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "role_p [name=" + name + ", roleId=" + roleId + ", remarks=" + remarks + ", orderNo=" + orderNo + "]";
	}
	
}
