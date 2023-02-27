package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.ThreadPosts;

public class PostDBConnect {
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/keijiban";
	private final String USER_ID = "postgres";
	private final String PASS = "test";

	//Top画面のスレッド毎の投稿数を取得
	public int getPostCount(int threadId) {
		String sql = "SELECT COALESCE(COUNT(posts_id),0) as posts_count ";
		sql += "from posts WHERE thread_id = ? ;";
		int post_count = 0;
		//DBへ接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, USER_ID,
				PASS)) {
			//SQLを実行
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, threadId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				post_count = rs.getInt("posts_count");
			}
		} catch (Exception e) {
			System.out.println("PostCountError");
			e.printStackTrace();
		}
		return post_count;
	}

	//投稿内容の取得
	public List<ThreadPosts> postsShowAll(int threadId) {
		String sql = "SELECT * FROM posts ";
		sql += "WHERE thread_id = ? ";
		sql += "ORDER BY posts_id DESC;";
		//投稿データの格納リスト生成
		List<ThreadPosts> postsList = new ArrayList<>();
		//DBへ接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, USER_ID,
				PASS)) {
			//SQLを実行
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, threadId);
			ResultSet rs = ps.executeQuery();
			//取得データをBeansに格納しリスト化
			while (rs.next()) {
				int posts_id = rs.getInt("posts_id");
				String poster_name = rs.getString("poster_name");
				Timestamp posts_date = rs.getTimestamp("posts_date");
				String posts_text = rs.getString("posts_text");
				ThreadPosts tposts = new ThreadPosts(posts_id, poster_name,
						posts_date, posts_text);
				postsList.add(tposts);
			}
		} catch (Exception e) {
			System.out.println("PostsShowError");
			e.printStackTrace();
		}
		return postsList;
	}

	//投稿データをデータベースへ保存
	public void insertPost(int threadId, int posts_id, String posterName,
			String postText) {
		String sql = "INSERT INTO posts ( ";
		sql += "thread_id, posts_id, poster_name, posts_text) ";
		sql += "VALUES(?,?,?,?);";
		//DBへ接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, USER_ID,
				PASS)) {
			//SQLを実行
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, threadId);
			ps.setInt(2, posts_id);
			ps.setString(3, posterName);
			ps.setString(4, postText);
			int result = ps.executeUpdate();
			System.out.println(result + "件、投稿しました");
		} catch (Exception e) {
			;
			System.out.println("InsertPostError");
			e.printStackTrace();
			;
		}
	}

}
