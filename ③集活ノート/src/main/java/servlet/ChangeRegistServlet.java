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

@WebServlet("/ChangeRegist")
public class ChangeRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//ユーザーIDと企業名を取得
		int userId = Integer.parseInt(request.getParameter("userId"));
		String companyName = request.getParameter("companyName");

		//企業情報をDBから取得
		List<CompanyBeans> comBeansList = new CompanyDBConnect()
				.SearchALL(userId, companyName);
		//スコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("comBeansList", comBeansList);

		//登録内容変更画面へフォワード
		request.getRequestDispatcher("WEB-INF/jsp/changeRegist.jsp")
				.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
