package cn.bagebeyond.oa.sysmanage.commonutils;

import cn.bagebeyond.oa.framework.util.SpringContextHolder;
import cn.bagebeyond.oa.sysmanage.entity.User;
import cn.bagebeyond.oa.sysmanage.mapper.UserMapper;

public class UserUtils {
	
	public static String getUserNameById(String userId){
		
		UserMapper userMapper = (UserMapper) SpringContextHolder.getBean(UserMapper.class);
		
		User user = userMapper.getUserById(new Long(userId));
		
		return user.getUserName();
	}
}
