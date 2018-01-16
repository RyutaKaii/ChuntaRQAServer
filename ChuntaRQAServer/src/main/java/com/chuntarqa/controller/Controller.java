package com.chuntarqa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuntarqa.dto.UserTable;
import com.chuntarqa.json.JsonObjectRQA;
import com.chuntarqa.model.UserModel;
import com.google.gson.Gson;

/**
 * コントローラクラス.
 * @author Chunta Web
 *
 */
@RestController
@RequestMapping(value = "")
public class Controller {
	/** セッション情報. */
	@Autowired
	HttpSession session;

	/** userModel. */
	@Autowired
	UserModel userModel;


	/**
	 * 永続化オブジェクトから該当ユーザーのカテゴリを取得.
	 * @return 受信したリクエストのUserTableのCategoryに取得したCategoryリストを上書きしたもの
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectCategory", produces="application/JSON;charset=utf-8")
	public String selectCategory() throws Exception {
		JsonObjectRQA reqJsonObject = (JsonObjectRQA) session.getAttribute("jsonObject");

		List<UserTable> userTableList = userModel.selectCategory(reqJsonObject.getUserTableList().get(0));
		reqJsonObject.getUserTableList().get(0).setCategory(userTableList.get(0).getCategory());

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
	public String authError() throws Exception {
		return new Gson().toJson(new JsonObjectRQA("1", "認証エラーです。IDまたはパスワードが間違っています。"));
	}

	/**
	 * リクエストURL認証エラー.<br>
	 * 該当ユーザーにリクエストしたURLの権限が与えられていない場合にここに遷移する。<br>
	 * インターセプターから遷移される。
	 * @return エラー情報のJsonオブジェクト
	 * @throws Exception
	 */
	@RequestMapping(value = "/urlauthError", produces="application/JSON;charset=utf-8")
	public String UrlAuthError() throws Exception {
		return new Gson().toJson(new JsonObjectRQA("3", "認証エラーです。リクエストしたURLは許可されていません。"));
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

		return new Gson().toJson(new JsonObjectRQA("2", errorMsg));
	}

	/**
	 * コントローラ内でエラーが発生した場合ここに遷移する.
	 * @param e
	 * @return エラー情報のJsonオブジェクト
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(final Exception e) {
		return new Gson().toJson(new JsonObjectRQA("99", e.getMessage()));
	}
}
