package cn.bagebeyond.oa.sysmanage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.bagebeyond.oa.framework.util.EncryptUtil;
import cn.bagebeyond.oa.framework.util.UserUtils;
import cn.bagebeyond.oa.sysmanage.dto.PageParam;
import cn.bagebeyond.oa.sysmanage.dto.UserDto;
import cn.bagebeyond.oa.sysmanage.entity.User;
import cn.bagebeyond.oa.sysmanage.entity.UserToRole;
import cn.bagebeyond.oa.sysmanage.mapper.UserMapper;
import cn.bagebeyond.oa.sysmanage.service.IUserService;
import cn.bagebeyond.oa.sysmanage.vo.UserVo;

@Service
public class UserServiceImpl implements IUserService {

	public static final int SALT_SIZE = 8;
	
	public static final int HASH_ITERATIONS = 1024;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(String loginName, String password) {
		//1：先根据用户loginName查出用户对象
		//2：将查询出对象的密码与界面输入过来的面膜进行比对
		User user = userMapper.getUserByLoginName(loginName);
		if(user == null){
			return null;
		}else{
			String encrptPwd = user.getPassword();
			if(validatePassword(password, encrptPwd)){
				return user;
			}else
				return null;
		}
	}

	/**
	 * 密码验证
	 * @param plainPwd
	 * @param encrptPwd
	 * @return
	 */
	public boolean validatePassword(String plainPwd,String encrptPwd){
		byte[] salt = EncryptUtil.decodeHex(encrptPwd.substring(0,SALT_SIZE*2));
		byte[] hashencrptPwd = EncryptUtil.sha1(plainPwd.getBytes(), salt, HASH_ITERATIONS);
		String newencrptPwd = EncryptUtil.encodeHex(salt) + EncryptUtil.encodeHex(hashencrptPwd);
		return newencrptPwd.equals(encrptPwd);
	}

	
	@Override
	public User getUserById(Long userId) {
		return userMapper.getUserById(userId);
	}

	@Override
	public boolean updateUserPassword(Long userId, String password) {
		// TODO Auto-generated method stub
		return userMapper.updateUserPassword(userId, encrptPwd(password));
	}
	
	/**
	 * 加密
	 * @param plainPwd
	 * @return
	 */
	public String encrptPwd(String plainPwd){
		//	1
		byte[] random = EncryptUtil.generateSalt(SALT_SIZE);
		//	2
		String randomHex = EncryptUtil.encodeHex(random);
		//	3
		byte[] thirdEncrpt = EncryptUtil.sha1(plainPwd.getBytes(), random, HASH_ITERATIONS);
		//	4
		String sha1Pwd = EncryptUtil.encodeHex(thirdEncrpt);
		//	5 randomHex + sha1Pwd
		String encrptpwd = randomHex + sha1Pwd;
		return encrptpwd;
	}

	@Override
	public UserDto getUserInfoById(Long userId) {
		// TODO Auto-generated method stub
		return userMapper.getUserInfoById(userId);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateUser(user);
	}

	@Override
	public PageInfo<UserDto> getUserDtoList(User user, PageParam pageParam) {
		PageHelper.startPage(pageParam.getPageNo(), pageParam.getPageSize());
		// 部门查询员工 查询该部门下所属所有员工的列表
		Long userId = UserUtils.getCurrentUserId();
		user.setUserId(userId);
		List<UserDto> userList;
		if (userId == 1){
			userList = this.userMapper.getUserDtoListByUserIdAdmin(user);
		} else {
			userList = this.userMapper.getUserDtoListByUserId(user);
		}
		// List<UserDto> userList = this.userMapper.getUserDtoList(user);
		PageInfo<UserDto> pageInfo = new PageInfo<UserDto>(userList);
		return pageInfo;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean delUser(Long userId) {
		boolean flag = false;
		// 先删除用户-角色对应关系（从表）
		flag = userMapper.delUserRoleByUserId(userId);
		// 再删用户信息（主表）
		flag = userMapper.delUser(userId);
		return flag;
	}

	@Override
	public List<UserToRole> getUserRoleByUserId(Long userId) {
		
		return this.userMapper.getUserRoleByUserId(userId);
	}

	@SuppressWarnings("null")
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean addUser(UserVo userVo) {
		boolean flag = false;
		// 添加用户基本信息  注意需要添加操作用户和时间
		User user = userVo.getUser();
		user.setUpdateBy(UserUtils.getCurrentUserId().toString());
		user.setUpdateDate(new Date());
		// 新增用户为了安全考虑，不用手动填写密码  应默认一个密码
		String password = "123456a";
		String encryptPassword = this.encrptPwd(password);
		user.setPassword(encryptPassword);
		flag = this.userMapper.addUser(user);
		Long userId = user.getUserId();
		// 用户增加成功以后,需要讲用户和角色的对应关系放入到pm_sys_user_role
		// 批量插入用户角色信息对应表
		List<UserToRole> userRoleList = new ArrayList<UserToRole>();
		UserToRole userRole;
		for (Long roleId : userVo.getRoleIds().values()){
			userRole = new UserToRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			userRoleList.add(userRole);
		}
		flag = this.userMapper.addUserRoleBatch(userRoleList);
		return flag;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean updateUserVo(UserVo userVo) {
		boolean flag = false;
		//修改的时候注意填充修改人和修改时间	
		User user = userVo.getUser();
		user.setUpdateDate(new Date());
		user.setUpdateBy(UserUtils.getCurrentUserId().toString());
		
		Long userId = user.getUserId();		 
		flag = userMapper.delUserRoleByUserId(userId);
		//批量插入用户角色信息对应表
 		List<UserToRole> userRoleList = new ArrayList<UserToRole>();
  		UserToRole userRole;
 		for (Long roleId : userVo.getRoleIds().values()) {  		  
        	userRole = new UserToRole();
        	userRole.setUserId(userId);
        	userRole.setRoleId(roleId);
        	userRoleList.add(userRole);
         }
        flag = this.userMapper.addUserRoleBatch(userRoleList);
        
		flag =  userMapper.updateUser(user);
				
		return flag;
	}

	@Override
	public void updateLastLoginTime(Long userId) {
		this.userMapper.updateLastLoginTime(userId);
	}



}
