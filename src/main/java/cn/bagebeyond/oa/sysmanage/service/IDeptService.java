package cn.bagebeyond.oa.sysmanage.service;

import java.util.List;

import cn.bagebeyond.oa.sysmanage.entity.Dept;

/**
 * 部门的逻辑业务实现
 * @author ZJD
 *
 */
public interface IDeptService {
	
	/**
	 * @Title : addDept
	 * @Description : TODO(添加部门信息)
	 * @Param @param dept
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:20:11
	 */
	public boolean addDeptInfo(Dept dept);

	/**
	 * @Title : getDeptListByUserId
	 * @Description : 根据用户id获取部门列表
	 * @Param @param currentUserId
	 * @Return List<Dept>
	 * @Author ZJD
	 * 2018年3月15日上午11:35:45
	 */
	public List<Dept> getDeptListByUserId(Long currentUserId);
	
	/**
	 * @Title : getAllDeptList
	 * @Description : TODO(获取所有部门列表)
	 * @Param @return 设定文件
	 * @Return List<Dept>
	 * @Author ZJD
	 * 2017年10月12日下午8:47:22
	 */
	public List<Dept> getAllDeptList();
	
	/**
	 * @Title : getDeptById
	 * @Description : TODO(根据id获取部门信息)
	 * @Param @return 设定文件
	 * @Return Dept
	 * @Author ZJD
	 * 2017年10月16日下午10:46:24
	 */
	public Dept getDeptById(Long deptId);
	
	/**
	 * @Title : getChildCount
	 * @Description : TODO(得到子节点的数量)
	 * @Param @param deptId
	 * @Param @return 设定文件
	 * @Return int
	 * @Author ZJD
	 * 2017年10月12日下午9:09:34
	 */
	public int getChildCount(Long deptId);
	
	/**
	 * @Title : delDept
	 * @Description : TODO(删除部门)
	 * @Param @param deptId
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月12日下午10:01:04
	 */
	public boolean delDept(Long deptId);
	
	/**
	 * @Title : updateDept
	 * @Description : TODO(更新部门信息)
	 * @Param @param dept
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:20:50
	 */
	public boolean updateDeptInfo(Dept dept);

	
}
