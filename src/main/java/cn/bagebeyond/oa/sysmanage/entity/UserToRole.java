package cn.bagebeyond.oa.sysmanage.entity;

/**
 * 用户角色对应表
 * <p>Title: UserToRole</p>
 * <p>Description: </p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月12日 上午10:25:18
 *
 */
public class UserToRole implements java.io.Serializable {

	private static final long serialVersionUID = 5740104362530862141L;
	
	private Long userId;
	private Long roleId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	 	 
}
