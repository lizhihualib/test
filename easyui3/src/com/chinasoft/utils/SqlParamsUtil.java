package com.chinasoft.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * sql语句多条件或者不定条件的工具类
 * @author Administrator
 *
 */
public class SqlParamsUtil {
	/**
	 * 查询数量
	 */
	public final static int TYPE_COUNT=1;
	/**
	 * 分页查询
	 */
	public final static int TYPE_LIMIT=0;
	private String baseSql;
	private String templet = " AND %s %s ?";
	private ArrayList<Paramters>  paramters=new ArrayList<Paramters>();
	
	/**
	 * 设置基本的，无条件的查询语句
	 * @param baseSql
	 */
	public void setBaseSql(String baseSql) {
		this.baseSql = baseSql;
	}
	/**
	 * 添加要查询的条件
	 * @param p
	 */
	public void addParamters(Paramters p){
		if(p.getValue() != null && !p.getValue().equals("") 
				&& !p.getValue().equals("null") && !p.getValue().equals("0")){
			if(p.getOprator().equals("like")){				
					p.setValue("%"+p.getValue()+"%");
					paramters.add(p);
							
			}else {
				paramters.add(p);
			}
		}	
	}
	/**
	 * 得到一个将要预编译的sql语句
	 * @return
	 */
	public String getSql(int type){
		StringBuffer sb=new StringBuffer(baseSql);
		int index=0;
		for (Paramters p : paramters) {
			if(index==0){
				sb.append(" where ").append(String.format(" %s %s ?",p.getCloum(),p.getOprator()));
			}else {
				sb.append(String.format(" AND %s %s ?",p.getCloum(),p.getOprator()));
			}
			index++;
		}
		if(type==TYPE_LIMIT)
			sb.append(" limit ?,?");
		
		return sb.toString();
	}
	/**
	 * 
	 * 为预编译sql语句中的问号赋值
	 *分页
	 * @param pst
	 * @param start 从哪一条开始
	 * @param pageSize 取多少条
	 * @throws SQLException 
	 */
	public void setPst(PreparedStatement pst,int start,int pageSize) throws SQLException{
		int index=1;
		for (Paramters p : paramters) {
			pst.setObject(index++, p.getValue());	
		}
		pst.setObject(index++,start);
		pst.setObject(index++,pageSize);
			
	}
	/**
	 * 
	 * 为预编译sql语句中的问号赋值
	 * 不分页
	 * @param pst
	 * @throws SQLException 
	 */
	public void setPst(PreparedStatement pst) throws SQLException{
		int index=1;
		for (Paramters p : paramters) {
			pst.setObject(index++, p.getValue());	
		}
			
	}
}
