package cave.utils;

import net.sf.json.JSONObject;

/**
 * 统一的JSON返回格式
 * @author Jeor
 */
public class ResultJSON {
	
	public static final int FAILURE = 0;//操作失败
	public static final int SUCCESS = 1;//操作成功
	public static final int OTHER = 2;	//其他
	
	//login
	public static final int LOGIN_USERTYPE = 3;	//选择用户类型
	public static final int LOGIN_DOUBLEUSER = 4;	//用户名重复了
	public static final int LOGIN_UNACTIVE = 5;	//学生帐号未激活
	public static final int LOGIN_COUNT = 6;	//学生帐号未激活
	
	private int code = FAILURE;
	private String message;
	private Object object;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String toJsonString(){
		return JSONObject.fromObject(this).toString();
	}
}