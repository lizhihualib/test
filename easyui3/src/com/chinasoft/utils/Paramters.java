package com.chinasoft.utils;

/**
 * sql语句查询条件对象
 * @author Administrator
 *
 */
public class Paramters {
	/**
	 * 条件的列名
	 */
	private String cloum;
	/**
	 * 操作  =、>=、 > 、< 、<=、 like
	 */
	private String oprator;
	/**
	 * 条件的值
	 */
	private String value;
	
	public Paramters() {
		super();
	}
	/**
	 * 实例化sql语句查询条件对象
	 * @param cloum 条件的列名
	 * @param oprator 操作  =、>=、 > 、< 、<=、 like
	 * @param value 条件的值
	 */
	public Paramters(String cloum, String oprator, String value) {
		super();
		this.cloum = cloum;
		this.oprator = oprator;
		this.value = value;
	}
	
	
	public String getCloum() {
		return cloum;
	}
	public void setCloum(String cloum) {
		this.cloum = cloum;
	}
	public String getOprator() {
		return oprator;
	}
	public void setOprator(String oprator) {
		this.oprator = oprator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
