package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ThreadPosts implements Serializable {
	//スレッドの投稿内容
	private int posts_id;
	private String poster_name;
	private Timestamp posts_date;
	private String posts_text;

	public ThreadPosts() {
	}

	public ThreadPosts(int post_id, String poster_name, Timestamp post_date,
			String post_contet) {
		this.posts_id = post_id;
		this.poster_name = poster_name;
		this.posts_date = post_date;
		this.posts_text = post_contet;
	}

	public int getPost_id() {
		return posts_id;
	}

	public void setPost_id(int post_id) {
		this.posts_id = post_id;
	}

	public String getPoster_name() {
		return poster_name;
	}

	public void setPoster_name(String poster_name) {
		this.poster_name = poster_name;
	}

	public Timestamp getPost_date() {
		return posts_date;
	}

	public void setPost_date(Timestamp post_date) {
		this.posts_date = post_date;
	}

	public String getPost_contet() {
		return posts_text;
	}

	public void setPost_contet(String post_contet) {
		this.posts_text = post_contet;
	}

}
