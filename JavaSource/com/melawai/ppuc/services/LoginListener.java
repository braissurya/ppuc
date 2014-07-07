package com.melawai.ppuc.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Email;




@Service
public class LoginListener implements ApplicationListener<AbstractAuthenticationEvent> {

    private UserManager userManager;
    private HttpServletRequest httpServletRequest;
    
    @Autowired
    private Email email;

    @Autowired
    public void setUserManager(UserManager userManager) {
	this.userManager = userManager;
    }

    @Autowired
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
	this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {

	if (event instanceof AuthenticationSuccessEvent) {

	    String username = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
	    User user = userManager.getUserByUsername(username);
	  
	    userManager.audittrail(Audittrail.Activity.LOGIN, Audittrail.LoginType.SUCCESS, User.class.getSimpleName(), username, CommonUtil.getIpAddr(httpServletRequest), "LOGIN SUCCESS", user, null);

	} else if (event instanceof AuthenticationFailureBadCredentialsEvent) {

	    UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken) ((AuthenticationFailureBadCredentialsEvent) event).getSource();
	    String username = (String) upat.getPrincipal();
	    
	    User user = null;
	    if (username != null && username.trim().length() > 0) {
		try {
		    user = userManager.getUserByUsername(username);
		} catch (UsernameNotFoundException unfe) {
		}
	    }
		
	    
	    userManager.audittrail(Audittrail.Activity.LOGIN, Audittrail.LoginType.FAILED, User.class.getSimpleName(), username, CommonUtil.getIpAddr(httpServletRequest), "LOGIN FAILED", user, null);
	}
    }

}

