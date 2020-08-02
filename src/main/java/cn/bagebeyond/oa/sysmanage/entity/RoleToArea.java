package cn.bagebeyond.oa.sysmanage.entity;

/**
 * 角色区域对应表
 * <p>Title: RoleToArea</p>
 * <p>Description: </p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月12日 上午10:24:22
 *
 */
public class RoleToArea {
	private Long roleId;
	private Long areaId;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	 
}
