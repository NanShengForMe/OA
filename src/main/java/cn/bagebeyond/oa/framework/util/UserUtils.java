package cn.bagebeyond.oa.framework.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.bagebeyond.oa.sysmanage.entity.User;

/**
 * <p>Title: UserUtils</p>
 * <p>Description:  获取当前登录信息工具类</p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2017年11月2日 下午10:24:38
 *
 */
public class UserUtils {

	/**
	 * @Title : getCurrentUserId
	 * @Description : TODO(获取当前登陆用户Id)
	 * @Param @return 设定文件
	 * @Return Long
	 * @Author ZJD
	 * 2017年11月2日下午10:26:40
	 */
	public static Long getCurrentUserId(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		if(request.getSession().getAttribute("user") != null){
			User user = (User) request.getSession().getAttribute("user");
			return user.getUserId();
		}
		return null;
	}
	
	/**
	 * @Title : getCurrentUserName
	 * @Description : TODO(获取当前登陆用户用户名)
	 * @Param @return 设定文件
	 * @Return String
	 * @Author ZJD
	 * 2017年11月2日下午10:43:45
	 */
	public static String getCurrentUserName(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		if(request.getSession().getAttribute("user") != null){
			User user = (User) request.getSession().getAttribute("user");
			return user.getUserName();
		}
		return null;
	}
}
