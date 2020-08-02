package cn.bagebeyond.oa.activitimanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bagebeyond.oa.activitimanage.bean.LeaveBean;
import cn.bagebeyond.oa.activitimanage.service.ILeaveProcessService;
import cn.bagebeyond.oa.activitimanage.service.IWorkFlowService;
import cn.bagebeyond.oa.framework.util.UserUtils;

/**
 * <p>Title: ProcessTaskController</p>
 * <p>Description: 任务管理</p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月24日 下午5:35:38
 *
 */
@Controller
@RequestMapping("/activitimgr/processTask")
public class ProcessTaskController {

	private static Logger logger = Logger.getLogger(ProcessTaskController.class);
	
	@Autowired
	private IWorkFlowService workFlowService;
	
	@Autowired
	private ILeaveProcessService leaveProcessServcie;
	
	/**
	 * @Title : gotoProcessTask
	 * @Description : 获取用户任务列表
	 * @Param @return 设定文件
	 * @Return String
	 * @Author ZJD
	 * 2018年3月24日下午5:39:56
	 */
	@RequestMapping("/gotoProcessTaskList")
	public String gotoProcessTaskList(Model model){
		List<Task> taskList = new ArrayList<Task>();
		String assingee = UserUtils.getCurrentUserId().toString();
		taskList = this.workFlowService.getTaskList(assingee);
		model.addAttribute("taskList", taskList);
		return "activitimanage/processTask/processTaskList";
	}
	
	@RequestMapping("/gotoProcessTaskDetail")
	public String gotoProcessTaskDetail(String taskId){
		String taskDetailUrl = this.workFlowService.getTaskFormKeyByTaskId(taskId);
		return "redirect:" + taskDetailUrl + "?taskId=" + taskId;
	}
	
	@RequestMapping("/gotoLeaveProcessTaskDetail")
	public String gotoLeaveProcessTaskDetail(@ModelAttribute("taskId") String taskId, Model model){
		// 1、使用任务Id查询相关请假表单的信息
		LeaveBean leaveBean = this.leaveProcessServcie.getLeaveBeanByTaskId(taskId);
		// 2、加处理批注
		
		// 3、根据任务Id动态生成下一步操作按钮 （流程连线的走向）
		List<PvmTransition> pvmTransitionList = this.workFlowService.getOutComeListByTaskId(taskId);
		//只需两条连线的值
		//构造按钮列表
		List<String> buttonList = new ArrayList<String>();
		if(pvmTransitionList != null && pvmTransitionList.size() > 0){
			for(PvmTransition pvm : pvmTransitionList){
				String outcomeName = (String) pvm.getProperty("name");
				if(StringUtils.isNotBlank(outcomeName)){
					buttonList.add(outcomeName);
				}else{
					buttonList.add("确认提交");
				}
			}
		}
		// 4、根据任务Id获取流程处理的历史审批信息（act_hi_comment）
		List<Comment> commentList = this.workFlowService.getCommentListByTaskId(taskId);
		
		
		model.addAttribute("leaveBean", leaveBean);
		model.addAttribute("buttonNameList", buttonList);
		model.addAttribute("commentList", commentList);
		return "activitimanage/leaveProcess/leaveProcssTaskDetail";
	}
	
	@RequestMapping("completeTask")
	public @ResponseBody Map<String,Object> completeTask(String taskId, String outcome, String commentMsg){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		System.out.println("taskId="+taskId);
		System.out.println("outcome="+outcome);
		System.out.println("commentMsg="+commentMsg);
		try {
			if (this.workFlowService.completeTask(taskId, outcome, commentMsg) != null){ // 返回流程实例对象如果不为空，表示该流程还未完成
				resultMap.put("result", "任务处理成功!");
			} else {
				// 返回实例对象为空则表示该流程已结束，更新状态
				//this.leaveProcessServcie.up
				
				
				
			}
		} catch (Exception e){
			logger.error("任务处理失败",e);
			resultMap.put("result", "任务处理失败");
		}
		
		
		return resultMap;
	}
}
