package monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import character.Character;
import character.Hero;

public abstract class Monster {
	Random random = new Random();
	// ステータスフィールド
	protected String name;
	protected int hp;
	protected int mp;
	protected int power;
	protected int def_Power;
	protected int ex;
	protected int dropGold;
	Character character;
	protected String attackWord;
	protected String attackMissWord;
	// staticフィールド
	static int kill_Result;

	// GetterとSetter
	public String getName() {
		return this.name;
	}

	public void setHp(int hp) {
		this.hp += hp;
	}

	public int getHp() {
		return this.hp;
	}

	public void setMp(int mp) {
		this.mp -= mp;
	}

	public int getMp() {
		return this.mp;
	}

	public void setPower(int power) {
		this.power += power;
	}

	public void setDefPower(int defPower) {
		this.def_Power += defPower;
	}

	public int getDefPower() {
		return this.def_Power;
	}

	public void setDmg(int i) {
		this.hp -= i;
	}

	public Character attacked_Target() {
		return this.character;
	}

	public int getEx() {
		return this.ex;
	}

	public int getDropGold() {
		return this.dropGold;
	}

	public int get_KillResult() {
		return Monster.kill_Result;
	}

	public void set_KillResult() {
		Monster.kill_Result++;
	}

	// モンスターの単体攻撃メソッド
	public void attack() {
		int dmg;
		int result = 0;
		int random_Index;
		List<Character> suvlist = new ArrayList<>();
		// パーティの生き残りを抽出
		for (Character character : Hero.party) { // 勇者パーティの生き残りを抽出
			if (character.getHp() != 0) {
				suvlist.add(character);
			}
		}
		System.out.println("-----" + this.name + "のターン-----");
		System.out.println(this.name + "の攻撃");
		// 抽出したメンバーからランダムでアタック対象選択し攻撃
		while (true) {
			random_Index = random.nextInt(suvlist.size());
			if (suvlist.get(random_Index).getHp() == 0) { // 対象のHPが0の場合は再抽選
				continue;
			}
			this.character = suvlist.get(random_Index);
			dmg = Math.max(1,
					(this.power + random.nextInt(4) - character.getDefPoint()));
			if (character.getHp() - dmg <= 0) {
				result = dmg + (character.getHp() - dmg);
			} else {
				result = dmg;
			}
			character.setDmg(result);
			dmg_Result(dmg);
			break;
		}
	}

	//モンスターの全体攻撃メソッド
	public void attackAll(String skillWord, int mpcost) {
		int dmg;
		int result = 0;
		List<Character> suvlist = new ArrayList<>();

		// パーティの生き残りを抽出
		for (Character character : Hero.party) { // 勇者パーティの生き残りを抽出
			if (character.getHp() != 0) {
				suvlist.add(character);
			}
		}
		// 攻撃結果処理
		System.out.println("-----" + this.name + "のターン-----");
		if (this.mp < 2) {
			System.out.println(skillWord);
			System.out.println("しかし、MPが足りなかったようだ");
			return;
		}else {
			System.out.println(this.name + "は" + skillWord);
			for (Character character : suvlist) {
				this.character = character;
				dmg = Math.max(1,
						(this.power + random.nextInt(4) - character.getDefPoint()));
				if (character.getHp() - dmg <= 0) {
					result = dmg + (character.getHp() - dmg);
				} else {
					result = dmg;
				}
				setMp(mpcost);
				character.setDmg(result);
				dmg_Result(dmg);
			}
		}
	}

	// 攻撃の結果処理
	public void dmg_Result(int dmg) {
		System.out.println(getName() + "は" + character.getName() + "に" + dmg
				+ "のダメージを与えた！");
		if (character.getHp() == 0) {
			System.out.println(character.getName() + "はHPが0になった");
		}
	}
}
