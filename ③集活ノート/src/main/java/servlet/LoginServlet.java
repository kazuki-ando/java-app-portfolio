package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDBConnect;
import beans.UserBeans;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String signUp = request.getParameter("signUp");
		//新規登録か否かで分岐
		if (signUp == null) {
			//TOP画面へフォワード
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			//新規登録画面へフォワード
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/jsp/signUp.jsp");
			dispatcher.forward(request, response);

		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//ログイン情報の確認
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		//ユーザーインスタンス生成し、ログイン可否を確認
		UserBeans userBeans = new UserBeans(userName, password);
		UserDBConnect dao = new UserDBConnect();
		int result = dao.loginCheck(userBeans);

		//ログイン可否によって分岐
		if (result == 1) {
			//セッションスコープにユーザーインスタンスを保存
			HttpSession session = request.getSession();
			session.setAttribute("user", userBeans);

			//トップ画面にサーブレット経由でフォワード
			request.getRequestDispatcher("/Top").forward(request, response);
		} else {
			//エラーメッセージをリクエストスコープに保存
			String msg = "ログインに失敗しました。もう一度入力してください。";
			request.setAttribute("msg", msg);
			//ログイン画面にフォワード
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);

		}

	}

}
