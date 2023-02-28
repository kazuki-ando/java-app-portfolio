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

import dao.PostDBConnect;
import dao.ThreadDBConnect;
import model.ThreadPosts;

@WebServlet("/Posts")
public class Posts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//スレッドID、スレッド名をリクエスト、またはセッションから取得
		HttpSession session = request.getSession();
		int threadId = Integer.parseInt(request.getParameter("threadId"));
		//スレッド名を取得
		ThreadDBConnect tDao = new ThreadDBConnect();
		String threadName =tDao.getThreadName(threadId);

		//投稿データ取得用のDAOとリストインスタンス生成
		PostDBConnect dao = new PostDBConnect();
		List<ThreadPosts> postsList = new ArrayList<>();

		//投稿内容を取得
		postsList = dao.postsShowAll(threadId);

		//スレッド名をセッションへ保存
		session.setAttribute("threadName", threadName);
		//投稿内容の有無により分岐
		if (postsList.isEmpty()) {
			String msg = "＊まだ投稿がありません";
			session.setAttribute("threadId", threadId);
			request.setAttribute("msg", msg);
		} else {
			session.setAttribute("threadId", threadId);
			session.setAttribute("postsList", postsList);
		}

		//投稿画面へフォワード
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/jsp/post.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//投稿するデータを取得
		HttpSession session = request.getSession();
		int threadId = Integer.parseInt(request.getParameter("threadId"));
		String threadName = (String) session.getAttribute("threadName");
		String posterName = (String) request.getParameter("posterName");
		String postContent = (String) request.getParameter("postContent");
		//投稿するスレッドの投稿数を取得
		PostDBConnect dao = new PostDBConnect();
		int posts_id = dao.getPostCount(threadId) + 1;
		//投稿内容をインサート
		dao.insertPost(threadId, posts_id, posterName, postContent);
		//GETメソッドで投稿データ再取得し画面推移
		doGet(request, response);
	}

}
