package cn.bagebeyond.oa.sysmanage.mapper;

import java.util.List;

import cn.bagebeyond.oa.sysmanage.entity.Dept;

/**
 * 
 * <p>Title: DeptMapper</p>
 * <p>Description: 部门的增删查改的mapper代理接口</p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2017年10月12日 下午8:49:55
 *
 */
public interface DeptMapper {

	/**
	 * @Title : addDeptInfo
	 * @Description : TODO(添加部门信息)
	 * @Param @param dept
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:23:16
	 */
	public boolean addDeptInfo(Dept dept);
	
	/**
	 * @Title : getDeptListByUserId
	 * @Description : 根据用户id获取部门列表
	 * @Param @param currentUserId
	 * @Return List<Dept>
	 * @Author ZJD
	 * 2018年3月15日上午11:38:55
	 */
	public List<Dept> getDeptListByUserId(Long userId);
	
	/**
	 * @Title : getAllDeptList
	 * @Description : TODO(查询所有部门列表)
	 * @Param @return 设定文件
	 * @Return List<Dept>
	 * @Author ZJD
	 * 2017年10月12日下午8:55:21
	 */
	public List<Dept> getAllDeptList();
	
	/**
	 * @Title : getDeptById
	 * @Description : TODO(通过id获取部门信息)
	 * @Param @param deptId
	 * @Param @return 设定文件
	 * @Return Dept
	 * @Author ZJD
	 * 2017年10月16日下午10:49:03
	 */
	public Dept getDeptById(Long deptId);
	
	/**
	 * @Title : getChildCount
	 * @Description : TODO(根据deptId得到其子节点的数量)
	 * @Param @param deptId
	 * @Param @return 设定文件
	 * @Return int
	 * @Author ZJD
	 * 2017年10月12日下午9:10:59
	 */
	public int getChildCount(Long deptId);
	
	/**
	 * @Title : delDept
	 * @Description : TODO(删除部门)
	 * @Param @param deptId
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月12日下午10:08:51
	 */
	public boolean delDept(Long deptId);
	
	/**
	 * @Title : updateDeptInfo
	 * @Description : TODO(更新部门信息)
	 * @Param @param dept
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:21:41
	 */
	public boolean updateDeptInfo(Dept dept);

	
	
	/**
	 * @Title : getChildNodeId
	 * @Description : 根据部门ID获取该部门下属所有部门ID（包含自己）
	 * @Param @param deptId
	 * @Param @return 设定文件
	 * @Return List<String>
	 * @Author ZJD
	 * 2018年3月14日下午12:40:38
	 */
	/*List<String> getChildNodeId(Long deptId);*/
}
