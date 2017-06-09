package com.chinasoft.service.impl;

import java.util.List;

import com.chinasoft.bean.User;
import com.chinasoft.dao.IBaseDao;
import com.chinasoft.dao.impl.UserDaoImpl;
import com.chinasoft.query.Page;
import com.chinasoft.query.UserQuery;
import com.chinasoft.service.IBaseService;

public class UserServiceImpl implements IBaseService<User>{

	@Override
	public List<User> queryAll(Page uq) {
		IBaseDao<User> baseDao=new UserDaoImpl();
		return baseDao.queryAll(uq);
	}

	@Override
	public boolean delete(String ids) {
		IBaseDao<User> baseDao=new UserDaoImpl();
		return baseDao.delete(ids);
	}

	@Override
	public boolean update(User b) {
		IBaseDao<User> baseDao=new UserDaoImpl();
		return baseDao.update(b);
	}

	@Override
	public boolean add(User b) {
		IBaseDao<User> baseDao=new UserDaoImpl();
		return baseDao.add(b);
	}

	@Override
	public int queryAllCount(Page p) {
		IBaseDao<User> baseDao=new UserDaoImpl();
		return baseDao.queryAllCount(p);
	}

}
