package com.hdlend.myblog.dao.impl;



import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.hdlend.myblog.common.IgnoreCaseResultTransformer;
import com.hdlend.myblog.dao.BaseDao;


public class BaseDaoImpl<T,ID> implements BaseDao<T,ID>{
	
	@Autowired
	private EntityManager em;

	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> List<T> findListBySql(String sql,Class<T> cls) {
		/*return em.createNativeQuery(sql).unwrap(NativeQueryImpl.class)
		.setResultTransformer(Transformers.aliasToBean(cls)).getResultList();*/
		return em.createNativeQuery(sql).unwrap(NativeQueryImpl.class)
		.setResultTransformer(new IgnoreCaseResultTransformer((cls))).getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findListMapBySql(String sql) {
		// TODO Auto-generated method stub
		 return em.createNativeQuery
				(sql).unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.getResultList();
	}
	@Override
	public T findList() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
