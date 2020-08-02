package cn.bagebeyond.oa.sysmanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

import cn.bagebeyond.oa.framework.util.PageUtils;
import cn.bagebeyond.oa.framework.util.UserUtils;
import cn.bagebeyond.oa.sysmanage.dto.PageParam;
import cn.bagebeyond.oa.sysmanage.dto.UserDto;
import cn.bagebeyond.oa.sysmanage.entity.Role;
import cn.bagebeyond.oa.sysmanage.entity.User;
import cn.bagebeyond.oa.sysmanage.entity.UserToRole;
import cn.bagebeyond.oa.sysmanage.service.IRoleService;
import cn.bagebeyond.oa.sysmanage.service.IUserService;
import cn.bagebeyond.oa.sysmanage.vo.UserVo;

@Controller
//${ctx}/sysmgr/user/gotoUserInfo

@RequestMapping("/sysmgr/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("/gotoUserInfo")
	public String gotoUserInfo(){
		return "sysmanage/user/userInfo";
	}
	
	@RequestMapping("/gotoChangePwd")
	public String gotoChangePwd(){
		return "sysmanage/user/changePwd";
	}
	
	/*
	 * 进入用户列表页面
	 */
	@RequestMapping("/gotoUserList")
	public String gotoUserList(){
		return "sysmanage/user/userList";
	}
	
	@RequestMapping("/getUserList")
	public @ResponseBody Map<String,Object> getUserList(Long deptId, String userName, int pageNo, int pageSize){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		// 获取查询条件
		User user = new User();
		if (deptId != null){
			user.setDeptId(deptId);
		}
		if (userName != null){
			user.setUserName(userName);
		}
		// 获取分页条件
		PageParam pageParam = new PageParam();
		pageParam.setPageNo(pageNo);
		pageParam.setPageSize(pageSize);
		// 获取分页数据
		List<UserDto> userList = new ArrayList<UserDto>();
		PageInfo<UserDto> pageInfo = this.userService.getUserDtoList(user, pageParam);
		userList = pageInfo.getList();
		resultMap.put("userList", userList);
		// 获取分页条的java代码
		String pageStr = PageUtils.pageStr(pageInfo, "userMgr.getUserListPage");
		resultMap.put("pageStr", pageStr);
		return resultMap;
	}
	
	@RequestMapping("/saveUser")
	public @ResponseBody Map<String,Object> saveUser(@RequestBody UserVo userVo){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if (userVo != null && userVo.getUser() != null && userVo.getUser().getUserId() != null){// 修改
				if (userService.updateUserVo(userVo)){
					resultMap.put("result", "修改用户信息成功");
				}
			}else { //添加
				if (userService.addUser(userVo)){
					resultMap.put("result", "添加用户信息成功");
				}
			}
		} catch (Exception e){
			logger.error("操作用户失败", e);
			resultMap.put("result", "操作用户失败");
		}
		
		
		
		return resultMap;
	}
	
	@RequestMapping("/saveChangePwd")
	public @ResponseBody HashMap<String, Object> saveChangePwd(String oldPassword,String newPassword,HttpSession session){
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		User adminuser = (User)session.getAttribute("user");
		Long userId = adminuser.getUserId();
		User user = userService.getUserById(userId);
		if(!userService.validatePassword(oldPassword, user.getPassword())){
			resultMap.put("result", "修改密码失败，原密码错误！");
		}else{
			if(StringUtil.isNotEmpty(newPassword)){
				try{
					if(userService.updateUserPassword(user.getUserId(), newPassword)){
						resultMap.put("result", "修改密码成功！");
					}else{
						resultMap.put("result", "修改密码失败！");
					}
				}catch(Exception e){
					resultMap.put("result", "修改密码失败！");
					logger.error("修改密码失败！",e);
				}
			}else{
				resultMap.put("result", "修改密码失败，新密码为空！");
			}
		}
		return resultMap;
	}
	
	@RequestMapping("/getUserInfoById")
	public @ResponseBody UserDto getUserInfoById(){
		//1:可以通过session获取id ,同样也可以封装一个统一的方法来获取id
		Long userId = UserUtils.getCurrentUserId();
		UserDto dto = userService.getUserInfoById(userId);
		return dto;
	}
	
	@RequestMapping("/saveSelfUserInfo")
	public @ResponseBody Map<String,Object> saveSelfUserInfo(@RequestBody User user){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(userService.updateUser(user)){
				resultMap.put("result", "修改个人信息成功！");
			}else{
				resultMap.put("result", "修改个人信息失败！");
			}
		}catch(Exception e){
			resultMap.put("result", "修改个人信息失败！");
			logger.error("修改个人信息失败！",e);
		}
		return resultMap;
	}
	
	@RequestMapping("/delUser")
	public @ResponseBody Map<String,Object> delUser(Long userId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		 try{
			 if (userService.delUser(userId)){
				 resultMap.put("result", "删除用户成功");
			 }
		 } catch(Exception e){
			 logger.error("删除用户失败",e);
			 resultMap.put("result", "删除用户失败");
		 }
		return resultMap;
	}
	
	@RequestMapping("/gotoUserEdit")
	public String gotoUserEdit(@ModelAttribute("editFlag") int editFlag, Long userId, Model model){
		// 查询出所有角色信息
		List<Role> roleList = this.roleService.getAllRoleList();
		// 1 添加
		model.addAttribute("roleList", roleList);
		UserDto userDto = null;
		if (editFlag == 1){
			userDto = new UserDto();
		}
		//2 修改
		if (editFlag == 2){
			userDto = this.userService.getUserInfoById(userId);
			// 还需要将当前用户的角色信息查询出来,并给页面默认勾选上 pm_sys_user_role
			List<UserToRole> userRoleList = this.userService.getUserRoleByUserId(userId);
			Map<Long,Long> roleCheckMap = new HashMap<Long,Long>(); 
			for (UserToRole userRole : userRoleList){
				roleCheckMap.put(userRole.getRoleId(), userRole.getRoleId());
			}
			model.addAttribute("roleCheckMap", roleCheckMap);
		}
		
		model.addAttribute("userDto", userDto);
		return "sysmanage/user/userEdit";
	}
	
	@RequestMapping("/gotoLayim")
	public String gotoLayim(){
		return "sysmanage/user/layim";
	}
	
	
	@RequestMapping("/imageEdit")
	public String imageEdit(){
		return "sysmanage/user/userImageEdit";
	}
	
}
