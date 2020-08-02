package cn.bagebeyond.oa.sysmanage.entity;

/**
 * 角色部门对应表
 * <p>Title: RoleToDept</p>
 * <p>Description: </p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月12日 上午10:24:37
 *
 */
public class RoleToDept {
	private Long roleId;
	private Long deptId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
}
