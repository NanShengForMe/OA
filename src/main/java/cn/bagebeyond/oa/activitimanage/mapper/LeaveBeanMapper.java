package cn.bagebeyond.oa.activitimanage.mapper;

import java.util.List;

import cn.bagebeyond.oa.activitimanage.bean.LeaveBean;
 
public interface LeaveBeanMapper {
	
	/**
	 * @Title : getLeaveBeanList
	 * @Description : 获取用户请假列表
	 * @Param @param leaveBean
	 * @Param @return 设定文件
	 * @Return List<LeaveBean>
	 * @Author ZJD
	 * 2018年3月24日上午11:17:03
	 */
	public List<LeaveBean> getLeaveBeanList(LeaveBean leaveBean);
	
	/**
	 * @Title : getLeaveBeanById
	 * @Description : 通过leaveId获取请假详情
	 * @Param @param leaveId
	 * @Param @return 设定文件
	 * @Return LeaveBean
	 * @Author ZJD
	 * 2018年3月24日上午11:17:45
	 */
	public LeaveBean getLeaveBeanById(Long leaveId);
	
	/**
	 * @Title : addLeaveBean
	 * @Description : 添加一条请假信息
	 * @Param @param leaveBean
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月24日上午11:18:17
	 */
	public boolean addLeaveBean(LeaveBean leaveBean);
	
	/**
	 * @Title : delLeaveBean
	 * @Description : 删除请假信息
	 * @Param @param leaveId
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月24日上午11:18:42
	 */
	public boolean delLeaveBean(Long leaveId);
	
	/**
	 * @Title : updateLeaveBeanState
	 * @Description : 更新请假表单状态
	 * @Param @param leaveId
	 * @Param @param state
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月24日上午11:18:57
	 */
	public boolean updateLeaveBeanState(Long leaveId,Integer state);
	
	/**
	 * @Title : updateLeaveBeanInstanceId
	 * @Description : 根据leaveId更新请假流程实例Id
	 * @Param @param leaveId
	 * @Param @param processInstanceId
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月25日上午11:49:34
	 */
	boolean updateLeaveBeanInstanceId(Long leaveId, String processInstanceId);
	
	/**
	 * @Title : updateLeaveBean
	 * @Description : 更新请假表单信息
	 * @Param @param leaveBean
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月24日上午11:19:24
	 */
	public boolean updateLeaveBean(LeaveBean leaveBean);
	
	
}
