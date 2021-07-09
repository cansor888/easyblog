package com.blog.realm;

import com.blog.entity.Blogger;
import com.blog.service.BloggerService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {
    @Resource
    private BloggerService bloggerService;

    //验证权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //验证身份(登录)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            //得到当前用户名
            String userName = (String) token.getPrincipal();
            Blogger blogger = bloggerService.findBloggerByUserName(userName);
            //对象不为空，表示存在此人，用户名验证通过
            if(blogger!=null){
                //创建验证身份的对象
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(blogger.getUserName(),blogger.getPassword(),"");
                return authenticationInfo;//登录成功
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //登录失败
        return null;
    }
}
