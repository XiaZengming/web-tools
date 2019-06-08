package com.itshidu.web.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	/**
	 * CH:从Request中获取指定名字的cookie
	 * EN:Gets a cookie with name from the Request
	 * @param name (cookie-name)
	 * @param request (请求)
	 * @return ( cookie | NULL )
	 */
	public static Cookie getCookie(String name,HttpServletRequest request){
		Cookie[] cos = request.getCookies();
		if(cos!=null){
			for(Cookie co : cos){
				if(co.getName().equals(name)){
					return co;
				}
			}
		}
		return null;
	}
	/**
	 * 设置Cookie值，age的单位是秒
	 * @param name 名称
	 * @param value 值
	 * @param path 作用路径
	 * @param age 声明周期
	 * @param response 响应
	 */
	public static void setCookie(String name,String value,String path,int age,HttpServletResponse response){
		Cookie co = new Cookie(name, value);
		co.setPath(path);
		co.setMaxAge(age); //秒
		response.addCookie(co);
	}
	/**
	 * 设置Cookie值，age的单位是秒
	 * @param name 名称
	 * @param value 值
	 * @param age 声明周期
	 * @param response 响应
	 */
	public static void setCookie(String name,String value,int age,HttpServletResponse response){
		setCookie(name, value, "/", age,response);
	}
	/**
	 * 通过name移除指定的cookie值
	 * @param name 名称
	 * @param response 响应
	 */
	public static void removeCookie(String name,HttpServletResponse response){
		setCookie(name, null, 0, response);
	}
}
