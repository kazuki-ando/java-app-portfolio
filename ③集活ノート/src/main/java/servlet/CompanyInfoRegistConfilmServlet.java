package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CompanyDBConnect;

@WebServlet("/CompanyInfoRegistConfilm")
public class CompanyInfoRegistConfilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//企業データを取得
		int userId = Integer.parseInt(request.getParameter("userId"));
		String companyName = request.getParameter("companyName");
		String firstContact = request.getParameter("firstContact");
		String lastContact = request.getParameter("lastContact");
		String site = request.getParameter("site");
		String status = request.getParameter("status");
		String memo = request.getParameter("memo");
		//チェック後、DBへ登録
		CompanyDBConnect dao = new CompanyDBConnect();
		boolean result = dao.insertCompanyData(userId, companyName,
				firstContact,
				lastContact, site, status, memo);

		if (result) {
			//結果メッセージをスコープ保存
			String msg = "【" + companyName + "】の情報を登録しました。";
			request.setAttribute("msg", msg);
			//登録内容の確認画面へフォワード
			request.getRequestDispatcher("WEB-INF/jsp/result.jsp")
					.forward(request,
							response);
		} else {
			//結果メッセージをスコープ保存
			String msg = "【" + companyName + "】の情報に失敗しました。もう一度最初から行ってください。";
			request.setAttribute("msg", msg);
			//登録内容の確認画面へフォワード
			request.getRequestDispatcher("WEB-INF/jsp/result.jsp")
					.forward(request,
							response);

		}
	}

}
