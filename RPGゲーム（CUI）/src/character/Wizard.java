package character;

import java.util.Scanner;

import equipment.Wand;
import magic.Fireball;
import magic.Heal;

public class Wizard extends Character {
	// インスタンスカウント
	static int wizCounter;
	// フィールド
	Scanner sc = new Scanner(System.in);

	// GetterとSetter
	public void set_Wand(Wand wand) {
		this.weapon = wand;
		this.attackPoint = getAttackPoint();
	}

	public Wizard(String name) {
		this.name = name;
		this.job = "魔法使い";
		this.hp = 15;
		this.maxHp = 15;
		this.mp = 5;
		this.maxMp = 5;
		this.power = 1;
		this.defPower = 1;
		this.magicPower = 4;
		// パーティリストへ加入しメンバー表示
		Hero.party.add(this);
		System.out.print("[現在のパーティ] ");
		for (Character s : party) {
			System.out.print(s.getName() + " ");
		}
		wizCounter++;
	}

	@Override
	public void level_Up() {
		int up_Hp;
		int up_Mp;
		int up_Power;
		int up_DefPower;
		int up_MagicPower;
		// レベルアップ処理
		++this.lebel;
		System.out.println(this.name + "はレベルが" + this.lebel + "に上がった");
		// ステータスアップ処理
		up_Hp = (int) (this.lebel * 1.3);
		this.maxHp += up_Hp;
		up_Mp = (int) (this.lebel * 1.7);
		this.maxMp += up_Mp;
		up_Power = (int) (this.lebel * 1.1);
		this.power += up_Power;
		up_DefPower = (int) (this.lebel * 1.0);
		this.defPower += up_DefPower;
		up_MagicPower = (int) (this.lebel * 1.4);
		this.defPower += up_MagicPower;
		// HPとMPを全回復
		this.hp = this.maxHp;
		this.mp = this.maxMp;
		System.out.println("HPが" + up_Hp +
				"、MPが" + up_Mp +
				"、力が" + up_Power+
				"、防御が" + up_DefPower +
				"、魔力が" + up_MagicPower +
				"、それぞれ上がった");

		// レベル2でヒール・ファイアボール取得
		if (this.lebel == 2) {
			Heal heal = new Heal();
			heal.magic_Memorize(this, magicList);
			Fireball fireball = new Fireball();
			fireball.magic_Memorize(this, magicList);
		}
	}
}
