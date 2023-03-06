package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CompanyDBConnect;

/**
 * Servlet implementation class DeleteInfoServlet
 */
@WebServlet("/DeleteInfo")
public class DeleteInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//削除する企業情報を取得
		int userId = Integer.parseInt(request.getParameter("userId"));
		String companyName = request.getParameter("companyName");

		//企業情報をスコープ保存
		request.setAttribute("userId", userId);
		request.setAttribute("companyName", companyName);

		//削除確認画面にフォワード
		request.getRequestDispatcher("WEB-INF/jsp/deleteConfilm.jsp")
				.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//削除する企業情報を取得
		int userId = Integer.parseInt(request.getParameter("userId"));
		String companyName = request.getParameter("companyName");

		//DBに接続し企業情報を削除
		new CompanyDBConnect().deleteInfo(userId, companyName);

		//結果をリクエスト保存
		String msg = "【" + companyName + "】の登録情報を削除しました";
		request.setAttribute("msg", msg);

		//結果画面へフォワード
		request.getRequestDispatcher("WEB-INF/jsp/result.jsp").forward(request,
				response);
	}

}
