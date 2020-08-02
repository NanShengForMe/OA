package cn.bagebeyond.oa.framework.util; 

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
* Spring Ioc容器辅助工具
* <br>
* 用于在当前SpringIoc中取出某个对象
*/
public class SpringUtil implements ApplicationContextAware{
	
	private static  ApplicationContext applicationContext;
	 
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	public static Object getBean(String name){  
        return applicationContext.getBean(name);  
    }
	
	
	public static Object getBean(Class<?> clazz){
		return applicationContext.getBean(clazz);
	}
}

