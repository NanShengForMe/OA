package cn.bagebeyond.oa.sysmanage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.bagebeyond.oa.framework.util.UserUtils;
import cn.bagebeyond.oa.sysmanage.entity.Dept;
import cn.bagebeyond.oa.sysmanage.entity.RoleToDept;
import cn.bagebeyond.oa.sysmanage.mapper.DeptMapper;
import cn.bagebeyond.oa.sysmanage.mapper.RoleMapper;
import cn.bagebeyond.oa.sysmanage.service.IDeptService;

@Service
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Dept> getAllDeptList() {
		return deptMapper.getAllDeptList();
	}

	@Override
	public int getChildCount(Long deptId) {
		return deptMapper.getChildCount(deptId);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean delDept(Long deptId) {
		boolean flag = false;
		flag = roleMapper.delRoleDeptByDeptId(deptId);
		flag = deptMapper.delDept(deptId);
		return flag;
	}

	@Override
	public Dept getDeptById(Long deptId) {
		return deptMapper.getDeptById(deptId);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean addDeptInfo(Dept dept) {
		boolean flag = false;
		Long userId = UserUtils.getCurrentUserId();
		if (userId != null){
			dept.setUpdateBy(userId.toString());
		}
		dept.setUpdateDate(new Date());
		flag = deptMapper.addDeptInfo(dept);
		// 添加部门时 需要给超级管理员添加一条映射记录
		RoleToDept roleDept = new RoleToDept();
		roleDept.setRoleId(1L);
		roleDept.setDeptId(dept.getId());
		flag = roleMapper.addRoleToDetp(roleDept);
		return flag;
	}

	@Override
	public boolean updateDeptInfo(Dept dept) {
		String userId = UserUtils.getCurrentUserId().toString();
		dept.setUpdateDate(new Date());
		dept.setUpdateBy(userId);
		return deptMapper.updateDeptInfo(dept);
	}

	@Override
	public List<Dept> getDeptListByUserId(Long currentUserId) {
		return this.deptMapper.getDeptListByUserId(currentUserId);
	}

	
}
