package com.chuntarqa.json;

import java.util.ArrayList;
import java.util.List;

import com.chuntarqa.dto.ErrorTable;
import com.chuntarqa.dto.QaTable;
import com.chuntarqa.dto.UserTable;

/**
 * JsonObjectクラス.<br>
 * Json文字列から生成、またはJson文字列に変換される。
 * @author Ryuta
 *
 */
public class JsonObject {
	/** userTableList. */
	private List<UserTable> userTableList;
	/** qaTableList. */
	private List<QaTable> qaTableList;
	/** errorTableList. */
	private List<ErrorTable> errorTableList;

	/**
	 * 空のJsonObjectを生成する.
	 */
	public JsonObject() {
		this.userTableList = new ArrayList<UserTable>();
		this.qaTableList = new ArrayList<QaTable>();
		this.errorTableList = new ArrayList<ErrorTable>();
	}

	public List<UserTable> getUserTableList() {
		return userTableList;
	}

	public void setUserTableList(List<UserTable> userTableList) {
		this.userTableList = userTableList;
	}

	public List<QaTable> getQaTableList() {
		return qaTableList;
	}

	public void setQaTableList(List<QaTable> qaTableList) {
		this.qaTableList = qaTableList;
	}

	public List<ErrorTable> getErrorTableList() {
		return errorTableList;
	}

	public void setErrorTableList(List<ErrorTable> errorTableList) {
		this.errorTableList = errorTableList;
	}

	/**
	 * 指定されたno、errorのerrorTableを生成しerrorTableListにaddされたJsonObjectを生成する.
	 * @param no
	 * @param error
	 */
	public JsonObject(String no, String error) {
		this.userTableList = new ArrayList<UserTable>();
		this.qaTableList = new ArrayList<QaTable>();
		this.errorTableList = new ArrayList<ErrorTable>();

		errorTableList.add(new ErrorTable(no, error));
	}
}
