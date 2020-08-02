package cn.bagebeyond.oa.sysmanage.mapper;

import java.util.List;

import cn.bagebeyond.oa.sysmanage.entity.Role;
import cn.bagebeyond.oa.sysmanage.entity.RoleToArea;
import cn.bagebeyond.oa.sysmanage.entity.RoleToDept;
import cn.bagebeyond.oa.sysmanage.entity.RoleToMenu;

/**
 * 区域的增删改查的mapper代理接口
 * @author Administrator
 *
 */
public interface RoleMapper {
	
	/**
	 * 查询所有角色列表
	 * @return
	 */
	List<Role> getAllRoleList();

	/**
	 * 增加角色对象
	 * @param role
	 * @return
	 */
	boolean addRole(Role role);
	
	/**
	 * 批量插入角色菜单对应信息
	 * @param roleMenuList
	 * @return
	 */
	boolean addRoleToMenuBatch(List<RoleToMenu> roleMenuList);
	/**
	 * 批量插入角色部门对应信息
	 * @param roleDeptList
	 * @return
	 */
	boolean addRoleToDeptBatch(List<RoleToDept> roleDeptList);
	
	/**
	 * 批量插入角色区域对应信息
	 * @param roleAreaList
	 * @return
	 */
	boolean addRoleToAreaBatch(List<RoleToArea> roleAreaList);

	/**
	 * 根据角色ID删除角色菜单对应关系
	 * @param roleId
	 * @return
	 */
	boolean delRoleMenuByRoleId(Long roleId);

	/**
	 * 根据角色ID删除角色部门单对应关系
	 * @param roleId
	 * @return
	 */
	boolean delRoleDeptByRoleId(Long roleId);

	/**
	 * 根据角色ID删除角色区域对应关系
	 * @param roleId
	 * @return
	 */
	public boolean delRoleAreaByRoleId(Long roleId);

	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	boolean delRole(Long roleId);
	
	/**
	 * @Title : delRoleMenuByMenuId
	 * @Description :根据菜单ID删除角色菜单对应关系
	 * @Param @param menuId
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月15日上午10:26:53
	 */
	boolean delRoleMenuByMenuId(Long menuId);
	
	/**
	 * @Title : delRoleDeptByDeptId
	 * @Description : 根据部门ID删除角色部门对应关系
	 * @Param @param deptId
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月15日上午10:28:49
	 */
	boolean delRoleDeptByDeptId(Long deptId);
	
	/**
	 * @Title : delRoleAreaByAreaId
	 * @Description : 根据区域id删除角色区域对应关系
	 * @Param @param areaId
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月15日上午10:29:11
	 */
	boolean delRoleAreaByAreaId(Long areaId);
	
	/**
	 * 根据角色id查询角色菜单对应关系
	 * @param roleId
	 * @return
	 */
	public List<RoleToMenu> getMenuListByRoleId(Long roleId);

	/**
	 * 根据角色id查询角色部门对应关系
	 * @param roleId
	 * @return
	 */
	List<RoleToDept> getDeptListByRoleId(Long roleId);

	/**
	 * 根据角色id查询角色区域对应关系
	 * @param roleId
	 * @return
	 */
	List<RoleToArea> getAreaListByRoleId(Long roleId);
	
	/**
	 * 根据角色id获取角色信息
	 * @param roleId
	 * @return
	 */
	Role getRoleById(Long roleId);

	/**
	 * 修改角色信息
	 * @param role
	 * @return
	 */
	boolean updateRole(Role role);

	/**
	 * @Title : addRoleToMenu
	 * @Description : 添加角色菜单对应记录
	 * @Param @param roleMenu
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月15日上午10:55:51
	 */
	boolean addRoleToMenu(RoleToMenu roleMenu);
	
	/**
	 * @Title : addRoleToDetp
	 * @Description : 添加角色部门对应记录
	 * @Param @param roleDept
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月15日上午10:56:31
	 */
	boolean addRoleToDetp(RoleToDept roleDept);
	
	/**
	 * @Title : addRoleToArea
	 * @Description : 添加角色区域对应记录
	 * @Param @param roleArea
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月15日上午10:56:49
	 */
	boolean addRoleToArea(RoleToArea roleArea);
}
