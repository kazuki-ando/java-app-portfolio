package model;

import java.io.Serializable;

public class User implements Serializable{
	String name;
	int win,lose,draw;

	public User(String name, int win, int lose, int draw) {
		this.name = name;
		this.win = win;
		this.lose = lose;
		this.draw = draw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}
}
