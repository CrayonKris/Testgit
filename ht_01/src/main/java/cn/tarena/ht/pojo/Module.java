package cn.tarena.ht.pojo;

public class Module extends BaseEntity {

	private String moduleId;
	private Module parentmodule;
	private String name;
	private Integer ctype;
	private Integer orderNo;
	private String remark;
	private Integer state;
	private boolean checked;

	public String getId(){
		return moduleId;
	}
	public String getpId(){
		if (parentmodule==null) {
			return "";
		}
		return parentmodule.moduleId;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public Module getParentmodule() {
		return parentmodule;
	}
	public void setParentmodule(Module parentmodule) {
		this.parentmodule = parentmodule;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", parentmodule=" + parentmodule + ", name=" + name + ", ctype=" + ctype
				+ ", orderNo=" + orderNo + ", remark=" + remark + ", state=" + state + "]";
	}
	
}
