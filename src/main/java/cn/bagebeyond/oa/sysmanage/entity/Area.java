package cn.bagebeyond.oa.sysmanage.entity;

import java.util.Date;

import cn.bagebeyond.oa.sysmanage.dto.TreeDto;
/**
 * <p>Title: Area</p>
 * <p>Description:区域对象 </p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月4日 下午8:29:50
 *
 */
public class Area extends TreeDto implements java.io.Serializable{
	
 	private static final long serialVersionUID = -8572618187826567349L;

  	private String parentName;
   	private Integer sort;
  	private String code;
  	private String createBy;
  	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String remarks;
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	 
}