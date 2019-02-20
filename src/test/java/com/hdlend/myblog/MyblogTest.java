package com.hdlend.myblog;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.hdlend.myblog.dao.BlogDao;
import com.hdlend.myblog.dao.RoleDao;
import com.hdlend.myblog.dao.StudentInfo;
import com.hdlend.myblog.entity.Role;
import com.hdlend.myblog.service.BlogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	BlogDao blogDao;
	@Autowired
	private BlogService blogService;
	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void list() {
	//	List<StudentInfo> stus = blogService.findStudents();
		// Sort sort = new Sort(Direction.DESC, "id");
		 Pageable pageable =  PageRequest.of(0, 1);
		 Page<StudentInfo> pages= blogDao.findStudentList(pageable);
		 List<StudentInfo> stus = pages.getContent();
		 for(StudentInfo stu :stus) {
			System.out.println(stu.getId());
			System.out.println(stu.getName());
			System.out.println(stu.getAge());
			System.out.println(stu.getClassName());
		}
	}
	
	/*@Test
	public void save() {
		Role role = new Role();
	//	role.setrId((long)234);
		role.setrName("admin");
		roleDao.save(role);
	//	System.out.println(roleDao.findAll());
		//logger.info(msg);
	}*/
	@Test
	public void testList() {
		String sql = "SELECT s.id AS studentId,c.id AS classId,c.class_name AS className,"
				+ "c.teacher_name AS teacherName,"
				+ "s.name,s.age FROM app_student s,app_class c where s.class_id=c.id";
		sql = "select s.*,c.class_name,c.teacher_name from app_student s,app_class c where s.class_id=c.id";
		System.out.println(blogService.findBySql(sql));
		
	}

}
