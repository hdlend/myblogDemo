package com.hdlend.myblog.comtroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdlend.myblog.entity.Blog;
import com.hdlend.myblog.service.BlogService;

@Controller
public class ViewBlogController {
	
	@Autowired
	BlogService blogService;

	@RequestMapping(value="/viewBlog")  
	public String view(HttpServletRequest req) {
		String bId = req.getAttribute("bId").toString();
		Blog blog = blogService.findBlogById(bId);
		if(blog==null) {
			return "forward:/error/404.html";
		}
		req.setAttribute("blog", blog);
		return "thymeleaf/model";
	}
	
	@RequestMapping(value="/addBlogView")  
	public String addBlog(Blog blog) {
		
		return "thymeleaf/demo";
	}

}
