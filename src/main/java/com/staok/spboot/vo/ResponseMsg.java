package com.staok.spboot.vo;
/**
 * 消息响应
 * @author xiejs
 *
 */
public class ResponseMsg {
	
	private String code;
	private String desc;
	private Object ctx;
	
	public ResponseMsg(){}

	public ResponseMsg(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public ResponseMsg(String code,String desc,Object ctx){
		this.code = code;
		this.desc = desc;
		this.ctx = ctx;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Object getCtx() {
		return ctx;
	}
	public void setCtx(Object ctx) {
		this.ctx = ctx;
	}
	
}
