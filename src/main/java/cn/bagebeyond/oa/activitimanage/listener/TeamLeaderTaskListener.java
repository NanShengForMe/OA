package cn.bagebeyond.oa.activitimanage.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

 
public class TeamLeaderTaskListener implements TaskListener{

	 
	private static final long serialVersionUID = -5939344239357822841L;

	public void notify(DelegateTask deleGateTask) {
		//这里我们可以编写一系列的逻辑,得到当前用户的ID UserUtils.getCurrentUserId
		//然后根据系统的权限模型找到此人对应的主管 这里以vicky老师为例子
		deleGateTask.setAssignee("17");//yourou
		
		 
	}

}
