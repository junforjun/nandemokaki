package com.nandemokaki.service.impl;

import static com.nandemokaki.model.QUserInfo.*;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.nandemokaki.model.Login;
import com.nandemokaki.model.QUserAuthentication;
import com.nandemokaki.model.UserAuthentication;
import com.nandemokaki.model.UserInfo;
import com.nandemokaki.model.Users;
import com.nandemokaki.model.db.UserAuthentication_DB;
import com.nandemokaki.model.db.UserInfo_DB;
import com.nandemokaki.model.db.Users_DB;
import com.nandemokaki.service.UserService;
import com.nandemokaki.util.StrUt;

@Service
public class UserServiceImpl implements UserService {

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private EntityManager em;

	@Autowired
	private UserInfo_DB userInfoDb;

	@Autowired
	private UserAuthentication_DB userAuthenticationDb;

	@Autowired
	private Users_DB UsersDb;

	@Override
	public UserDetails loadUserByUsername(String username)  {
		UserInfo user = readUser(username);

		Login userDetails = new Login();
		userDetails.setUsername(username);
		userDetails.setPassword(user.userPass);

		System.out.println(user.userPass);

		try {
			System.out.println(StrUt.digestString(user.userPass));
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		UserInfo user =  new JPAQuery(em).from(userInfo).where(userInfo.userId.eq(username))
				.uniqueResult(userInfo);

		return user;
	}

	@Override
	@Transactional
	public void createUser(Login user) throws Exception {

		UserInfo userInfo = new UserInfo();

		userInfo.userId = user.getUsername();
		userInfo.userPass = passwordEncoder.encode(user.getPassword());

		userInfoDb.save(userInfo);

		UserAuthentication ua = new UserAuthentication();
		ua.userId = user.getUsername();
		ua.userAuth = "1";

		userAuthenticationDb.save(ua);

		Users mailAccount = new Users();
		mailAccount.username = user.getUsername();
		mailAccount.pwdhash = StrUt.digestString(userInfo.userPass);
		mailAccount.pwdalgorithm = "SHA";
		mailAccount.useforwarding = 0;
		mailAccount.usealias = 0;

		UsersDb.save(mailAccount);
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
