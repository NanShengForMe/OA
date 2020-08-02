package cn.bagebeyond.oa.sysmanage.vo;

import java.io.Serializable;
import java.util.Map;

import cn.bagebeyond.oa.sysmanage.entity.User;

/**
 * <p>Title: UserVo</p>
 * <p>Description: 用户的显示层对象（视图对象）</p>
 * <p>Conpany:  cn.bagebeyond</p>
 * @author ZJD
 * @date 2018年3月14日 下午3:55:07
 *
 */
public class UserVo implements Serializable {

	private static final long serialVersionUID = 6824484976706374169L;

	private User user;
	
	private Map<Long,Long> roleIds;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Long, Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Map<Long, Long> roleIds) {
		this.roleIds = roleIds;
	}
	
	
	
}
