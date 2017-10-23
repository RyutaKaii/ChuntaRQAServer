package com.chuntarqa.dto;

/**
 * QaTableクラス.<br>
 * jsonオブジェクトおよび永続化オブジェクトとのマッピング、両方に用いる.
 * @author Ryuta
 *
 */
public class QaTable {
	/** no. */
	private String no;
	/** question. */
	private String question;
	/** title. */
	private String title;
	/** answer. */
	private String answer;
	/** lastupdate. */
	private String lastupdate;
	/** user. */
	private String user;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
