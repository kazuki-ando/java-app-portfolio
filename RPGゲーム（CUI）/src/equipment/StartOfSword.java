package equipment;

import java.util.Scanner;

import character.Hero;

public class StartOfSword extends Sword {
	Scanner sc = new Scanner(System.in);

	//ショップ用
	public StartOfSword() {
		this.name = "始まりの剣";
		this.power = 3;
		this.price = 30;
	}

	//獲得時
	public StartOfSword(Hero hero) {
		this.hero = hero;
		this.name = "始まりの剣";
		this.power = 3;
		this.price = 30;
	}
}
