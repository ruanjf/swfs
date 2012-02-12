package com.ruanjf.springMVC.security;

import javax.xml.registry.infomodel.User;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {
	
	/**
	 * 授权信息 
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username=(String)principals.fromRealm(getName()).iterator().next();  
        if( username != null ){  
//            User user = accountManager.get( username );  
//            if( user != null && user.getRoles() != null ){  
//                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
//                for( SecurityRole each: user.getRoles() ){
//                        info.addRole(each.getName());  
//                        info.addStringPermissions(each.getPermissionsAsString());  
//                }  
//                return info;  
//            }  
        }  
        return null;  
	}

	/** 
	 * 认证信息 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
        String userName = token.getUsername();  
        if( userName != null && !"".equals(userName) ){  
//            User user = accountManager.login(token.getUsername(),  
//                            String.valueOf(token.getPassword()));  
//  
//            if( user != null )  
//                return new SimpleAuthenticationInfo(  
//                            user.getLoginName(),user.getPassword(), getName());  
        }  
        return null; 
	}

}
