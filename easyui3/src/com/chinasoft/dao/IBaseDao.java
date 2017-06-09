package com.chinasoft.dao;

import java.util.List;

import com.chinasoft.query.Page;

public interface IBaseDao<B> {
	List<B> queryAll(Page p);
	int queryAllCount(Page p);
	boolean delete(String ids);
	boolean update(B b);
	boolean add(B b);
	
}
