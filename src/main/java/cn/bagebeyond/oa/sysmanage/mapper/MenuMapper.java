package cn.bagebeyond.oa.sysmanage.mapper;

import java.util.List;

import cn.bagebeyond.oa.sysmanage.entity.Menu;

/**
 * 
 * <p>Title: MenuMapper</p>
 * <p>Description: 菜单的增删查改的mapper代理接口</p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2017年10月12日 下午8:49:55
 *
 */
public interface MenuMapper {

	/**
	 * @Title : addMenuInfo
	 * @Description : TODO(添加菜单信息)
	 * @Param @param menu
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:23:16
	 */
	public boolean addMenuInfo(Menu menu);
	
	/**
	 * @Title : getMenuListByUserId
	 * @Description : 根据用户id获取菜单列表
	 * @Param @param currentUserId
	 * @Return List<Menu>
	 * @Author ZJD
	 * 2018年3月15日上午11:40:05
	 */
	public List<Menu> getMenuListByUserId(Long userId);
	
	/**
	 * @Title : getAllMenuList
	 * @Description : TODO(查询所有菜单列表)
	 * @Param @return 设定文件
	 * @Return List<Menu>
	 * @Author ZJD
	 * 2017年10月12日下午8:55:21
	 */
	public List<Menu> getAllMenuList();
	
	/**
	 * @Title : getMenuById
	 * @Description : TODO(通过id获取菜单信息)
	 * @Param @param menuId
	 * @Param @return 设定文件
	 * @Return Menu
	 * @Author ZJD
	 * 2017年10月16日下午10:49:03
	 */
	public Menu getMenuById(Long menuId);
	
	/**
	 * @Title : getChildCount
	 * @Description : TODO(根据menuId得到其子节点的数量)
	 * @Param @param menuId
	 * @Param @return 设定文件
	 * @Return int
	 * @Author ZJD
	 * 2017年10月12日下午9:10:59
	 */
	public int getChildCount(Long menuId);
	
	/**
	 * @Title : delMenu
	 * @Description : TODO(删除菜单)
	 * @Param @param menuId
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月12日下午10:08:51
	 */
	public boolean delMenu(Long menuId);
	
	/**
	 * @Title : updateMenuInfo
	 * @Description : TODO(更新菜单信息)
	 * @Param @param menu
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:21:41
	 */
	public boolean updateMenuInfo(Menu menu);

	
}
