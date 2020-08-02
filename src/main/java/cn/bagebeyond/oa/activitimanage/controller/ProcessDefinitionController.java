package cn.bagebeyond.oa.activitimanage.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bagebeyond.oa.activitimanage.service.IWorkFlowService;

/**
 * <p>Title: ProcessDefinitionController</p>
 * <p>Description: 流程定义的查看</p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月22日 下午6:09:07
 *
 */
@Controller
@RequestMapping("/activitimgr/processDefinition")
public class ProcessDefinitionController {

	private static Logger logger = Logger.getLogger(ProcessDefinitionController.class);
	
	@Autowired
	private IWorkFlowService workFlowService;
	
	/**
	 * @Title : gotoProcessDeployList
	 * @Description : 进入流程定义列表
	 * @Param @param model
	 * @Return String
	 * @Author ZJD
	 * 2018年3月22日下午6:42:54
	 */
	@RequestMapping("/gotoProcessDefinitionList")
	public String gotoProcessDeployList(Model model){
		List<ProcessDefinition> processDefinitionList = this.workFlowService.getProcessDefinitions();
		model.addAttribute("processDefinitionList", processDefinitionList);
		return "activitimanage/processDefinition/processDefinitionList";
	}
	
	/**
	 * @Title : gotoProcessDefinitionImage
	 * @Description : 查看流程定义图片
	 * @Param @param model
	 * @Return String
	 * @Author ZJD
	 * 2018年3月22日下午6:43:18
	 */
	@RequestMapping("/gotoProcessDefinitionImage")
	public String gotoProcessDefinitionImage(@ModelAttribute("deploymentId") String deploymentId, @ModelAttribute("imageName") String imageName){
		
		return "activitimanage/processDefinition/processDefinitionImage";
	}
	
	@RequestMapping("/getProcessDefinitionImage")
	public void getProcessDefinitionImage(String deploymentId, String imageName, HttpServletResponse response){
		InputStream inputStream = this.workFlowService.getProcessDefinitionImageStream(deploymentId, imageName);
		
		response.setContentType("img/png");
		response.setCharacterEncoding("utf-8");
		
		try {
			OutputStream outputStream = response.getOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while((len=inputStream.read(buffer, 0, 1024)) != -1){
				outputStream.write(buffer, 0 ,len);
			}
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
