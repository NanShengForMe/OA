package cn.bagebeyond.oa.sysmanage.dto;

import cn.bagebeyond.oa.sysmanage.entity.User;

 
/**
 * <p>Title: UserDto</p>
 * <p>Description:用户对象bean </p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月4日 下午8:29:04
 *
 */
public class UserDto extends User implements java.io.Serializable{

 	private static final long serialVersionUID = 3573926540976059111L;
 	
 	private String deptName;
 	//private List<Role> roleList;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	 
	 
	
}