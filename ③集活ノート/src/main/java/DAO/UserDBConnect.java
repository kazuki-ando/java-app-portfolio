package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserBeans;

public class UserDBConnect {
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/syukatsunote";
	private final String USER_ID = "postgres";
	private final String PASS = "test";

	//コンストラクタでドライバ接続
	public UserDBConnect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("ドライバのロードに失敗しました");
		}
	}

	public int loginCheck(UserBeans userBeans) {
		String sql = "SELECT user_id,COUNT(*) as result FROM userlist ";
		sql += "WHERE user_name = ? AND password = ? ";
		sql += "GROUP BY user_id;";
		int result = 0;
		try (Connection con = DriverManager.getConnection(JDBC_URL, USER_ID, PASS)){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userBeans.getUserName());
			ps.setString(2, userBeans.getPassword());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("result");
				int userId = rs.getInt("user_id");
				if(result == 1) {
					userBeans.setUserId(userId);
				}
			}
		} catch (Exception e) {
			System.out.println("LoginCheckERROR");
			e.printStackTrace();
		}
		return result;
	}

	public boolean insertUserData(UserBeans userBeans) {
		String sql = "INSERT INTO userlist(user_name, password) ";
		sql += "VALUES(?, ?) ";

		try (Connection con = DriverManager.getConnection(JDBC_URL, USER_ID, PASS)){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userBeans.getUserName());
			ps.setString(2, userBeans.getPassword());
			int result = ps.executeUpdate();
			if(result == 1) {
				System.out.println(result + "件のユーザー情報を登録しました");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("ユーザー情報登録エラー");
			e.printStackTrace();
		}

		System.out.println("すでにユーザーが存在しています");
		return false;
	}


}
