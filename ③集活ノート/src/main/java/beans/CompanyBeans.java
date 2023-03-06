package beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class CompanyBeans implements Serializable {
	private int userId;
	private String companyName;
	private Date firstContact;
	private Date lastContact;
	private String site;
	private String status;
	private String memo;
	private Timestamp createDate;

	public CompanyBeans() {
	}

	public CompanyBeans(int userId, String companyName, Date firstContact,
			Date lastContact, String site, String status,
			String memo, Timestamp createDate) {
		this.userId = userId;
		this.companyName = companyName;
		this.firstContact = firstContact;
		this.lastContact = lastContact;
		this.site = site;
		this.status = status;
		this.memo = memo;
		this.createDate = createDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getFirstContact() {
		return firstContact;
	}

	public void setFirstContact(Date firstContact) {
		this.firstContact = firstContact;
	}

	public Date getLastContact() {
		return lastContact;
	}

	public void setLastContact(Date lastContact) {
		this.lastContact = lastContact;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String setStatusWord(int parameter) {
		//値からステータスを取得
		String[] status = {
				"書類審査中",
				"1次面接日程調整中",
				"1次面接結果待ち",
				"2次面接日程調整中",
				"2次面接結果待ち",
				"最終面接日程調整中",
				"合否結果結果待ち",
				"書類落選",
				"不採用通知",
				"辞退"
		};

		String s = status[parameter];
		return s;
	}

	public boolean insertCheck(String companyName2, String site2,
			String memo2) {

		if (companyName2.length() >= 30) {
			return false;
		} else if (site2.length() >= 20) {
			return false;
		} else if (memo2.length() >= 100) {
			return false;
		}

		return true;
	}

}
