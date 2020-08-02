package cn.bagebeyond.oa.activitimanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bagebeyond.oa.activitimanage.bean.LeaveBean;
import cn.bagebeyond.oa.activitimanage.service.ILeaveProcessService;
import cn.bagebeyond.oa.activitimanage.service.IWorkFlowService;
import cn.bagebeyond.oa.framework.util.UserUtils;

/**
 * <p>Title: LeaveProcessController</p>
 * <p>Description: 请假信息管理（增删查改、提交）</p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月24日 上午10:51:28
 *
 */
@Controller
@RequestMapping("/activitimgr/leaveProcess")
public class LeaveProcessController {

	private static Logger logger = Logger.getLogger(LeaveProcessController.class);
	
	@Autowired
	private ILeaveProcessService leaveProcessServcie;
	
	@Autowired
	private IWorkFlowService workFlowService;
	
	/**
	 * @Title : gotoLeaveProcessList
	 * @Description : 跳转请假列表，列表数据在页面中用ajax获取
	 * @Param @return 设定文件
	 * @Return String
	 * @Author ZJD
	 * 2018年3月24日上午10:57:23
	 */
	@RequestMapping("/gotoLeaveProcessList")
	public String gotoLeaveProcessList(){
		
		return "activitimanage/leaveProcess/leaveProcessList";
	}
	
	/**
	 * @Title : getLeaveProcessList
	 * @Description : ajax获取请假列表
	 * @Return List<LeaveBean>
	 * @Author ZJD
	 * 2018年3月24日下午2:30:12
	 */
	@RequestMapping("/getLeaveProcessList")
	public @ResponseBody Map<String,Object> getLeaveProcessList(LeaveBean leaveBean){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		leaveBean.setLeaveUserId(UserUtils.getCurrentUserId());
		List<LeaveBean> leaveBeanList = this.leaveProcessServcie.getLeaveBeanList(leaveBean);
		resultMap.put("leaveBeanList", leaveBeanList);
		return resultMap;
	}
	
	@RequestMapping("/gotoLeaveProcessEdit")
	public String gotoLeaveProcessEdit(@ModelAttribute("editFlag") int editFlag, Long leaveId, Model model){
		// 如果是修改，则查询详情放到页面上
		if (editFlag == 2){
			LeaveBean leaveBean = this.leaveProcessServcie.getLeaveBeanById(leaveId);
			model.addAttribute("leaveBean", leaveBean);
		}
		
		return "activitimanage/leaveProcess/leaveProcessEdit";
	}
	
	@RequestMapping("/saveLeaveProcess")
	public @ResponseBody Map<String,Object> saveLeaveProcess(@RequestBody LeaveBean leaveBean){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (leaveBean.getLeaveId() == null){ //添加
				this.leaveProcessServcie.addLeaveBean(leaveBean);
				resultMap.put("result", "添加请假表单成功!");
			} else { // 修改
				this.leaveProcessServcie.updateLeaveBean(leaveBean);
				resultMap.put("result", "修改请假表单成功!");
			}
		} catch (Exception e){
			logger.error("操作请假表单失败!",e);
			resultMap.put("result", "操作请假表单失败!");
		}
		
		return resultMap;
	}
	
	@RequestMapping("/delLeaveProcess")
	public @ResponseBody Map<String,Object> delLeaveProcess(Long leaveId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(this.leaveProcessServcie.delLeaveProcess(leaveId)){
				resultMap.put("result", "删除请假单成功!");
			}
		} catch (Exception e) {
			logger.error("删除失败!",e);
			resultMap.put("result", "删除请假单失败!");
		}
		return resultMap;
	}
	
	
	@RequestMapping("/doLeaveProcess")
	public @ResponseBody Map<String,Object> doLeaveProcess(Long leaveId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			this.leaveProcessServcie.doLeaveProcess(leaveId);
			resultMap.put("result", "申请请假成功,请转往任务处理功能提交请假单!");
		} catch (Exception e){
			logger.error("申请请假失败!", e);
			resultMap.put("result", "申请请假失败!");
		}
		return resultMap;
	}
	
	@RequestMapping("/gotoLeaveProcessImage")
	public String gotoLeaveProcessImage(String processInstanceId, Model model){
		if(processInstanceId != null){
			// 通过流程实例Id==>流程定义对象==>获取deployment_id和dgrm_resource_name==>获取图片输出流
			ProcessDefinition processDefinition = this.workFlowService.getProcessDefinitonByInstanceId(processInstanceId);
			String deploymentId = processDefinition.getDeploymentId();
			String imageName = processDefinition.getDiagramResourceName();
			model.addAttribute("deploymentId", deploymentId);
			model.addAttribute("imageName", imageName);
			// 通过流程Id==>流程对象==>获取当前活动点id（activityId）
			
			ActivityImpl activityImpl = this.workFlowService.getActivitiCoordinate(processInstanceId);
			model.addAttribute("activityImpl", activityImpl);
			return "activitimanage/leaveProcess/leaveProcessImage";
		} else {
			return "activitimanage/leaveProcess/leaveProcessImage";
		}
	}
}
