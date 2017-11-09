package com.chuntarqa.dto;

/**
 * ErrorTableクラス.
 * @author Chunta Web
 *
 */
public class ErrorTable {
	/** no. */
	private String no;
	/** エラー内容 */
	private String error;

	/**
	 * コンストラクタ.
	 * @param no
	 * @param error
	 */
	public ErrorTable(String no, String error) {
		this.no = no;
		this.error = error;
	}

	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
