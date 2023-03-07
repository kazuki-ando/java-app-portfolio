package character;

import java.util.Scanner;

import equipment.Armor;
import equipment.Sword;

public class Hero extends Character {
	Scanner sc = new Scanner(System.in);

	// GetterとSetter
	public void setSword(Sword sword) {
		this.weapon = sword;
		this.attackPoint = getAttackPoint();
	}

	public void setArmor(Armor armor) {
		this.protecter = armor;
	}

	public Hero(String name) {
		this.name = name;
		this.job = "勇者";
		this.hp = 20;
		this.maxHp = 20;
		this.mp = 3;
		this.maxMp = 3;
		this.power = 4;
		this.defPower = 2;
		this.magicPower = 1;
		this.attackPoint = this.power;
		Hero.partyGold += 100;
		System.out.println(this.name + "が召喚されました");
		// パーティリストへ加入しメンバー表示
		Hero.party.add(this);
		for (Character s : Hero.party) {
			System.out.print("[現在のパーティ] ");
			System.out.println(s.getName());
		}
	}

	@Override
	public void level_Up() {
		int up_Hp;
		int up_Mp;
		int up_Power;
		int up_DefPower;
		// レベルアップ処理
		++this.lebel;
		System.out.println(this.name + "はレベルが" + this.lebel + "に上がった");
		// ステータスアップ処理
		up_Hp = (int) (this.lebel * 1.5);
		this.maxHp += up_Hp;
		up_Mp = (int) (this.lebel * 1.2);
		this.maxMp += up_Mp;
		up_Power = (int) (this.lebel * 1.2);
		this.power += up_Power;
		up_DefPower = (int) (this.lebel * 1.1);
		this.defPower += up_DefPower;
		// HPとMPを全回復
		this.hp = this.maxHp;
		this.mp = this.maxMp;
		System.out.println("HPが" + up_Hp + "、MPが" + up_Mp + "、力が" + up_Power
				+ "、防御が" + up_DefPower + "、それぞれ上がった");
	}
}
