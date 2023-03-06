package servlet;

import java.io.IOException;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CompanyDBConnect;
import beans.CompanyBeans;
import beans.UserBeans;

@WebServlet("/Top")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//ログインユーザー情報取得
		HttpSession session = request.getSession();
		UserBeans user = (UserBeans) session.getAttribute("user");

		//ログアウト確認
		String logout = request.getParameter("logout");
		if (logout != null) {
			//ユーザー情報をセッションから削除
			session.removeAttribute("user");
			//ログイン画面へリダイレクト
			request.getRequestDispatcher("/Login").forward(request, response);
		}

		//検索ワード、並び替えワードがあれば取得
		String searchWord = request.getParameter("search");
		String sortWord = request.getParameter("sort");
		String order = request.getParameter("order");

		//企業リストを取得
		List<CompanyBeans> comBeansList;
		if (searchWord != null) {
			//企業名でリストを絞り込み取得
			comBeansList = new CompanyDBConnect().SearchALL(user.getUserId(),
					searchWord);
		} else {
			//表示する企業情報を全取得
			comBeansList = new CompanyDBConnect().showAll(user.getUserId());
		}

		//リストの並び替え
		if (sortWord != null) {
			listSort(comBeansList, sortWord, order);
		} else {
			//リストを最新順に並び替え
			Collections.reverse(comBeansList);
		}

		//Listが空の場合の処理（企業未登録）
		String msg = "";
		if ((comBeansList == null || comBeansList.isEmpty())
				&& searchWord != null) {
			//メッセージをスコープ保存
			msg = "＊検索結果がありません";
			request.setAttribute("msg", msg);
			//TOP画面へフォワード
			request.getRequestDispatcher("WEB-INF/jsp/top.jsp").forward(request,
					response);
		} else if (comBeansList == null || comBeansList.isEmpty()) {
			//メッセージをスコープ保存
			msg = "＊企業情報が未登録です";
			request.setAttribute("msg", msg);
			//TOP画面へフォワード
			request.getRequestDispatcher("WEB-INF/jsp/top.jsp").forward(request,
					response);

		}
		//登録企業リストをスコープ保存
		session.setAttribute("comBeansList", comBeansList);

		//TOP画面へフォワード
		request.getRequestDispatcher("WEB-INF/jsp/top.jsp").forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void listSort(List<CompanyBeans> comBeansList, String sortWord,
			String order) {
		Collator cmpl = Collator.getInstance(Locale.JAPANESE); // 日本語を考慮したソート
		cmpl.setDecomposition(Collator.FULL_DECOMPOSITION); //半分幅と完全幅の ASCII 文字とカタカナ文字を一緒に照合
		cmpl.setStrength(Collator.IDENTICAL); // 大文字小文字アクセント記号等々を全部別文字と見なす
		//並び替え方法で分岐
		switch (sortWord) {
		case "name":
			comBeansList.stream().sorted(
					(Comparator.comparing(CompanyBeans::getCompanyName)));
			break;
		case "first":
			comBeansList
					.sort(Comparator.comparing(CompanyBeans::getFirstContact));
			break;
		case "last":
			comBeansList
					.sort(Comparator.comparing(CompanyBeans::getLastContact));
			break;
		case "site":
			comBeansList.sort(Comparator.comparing(CompanyBeans::getSite));
			break;
		case "status":
			comBeansList.sort(Comparator.comparing(CompanyBeans::getStatus));
			break;
		case "memo":
			comBeansList.sort(
					Comparator.comparing(CompanyBeans::getMemo).reversed());
			break;
		}

		if (order.equals("reverse")) {
			Collections.reverse(comBeansList);
		}
	}

}
