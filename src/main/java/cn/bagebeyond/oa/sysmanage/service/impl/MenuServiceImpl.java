package cn.bagebeyond.oa.sysmanage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.bagebeyond.oa.framework.util.UserUtils;
import cn.bagebeyond.oa.sysmanage.entity.Menu;
import cn.bagebeyond.oa.sysmanage.entity.RoleToMenu;
import cn.bagebeyond.oa.sysmanage.mapper.MenuMapper;
import cn.bagebeyond.oa.sysmanage.mapper.RoleMapper;
import cn.bagebeyond.oa.sysmanage.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Menu> getAllMenuList() {
		return menuMapper.getAllMenuList();
	}

	@Override
	public int getChildCount(Long menuId) {
		return menuMapper.getChildCount(menuId);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean delMenu(Long menuId) {
		boolean flag = false;
		flag = roleMapper.delRoleMenuByMenuId(menuId);
		flag = menuMapper.delMenu(menuId);
		return flag;
	}

	@Override
	public Menu getMenuById(Long menuId) {
		return menuMapper.getMenuById(menuId);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean addMenuInfo(Menu menu) {
		boolean flag = false;
		Long userId = UserUtils.getCurrentUserId();
		if (userId != null){
			menu.setUpdateBy(userId.toString());
		}
		menu.setUpdateDate(new Date());
		flag = menuMapper.addMenuInfo(menu);
		// 添加菜单的时候同时需要给超级管理员添加一条记录
		RoleToMenu roleMenu = new RoleToMenu();
		roleMenu.setRoleId(1L);
		roleMenu.setMenuId(menu.getId());
		flag = roleMapper.addRoleToMenu(roleMenu);
		return flag;
	}

	@Override
	public boolean updateMenuInfo(Menu menu) {
		String userId = UserUtils.getCurrentUserId().toString();
		menu.setUpdateDate(new Date());
		menu.setUpdateBy(userId);
		return menuMapper.updateMenuInfo(menu);
	}

	@Override
	public List<Menu> getMenuListByUserId(Long currentUserId) {
		return this.menuMapper.getMenuListByUserId(currentUserId);
	}

	
}
