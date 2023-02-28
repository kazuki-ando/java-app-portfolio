package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.KeijibanThread;

public class ThreadDBConnect {
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/keijiban";
	private final String USER_ID = "postgres";
	private final String PASS = "test";
	List<KeijibanThread> ktList = new ArrayList<>();

	//コンストラクタでドライバを読込
	public ThreadDBConnect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("ドライバのロードに失敗しました");
		}
	}

	//スレッド一覧を取得するメソッド
	public List<KeijibanThread> findAll() {
		//SQL文の用意
		String sql = "SELECT * from thread ORDER BY thread_id DESC;";
		//DBへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, USER_ID,
				PASS)) {
			//SQL文の実行
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			PostDBConnect postDao = new PostDBConnect();
			//取得データでBeans生成しリスト化
			while (rs.next()) {
				int thread_id = rs.getInt("thread_id");
				String thread_name = rs.getString("thread_name");
				String creater_name = rs.getString("creater_name");
				Timestamp create_date = rs.getTimestamp("create_date");
				int post_count = postDao.getPostCount(thread_id);
				KeijibanThread kt = new KeijibanThread(thread_id, thread_name,
						creater_name, create_date, post_count);
				ktList.add(kt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ktList;
	}

	public void createThread(String threadname, String createrName) {
		//スレッド作成のSQL文
		String cSql = "";
		cSql += "INSERT INTO thread(thread_name, creater_name) ";
		cSql += "VALUES(?,?);";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, USER_ID,
				PASS)) {
			//新規スレッド作成SQL実行
			PreparedStatement ptsmt = conn.prepareStatement(cSql);
			ptsmt.setString(1, threadname);
			ptsmt.setString(2, createrName);
			int result = ptsmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("CreateError");
			e.printStackTrace();
		}
	}

	public String getThreadName(int threadId) {
		String sql = "SELECT thread_name from thread WHERE thread_id = ?;";
		String threadName = "";
		try (Connection con = DriverManager.getConnection(JDBC_URL, USER_ID, PASS)){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, threadId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				threadName = rs.getString("thread_name");
			}
		} catch (Exception e) {
			System.out.println("GetThreadNameERROR");
			e.printStackTrace();
		}
		return threadName;
	}
}
