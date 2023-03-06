package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CompanyDBConnect;
import beans.CompanyBeans;

@WebServlet("/ChangeRegistConfilm")
public class ChangeRegistConfilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//変更内容取得
		String lastContact = request.getParameter("lastContact");
		String status = new CompanyBeans().setStatusWord(
				Integer.parseInt(request.getParameter("status")));
		String memo = request.getParameter("memo");

		//値の正常性チェック

		//スコープ保存
		request.setAttribute("lastContact", lastContact);
		request.setAttribute("status", status);
		request.setAttribute("memo", memo);

		//確認画面へフォワード
		request.getRequestDispatcher("WEB-INF/jsp/changeConfilm.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//ユーザービーンズ取得
		HttpSession session = request.getSession();
		CompanyBeans comBeans = ((List<CompanyBeans>) session
				.getAttribute("comBeansList")).get(0);
		//変更内容取得
		int userId = comBeans.getUserId();
		String companyName = comBeans.getCompanyName();
		String lastContact = request.getParameter("lastContact");
		String status = request.getParameter("status");
		String memo = request.getParameter("memo");

		//変更内容をDBへセット
		boolean result = new CompanyDBConnect().setChangeRegister(userId,
				companyName, lastContact, status,
				memo);

		if (result) {
			//変更に成功した場合、メッセージをスコープ保存しフォワード
			String msg = "企業情報の変更が完了しました";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("WEB-INF/jsp/result.jsp")
					.forward(request, response);
		}
	}

}
