package magic;

import static battle.Battle.*;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import character.Character;
import character.Hero;
import monster.Monster;

public abstract class Magic {
	Scanner sc = new Scanner(System.in);
	Random random = new Random();
	String name;
	Character character;
	Monster monster;
	int mpCost;

	// Getter＆Setter
	public String getName() {
		return this.name;
	}

	public int getMpCost() {
		return mpCost;
	}

	// 魔法を覚える時の挙動
	public void magic_Memorize(Character character, List<Magic> magic_List) {
		magic_List.add(this);
		System.out.println(character.getName() + "は【" + this.name + "】を覚えた！");
	}

	// 魔法ごとに使用効果を記載
	public abstract boolean magic_Use(Character character);
	
	//攻撃魔法用のダメージ処理
	public void dmg_Result(int dmg) {
		System.out.println(getName() + "は" + this.monster.getName() + "に" + dmg
				+ "のダメージを与えた！");
		if (this.monster.getHp() == 0) {
			System.out
					.println(getName() + "は" + this.monster.getName() + "を倒した");
			character.setKillCount();
			monster_List.remove(this.monster);
			Hero.battleEx += this.monster.getEx();
			Hero.battleGold += this.monster.getDropGold();
		}
	}
}
