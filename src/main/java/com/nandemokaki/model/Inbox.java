package com.nandemokaki.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
/**
 * MODEL autogen macro
 * Inbox
 * @auther KIM
*/
@Entity
@Table(name="INBOX")
@IdClass(InboxPK.class)
public class Inbox implements Serializable {
	public static final String TABLE = "INBOX";

	/** message_name */
	@Id
	@Column(name = "MESSAGE_NAME")
	public String messageName;

	/** repository_name */
	@Id
	@Column(name = "REPOSITORY_NAME")
	public String repositoryName;

	/** message_state */
	@Column(name = "MESSAGE_STATE", nullable = false)
	public String messageState;

	/** error_message */
	@Column(name = "ERROR_MESSAGE")
	public String errorMessage;

	/** sender */
	@Column(name = "SENDER")
	public String sender;

	/** recipients */
	@Column(name = "RECIPIENTS", nullable = false)
	public String recipients;

	/** remote_host */
	@Column(name = "REMOTE_HOST", nullable = false)
	public String remoteHost;

	/** remote_addr */
	@Column(name = "REMOTE_ADDR", nullable = false)
	public String remoteAddr;

	/** message_body */
	@Column(name = "MESSAGE_BODY", nullable = false)
	public String messageBody;

	/** message_attributes */
	@Column(name = "MESSAGE_ATTRIBUTES")
	public String messageAttributes;

	/** last_updated */
	@Column(name = "LAST_UPDATED", nullable = false)
	public Timestamp lastUpdated;

}