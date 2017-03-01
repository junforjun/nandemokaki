package com.nandemokaki.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.nandemokaki.model.EmailAck;
import com.nandemokaki.model.QEmailAck;
import com.nandemokaki.util.FileUtil;
import com.nandemokaki.util.StrUt;



@RestController
public class EmailAckController {

	@Autowired
	private EntityManager em;

	@RequestMapping(value = "/auth/{jpgNm}.jpg", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	@Transactional
	public byte[] testphoto(@PathVariable("jpgNm") String jpgNm) throws IOException {

		File f = null;
		if (!StrUt.isNumber(jpgNm)) {
			f = new File("/data/upload/" + jpgNm + ".jpg");
		} else {
			JPQLQuery  query = new JPAQuery(em);

			QEmailAck emailAck = QEmailAck.emailAck;
			EmailAck email = null;
			try {
				email = query.from(emailAck).where(emailAck.sendNo.eq(Integer.parseInt(jpgNm)))
						.uniqueResult(emailAck);

				if(email == null) {
					throw new Exception();
				}
			} catch (Exception e) {
				return null;
			}

			if(email.recvTime == null) {
				new JPAUpdateClause(em, emailAck).where(emailAck.sendNo.eq(Integer.parseInt(jpgNm)))
						.set(emailAck.recvCnt, email.recvCnt + 1)
						.set(emailAck.recvTime, Timestamp.valueOf(LocalDateTime.now()))
						.execute();;
			} else {
				new JPAUpdateClause(em, emailAck).where(emailAck.sendNo.eq(Integer.parseInt(jpgNm)))
						.set(emailAck.recvCnt, email.recvCnt + 1)
						.execute();;
			}

			f = email != null ? new File("/data/upload/" + email.motoJpgNm + ".jpg") : null;
		}

	    return FileUtil.inputStreamToByteArray(new FileInputStream(f));
	}
}
