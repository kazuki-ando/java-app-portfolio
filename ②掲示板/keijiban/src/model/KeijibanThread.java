package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class KeijibanThread implements Serializable {
	//スレッドの情報
	private int threadId;
	private String threadName;
	private String createrName;
	private Timestamp createDate;
	private int postCount;

	public KeijibanThread() {
	}

	public KeijibanThread(int threadId, String threadName, String createrName,
			Timestamp createDate, int postCount) {
		this.threadId = threadId;
		this.threadName = threadName;
		this.createrName = createrName;
		this.createDate = createDate;
		this.postCount = postCount;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
}
