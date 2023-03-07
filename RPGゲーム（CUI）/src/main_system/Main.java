package main_system;

import character.Hero;
import field.FieldEvent;

public class Main {
	public static void main(String[] args) {
		int turnCount;
		Hero hero;
		String hero_name;
		FieldEvent fieldEvent = new FieldEvent();

		// ゲームスタート
		System.out.println("【勇者の冒険】スタート");
		// 勇者の名前を決めて召喚
		hero = new Hero(hero_name = fieldEvent.hero_name());
		System.out.println("龍王を倒し世界に平和をもたらそう！");

		// 冒険に出かける
		turnCount = fieldEvent.field_Action();

		// ゲーム終了
		System.out.println("ゲーム終了！");
		System.out.println("今回は" + turnCount + "ターン遊びました");
		System.out.println("倒した敵の数は" + hero.getKillCount() + "体でした！");
	}
}
