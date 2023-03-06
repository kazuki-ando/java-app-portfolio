package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.CompanyBeans;

public class CompanyDBConnect {
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/syukatsunote";
	private final String USER = "postgres";
	private final String PASS = "test";

	public CompanyDBConnect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException();
		}
	}

	public boolean insertCompanyData(int userId, String companyName,
			String firstContact, String lastContact, String site, String status,
			String memo) {
		String sql = "INSERT INTO company( ";
		sql += "user_id ,company_name, first_contact, last_contact,register_site, status, memo) ";
		sql += "VALUES(?,?,to_date(?, 'yyyy-MM-dd'), to_date(?, 'yyyy-MM-dd'), ? ,? ,?);";
		try (Connection con = DriverManager.getConnection(JDBC_URL, USER,
				PASS)) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2, companyName);
			ps.setString(3, firstContact);
			ps.setString(4, lastContact);
			ps.setString(5, site);
			ps.setString(6, status);
			ps.setString(7, memo);
			int result = ps.executeUpdate();
			System.out.println(result + "件の企業情報を登録しました");

			if (result == 0) {
				return false;
			}
		} catch (Exception e) {
			System.err.println("企業情報登録エラー");
			e.printStackTrace();
		}
		return true;
	}

	public List<CompanyBeans> showAll(int userId) {
		String sql = "SELECT * from company ";
		sql += "WHERE user_id = ? ";
		sql += "ORDER BY first_contact DESC;";
		List<CompanyBeans> comBeansList = new ArrayList<CompanyBeans>();
		try (Connection con = DriverManager.getConnection(JDBC_URL, USER,
				PASS)) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String companyName = rs.getString("company_name");
				Date firstContact = rs.getDate("first_contact");
				Date lastContact = rs.getDate("last_contact");
				String site = rs.getString("register_site");
				String status = rs.getString("status");
				String memo = rs.getString("memo");
				Timestamp createDate = rs.getTimestamp("create_date");
				if (companyName != null) {
					CompanyBeans comBeans = new CompanyBeans(userId,
							companyName, firstContact, lastContact, site,
							status, memo, createDate);
					comBeansList.add(comBeans);
				}
			}
		} catch (Exception e) {
			System.out.println("企業情報取得エラー");
			e.printStackTrace();
		}
		return comBeansList;
	}

	public List<CompanyBeans> SearchALL(int userId, String searchWord) {
		String sql = "SELECT * from company ";
		sql += " WHERE user_id = ? AND company_name LIKE ? ";
		sql += "ORDER BY first_contact DESC;";
		List<CompanyBeans> comBeansList = new ArrayList<CompanyBeans>();
		try (Connection con = DriverManager.getConnection(JDBC_URL, USER,
				PASS)) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2, "%" + searchWord + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String companyName = rs.getString("company_name");
				Date firstContact = rs.getDate("first_contact");
				Date lastContact = rs.getDate("last_contact");
				String site = rs.getString("register_site");
				String status = rs.getString("status");
				String memo = rs.getString("memo");
				Timestamp createDate = rs.getTimestamp("create_date");
				if (companyName != null) {
					CompanyBeans comBeans = new CompanyBeans(userId,
							companyName, firstContact, lastContact, site,
							status, memo, createDate);
					comBeansList.add(comBeans);
				}
			}
		} catch (Exception e) {
			System.out.println("企業情報取得エラー");
			e.printStackTrace();
		}
		return comBeansList;
	}

	public boolean setChangeRegister(int userId, String companyName,
			String lastContact,
			String status,
			String memo) {
		String sql = "UPDATE company SET ";
		sql += "last_contact = to_date(?, 'yyyy-MM-dd'),";
		sql += "status = ?,";
		sql += "memo = ? ";
		sql += "WHERE user_id = ? AND company_name = ?;";
		try (Connection con = DriverManager.getConnection(JDBC_URL, USER,
				PASS)) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, lastContact);
			ps.setString(2, status);
			ps.setString(3, memo);
			ps.setInt(4, userId);
			ps.setString(5, companyName);
			int result = ps.executeUpdate();
			System.out.println(result + "件の変更を登録");
		} catch (Exception e) {
			System.out.println("登録内容変更エラー");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void deleteInfo(int userId, String companyName) {
		String sql = "DELETE FROM company ";
		sql += "WHERE user_id = ? AND ";
		sql += "company_name = ?;";
		try (Connection con = DriverManager.getConnection(JDBC_URL, USER,
				PASS)) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2, companyName);
			int result = ps.executeUpdate();
			System.out.println(result + "件の企業情報を削除しました");
		} catch (Exception e) {
			System.out.println("企業情報削除エラー");
			e.setStackTrace(null);
		}
	}

}
