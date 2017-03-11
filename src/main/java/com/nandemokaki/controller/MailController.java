package com.nandemokaki.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mysema.query.jpa.impl.JPAQuery;
import com.nandemokaki.model.MailContent;
import com.nandemokaki.model.QInbox;
import com.nandemokaki.model.UserInfo;
import com.nandemokaki.service.MailService;
import com.nandemokaki.util.DateUtil;
import com.nandemokaki.util.UserUtil;

@RestController
public class MailController {
	@Autowired
	private EntityManager em;

	@Autowired
	private MailService mailService;

	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public ModelAndView mailMain(ModelAndView mov, HttpSession session) throws Exception {

		UserInfo user = UserUtil.getUser();

		QInbox inbox = QInbox.inbox;
		long mailcnt = new JPAQuery(em).from(inbox).where(inbox.repositoryName.eq("test2")).orderBy(inbox.lastUpdated.desc()).count();


		List<MailContent> mc = mailService.fetchMail(UserUtil.getUser());

		mc.forEach(v -> {
			v.isRead = "○";
			v.readTime = DateUtil.dateToStr(new Date());
		});

		mov.addObject("mailcnt", mailcnt);
		mov.addObject("mailList", mc);

		session.setAttribute(user.userId, mc);
		session.setAttribute("test2", mc);

		return mov;
	}

	@RequestMapping(value = "/mail/get", method = RequestMethod.GET)
	@ResponseBody
	public String getMail(HttpSession session) throws Exception {
		return new Gson().toJson(mailService.fetchMail(UserUtil.getUser()));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mail/getDetail", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getMailDetail(@RequestParam String mailId, ModelAndView mov, HttpSession session) throws Exception {

		UserInfo user = UserUtil.getUser();

		List<MailContent> mailList = (List<MailContent>)session.getAttribute(user.userId);

		MailContent mailContenct = mailList.stream()
				.filter(v -> v.mailId == (Integer.parseInt(mailId)))
				.findFirst().get();

		mov.addObject("mailDetail", mailContenct);

		System.out.println("getDetail");

		return mov;
	}
}
