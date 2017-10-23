package com.chuntarqa.intercepter;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chuntarqa.controller.Controller;
import com.chuntarqa.dto.ErrorTable;
import com.chuntarqa.dto.UserTable;
import com.chuntarqa.json.JsonObject;
import com.chuntarqa.model.UserModel;
import com.google.gson.Gson;

/**
 * インターセプタークラス.<br>
 * 全てのリクエストはこのインターセプタークラスが適用される.<br>
 * refs http://thread.main.jp/spring/interceptor.html
 *
 * @author Chunta Web.
 *
 */
@Component
public class RQAServerIntercepter implements HandlerInterceptor {
	/** userModel. */
	@Autowired
	UserModel userModel;

	/** ロガー. */
	protected Logger logger = LogManager.getLogger(Controller.class);

	/**
	 * リクエスト受信時の処理.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			logger.info("Receive Request.");
			logger.info("RequestUri : " + request.getRequestURI());

			// bodyを取得しパース
			String body = request.getReader().lines().collect(Collectors.joining(System.getProperty("line.separator")));
			JsonObject reqJsonObject = new Gson().fromJson(body, JsonObject.class);

			logger.info("body : " + body);

			// 認証
			List<UserTable> userTableList = userModel.login(reqJsonObject.getUserTableList().get(0));

			if (userTableList.isEmpty()) {
				// 認証エラーの場合
				logger.warn("authError.");

				RequestDispatcher dispatch = request.getRequestDispatcher("/authError");
				dispatch.forward(request, response);

				return false;
			}

			// 正常時のerrorTableListを設定
			setOkErrorTableList(reqJsonObject);

			// セッションにパースしたオブジェクトを設定
			request.getSession().setAttribute("jsonObject", reqJsonObject);

			return true;
		} catch (Exception e) {
			// 想定外エラーの場合
			logger.warn("receiveError.");
			logger.warn(e.getMessage());

			// セッションにエラー情報を設定
			request.getSession().setAttribute("errorMsg", e.getMessage());

			RequestDispatcher dispatch = request.getRequestDispatcher("/receiveError");
			dispatch.forward(request, response);

			return false;
		} finally {
			logger.debug("End intercepter Process.");
		}
	}

	/**
	 * post後の処理.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * レスポンス返却時の処理.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("Return Response.");
	}

	/**
	 * 正常時のerrorTableListを設定する.<br />
	 * 設定されているerrorTableListはクリアする.
	 * @param reqJsonObject
	 */
	private void setOkErrorTableList(JsonObject reqJsonObject) {
		reqJsonObject.getErrorTableList().clear();
		reqJsonObject.getErrorTableList().add(new ErrorTable("0", ""));
	}

}
