package com.nandemokaki.service;

import org.springframework.stereotype.Service;

import com.nandemokaki.model.MailContent;
import com.nandemokaki.model.UserInfo;

@Service
public interface MailService {
	public MailContent fetchMail(UserInfo user);

	public void deleteMail(UserInfo user);

	public String sendMail(MailContent mail);
}
