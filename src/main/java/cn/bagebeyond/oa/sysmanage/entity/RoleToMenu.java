package cn.bagebeyond.oa.sysmanage.entity;

/**
 * 角色菜单对应表
 * <p>Title: RoleToMenu</p>
 * <p>Description: </p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月12日 上午10:24:54
 *
 */
public class RoleToMenu {
	
	private Long roleId;
	private Long menuId;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	
}
