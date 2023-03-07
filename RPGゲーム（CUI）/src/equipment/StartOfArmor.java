package equipment;

import java.util.Scanner;

import character.Hero;

public class StartOfArmor extends Armor {
	Scanner sc = new Scanner(System.in);

	public StartOfArmor(Hero hero) {
		this.hero = hero;
		this.name = "始まりの鎧";
		this.defPower = 3;
		this.price = 10;
		hero.getProtecterList().add(this);
		System.out.println(hero.getName() + "は" + this.name + "を手に入れた！");
		while (true) {
			System.out.print("すぐに装備しますか？（y、n：）");
			String ans = sc.next();
			if (ans.equals("y")) {
				equipProtecter();
				break;
			} else if (ans.equals("n")) {
				break;
			} else {
				continue;
			}
		}
	}
}
