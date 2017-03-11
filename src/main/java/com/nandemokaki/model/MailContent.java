package com.nandemokaki.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MailContent implements Serializable {
	public int mailId;
	public String from;
	public List<String> to = new ArrayList<>();
	public List<String> cc = new ArrayList<>();


	public String subject;
	public String contentType;
	public String date;
	public String message;

	public String isRead;
	public String readTime;
	@Override
	public String toString() {
		return "MailContent [mailId=" + mailId + ", from=" + from + ", to=" + to + ", cc=" + cc + ", subject=" + subject
				+ ", contentType=" + contentType + ", date=" + date + ", message=" + message + ", isRead=" + isRead
				+ ", readTime=" + readTime + "]";
	}
	public int getMailId() {
		return mailId;
	}
	public void setMailId(int mailId) {
		this.mailId = mailId;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public List<String> getTo() {
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}
	public List<String> getCc() {
		return cc;
	}
	public void setCc(List<String> cc) {
		this.cc = cc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String getReadTime() {
		return readTime;
	}
	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}
}

