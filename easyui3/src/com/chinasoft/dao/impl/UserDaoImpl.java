package com.chinasoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.converters.SqlDateConverter;

import com.chinasoft.bean.User;
import com.chinasoft.dao.IBaseDao;
import com.chinasoft.query.Page;
import com.chinasoft.query.UserQuery;
import com.chinasoft.service.IBaseService;
import com.chinasoft.utils.DateUtil;
import com.chinasoft.utils.JdbcUtil;
import com.chinasoft.utils.Paramters;
import com.chinasoft.utils.SqlParamsUtil;

public class UserDaoImpl implements IBaseDao<User>{

	@Override
	public List<User> queryAll(Page page) {
		UserQuery uq=(UserQuery)page;
		
		Connection conn=JdbcUtil.getConn();
		String baseSql="select * from t_users ";
		System.out.println(uq.toString());
		
		SqlParamsUtil paramsUtil=new SqlParamsUtil();
		paramsUtil.setBaseSql(baseSql);
		paramsUtil.addParamters(new Paramters("username", "like", uq.getUsername()));
		paramsUtil.addParamters(new Paramters("realname", "like", uq.getRealname()));
		paramsUtil.addParamters(new Paramters("createDate", ">=", uq.getStartDate()));
		paramsUtil.addParamters(new Paramters("createDate", "<=", uq.getEndDate()));
		
	
		String sql=paramsUtil.getSql(SqlParamsUtil.TYPE_LIMIT);
		
		List<User> list=new ArrayList<User>();
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			
			paramsUtil.setPst(pst,uq.getStart(),uq.getPageSize());
			
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setUserpwd(rs.getString("userpwd"));
				user.setRealname(rs.getString("realname"));
				user.setSex(rs.getString("sex"));
				user.setCreateId(rs.getInt("createId"));
				user.setCreateDate(DateUtil.str2Date(rs.getString("createDate")));
				user.setUpdateId(rs.getInt("updateId"));
				user.setUpdateDate(DateUtil.str2Date(rs.getString("updateDate")));
				
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConn(conn);
		}
		
		return list;
	}

	@Override
	public boolean delete(String ids) {
		Connection conn=JdbcUtil.getConn();
		System.out.println(ids);
		String sql="delete from t_users where id in("+ids+")";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			int rows=pst.executeUpdate();
			if(rows>0)
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConn(conn);
		}
		
		return false;
	}

	@Override
	public boolean update(User b) {
		Connection conn=JdbcUtil.getConn();
		String sql="update t_users set userpwd=? where id=?";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, b.getUserpwd());
			pst.setInt(2, b.getId());
			int rows=pst.executeUpdate();
			if(rows>0)
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConn(conn);
		}
		return false;
	}

	@Override
	public boolean add(User b) {
		Connection conn=JdbcUtil.getConn();
		String sql="insert into t_users(username,userpwd,realname,sex,createDate,updateDate) values(?,?,?,?,now(),now())";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, b.getUsername());
			pst.setString(2, b.getUserpwd());
			pst.setString(3, b.getRealname());
			pst.setString(4, b.getSex());
			//pst.setString(5, DateUtil.date2Str(b.getCreateDate()));
			int rows=pst.executeUpdate();
			if(rows>0)
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConn(conn);
		}
		
		return false;
	}

	@Override
	public int queryAllCount(Page p) {
		UserQuery uq=(UserQuery)p;
		Connection conn=JdbcUtil.getConn();
		String baseSql="select count(id) from t_users";
		SqlParamsUtil paramsUtil=new SqlParamsUtil();
		paramsUtil.setBaseSql(baseSql);
		paramsUtil.addParamters(new Paramters("username", "like", uq.getUsername()));
		paramsUtil.addParamters(new Paramters("realname", "like", uq.getRealname()));
		paramsUtil.addParamters(new Paramters("createDate", ">=", uq.getStartDate()));
		paramsUtil.addParamters(new Paramters("createDate", "<=", uq.getEndDate()));
		
		String sql=paramsUtil.getSql(SqlParamsUtil.TYPE_COUNT);
		int count=0;
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			paramsUtil.setPst(pst);

			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.closeConn(conn);
		}
		
		return count;
	}


}
