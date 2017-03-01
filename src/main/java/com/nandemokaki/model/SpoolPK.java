package com.nandemokaki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * MODEL autogen macro
 * Spool
 * @auther KIM
*/
@Entity
public class SpoolPK implements Serializable {
	public static final String TABLE = "SPOOL";

	/** message_name */
	@Id
	@Column(name = "MESSAGE_NAME")
	public String messageName;

	/** repository_name */
	@Id
	@Column(name = "REPOSITORY_NAME")
	public String repositoryName;
}