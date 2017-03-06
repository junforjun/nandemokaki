package com.nandemokaki.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MailContent implements Serializable {
	public String from;
	public List<String> to = new ArrayList<>();
	public List<String> cc = new ArrayList<>();


	public String subject;
	public String contentType;
	public String date;
	public String message;

	@Override
	public String toString() {
		return "MailContent [from=" + from + ", to=" + to + ", cc=" + cc + ", subject=" + subject + ", contentType="
				+ contentType + ", date=" + date + ", message=" + message + "]";
	}
}

