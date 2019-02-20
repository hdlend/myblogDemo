package com.hdlend.myblog.config;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.hdlend.myblog.entity.User;
import com.hdlend.myblog.service.UserService;

public class MyShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	 @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	        return authorizationInfo;
	    }

	    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
	    @Override
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
	            throws AuthenticationException {
	        //获取用户的输入的账号.
	        String username = (String)token.getPrincipal();
	        //通过username从数据库中查找 User对象，如果找到，没找到.
	        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	        User user = userService.findUserByLoginName(username);
	        if(user != null){
	             //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息  
	            return new SimpleAuthenticationInfo(user.getuName(), user
	                    .getuPass(), getName());
	        }else{
	        	   //没有返回登录用户名对应的SimpleAuthenticationInfo对象时
	        	//就会在LoginController中抛出UnknownAccountException异常
	        	throw new UnknownAccountException();
	        }
	    }

}
