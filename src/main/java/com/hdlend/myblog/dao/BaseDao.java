package com.hdlend.myblog.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T,ID> {

	public T findList();
	
	public <T> List<T> findListBySql(String sql,Class<T> cls);
	
	public List<Map<String,Object>> findListMapBySql(String sql);
}
