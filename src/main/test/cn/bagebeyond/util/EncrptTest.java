package cn.bagebeyond.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import cn.bagebeyond.oa.framework.util.EncryptUtil;


/**
 * 加密算法分为不可逆加密和可逆加密 common-codec包提供了几种加密算法
 *	* 不可逆算法类型
 *		MD5
 *		SHA1
 *	* 可逆算法 
 *		BASE64
 *		HEX	
 * @author ZJD
 *
 */
public class EncrptTest {
	
	//测试不可逆加密算法MD5
	@Test
	public void testMD5(){
		String plainPwd = "123456";
		String encrptPwd = DigestUtils.md5Hex(plainPwd.getBytes());
		System.out.println(encrptPwd);
	}
	
	//测试SHA1
	@Test
	public void testSHA1(){
		String plainPwd = "123456";
		String encrptPwd = DigestUtils.sha1Hex(plainPwd.getBytes());
		System.out.println(encrptPwd);
	}
	
	//测试可逆算法
	@Test
	public void testBASE64(){
		String plainPwd = "123456";
		String encrptPwd = Base64.encode(plainPwd.getBytes());
		System.out.println(encrptPwd);
		System.out.println("解密" + new String(Base64.decode(encrptPwd)));
	}
	
	//可逆HEX加密算法
	@Test
	public void testHex() throws DecoderException{
		String plainPwd = "123456";
		String encrptPwd = Hex.encodeHexString(plainPwd.getBytes());
		System.out.println(encrptPwd);
		System.out.println("解密" + new String(Hex.decodeHex(encrptPwd.toCharArray())));
	}
	
	//  plainPwd = 123456
	//	1、生成一个随机数
	//	2、用可逆的加密算法加密这个随机数（HEX）
	//	3、将随机数和密码用不可逆加密算法（SHA1）
	//	4、将第三步得到的字符串用可逆的算法进行加密
	//	5、将第二步和第四步的值拼凑成我们需要的加密值encrptPwd
	//
	//
	@Test
	public void testOAencrpt(){
		String plainPwd = "123456";
		//	1
		byte[] random = EncryptUtil.generateSalt(8);
		//	2
		String randomHex = EncryptUtil.encodeHex(random);
		//	3
		byte[] thirdEncrpt = EncryptUtil.sha1(plainPwd.getBytes(), random, 1024);
		//	4
		String sha1Pwd = EncryptUtil.encodeHex(thirdEncrpt);
		//	5 randomHex + sha1Pwd
		String encrptPwd = randomHex + sha1Pwd;
		System.out.println(encrptPwd);
		
		//9531ea947bd6cce5e957510e6623a56d1f2d959477b647e78c8ff056
		//fbb72d6edd45696274cdc2dad9ef236e838962e9731265f77e2b1110
	}
	
	@Test
	public void testPwdValidator(){
		//重新根据盐值，输入的明文密码，加密次数进行验证
		
		String plainPwd = "123456";
		
		String encrptPwd = "fbb72d6edd45696274cdc2dad9ef236e838962e9731265f77e2b1110";
		
		byte[] salt = EncryptUtil.decodeHex(encrptPwd.substring(0, 16));
		
		byte[] hashencrptPwd = EncryptUtil.sha1(plainPwd.getBytes(), salt, 1024);
		
		String newencrptPwd = EncryptUtil.encodeHex(salt) + EncryptUtil.encodeHex(hashencrptPwd);
		
		System.out.println(newencrptPwd);
	}
}
