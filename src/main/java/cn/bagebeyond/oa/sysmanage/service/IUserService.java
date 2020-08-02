package cn.bagebeyond.oa.sysmanage.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.bagebeyond.oa.sysmanage.dto.PageParam;
import cn.bagebeyond.oa.sysmanage.dto.UserDto;
import cn.bagebeyond.oa.sysmanage.entity.User;
import cn.bagebeyond.oa.sysmanage.entity.UserToRole;
import cn.bagebeyond.oa.sysmanage.vo.UserVo;

/**
 * 用户增删改查
 * @author ZJD
 *
 */
public interface IUserService {
	
	/**
	 * @Title : addUser
	 * @Description : 添加用户信息
	 * @Param @param userVo
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月14日下午4:07:54
	 */
	boolean addUser(UserVo userVo);
	
	/**
	 * 登陆验证
	 * @param loginName
	 * @param password
	 * @return
	 */
	public User login(String loginName,String password);
	
	/**
	 * @Title : delUser
	 * @Description :删除用户和用户对应关系
	 * @Param @param userId
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月14日下午2:45:01
	 */
	boolean delUser(Long userId);
	
	/**
	 * 通过id查询用户对象
	 * @Param @param userId
	 * @Param @return 设定文件
	 * @Return User
	 */
	public User getUserById(Long userId);
	
	/**
	 * 通过id获取包装类对象
	 * @Param @param userId
	 * @Param @return 设定文件
	 * @Return UserDto
	 * @Author ZJD
	 * 2018年3月14日下午12:20:05
	 */
	public UserDto getUserInfoById(Long userId);
	
	/**
	 * 按条件分页查询用户列表
	 * @param user
	 * @param pageParam
	 * @return
	 */
	public PageInfo<UserDto> getUserDtoList(User user,PageParam pageParam);
	
	/**
	 * @Title : getUserRoleByUserId
	 * @Description : 获取用户所有角色Id
	 * @Param @param userId
	 * @Return List<UserToRole>
	 * @Author ZJD
	 * 2018年3月14日下午3:16:01
	 */
	List<UserToRole> getUserRoleByUserId(Long userId);
	
	/**
	 * 校验密码
	 * @Param @param plainPwd
	 * @Param @param encrptPwd
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月14日下午12:20:20
	 */
	public boolean validatePassword(String plainPwd,String encrptPwd);
	
	/**
	 * 更新用户密码
	 * @Param @param userId
	 * @Param @param password
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月14日下午12:20:31
	 */
	public boolean updateUserPassword(Long userId,String password);
	
	/**
	 * 更新个人信息
	 * @Param @param user
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月14日下午12:20:40
	 */
	public boolean updateUser(User user);
	
	/**
	 * @Title : updateUserVo
	 * @Description : 修改用户以及角色对应关系
	 * @Param @param userVo
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月14日下午5:52:02
	 */
	boolean updateUserVo(UserVo userVo);
	
	/**
	 * @Title : updateLastLoginTime
	 * @Description : 更新用户最后一次登录时间
	 * @Param  userId
	 * @Return void
	 * @Author ZJD
	 * 2018年3月26日下午4:48:11
	 */
	void updateLastLoginTime(Long userId);
	
}
