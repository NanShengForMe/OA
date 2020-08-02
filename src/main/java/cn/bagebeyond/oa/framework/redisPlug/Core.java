package cn.bagebeyond.oa.framework.redisPlug;

import cn.bagebeyond.oa.framework.util.SpringUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class Core {
	private static final int JEDIS_EXPIRE = 60 * 60;//默认失效时间一小时   单位秒
	private static JedisPool pool;
	
	
	
	private static Jedis getJedis(){
		if(pool == null){
			pool = (JedisPool) SpringUtil.getBean(JedisPool.class);
		}
		return pool.getResource();
	}
	
	/****
	 * 写入一个字符串
	 * @param str
	 * @return 受影响的行数
	 */
	public static Long putString(String key,String value){
		Jedis jedis = getJedis();
		String l = jedis.set(key,value);
		jedis.expire(key, JEDIS_EXPIRE);
		jedis.close();
		if(l.equals("OK")){
			return 1L;
		}else{
			return 0L;
		}
	}
	
	/****
	 * 写入一个字符串
	 * @param str
	 * @return 受影响的行数
	 */
	public static Long putString(String key,String value, int outTime){
		Jedis jedis = getJedis();
		String l = jedis.set(key,value);
		jedis.expire(key, outTime);
		jedis.close();
		if(l.equals("OK")){
			return 1L;
		}else{
			return 0L;
		}
	}
	
	/***
	 * 获取一个字符串
	 * @param key
	 * @return
	 */
	public static String getString(String key){
		Jedis jedis = getJedis();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}
	
	/***
	 * 删除一个对象
	 * @param key		主键
	 * @return  删除掉的行数
	 */
	public static Long delObject(String key){
		Jedis jedis = getJedis();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}
	
	/***
	 * 检查一个KEY是否存在
	 * @param key
	 * @return
	 */
	public static Boolean exists(String key){
		Jedis jedis = getJedis();
		boolean result = jedis.exists(key);
		jedis.close();
		return result;
	}
	
	/***
	 * 为一个KEY设置过期时间
	 * <br>设置这个将在s秒后过期
	 * @param key			主键
	 * @param s				秒
	 * @return 受影响的行数
	 */
	public static Long expire(String key,int s){
		Jedis jedis = getJedis();
		long result = jedis.expire(key, s);
		jedis.close();
		return result;
	}
	
	/***
	 * 为一个KEY指定过期时间,这个Object将在time过期失效
	 * @param key
	 * @param time 				Unix时间戳
	 * @return
	 */
	public static Long expireat(String key,long time){
		Jedis jedis = getJedis();
		long result = jedis.expireAt(key, time);
		jedis.close();
		return result;
	}
	
	/***
	 * 在已有的key中随机获取一个key出来
	 * @return	key
	 */
	public static String randomKey(){
		Jedis jedis = getJedis();
		String result = jedis.randomKey();
		jedis.close();
		return result;
	}
	
	/***
	 * 更改一个主键的名称
	 * @param oldKey			旧(历史)的KEY名称
	 * @param newKey			新的KEY名称
	 * @return 返回OK标示修改成功
	 */
	public static String reNmae(String oldKey,String newKey){
		Jedis jedis = getJedis();
		String result = jedis.rename(oldKey, oldKey);
		jedis.close();
		return result;
	}
	
	/***
	 * 获取值的部分数据信息
	 * @param key				主键 
	 * @param start				开始位置
	 * @param end				结束位置
	 * @return
	 */
	public static String getRange(String key,int start,int end){
		Jedis jedis = getJedis();
		String result = jedis.getrange(key, start, end);
		jedis.close();
		return result;
	}
	
	
	/***
	 * 讲一个对象放入Redis中的
	 * @param key				主键
	 * @param object			值
	 * @return
	 */
	public static String setObject(String key,Object object){
		Jedis jedis = getJedis();
		String result = jedis.set(key.getBytes(), SerializeUtil.serialize(object));
		jedis.close();
		return result;
	}
	

	/***
	 * 取出Redis中的一个对象
	 * @param key				主键
	 * @return 
	 */
	public static Object getObject(String key){
		Jedis jedis = getJedis();
		byte[] bInfo = jedis.get(key.getBytes());
		if(bInfo == null){
			jedis.close();
			return null;
		}else{
			jedis.close();
			return SerializeUtil.unserialize(bInfo);
		}
	}
	
	
	/***
	 * 写入用户RSA到Redis中
	 * @param apply_id				APP应用编号
	 * @param plugn					加密RSA对象
	 * @return 
	 */
	public static int putRsa(String apply_id,Object plugn){
		Jedis jedis = getJedis();
		int result = 0;
		try {
			byte[] bInfo = apply_id.getBytes();
			byte[] bSInfo = SerializeUtil.serialize(plugn);
			jedis.set(bInfo, bSInfo);
			jedis.expire(apply_id.getBytes(),JEDIS_EXPIRE);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
			jedis.close();
			result = 0;
		}
		jedis.close();
		return result;
	}

	
	
	/***
	 * 根据会员登录名称获取登录验证码
	 * @param userName
	 * @return
	 */
	public static Object getVerCodeByUserLognName(String userName) {
		Jedis jedis = getJedis();
		Object result = jedis.get(userName+"dl");
		jedis.close();
		return result;
	}
	
	
	
	/***
	* 根据手机号获取短信验证码
	* 登录的时候根据手机号获取短信验证码
	* @author 李杰 
	* @Title: getVerCodeByPhone 
	* @param phone	
	* @return Object 返回类型 String 6位验证码
	* @date 2016-3-21 上午9:26:03
	 */
	public static Object getVerCodeByPhone(String phone){
		Jedis jedis = getJedis();
		Object result = jedis.get(phone+"dl");
		jedis.close();
		return result;
	}
	
	
	/***
	* 根据手机号作为Key将登录验证码缓存起来
	* @author 李杰 
	* @Title: putVerCodeByPhone 
	* @return 设定文件 
	* @return int 返回类型  1缓存成功
	* @date 2016-3-21 上午9:29:13
	* @throws
	 */
	public static int putVerCodeByPhone(String phone,String code){
		Jedis jedis = getJedis();
		if(getString(phone+"register")==null){
			jedis.set(phone+"dl", code);
			jedis.expire(phone+"dl", 180);
			jedis.close();
			return 1;
		}
		jedis.close();
		return 0;
	}

	
	/***
	* 缓存注册发送的短信验证码
	* 
	* @author 李杰
	* @Title: putRegisterPhoneCode
	* @param phone
	* @param code
	* @return 1缓存成功
	* @Description: TODO
	* @date 2016-4-15 上午9:48:39
	 */
	public static int putRegisterPhoneCode(String phone,String code){
		long result = putString(phone+"register", code);
		Jedis jedis = getJedis();
		jedis.expire(phone+"register", 180);
		jedis.close();
		return 1;
	}
	
	/***
	* 获取缓存中的注册短信验证码
	* @author 李杰
	* @Title: getRegisterPhoneCode
	* @param phone
	* @return
	* @Description: TODO
	* @date 2016-4-15 上午11:05:27
	 */
	public static String getRegisterPhoneCode(String phone){
		String result = getString(phone+"register");
		return result;
	}
	
	/***
	* 清除缓存中的注册短信验证码
	* 
	* @author 李杰
	* @param phone
	* @return
	* @date 2016-5-13 上午11:03:33
	 */
	public static long removeRegisterPhoneCode(String phone){
		Jedis jedis = getJedis();
		long result = jedis.del(phone+"register");
		jedis.close();
		return result;
	}
	
	
	
	/**
	 * 缓存忘记密码中发送的短信验证码
	 * putForgetPWDPhoneCode
	 * @author 邱陈东  
	 * * @Title: putForgetPWDPhoneCode 
	 * @param @param phone
	 * @param @param code
	 * @param @return 设定文件 
	 * @return int 返回类型 
	 * @date 2016-5-6 下午4:11:40
	 * @throws
	 */
	public static int putWithdrawPhoneCode(String phone,String code){
		Jedis jedis = getJedis();
		delObject(phone+"Withdraw");
		if(getString(phone+"Withdraw")==null){
			putString(phone+"Withdraw", code);
			jedis.expire(phone+"Withdraw", 180);
			jedis.close();
			return 1;
		}
		jedis.close();
		return 0;
	}
	
	public static String getWithdrawPhoneCode(String phone){
		return getString(phone+"Withdraw");
	}
	
	public static int putForgetPWDPhoneCode(String phone,String code){
		Jedis jedis = getJedis();
		delObject(phone+"ForgetPWD");
		if(getString(phone+"ForgetPWD")==null){
			putString(phone+"ForgetPWD", code);
			jedis.expire(phone+"ForgetPWD", 180);
			jedis.close();
			return 1;
		}
		jedis.close();
		return 0;
	}
	/**
	 * 获取忘记密码中发送的短信验证码
	* getForgetPWDPhoneCode
	* @author 邱陈东  
	* * @Title: getForgetPWDPhoneCode 
	* @param @param phone
	* @param @return 设定文件 
	* @return String 返回类型 
	* @date 2016-5-6 下午4:16:43
	* @throws
	 */
	public static String getForgetPWDPhoneCode(String phone){
		return getString(phone+"ForgetPWD");
	}
	
	public static int putForgetPWDStatu(String phone,String statu){
		Jedis jedis = getJedis();
		if(getString(phone+"ForgetPWDStatu")==null){
			putString(phone+"ForgetPWDStatu", statu);
			jedis.expire(phone+"ForgetPWDStatu", 180);
			jedis.close();
			return 1;
		}
		jedis.close();
		return 0;
	}
	/**
	 * 获取忘记密码中发送的短信验证码
	* getForgetPWDPhoneCode
	* @author 邱陈东  
	* * @Title: getForgetPWDPhoneCode 
	* @param @param phone
	* @param @return 设定文件 
	* @return String 返回类型 
	* @date 2016-5-6 下午4:16:43
	* @throws
	 */
	public static String getForgetPWDStatu(String phone){
		return getString(phone+"ForgetPWDStatu");
	}
	
	/***
	* 缓存修改手机绑定验证码
	* 
	* @author 李杰
	* @param phone
	* @param code
	* @return
	* @date 2016-4-26 下午8:54:57
	 */
	public static int putEditBindPhoneCode(String phone,String code){
		Jedis jedis = getJedis();
		putString(phone+"editBindPhone", code);
		jedis.expire(phone+"editBindPhone", 180);
		jedis.close();
		return 1;
	}
	
	/***
	* 获取修改手机绑定验证码
	* @author 李杰
	* @param newPhone
	* @return
	* @date 2016-4-26 下午8:07:14
	 */
	public static String getEditBindPhoneCode(String newPhone) {
		return getString(newPhone+"editBindPhone");
	}

	/***
	* 获取绑定邮箱验证码
	* 
	* @author 李杰
	* @param email
	* @return
	* @date 2016-4-27 上午11:24:32
	 */
	public static int putBindEmailCode(String email,String code) {
		Jedis jedis = getJedis();
		putString(email+"bindEmail", code);
		jedis.expire(email+"bindEmail", 180);
		jedis.close();
		return 1;
	}
	
	/***
	* 获取绑定邮箱验证码
	* 
	* @author 李杰
	* @param email
	* @return
	* @date 2016-4-27 上午11:24:32
	 */
	public static String getBindEmailCode(String email) {
		return getString(email+"bindEmail");
	}
	
	
	/**
	 * 好友转账-发送短信验证码
	* putFriendTransferPhoneCode
	* @author 邱陈东  
	* * @Title: putFriendTransferPhoneCode 
	* @param @param phone
	* @param @param code
	* @param @return 设定文件 
	* @return int 返回类型 
	* @date 2016-5-23 上午10:32:41
	* @throws
	 */
	public static int putFriendTransferPhoneCode(String phone,String code){
		if(getString(phone+"FriendTransfer")==null){
			Jedis jedis = getJedis();
			putString(phone+"FriendTransfer", code);
			jedis.expire(phone+"FriendTransfer", 180);
			jedis.close();
			return 1;
		}
		return 0;
	}

	/**
	 * 好友转账 - 获取已发送的验证码
	* getFriendTransferPhoneCode
	* @author 邱陈东  
	* * @Title: getFriendTransferPhoneCode 
	* @param @param phone
	* @param @return 设定文件 
	* @return String 返回类型 
	* @date 2016-5-23 上午10:33:17
	* @throws
	 */
	public static String getFriendTransferPhoneCode(String phone){
		return getString(phone+"FriendTransfer");
	}
	


}