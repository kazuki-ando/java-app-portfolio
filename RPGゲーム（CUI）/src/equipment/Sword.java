package equipment;

import java.util.Scanner;

import character.Hero;

public abstract class Sword implements Weapon {
	Scanner sc = new Scanner(System.in);

	//ステータスフィールド
	String name; //武器の名前
	int power; //武器の攻撃力
	Hero hero; //装備キャラ
	int price; //武器の値段
	//GetterとSetter

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getPower() {
		return this.power;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void equip_Weapon() {
		hero.setSword(this);
		System.out.println(hero.getName() + "は" + this.name + "を装備した");
	}

	@Override
	public void remove_Weapon() {
		hero.setSword(null);
		System.out.println(hero.getName() + "は" + this.name + "を外した");
	}

	public void getSword() {
		hero.getWeaponList().add(this);
		System.out.println(hero.getName() + "は" + this.name + "を手に入れた！");
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
