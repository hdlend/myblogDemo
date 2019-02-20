package com.hdlend.myblog.comtroller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;


@Controller
public class LoginController {
	
	@RequestMapping(value="/")  
	public String login() {
		System.out.println("123");
		return "thymeleaf/login";
	}

	@RequestMapping(value="/login")  
	public String doLogin(HttpServletRequest request, HttpServletResponse response)  {
		
		String userName=request.getParameter("uname");
		String pass=request.getParameter("upass");

		String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY); 
		String captcha = "";
		//验证码校验
		try{
			 captcha = request.getParameter("captcha").trim();
		}catch(NullPointerException e){
			return "redirect:/login.html#timeout";
		}
		if(!captcha.equals(code)){
			
			return "redirect:/login.html#captcha";
		}
		//用户信息校验
		   
	    try {
	        Subject currentUser = SecurityUtils.getSubject();
	        String passMd5 = new Md5Hash(pass).toString().toUpperCase();
	        UsernamePasswordToken token = new UsernamePasswordToken(userName,passMd5);
	        currentUser.login(token);
	    } catch (UnknownAccountException ex) {//用户名没有找到  
	        System.out.println("用户名没有找到");
	        ex.printStackTrace();  
	        return "redirect:thymeleaf/login.html#information";  
	    } catch (IncorrectCredentialsException ex) {//用户名密码不匹配  
	        System.out.println("用户名密码不匹配");
	        
	        ex.printStackTrace();  
	        return "redirect:thymeleaf/login.html#information";  
	    }catch (AuthenticationException e) {//其他的登录错误  
	        System.out.println("其他的登录错误");
	        e.printStackTrace();
	        return "redirect:thymeleaf/login.html#information";
	    }
	    
		return "thymeleaf/index";
	}
	
}
