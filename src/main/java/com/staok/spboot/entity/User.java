package com.staok.spboot.entity;


/**
 * 用户信息
 * @descript
 * @author xiejs
 * @createtime 2016年2月26日
 */
public class User implements java.io.Serializable{
	
	private static final long serialVersionUID = -8620953476801773076L;
	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String userPwd;
	/**
	 * 邮箱,用户找回密码
	 */
	private String email;
	/**
	 * 注册时间
	 */
	private String registerTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
}
