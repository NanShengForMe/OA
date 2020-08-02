package cn.bagebeyond.oa.sysmanage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.bagebeyond.oa.framework.util.UserUtils;
import cn.bagebeyond.oa.sysmanage.entity.Area;
import cn.bagebeyond.oa.sysmanage.entity.RoleToArea;
import cn.bagebeyond.oa.sysmanage.mapper.AreaMapper;
import cn.bagebeyond.oa.sysmanage.mapper.RoleMapper;
import cn.bagebeyond.oa.sysmanage.service.IAreaService;

@Service
public class AreaServiceImpl implements IAreaService {

	@Autowired
	private AreaMapper areaMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Area> getAllAreaList() {
		return areaMapper.getAllAreaList();
	}

	@Override
	public int getChildCount(Long areaId) {
		return areaMapper.getChildCount(areaId);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean delArea(Long areaId) {
		boolean flag = false;
		flag = roleMapper.delRoleAreaByAreaId(areaId);
		flag = areaMapper.delArea(areaId);
		return flag;
	}

	@Override
	public Area getAreaById(Long areaId) {
		return areaMapper.getAreaById(areaId);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean addAreaInfo(Area area) {
		boolean flag = false;
		Long userId = UserUtils.getCurrentUserId();
		if (userId != null){
			area.setUpdateBy(userId.toString());
			area.setCreateBy(userId.toString());
		}
		area.setUpdateDate(new Date());
		area.setCreateDate(new Date());
		flag = areaMapper.addAreaInfo(area);
		// 添加区域时 添加超级管理员的映射记录
		RoleToArea roleArea = new RoleToArea();
		roleArea.setRoleId(1L);
		roleArea.setAreaId(area.getId());
		flag = roleMapper.addRoleToArea(roleArea);
		return flag;
	}

	@Override
	public boolean updateAreaInfo(Area area) {
		String userId = UserUtils.getCurrentUserId().toString();
		area.setUpdateDate(new Date());
		area.setUpdateBy(userId);
		return areaMapper.updateAreaInfo(area);
	}

	@Override
	public List<Area> getAreaListByUserId(Long currentUserId) {
		return this.areaMapper.getAreaListByUserId(currentUserId);
	}

	
}
