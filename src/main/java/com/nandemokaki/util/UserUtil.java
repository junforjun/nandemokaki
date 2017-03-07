package com.nandemokaki.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.nandemokaki.model.UserInfo;

public class UserUtil {
	public static UserInfo getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserInfo user = new UserInfo();
		user.userId = auth.getName();

		return user;

	}

}
