package cn.bagebeyond.oa.activitimanage.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.bagebeyond.oa.activitimanage.service.IWorkFlowService;
import cn.bagebeyond.oa.framework.util.UserUtils;

@Service
public class WorkFlowService implements IWorkFlowService {
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private FormService formService;

	@Override
	public List<Deployment> getDeployments() {
		return this.repositoryService.createDeploymentQuery().orderByDeploymentName().desc().list();
	}

	@Override
	public Deployment addDeployment(InputStream inputStream, String deploymentName) {
		if (inputStream != null){
			ZipInputStream zipInputStream = new ZipInputStream(inputStream);
			return this.repositoryService.createDeployment()
								.addZipInputStream(zipInputStream)
								.name(deploymentName)
								.deploy();
		}
		return null;
	}

	@Override
	public void delDeployment(String depploymentId, boolean flag) {
		this.repositoryService.deleteDeployment(depploymentId, flag);
		
	}

	/**
	 * 从这里可以还进行一步操作,
	 * 当在实际的应用中,查询流程定义的时候,其实只需要查询最新版本的流程定义信息 即相同key的流程只需最新版本
	 */
	@Override
	public List<ProcessDefinition> getProcessDefinitions() {
		List<ProcessDefinition> processDefinitionList  =  this.repositoryService.createProcessDefinitionQuery()
										.orderByProcessDefinitionKey().orderByProcessDefinitionVersion().asc().list();
		Map<String,ProcessDefinition> map = new HashMap<String,ProcessDefinition>();
		for (ProcessDefinition pf : processDefinitionList){
			map.put(pf.getKey(), pf);
		}
		List<ProcessDefinition> processDefinitionListNewVersion = new ArrayList<ProcessDefinition>();
		for (ProcessDefinition pf : map.values()){
			processDefinitionListNewVersion.add(pf);
		}
		return processDefinitionListNewVersion;
	}

	@Override
	public InputStream getProcessDefinitionImageStream(String deploymentId, String imageName) {
		
		return this.repositoryService.getResourceAsStream(deploymentId, imageName);
	}

	@Override
	public ProcessInstance startProcess(String processDefinitionKey, String businessKey, Map<String, Object> varaibles) {
		return this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, varaibles);
	}

	@Override
	public List<Task> getTaskList(String assingee) {
		return this.taskService.createTaskQuery().taskAssignee(assingee).list();
	}

	@Override
	public String getTaskFormKeyByTaskId(String taskId) {
		TaskFormData taskFormData = this.formService.getTaskFormData(taskId);
		String url = taskFormData.getFormKey();
		return url;
	}

	@Override
	public Task getTaskById(String taskId) {
		return this.taskService.createTaskQuery().taskId(taskId).singleResult();
	}

	@Override
	public ProcessInstance getProcessInstanceById(String processInstanceId) {
		return this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	}

	/*
	 * 根据任务ID,获取当前任务完成之后的连线名称,返回页面生成处理按钮
	 * @see cn.bagebeyond.oa.activitimanage.service.IWorkFlowService#getOutComeListByTaskId(java.lang.String)
	 */
	@Override
	public List<PvmTransition> getOutComeListByTaskId(String taskId) {
		// 1:根据任务ID 查询任务对象 ,获取流程定义ID和流程实例ID
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		String processDefinitionId = task.getProcessDefinitionId();
		// 2:根据流程实例id找到流程实例对象,查询当前活动节点
		ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String activityId = processInstance.getActivityId();
		// 3:根据流程定义id,查找流程定义对象(即流程图)(processDfinition (接口) ->processDefinitionEntity)
		// 注意：这里不能用createProcessDefinitionQuery()获取processDefinitionEntity实例  会报空指针错误
		/*ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();*/
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) this.repositoryService.getProcessDefinition(processDefinitionId);
		// 
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
		// 4:根据当前活动节点从processDefinitonEntity对象里面获取当前活动后面的连线信息
		List<PvmTransition> pvmTransitionList =  activityImpl.getOutgoingTransitions();
		return pvmTransitionList;
		
	}

	@Override
	public List<Comment> getCommentListByTaskId(String taskId) {
		// 根据任务ID 查询任务对象 流程实例ID
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		return this.taskService.getProcessInstanceComments(processInstanceId);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public ProcessInstance completeTask(String taskId, String outcome, String commentMsg) {
		//完成任务的同时我们需要做以下几件事情
		// 1: 根据页面按钮操作的不同来决定流程的走向(流程变量)
		Map<String,Object> variables = new HashMap<String,Object>();
		if (StringUtils.isNotEmpty(outcome) && !(outcome.equals("确认提交"))){
			variables.put("outcome", outcome);
		}
		
		// 2: 下一个任务的处理人(根据当前操作用户通过监听器来确定下一任务的处理人)
		// 3: 添加评论记录
		// activiti有自己的认证体系 添加批注时会自动的获取认证的UserId，而这里我直接将userName放进去便于在查询时不必再转成name（偷个懒）
		Authentication.setAuthenticatedUserId(UserUtils.getCurrentUserName());
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		if (StringUtils.isNotEmpty(commentMsg)){// 判断填写记录
			this.taskService.addComment(taskId, processInstanceId, commentMsg);
		}
		
		this.taskService.complete(taskId, variables);
		// 4: 执行完任务以后还要判断是否此任务结束,需要将请假单状态更改（1->2）
		ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		return processInstance;
		
	}

	@Override
	public ProcessDefinition getProcessDefinitonByInstanceId(String processInstanceId) {
		// 查询流程实例对象
		ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String processDefinitionId = "";
		// 判断为空则表示该流程已结束，需要到历史表中查流程实例对象
		if (processInstance == null){
			HistoricProcessInstance historicProcessInstance = this.historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			processDefinitionId = historicProcessInstance.getProcessDefinitionId();
		} else {
			processDefinitionId = processInstance.getProcessDefinitionId();
		}
		ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		return processDefinition;
	}

	@Override
	public ActivityImpl getActivitiCoordinate(String processInstanceId) {
		// 查询流程实例对象
		ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if (processInstance != null){
			// 获取此流程实例当前的活动id
			String activityId = processInstance.getActivityId();
			// 通过流程定义的实现类ProcessInstanceEntity来获取当前活动点的坐标信息
			String processDefinitionId = processInstance.getProcessDefinitionId();
			ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) this.repositoryService.getProcessDefinition(processDefinitionId);
			ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
			return activityImpl;
		} else {
			return null;
		}
	}

}
