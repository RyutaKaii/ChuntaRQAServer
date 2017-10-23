package com.chuntarqa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuntarqa.dto.QaTable;
import com.chuntarqa.dto.UserTable;
import com.chuntarqa.json.JsonObject;
import com.chuntarqa.model.QaModel;
import com.chuntarqa.model.UserModel;
import com.google.gson.Gson;

/**
 * コントローラクラス.
 * @author Ryuta
 *
 */
@RestController
public class Controller {
	/** セッション情報. */
	@Autowired
	HttpSession session;

	/** userModel. */
	@Autowired
	UserModel userModel;

	/** qaModel. */
	@Autowired
	QaModel qaModel;


	/**
	 * 永続化オブジェクトからレスポンスリストを取得して返却.
	 * @return レスポンスリスト
	 * @throws Exception
	 */
	@RequestMapping(value = "/getResponselist", produces="application/JSON;charset=utf-8")
	public String getResponselist() throws Exception {
		JsonObject reqJsonObject = (JsonObject) session.getAttribute("jsonObject");

		List<UserTable> userTableList = userModel.getResponselist(reqJsonObject.getUserTableList().get(0));
		reqJsonObject.setUserTableList(userTableList);

		return new Gson().toJson(reqJsonObject);
	}

	/**
	 * 永続化オブジェクトのレスポンスリストを更新.
	 * @return 受信したリクエストそのまま
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateResponse", produces="application/JSON;charset=utf-8")
	public String updateResponse() throws Exception {
		JsonObject reqJsonObject = (JsonObject) session.getAttribute("jsonObject");

		userModel.updateResponse(reqJsonObject.getUserTableList().get(0));

		return new Gson().toJson(reqJsonObject);
	}

	/**
	 * 永続化オブジェクトから一覧を更新.
	 * @return 受信したリクエストに一覧を上書きしたもの
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectList", produces="application/JSON;charset=utf-8")
	public String selectList() throws Exception {
		JsonObject reqJsonObject = (JsonObject) session.getAttribute("jsonObject");

		List<QaTable> qaTableList = qaModel.selectList();
		reqJsonObject.setQaTableList(qaTableList);

		return new Gson().toJson(reqJsonObject);
	}

	/**
	 * 永続化オブジェクトに挿入.
	 * @return 受信したリクエストそのまま
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertQa", produces="application/JSON;charset=utf-8")
	public String insertQa() throws Exception {
		JsonObject reqJsonObject = (JsonObject) session.getAttribute("jsonObject");

		// userTableのuserをqaTableのuserに設定
		reqJsonObject.getQaTableList().get(0).setUser(reqJsonObject.getUserTableList().get(0).getUser());

		qaModel.insertQa(reqJsonObject.getQaTableList().get(0));

		// TODO メール送信

		return new Gson().toJson(reqJsonObject);
	}

	/**
	 * 永続化オブジェクトを更新.
	 * @return 受信したリクエストそのまま
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateQa", produces="application/JSON;charset=utf-8")
	public String updateQa() throws Exception {
		JsonObject reqJsonObject = (JsonObject) session.getAttribute("jsonObject");

		qaModel.updateQa(reqJsonObject.getQaTableList().get(0));

		return new Gson().toJson(reqJsonObject);
	}

	/**
	 * 永続化オブジェクトを削除.
	 * @return 受信したリクエストそのまま
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteQa", produces="application/JSON;charset=utf-8")
	public String deleteQa(@RequestParam("no") String no) throws Exception {
		JsonObject reqJsonObject = (JsonObject) session.getAttribute("jsonObject");

		// クエリパラメータで受け取ったnoを設定
		reqJsonObject.getQaTableList().get(0).setNo(no);

		qaModel.deleteQa(reqJsonObject.getQaTableList().get(0));

		return new Gson().toJson(reqJsonObject);
	}

	/**
	 * 永続化オブジェクトから一覧を更新.
	 * @return 受信したリクエストに1件を上書きしたもの
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectQaOne", produces="application/JSON;charset=utf-8")
	public String selectOne(@RequestParam("no") String no) throws Exception {
		JsonObject reqJsonObject = (JsonObject) session.getAttribute("jsonObject");

		// クエリパラメータで受け取ったnoを設定
		reqJsonObject.getQaTableList().get(0).setNo(no);

		List<QaTable> qaTableList = qaModel.selectQaOne(reqJsonObject.getQaTableList().get(0));
		reqJsonObject.setQaTableList(qaTableList);

		return new Gson().toJson(reqJsonObject);
	}


	/**
	 * 認証エラー.<br>
	 * idパスワードの組が永続化オブジェクトに存在しない場合にここに遷移する。<br>
	 * インターセプターから遷移される。
	 * @return エラー情報のJsonオブジェクト
	 * @throws Exception
	 */
	@RequestMapping(value = "/authError", produces="application/JSON;charset=utf-8")
	public String error() throws Exception {
		return new Gson().toJson(new JsonObject("1", "認証エラーです。IDまたはパスワードが間違っています。"));
	}

	/**
	 * 受信エラー.<br>
	 * インターセプターでのデータ受信時にエラーが発生した場合ここに遷移する。<br>
	 * 受信時のJsonパースエラー時もここに遷移する。
	 * @return エラー情報のJsonオブジェクト
	 * @throws Exception
	 */
	@RequestMapping(value = "/receiveError", produces="application/JSON;charset=utf-8")
	public String receiveError() throws Exception {
		// セッションからエラー情報を取得
		String errorMsg = (String) session.getAttribute("errorMsg");

		return new Gson().toJson(new JsonObject("2", errorMsg));
	}

	/**
	 * コントローラ内でエラーが発生した場合ここに遷移する.
	 * @param e
	 * @return エラー情報のJsonオブジェクト
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(final Exception e) {
		return new Gson().toJson(new JsonObject("99", e.getMessage()));
	}
}
