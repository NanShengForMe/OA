package cn.bagebeyond.oa.sysmanage.mapper;

import java.util.Date;
import java.util.List;

import cn.bagebeyond.oa.sysmanage.dto.UserDto;
import cn.bagebeyond.oa.sysmanage.entity.User;
import cn.bagebeyond.oa.sysmanage.entity.UserToRole;

/**
 * 用户增删改查，登陆验证的mapper接口
 * @author ZJD
 *
 */
public interface UserMapper {
	
	/**
	 * @Title : addUser
	 * @Description : 添加用户信息
	 * @Param @param user
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月14日下午4:14:12
	 */
	boolean addUser(User user);
	
	/**
	 * @Title : addUserRoleBatch
	 * @Description : 批量添加用户角色对应关系
	 * @Param @param userRoleList
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月14日下午4:30:31
	 */
	boolean addUserRoleBatch(List<UserToRole> userRoleList);
	
	/**
	 * @Title : getUserDtoListByUserIdAdmin
	 * @Description : 通过用户id获取拥有区域权限下的用户列表(超级管理员用的)
	 * @Param @param user
	 * @Return List<UserDto>
	 * @Author ZJD
	 * 2018年3月15日下午4:54:56
	 */
	List<UserDto> getUserDtoListByUserIdAdmin(User user);
	
	/**
	 * @Title : getUserDtoListByUserIdAdmin
	 * @Description : 通过用户id获取拥有区域权限下的用户列表(普通管理员用的)
	 * @Param @param user
	 * @Return List<UserDto>
	 * @Author ZJD
	 * 2018年3月15日下午4:54:56
	 */
	List<UserDto> getUserDtoListByUserId(User user);
	
	/**
	 * @Title : getUserDtoList
	 * @Description : 根据条件查询列表
	 * @Param @return 设定文件
	 * @Return List<UserDto>
	 * @Author ZJD
	 * 2018年3月14日下午12:28:07
	 */
	List<UserDto> getUserDtoList(User user);
	
	/**
	 * 根据条件查询用户列表
	 * @param user
	 * @return
	 */
	public List<User> getUserList(User user);
	
	/**
	 * 根据用户名查出用户对象
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName);
	
	/**
	 * 通过用户id查询用户对象
	 * @param userId
	 * @return
	 */
	public User getUserById(Long userId);
	
	/**
	 * 通过用户id获取包装类对象
	 * @param userId
	 * @return
	 */
	public UserDto getUserInfoById(Long userId);
	
	/**
	 * 更新用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean updateUserPassword(Long userId,String password);
	
	/**
	 * 更新个人信息
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);
	
	/**
	 * @Title : delUserRoleByUserId
	 * @Description : 根据用户ID删除用户角色对应关系
	 * @Param @param userId
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月14日下午2:49:36
	 */
	boolean delUserRoleByUserId(Long userId);
	
	/**
	 * @Title : delUser
	 * @Description : 删除用户信息
	 * @Param @param userId
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月14日下午2:53:36
	 */
	boolean delUser(Long userId);
	
	/**
	 * @Title : getUserRoleByUserId
	 * @Description : 获取用户的所有角色ID
	 * @Param @param userId
	 * @Return List<UserToRole>
	 * @Author ZJD
	 * 2018年3月14日下午3:22:37
	 */
	List<UserToRole> getUserRoleByUserId(Long userId);

	
	/**
	 * @Title : updateLastLoginTime
	 * @Description : 更新用户最后一次登录时间
	 * @Param @param lastLoginTime
	 * * @Param @param  userId
	 * @Return void
	 * @Author ZJD
	 * 2018年3月26日下午4:51:07
	 */
	void updateLastLoginTime(Long userId);
	
}
