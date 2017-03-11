package com.nandemokaki.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nandemokaki.model.MailContent;
import com.nandemokaki.model.UserInfo;

@Service
public interface MailService {
	public List<MailContent> fetchMail(UserInfo user) throws Exception;

	public void deleteMail(UserInfo user);

	public String sendMail(MailContent mail, UserInfo user);
}
