package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBeans;

@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String msg;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//登録情報を取得
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String pwConfilm = request.getParameter("pwConfilm");

		//ユーザー名入力値チェック
		boolean isName = userNameCheck(userName);
		//ユーザー名チェックエラーの場合
		if(isName == false) {
			//エラーメッセージをスコープ保存し元の画面にフォワード
			request.setAttribute("msg", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/signUp.jsp");
			dispatcher.forward(request, response);
		}

		//パスワード入力値チェック
		boolean isPassword = passwordCheck(password,pwConfilm);
		//パスワードチェックエラーの場合
		if(isPassword == false) {
			//エラーメッセージをスコープ保存し元の画面にフォワード
			request.setAttribute("msg", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/signUp.jsp");
			dispatcher.forward(request, response);
		}

		//チェッククリアでユーザーインスタンス作成
		UserBeans userBeans = new UserBeans(userName, password);
		//ユーザーBeansをスコープ保存
		HttpSession session = request.getSession();
		session.setAttribute("user", userBeans);

		//確認画面へフォワード
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("WEB-INF/jsp/confilm.jsp");
		dispatcher.forward(request, response);
	}


	private boolean userNameCheck(String userName) {

		//文字数が10字以内かチェック
		if(userName.length() > 10 || userName.isBlank()) {
			msg = "ユーザ名は1~10字以内で指定してください。";
			return false;
		}
		return true;

	}

	private boolean passwordCheck(String password, String pwConfilm) {

		//パスワード確認
		if (!password.equals(pwConfilm)) {
			//エラーメッセージをリクエストスコープに保存
			msg = "パスワードが一致しません。もう一度入力してください";
			return false;
		} else if (password.isBlank()) {
			//エラーメッセージをリクエストスコープに保存
			msg = "パスワードを入力してください（英数字）";
			return false;
		}

		//文字数が4字～20字以内かチェック
		if(password.length() < 4 || password.length() > 20) {
			msg = "パスワードは4～20字以内で指定してください。";
			System.out.println("PW桁数");
			return false;
			//英数字のみかチェック
		}else if(!password.matches("^[A-Za-z0-9]+$")) {
			msg = "パスワードは英数字の組み合わせ";
			System.out.println("PW英数字");
			return false;
		}
		return true;
	}
}
