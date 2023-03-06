package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CompanyBeans;

@WebServlet("/CompanyInfoRegist")
public class CompanyInfoRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String msg = "";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//登録画面へフォワード
		request.getRequestDispatcher("WEB-INF/jsp/companyInfoRegist.jsp")
				.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//企業データを取得
		int userId = Integer.parseInt(request.getParameter("userId"));
		String companyName = request.getParameter("companyName");
		String firstContact = request.getParameter("firstContact");
		String lastContact = request.getParameter("lastContact");
		String site = request.getParameter("site");
		String status = new CompanyBeans().setStatusWord(
				Integer.parseInt(request.getParameter("status")));
		String memo = request.getParameter("memo");

		//各項目をチェック
		boolean check = new CompanyBeans().insertCheck(companyName, site, memo);

		if (check == false) {
			//エラーメッセージをスコープ保存
			String msg = "文字数制限内で入力してください。";
			request.setAttribute("msg", msg);
			//入力画面へフォワード
			request.getRequestDispatcher("WEB-INF/jsp/companyInfoRegist.jsp")
					.forward(request,
							response);
		}
		//取得データをスコープ保存
		request.setAttribute("userId", userId);
		request.setAttribute("companyName", companyName);
		request.setAttribute("firstContact", firstContact);
		request.setAttribute("lastContact", lastContact);
		request.setAttribute("site", site);
		request.setAttribute("status", status);
		request.setAttribute("memo", memo);

		//登録内容の確認画面へフォワード
		request.getRequestDispatcher("WEB-INF/jsp/companyInfoRegistConfilm.jsp")
				.forward(request,
						response);
	}
}
