package cn.bagebeyond.oa.sysmanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.bagebeyond.oa.sysmanage.dto.RoleDto;
import cn.bagebeyond.oa.sysmanage.entity.Area;
import cn.bagebeyond.oa.sysmanage.entity.Dept;
import cn.bagebeyond.oa.sysmanage.entity.Menu;
import cn.bagebeyond.oa.sysmanage.entity.Role;
import cn.bagebeyond.oa.sysmanage.entity.RoleToArea;
import cn.bagebeyond.oa.sysmanage.entity.RoleToDept;
import cn.bagebeyond.oa.sysmanage.entity.RoleToMenu;
import cn.bagebeyond.oa.sysmanage.service.IAreaService;
import cn.bagebeyond.oa.sysmanage.service.IDeptService;
import cn.bagebeyond.oa.sysmanage.service.IMenuService;
import cn.bagebeyond.oa.sysmanage.service.IRoleService;

/**
 * <p>Title: RoleController</p>
 * <p>Description:角色管理 </p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月12日 上午9:59:13
 *
 */
@Controller
@RequestMapping("/sysmgr/role")
public class RoleController {
	
	private static Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private IDeptService deptService;
	
	@Autowired
	private IAreaService areaService;
	
	/**
	 * 进入角色管理列表
	 */
	@RequestMapping("/gotoRoleList")
	public String gotoRoleList(Model model){
		//进入列表初始化所有角色数据
		List<Role> roleList = new ArrayList<Role>();
		roleList = roleService.getAllRoleList();
		model.addAttribute("roleList", roleList);
		return "sysmanage/role/roleList";
	}
	
	@RequestMapping("/gotoRoleEdit")
	public String gotoRoleEdit(@ModelAttribute("editFlag") int editFlag, Long roleId, Model model){
		List<Menu> menuList = menuService.getAllMenuList();
		List<Dept> deptList = deptService.getAllDeptList();
		List<Area> areaList = areaService.getAllAreaList();
		model.addAttribute("menuList", menuList);
		model.addAttribute("deptList", deptList);
		model.addAttribute("areaList", areaList);
		// 修改操作的时候 需要查询出该角色拥有的各项权限
		if (editFlag == 2){
			List<RoleToMenu> roleMenuList = roleService.getMenuListByRoleId(roleId);
			List<RoleToDept> roleDeptList = roleService.getDeptListByRoleId(roleId);
			List<RoleToArea> roleAreaList = roleService.getAreaListByRoleId(roleId);
			
			Role role = roleService.getRoleById(roleId);
			model.addAttribute("roleMenuList", roleMenuList);
			model.addAttribute("roleDeptList", roleDeptList);
			model.addAttribute("roleAreaList", roleAreaList);
			model.addAttribute("role", role);
		}
		return "sysmanage/role/roleEdit";
	}
	
	
	@RequestMapping("/saveRole")
	public @ResponseBody Map<String,Object> saveRole(@RequestBody RoleDto roleDto){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			// 根据包装类中的角色对象的ID是否存在  来判断是添加还是修改
			if(roleDto != null && roleDto.getRole() != null && roleDto.getRole().getId() != null) {
				roleService.updateRole(roleDto);
				resultMap.put("result", "修改角色信息成功！");
			} else {
				roleService.addRole(roleDto);
				resultMap.put("result", "添加角色信息成功！");
			}
		} catch(Exception e) {
			logger.error("操作角色信息失败!", e);
			resultMap.put("result", "操作角色信息失败!");
		}
		
		return resultMap;
	}
	
	@RequestMapping("/delRole")
	public @ResponseBody Map<String,Object> delRole(Long roleId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			roleService.delRole(roleId);
			resultMap.put("result", "删除该角色信息成功!");
		} catch(Exception e) {
			logger.error("删除角色信失败!");
			resultMap.put("result", "删除角色信息失败!");
		}
		return resultMap;
	}
	
}
