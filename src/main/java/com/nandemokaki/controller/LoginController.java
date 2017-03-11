package com.nandemokaki.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.nandemokaki.config.AuthenticationToken;
import com.nandemokaki.model.Login;
import com.nandemokaki.model.UserInfo;
import com.nandemokaki.service.UserService;

@Controller

public class LoginController {

	@Autowired UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@RequestMapping(value="/login", method=RequestMethod.POST,produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String login(Login login, HttpSession session) {

		UserInfo user = null;
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());

			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
			          SecurityContextHolder.getContext());

			user = userService.readUser(login.getUsername());
		} catch (AuthenticationException e) {
			return null;
		}

        return new Gson().toJson(new AuthenticationToken(user.userId, userService.getAuthorities(user.userId), session.getId()));


	}
}
