package com.nandemokaki.service.impl;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nandemokaki.model.MailContent;
import com.nandemokaki.model.UserInfo;
import com.nandemokaki.service.MailService;
import com.nandemokaki.util.DateUtil;
import com.nandemokaki.util.StrUt;

@Service
public class MailServiceImpl implements MailService {

	@Value("${mail.store.protocol}")
    private String protocol;

	@Value("${mail.pop3.host}")
    private String host;

	@Value("${mail.pop3.port}")
    private String port;

	@Value("${mail.pop3.starttls.enable}")
    private String starttls;


	@Override
	public List<MailContent> fetchMail(UserInfo user) throws Exception {
		List<MailContent> mailList = new ArrayList<>();

		try {
			// create properties field
			Properties properties = new Properties();
			properties.put("mail.store.protocol", protocol);
			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", port);
			properties.put("mail.pop3.starttls.enable", starttls);

			Session emailSession = Session.getDefaultInstance(properties);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3");

			store.connect(host, user.userId, user.userPass);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			// System.out.println("messages.length---" + messages.length);


			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];

				MailContent mail = new MailContent();

				writePart(message, mail);

//				message.writeTo(System.out);

				// message.writeTo(System.out);
				System.out.println(mail.toString());

				mailList.add(mail);
			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		Collections.reverse(mailList);

		return mailList;
	}

	@Override
	public void deleteMail(UserInfo user) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public String sendMail(MailContent mc, UserInfo user) {

		String sender_addr = user.userId + "@localhost";
		String sender_nm = user.userId + "@localhost";
		if(StrUt.isEmpty(mc.subject)) {
			mc.subject = "no title";
		}

		try {
			Properties props = new Properties();

			props.put("mail.smtp.host", host);
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender_addr, sender_nm, "utf-8"));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mc.to));

			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mc.cc));

			if(StrUt.isEmpty(mc.bcc)) {
				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(mc.bcc));
			}

			message.setSubject(mc.subject, "utf-8");

			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();

//				messageBodyPart.setText(content, "utf-8");
			 messageBodyPart.setContent(mc.message, "text/html;charset=utf-8");

			multipart.addBodyPart(messageBodyPart);
			message.setSentDate(new java.util.Date());
			message.setContent(multipart);

			Transport transport = session.getTransport("smtp");

			Transport.send(message);
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}

		return "1";
	}

	public void fetch(String pop3Host, String storeType, String user, String password) {

	}

	/**
	 * This method checks for content-type based on which, it processes and
	 * fetches the content of the message
	 */
	public void writePart(Part p, MailContent mail) throws Exception {

		if (p instanceof Message) {
			writeEnvelope((Message) p, mail);

		}

		// check if the content is plain text
		if (p.isMimeType("text/plain")) {
			mail.message = (String) p.getContent();
		}
		// check if the content has attachment
		else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();

			int count = mp.getCount();

			for (int i = 0; i < count; i++) {
				writePart(mp.getBodyPart(i), mail);
			}

		}
		// check if the content is a nested message
		else if (p.isMimeType("message/rfc822")) {
			// System.out.println("This is a Nested Message");
			// System.out.println("---------------------------");
			writePart((Part) p.getContent(), mail);
		}
		// check if the content is an inline image
		else if (p.isMimeType("image/jpeg")) {
			// System.out.println("--------> image/jpeg");
			Object o = p.getContent();

			InputStream x = (InputStream) o;
			// Construct the required byte array
			int i = 0;
			byte[] bArray = new byte[x.available()];

			// System.out.println("x.length = " + x.available());
			while ((i = (int) ((InputStream) x).available()) > 0) {
				int result = (int) (((InputStream) x).read(bArray));
				if (result == -1)

					break;
			}
			FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
			f2.write(bArray);
		} else if (p.getContentType().contains("image/")) {
			// System.out.println("content type" + p.getContentType());
			File f = new File("image" + new Date().getTime() + ".jpg");
			DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
			com.sun.mail.util.BASE64DecoderStream test = (com.sun.mail.util.BASE64DecoderStream) p.getContent();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = test.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
		} else {
			Object o = p.getContent();
			if (o instanceof String) {
				mail.message = (String) p.getContent();
				// System.out.println("This is a string");
				// System.out.println("---------------------------");
				// System.out.println((String) o);
			} else if (o instanceof InputStream) {
				// System.out.println("This is just an input stream");
				// System.out.println("---------------------------");
				InputStream is = (InputStream) o;
				is = (InputStream) o;
				int c;
				while ((c = is.read()) != -1)
					System.out.write(c);
			} else {
				// System.out.println("This is an unknown type");
				// System.out.println("---------------------------");
				// System.out.println(o.toString());
			}
		}

	}

	/*
	 * This method would print FROM,TO and SUBJECT of the message
	 */
	public void writeEnvelope(Message m, MailContent mail) throws Exception {

		mail.contentType = m.getContentType().split(";")[0];

		if (m.getSentDate() != null) {
			mail.date = DateUtil.dateToStr(m.getSentDate());
		}

		Address[] a;

		// FROM
		mail.from = ((InternetAddress) m.getFrom()[0]).getAddress().toString();

		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++) {
				mail.to = String.join(", ", a[j].toString());
			}
		}

		// SUBJECT
		if (m.getSubject() != null) {
			mail.subject = m.getSubject();
		}

		// ReplyTo
		if ((a = m.getRecipients(Message.RecipientType.CC)) != null) {
			for (int j = 0; j < a.length; j++) {
				mail.cc =  String.join(", ", a[j].toString());
			}
		}

		mail.mailId = m.getMessageNumber();



	}

}
