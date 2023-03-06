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

@WebServlet("/Confilm")
public class ConfilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//登録するユーザー情報取得
		HttpSession session = request.getSession();
		UserBeans userBeans = (UserBeans) session.getAttribute("user");

		//ユーザー情報をDBに登録
		UserDBConnect dao = new UserDBConnect();
		boolean success = dao.insertUserData(userBeans);

		//INSERT結果で分岐
		if (success) {
			//成功ならメッセージをスコープ保存し登録完了画面へフォワード
			String msg = "ユーザー登録が完了しました";
			request.setAttribute("msg", msg);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
		} else {
			//失敗ならエラーメッセージを保存
			String msg = "＊同一ユーザーが存在しています";
			request.setAttribute("msg", msg);
			//元の画面へフォワード
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/jsp/signUp.jsp");
			dispatcher.forward(request, response);
		}

	}
}
