package equipment;

import java.util.Scanner;

import character.Wizard;

public class StartOfWand extends Wand {
	Scanner sc = new Scanner(System.in);

	//ショップ用
	public StartOfWand() {
		this.name = "始まりの杖";
		this.power = 1;
		this.magicPower = 3;
		this.price = 10;
	}

	//獲得時
	public StartOfWand(Wizard wiz) {
		this.wiz = wiz;
		this.name = "始まりの杖";
		this.power = 1;
		this.magicPower = 3;
		this.price = 10;
	}

}
