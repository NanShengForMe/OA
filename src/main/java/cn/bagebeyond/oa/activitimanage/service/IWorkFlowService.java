package cn.bagebeyond.oa.activitimanage.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

public interface IWorkFlowService {

	/**
	 * @Title : getDeployments
	 * @Description : 获取所有流程部署对象
	 * @Param @return 设定文件
	 * @Return List<Deployment>
	 * @Author ZJD
	 * 2018年3月21日下午5:29:08
	 */
	List<Deployment> getDeployments();
	
	/**
	 * @Title : addDeployment
	 * @Description : 执行流程的部署
	 * @Param @param inputStream
	 * @Param @param deploymentName 设定文件
	 * @Return Deployment
	 * @Author ZJD
	 * 2018年3月22日下午3:14:31
	 */
	Deployment addDeployment(InputStream inputStream ,String deploymentName);
	
	/**
	 * @Title : delDeployment
	 * @Description : 删除流程部署信息
	 * @Param @param depploymentId
	 * @Param @param flag 是否级联删除
	 * @Return void
	 * @Author ZJD
	 * 2018年3月22日下午4:33:47
	 */
	void delDeployment(String depploymentId, boolean flag);
	
	/**
	 * @Title : getProcessDefinitions
	 * @Description : 获取流程定义列表
	 * @Param @return 设定文件
	 * @Return List<ProcessDefinition>
	 * @Author ZJD
	 * 2018年3月22日下午6:12:32
	 */
	List<ProcessDefinition> getProcessDefinitions();
	
	/**
	 * @Title : getProcessDefinitionImageStream
	 * @Description : 获取流程定义图片输出流
	 * @Param @param deploymentId
	 * @Param @param imageName
	 * @Return InputStream
	 * @Author ZJD
	 * 2018年3月22日下午6:49:34
	 */
	InputStream getProcessDefinitionImageStream(String deploymentId, String imageName);
	
	/**
	 * @Title : startProcess
	 * @Description :通过businessKey启动流程
	 * @Param @param processDefinitionKey
	 * @Param @param businessKey
	 * @Param @param varaibles 设定文件
	 * @Return ProcessInstance
	 * @Author ZJD
	 * 2018年3月24日下午4:46:06
	 */
	ProcessInstance startProcess(String processDefinitionKey, String businessKey, Map<String,Object> varaibles);
	
	/**
	 * @Title : getTaskList
	 * @Description : 获取用户任务列表
	 * @Param @param assingee
	 * @Param @return 设定文件
	 * @Return List<Task>
	 * @Author ZJD
	 * 2018年3月24日下午5:41:34
	 */
	List<Task> getTaskList(String assingee);
	
	/**
	 * @Title : getTaskFormKeyByTaskId
	 * @Description : 通过任务Id获取任务的FormKey
	 * @Param @param taskId
	 * @Param @return 设定文件
	 * @Return String
	 * @Author ZJD
	 * 2018年3月24日下午6:01:33
	 */
	String getTaskFormKeyByTaskId(String taskId);
	
	/**
	 * @Title : getTaskById
	 * @Description : 根据任务Id获取任务对象
	 * @Param @param taskId
	 * @Param @return 设定文件
	 * @Return Task
	 * @Author ZJD
	 * 2018年3月24日下午6:28:36
	 */
	Task getTaskById(String taskId);
	
	/**
	 * @Title : getProcessInstanceById
	 * @Description : 通过流程实例Id获取流程实例对象
	 * @Param @param processInstanceId
	 * @Param @return 设定文件
	 * @Return ProcessInstance
	 * @Author ZJD
	 * 2018年3月24日下午6:34:31
	 */
	ProcessInstance getProcessInstanceById(String processInstanceId);
	
	/**
	 * @Title : getOutComeListByTaskId
	 * @Description : 根据任务Id获取此任务结束后的连线
	 * @Param @param taskId 设定文件
	 * @Return List<PvmTransition>
	 * @Author ZJD
	 * 2018年3月24日下午6:52:08
	 */
	List<PvmTransition> getOutComeListByTaskId(String taskId);
	
	/**
	 * @Title : getCommentListByTaskId
	 * @Description : 通过任务Id获取流程批注
	 * @Param @param taskId
	 * @Param @return 设定文件
	 * @Return List<Comment>
	 * @Author ZJD
	 * 2018年3月24日下午7:32:57
	 */
	List<Comment> getCommentListByTaskId(String taskId);
	
	/**
	 * @Title : completeTask
	 * @Description : 完成任务
	 * @Param @param taskId
	 * @Param @param outcome
	 * @Param @param commentMsg 设定文件
	 * @Return ProcessInstance
	 * @Author ZJD
	 * 2018年3月25日上午10:26:41
	 */
	ProcessInstance completeTask(String taskId, String outcome, String commentMsg);
	
	/**
	 * @Title : getProcessDefinitonByInstanceId
	 * @Description : 通过流程实例id获取流程定义对象
	 * @Param @param processInstanceId
	 * @Param @return 设定文件
	 * @Return ProcessDefinition
	 * @Author ZJD
	 * 2018年3月25日下午2:26:43
	 */
	ProcessDefinition getProcessDefinitonByInstanceId(String processInstanceId);
	
	/**
	 * @Title : getActivitiCoordinate
	 * @Description : 通过流程实例Id获取当前节点的坐标信息
	 * @Param @param processInstanceId
	 * @Param @return 设定文件
	 * @Return ActivityImpl
	 * @Author ZJD
	 * 2018年3月25日下午3:11:28
	 */
	ActivityImpl getActivitiCoordinate(String processInstanceId);
}
