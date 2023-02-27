package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ThreadDBConnect;
import model.KeijibanThread;

@WebServlet("/Top")
public class Top extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//スレッドデータ取得
		List<KeijibanThread> ktList = new ArrayList<>();
		ThreadDBConnect dao = new ThreadDBConnect();
		ktList = dao.findAll();

		//スレッドデータ有無で分岐
		HttpSession session = request.getSession();
		if (!ktList.isEmpty()) {
			//スレッドデータ有ならセッションに保存
			session.setAttribute("ktList", ktList);
		} else {
			//スレッドデータ無ならメッセージをリクエストに保存
			String msg = "＊まだスレッドがありません";
			request.setAttribute("msg", msg);
		}
		//トップへフォワード
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//スレッド作成画面へフォワード
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("WEB-INF/jsp/createThread.jsp");
		dispatcher.forward(request, response);
	}

}
