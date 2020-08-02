package cn.bagebeyond.oa.activitimanage.service;

import java.util.List;

import cn.bagebeyond.oa.activitimanage.bean.LeaveBean;

public interface ILeaveProcessService {

	/**
	 * @Title : getLeaveBeanList
	 * @Description : 获取请假列表
	 * @Param @param leaveBean
	 * @Return List<LeaveBean>
	 * @Author ZJD
	 * 2018年3月24日上午11:22:27
	 */
	List<LeaveBean> getLeaveBeanList(LeaveBean leaveBean);
	
	/**
	 * @Title : getLeaveBeanById
	 * @Description : 通过leaveId获取请假详情
	 * @Param @param leaveId
	 * @Return LeaveBean
	 * @Author ZJD
	 * 2018年3月24日下午2:35:25
	 */
	LeaveBean getLeaveBeanById(Long leaveId);
	
	/**
	 * @Title : addLeaveBean
	 * @Description : 添加请假信息
	 * @Param @param leaveBean
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月24日下午3:11:56
	 */
	boolean addLeaveBean(LeaveBean leaveBean);
	
	/**
	 * @Title : updateLeaveBean
	 * @Description : 修改请假信息
	 * @Param @param leaveBean
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月24日下午3:12:39
	 */
	boolean updateLeaveBean(LeaveBean leaveBean);
	
	/**
	 * @Title : delLeaveProcess
	 * @Description : 删除请假单
	 * @Param @param leaveId
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2018年3月24日下午4:16:48
	 */
	boolean delLeaveProcess(Long leaveId);
	
	/**
	 * @Title : doLeaveProcess
	 * @Description : 申请请假（开启流程）
	 * @Param @param leaveId 设定文件
	 * @Return void
	 * @Author ZJD
	 * 2018年3月24日下午4:33:25
	 */
	void doLeaveProcess(Long leaveId);
	
	/**
	 * @Title : getLeaveBeanByTaskId
	 * @Description :根据任务ID获取请假表单的信息
	 * @Param @param taskId
	 * @Param @return 设定文件
	 * @Return LeaveBean
	 * @Author ZJD
	 * 2018年3月24日下午6:23:43
	 */
	LeaveBean getLeaveBeanByTaskId(String taskId);
}
