package com.chuntarqa.dto;

/**
 * UserTableクラス.<br>
 * jsonオブジェクトおよび永続化オブジェクトとのマッピング、両方に用いる.
 * @author Ryuta
 *
 */
public class UserTable {
	/** user */
	private String user;
	/** pass */
	private String pass;
	/** mail */
	private String mail;
	/** response */
	private String response;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

}
