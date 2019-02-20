package com.hdlend.myblog.comtroller;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdlend.myblog.entity.Blog;
import com.hdlend.myblog.service.BlogService;

@Controller
public class HandleBlogController {
	
	@Autowired
	BlogService blogService;

	@RequestMapping(value="/addBlog")  
	public String addBlog(Blog blog) {
		blogService.saveBlog(blog);
		return "thymeleaf/demo";
	}
	@RequestMapping(value="/deleteBlog") 
	@ResponseBody
	public Map<String,Object> deleteBlog(HttpServletRequest req) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "1");
		try {
			String bId = req.getParameter("id");
			blogService.deleteBlog(bId);
		} catch (Exception e) {
			map.put("status", "0");
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
	
}
