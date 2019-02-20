package com.hdlend.myblog.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.hdlend.myblog.entity.Blog;


public interface BlogDao extends JpaRepository<Blog,Integer>,JpaSpecificationExecutor<Blog>,BaseDao<Blog,Integer>{
	
	public Blog findByBId(int bId);
	
	/*@Query(value="SELECT s_0.id AS id,c.id AS classId,c.class_name AS className,"
			+ "c.teacher_name AS teacherName,"
			+ "s_0.name,s_0.age FROM app_student s_0,app_class c where s_0.class_id=c.id",nativeQuery=true)*/
	@Query(value="SELECT "
			+ "name,age FROM app_student as stu inner join app_class cla on stu.class_id=cla.id",
			countQuery="select count(*) from app_student ",nativeQuery=true)
	public Page<StudentInfo> findStudentList(Pageable pageable);
}
