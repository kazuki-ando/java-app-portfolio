package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ThreadDBConnect;

@WebServlet("/CreateThread")
public class CreateThread extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String threadName;
	String createrName;
	String errorMsg;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストからスレッド作成データを取得
		threadName = request.getParameter("threadName");
		createrName = request.getParameter("createrName");
		//空データの確認
		if (threadName.isBlank() || createrName.isBlank()) {
			//データが空なら再度、作成画面へディスパッチ
			errorMsg = "＊スレッド名、作成者名は1文字以上の登録が必要です";
			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/jsp/createThread.jsp");
			dispatcher.forward(request, response);
		} else {
			//データが正常に入力されていたら、内容確認画面へディスパッチ
			HttpSession session = request.getSession();
			session.setAttribute("threadName", threadName);
			session.setAttribute("createrName", createrName);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/jsp/createThreadEntry.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//リクエストスコープから値を取得
		HttpSession session = request.getSession();
		threadName = (String) session.getAttribute("threadName");
		createrName = (String) session.getAttribute("createrName");
		//掲示板スレッドDBクラスのインスタンスを生成しスレッド作成情報を渡す
		ThreadDBConnect ktDB = new ThreadDBConnect();
		ktDB.createThread(threadName, createrName);
		//トップ画面へリダイレクト
		response.sendRedirect("Top");

	}

}
