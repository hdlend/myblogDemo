package com.hdlend.myblog.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hdlend.myblog.dao.BlogDao;
import com.hdlend.myblog.dao.StudentInfo;
import com.hdlend.myblog.entity.AppStudent;
import com.hdlend.myblog.entity.Blog;
import com.hdlend.myblog.entity.Student;

@Service
@Transactional
public class BlogService {

	@Autowired
	BlogDao blogDao;
//	@Autowired
//	EntityManager em;
	
	public void saveBlog(Blog blog) {
		Calendar cal = Calendar.getInstance();
		blog.setbAddDate(cal.getTime());
		blogDao.save(blog);
	}
	public void deleteBlog(String bId) {
		int id = Integer.parseInt(bId);
		blogDao.deleteById(id);
	}
	public Blog findBlogById(String bId) {
		int id = Integer.parseInt(bId);
		return blogDao.findByBId(id);
	}
	
	public Page<Blog>  findBlogPageList(int page,String keyboard){
		 Sort sort = new Sort(Direction.DESC, "bId");
		 Pageable pageable =  PageRequest.of(page-1, 10, sort);
	  //   Page<Blog> pages= blogDao.findAll(pageable);
		 Page<Blog> pages = blogDao.findAll(new Specification<Blog>() {
			
			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				 List<Predicate> list = new ArrayList<Predicate>();
				 if(null != keyboard && !"".equals(keyboard)) {
					 Predicate predicate =criteriaBuilder.like(root.get("bTitle").as(String.class),"%"+keyboard+"%");
					 list.add(predicate);
				 }
				 Predicate[] p = new Predicate[list.size()];
	             return criteriaBuilder.and(list.toArray(p));
			}
		}, pageable);
	     return pages;
	}
/*	@SuppressWarnings("unchecked")
	public List<Student> findStudentList(){
		String sql = "SELECT s.id AS studentId,c.id AS classId,c.class_name AS className,"
				+ "c.teacher_name AS teacherName,"
				+ "s.name,s.age FROM app_student s,app_class c where s.class_id=c.id";
		return em.createNativeQuery
				(sql).unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(Student.class))
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findStudentListMap(){
		String sql = "SELECT s.*,c.id as c_id,c.class_name AS className,c.teacher_name AS teacherName FROM app_student s,app_class c where s.class_id=c.id";
		return em.createNativeQuery
				(sql).unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.getResultList();
	}*/
	public List<AppStudent> findBySql(String sql) {
		return blogDao.findListBySql(sql, AppStudent.class);
	}
	public List<Map<String,Object>> findListMap(String sql){
		return blogDao.findListMapBySql(sql);
	}
	
	public List<StudentInfo> findStudents(){
		 Sort sort = new Sort(Direction.DESC, "id");
		 Pageable pageable =  PageRequest.of(0, 1, sort);
		 Page<StudentInfo> pages= blogDao.findStudentList(pageable);
		 return pages.getContent();
	}
}
