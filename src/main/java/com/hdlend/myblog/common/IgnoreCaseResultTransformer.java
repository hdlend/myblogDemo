package com.hdlend.myblog.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.HibernateException;
import org.hibernate.transform.ResultTransformer;
 
/**
 * 
 * 修正hibernate返回自定义pojo类型时找不到属性的BUG
 * 主要发生在使用oracle时，查询返回的字段默认是大写的(除非SQL中指定了别名)，这导致返回自定义pojo类型时会报找不到属性的错误，该类用于修正此BUG。
 * 使用该类时SQL返回的字段名大小写或者带"_"都会被忽略，如数据库字段为 USER_NAME，自定义pojo的属性名为username就可以使用
 * 
 *
 */
public class IgnoreCaseResultTransformer implements ResultTransformer {
 
    private static final long serialVersionUID = -3779317531110592988L;
 
    private final Class<?> resultClass;
    private Field[] fields;
    private BeanUtilsBean beanUtilsBean;
 
    public IgnoreCaseResultTransformer(final Class<?> resultClass) {
        this.resultClass = resultClass;
        this.fields = this.resultClass.getDeclaredFields();
        beanUtilsBean=BeanUtilsBean.getInstance();
    }
 
    /**
     * aliases为每条记录的数据库字段名,ORACLE字段名默认为大写
     * tupe为与aliases对应的字段的值
     */
    public Object transformTuple(final Object[] tuple, final String[] aliases) {
        Object result;
		try {
			result = this.resultClass.newInstance();
		//	Map<String, String> columnsMap = getColumnAndField(resultClass);
			for (int i = 0; i < aliases.length; i++) {
				for (Field field : this.fields) {
					String fieldName = field.getName();
					//数据库字段带下划线的时候也能保证使用，如数据库字段为 USER_NAME，自定义pojo的属性名为username就可以使用
					if (fieldName.equalsIgnoreCase(aliases[i].replaceAll("_", ""))) {
						beanUtilsBean.setProperty(result, fieldName, tuple[i]);
						break;
					}
				}
				
				//修改当用@Column 属性名与数据库字段名不一样时取不到值
			/*	String columnKey = aliases[i].toUpperCase();//大写数据库字段名
				String propertyName = columnsMap.get(columnKey);// 尝试从columnsMap取出属性名
				if (propertyName != null) {// 如果有该属性则进行设值
					Object value =tuple[i];// 值
					if (value != null) {
						beanUtilsBean.setProperty(result, propertyName, tuple[i]);// 反射值
					}
				}*/
			}
		} catch (Exception e) {
			throw new HibernateException("Could not instantiate resultclass: " + this.resultClass.getName(), e);
		}
        return result;
    }
 
    @SuppressWarnings("rawtypes")
    public List transformList(final List collection) {
        return collection;
    }
	/**
	 * 获得持久化类的列名与属性名，列名大写
	 * 
	 * @param claz
	 * @return <列名,属性名>
	 */
	public  <T> Map<String, String> getColumnAndField(Class<T> claz) {
		Map<String, String> map = new HashMap<String, String>();
		if (claz != null) {
			Field[] fields = claz.getDeclaredFields();
			for (Field field : fields) {
				Column c = field.getAnnotation(Column.class);
				if (c != null &&  c.name() != null && c.name().length() > 0 && c.name().trim().length() > 0) {
					map.put(c.name().toUpperCase(), field.getName());
				} else {
					map.put(field.getName().toUpperCase(), field.getName());
				}
			}
		}
		return map;
	}
}