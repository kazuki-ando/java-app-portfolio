package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet("/JyankenResult")
public class JyankenResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		User user = new User(name, 0, 0, 0);

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gameDisplay.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User user;
		//リクエストスコープからプレイヤー情報と選択肢を取得
		HttpSession session = request.getSession();
		user = (User)session.getAttribute("user");
		int phand =  Integer.parseInt(request.getParameter("phand"));
		//コンピュータの手を決定
		int chand = new java.util.Random().nextInt(3);
		String[] handArray = {"ぐー", "ちょき", "ぱー"};
		//じゃんけんの勝敗を決定
		String result = jyankenResult(phand, chand);

		//リクエストスコープに勝敗の情報とコンピュータの情報を保存
		request.setAttribute("result", result);
		request.setAttribute("chand", handArray[chand]);

		//結果数をセッションスコープに保存
		//保存順は勝ち、負け、引き分け
		ServletContext application = request.getServletContext();
		if(result.equals("勝ち")) {
			user.setWin(user.getWin() + 1);
		}else if(result.equals("負け")) {
			user.setLose(user.getLose() + 1);
		}else {
			user.setDraw(user.getDraw() + 1);
		}
		application.setAttribute("user", user);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gameDisplay.jsp");
		dispatcher.forward(request, response);
	}

	private String jyankenResult(int phand, int chand) {
		//0(ぐー)、1（ちょき）、2（ぱー）
		//引き分けを判定
		if(phand == chand) {
			return "引き分け";
		}
		switch(phand){
		case 0:
			if(chand == 1) {
				return "勝ち";
			}else {
				return "負け";
			}
		case 1:
			if(chand == 2) {
				return "勝ち";
			}else {
				return "負け";
			}
		case 2:
			if(chand == 0) {
				return "勝ち";
			}else {
				return "負け";
			}
		}
		return "";
	}

}
