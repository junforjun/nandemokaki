package com.nandemokaki.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.nandemokaki.model.Inbox;
import com.nandemokaki.model.QInbox;
import com.nandemokaki.model.UserInfo;
import com.nandemokaki.service.MailService;

@RestController
public class MailController {
	@Autowired
	private EntityManager em;

	@Autowired
	private MailService mailService;

	@RequestMapping(value = "/login/getmail", method = RequestMethod.GET)
	@ResponseBody
	public String getMail(HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		UserInfo user = new UserInfo();
		user.userId = auth.getName();


		JPQLQuery query = new JPAQuery(em);

		QInbox inbox = QInbox.inbox;
// 		List<Inbox> InboxList = query.from(inbox).where(inbox.repositoryName.eq(auth.getName())).orderBy(inbox.lastUpdated.desc()).list(inbox);
 		List<Inbox> InboxList = query.from(inbox).where(inbox.repositoryName.eq("test2")).orderBy(inbox.lastUpdated.desc()).list(inbox);

 		InboxList.forEach(v -> System.out.println(v.messageBody));

// 		MimeMessage m = new MimeMessage();
//		if(email == null) {
//			throw new Exception();
//		}



		return  new Gson().toJson(mailService.fetchMail(user));

	}

}
