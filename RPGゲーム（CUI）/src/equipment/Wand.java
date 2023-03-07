package equipment;

import java.util.Scanner;

import character.Wizard;

public class Wand implements Weapon {
	Scanner sc = new Scanner(System.in);

	//ステータスフィールド
	String name; //武器の名前
	int power; //武器の攻撃力
	int magicPower; //武器の魔力
	Wizard wiz; //装備キャラ
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
		return this.price;
	}

	//装備する
	@Override
	public void equip_Weapon() {
		wiz.set_Wand(this);
		System.out.println(wiz.getName() + "は" + this.name + "を装備した");
	}

	//装備を外す
	@Override
	public void remove_Weapon() {
		wiz.set_Wand(null);
		System.out.println(wiz.getName() + "は" + this.name + "を外した");
	}

	public void getWand() {
		wiz.getWeaponList().add(this);
		System.out.println(wiz.getName() + "は" + this.name + "を手に入れた！");
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
