package com.itshidu.web.tools;

import java.nio.charset.Charset;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 字符串加密解密工具，可逆加密，秘钥很重要，一定要自己改秘钥，打死也不要告诉其他人
 * @author 夏增明
 * @version 1.0 Date:2013年2月8日15:45:56
 */
public class DesUtil {

	private final static String DES = "DES";
	private final static Charset CHARSET = Charset.forName("UTF-8");

	/**
	 * 加密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception 异常
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {

		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);

	}

	/**
	 * 解密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception 异常
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);

	}

	/**
	 * 密码解密
	 * @param data 密文
	 * @param key 秘钥
	 * @return 解密后的字符串
	 */
	public final static String decrypt(String data,String key) {
		try {
			return new String(decrypt(hex2byte(data.getBytes(CHARSET)), key.getBytes(CHARSET)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符串加密
	 * @param text 明文字符串
	 * @param key 秘钥
	 * @return 密文字符串
	 */
	public final static String encrypt(String text,String key) {
		try {
			return byte2hex(encrypt(text.getBytes(CHARSET), key.getBytes(CHARSET)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String byte2hex(byte[] b) {

		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {

		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	// 测试用例，不需要传递任何参数，直接执行即可。
	public static void main(String[] args) {
		String basestr = "[记得修改秘钥] 我的 #$%^&()first encrypt program 知道吗?DES算法要求有一个可信任的随机数源 --//*。@@@1";
		String str1 = encrypt(basestr,"456789451");

		System.out.println("原始值: " + basestr);
		System.out.println("加密后: " + str1);
		System.out.println("解密后: " + decrypt(str1,"456789451"));
		
	}

}