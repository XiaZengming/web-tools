package com.itshidu.web.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
/**
 * MD5不可逆加密
 * @author Master.Xia
 *
 */
public class MD5Util {

	/**
	 * 对字符串进行MD5加密，得到长度为32的小写密文
	 * @param str text
	 * @return md5String
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String md5(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] buffer = new byte[1024];
	        int length = -1;
	        while ((length = fis.read(buffer, 0, 1024)) != -1) {
	            md.update(buffer, 0, length);
	        }
	        BigInteger bigInt = new BigInteger(1, md.digest());
	        return bigInt.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return null;
	}

	
	
	public static void main(String[] args) {
		System.out.println(md5("123456"));
		System.out.println(md5("asdfalkjdsflj"));
	}
}
