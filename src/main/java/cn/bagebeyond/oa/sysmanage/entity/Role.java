package cn.bagebeyond.oa.sysmanage.entity;

import java.util.Date;
 
/**
 * 角色对象
 * <p>Title: Role</p>
 * <p>Description: </p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月12日 上午10:23:32
 *
 */
public class Role implements java.io.Serializable{
	
 	private static final long serialVersionUID = 1578372117141342190L;
 	
	private Long id;
 	private String name;
	private String	updateBy;
	private Date updateDate;
	private String remarks;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	 
	 
	 
	
}