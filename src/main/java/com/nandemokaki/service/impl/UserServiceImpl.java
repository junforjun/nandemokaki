package com.nandemokaki.service.impl;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.nandemokaki.model.Login;
import com.nandemokaki.model.QUserAuthentication;
import com.nandemokaki.model.QUserInfo;
import com.nandemokaki.model.UserAuthentication;
import com.nandemokaki.model.UserInfo;
import com.nandemokaki.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = readUser(username);

		Login userDetails = new Login();
		userDetails.setUsername(username);
		userDetails.setPassword(user.userPass);

		System.out.println(userDetails.getPassword());

		userDetails.setAccountNonExpired(true);
		userDetails.setAccountNonLocked(true);
		userDetails.setCredentialsNonExpired(true);
		userDetails.setEnabled(true);

		userDetails.setAuthorities(getAuthorities(username));

		return userDetails;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities(String username) {
		JPQLQuery  query = new JPAQuery(em);

		QUserAuthentication userAuth = QUserAuthentication.userAuthentication;
		UserAuthentication user = query.from(userAuth).where(userAuth.userId.eq(username))
				.uniqueResult(userAuth);

		HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority(user.userAuth));

        return authorities;

	}

	@Override
	public UserInfo readUser(String username) {
		JPQLQuery query = new JPAQuery(em);

		QUserInfo userInfo = QUserInfo.userInfo;

		UserInfo user = query.from(userInfo).where(userInfo.userId.eq(username))
				.uniqueResult(userInfo);

		return user;
	}

	@Override
	public void createUser(UserInfo user) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteUser(String username) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public PasswordEncoder passwordEncoder() {
		return this.passwordEncoder;
	}


}
