package cn.bagebeyond.util;

import org.activiti.engine.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActivitiEngineTest {

	@Test
	public void testActiviti(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("springContext.xml");
		
		ProcessEngine processEngine = (ProcessEngine)ctx.getBean("processEngine");
		System.out.println(processEngine);
	}
}
