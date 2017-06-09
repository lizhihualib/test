package com.chinasoft.service;

import java.util.List;

import com.chinasoft.query.Page;

public interface IBaseService<B> {
	List<B> queryAll(Page p);
	int queryAllCount(Page p);
	boolean delete(String ids);
	boolean update(B b);
	boolean add(B b);
}
