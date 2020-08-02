package cn.bagebeyond.oa.sysmanage.service;

import java.util.List;

import cn.bagebeyond.oa.sysmanage.entity.Menu;

/**
 * 菜单的逻辑业务实现
 * @author ZJD
 *
 */
public interface IMenuService {
	
	/**
	 * @Title : addMenu
	 * @Description : TODO(添加菜单信息)
	 * @Param @param menu
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:20:11
	 */
	public boolean addMenuInfo(Menu menu);

	/**
	 * @Title : getMenuListByUserId
	 * @Description : 根据用户id获取菜单列表
	 * @Param @param currentUserId
	 * @Return List<Menu>
	 * @Author ZJD
	 * 2018年3月15日上午11:33:56
	 */
	public List<Menu> getMenuListByUserId(Long currentUserId);
	
	/**
	 * @Title : getAllMenuList
	 * @Description : TODO(获取所有菜单列表)
	 * @Param @return 设定文件
	 * @Return List<Menu>
	 * @Author ZJD
	 * 2017年10月12日下午8:47:22
	 */
	public List<Menu> getAllMenuList();
	
	/**
	 * @Title : getMenuById
	 * @Description : TODO(根据id获取菜单信息)
	 * @Param @return 设定文件
	 * @Return Menu
	 * @Author ZJD
	 * 2017年10月16日下午10:46:24
	 */
	public Menu getMenuById(Long menuId);
	
	/**
	 * @Title : getChildCount
	 * @Description : TODO(得到子节点的数量)
	 * @Param @param menuId
	 * @Param @return 设定文件
	 * @Return int
	 * @Author ZJD
	 * 2017年10月12日下午9:09:34
	 */
	public int getChildCount(Long menuId);
	
	/**
	 * @Title : delMenu
	 * @Description : TODO(删除菜单)
	 * @Param @param menuId
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月12日下午10:01:04
	 */
	public boolean delMenu(Long menuId);
	
	/**
	 * @Title : updateMenu
	 * @Description : TODO(更新菜单信息)
	 * @Param @param menu
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:20:50
	 */
	public boolean updateMenuInfo(Menu menu);

	
}
