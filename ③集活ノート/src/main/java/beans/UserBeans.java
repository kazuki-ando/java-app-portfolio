package beans;

import java.io.Serializable;

public class UserBeans implements Serializable{
	private int userId;
	private String userName,password;

	public UserBeans() {}
	public UserBeans(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
