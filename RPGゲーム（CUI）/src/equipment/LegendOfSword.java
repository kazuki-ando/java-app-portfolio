package equipment;

import java.util.Scanner;

import character.Hero;

public final class LegendOfSword extends Sword {
	Scanner sc = new Scanner(System.in);

	public LegendOfSword(Hero hero) {
		this.hero = hero;
		this.name = "伝説の剣";
		this.power = 99;
		this.price = 0;
		hero.getWeaponList().add(this);
		getSword();
	}

	@Override
	public void getSword() {
		hero.getWeaponList().add(this);
		System.out
				.println("なんと" + hero.getName() + "は" + this.name + "を手に入れた！");
		while (true) {
			System.out.print("すぐに装備しますか？（y、n：）");
			String ans = sc.next();
			if (ans.equals("y")) {
				equip_Weapon();
				break;
			} else if (ans.equals("n")) {
				break;
			} else {
				continue;
			}
		}
	}
}
