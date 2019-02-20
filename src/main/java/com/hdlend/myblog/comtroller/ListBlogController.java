package com.hdlend.myblog.comtroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdlend.myblog.entity.Blog;
import com.hdlend.myblog.service.BlogService;

@Controller
public class ListBlogController {
	@Autowired
	BlogService blogService;
	@RequestMapping("/listBlog")
	public String listBlog(HttpServletRequest request) {
		int currPage=1;
		String page = request.getParameter("page");
		if(page!=null && !"".equals(page)) {
			currPage=Integer.parseInt(page);
		}
		String keyboard = request.getParameter("keyboard");
		Page<Blog> pages = blogService.findBlogPageList(currPage,keyboard);
		request.setAttribute("list", pages.getContent());
		request.setAttribute("currPage", currPage);
		request.setAttribute("totalPages", pages.getTotalPages());
		request.setAttribute("keyboard", keyboard);
		return "thymeleaf/admin/blogList";
	}
}
