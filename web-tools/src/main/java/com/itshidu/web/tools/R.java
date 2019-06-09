package com.itshidu.web.tools;

import java.util.HashMap;

public class R extends HashMap<String, Object> {
	
	public static R of() {
		return new R();
	}
	public static R of(int code) {
		R er = new R();
		er.put("code", code);
		return er;
	}
	public static R of(int code,String msg) {
		R er = new R();
		er.put("code", code);
		er.put("msg", msg);
		return er;
	}
	public static R of(int code,String msg,Object data) {
		R er = new R();
		er.put("code", code);
		er.put("msg", msg);
		er.put("data", data);
		return er;
	}
	
	public R put(String key,Object value) {
		super.put(key, value);
		return this;
	}
	
}
