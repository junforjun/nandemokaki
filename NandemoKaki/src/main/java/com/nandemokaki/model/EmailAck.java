package com.nandemokaki.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * MODEL autogen macro
 * EmailAck
 * @auther KIM
*/
@Entity
@Table(name="EMAIL_ACK")
public class EmailAck implements Serializable {
	public static final String TABLE = "EMAIL_ACK";

	/** 送信番号 */
	@Id
	@Column(name = "SEND_NO")
	public Integer sendNo;

	/** 送信時刻 */
	@Column(name = "SEND_TIME")
	public Timestamp sendTime;

	/** 元JPGファイル名 */
	@Column(name = "MOTO_JPG_NM")
	public String motoJpgNm;

	/** 受信時刻 */
	@Column(name = "RECV_TIME")
	public Timestamp recvTime;

	/** 閲覧回数 */
	@Column(name = "RECV_CNT")
	public Integer recvCnt;

}