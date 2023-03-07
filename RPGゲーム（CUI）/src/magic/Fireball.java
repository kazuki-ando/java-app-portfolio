package magic;

import battle.Battle;
import character.Character;
import monster.Monster;

public class Fireball extends Magic{
	int magicPower = 6;
	private String skillWord;


	public Fireball() {
		this.name = "ファイアボール";
		this.mpCost = 2;
		this.skillWord = this.name + "を唱えた";
	}

	@Override
	public boolean magic_Use(Character character) {
		int dmg;
		int result = 0;
		this.character = character;

		// 攻撃結果処理
		System.out.println("-----" + character.getName() + "のターン-----");
		if (character.getMp() < this.mpCost) {
			System.out.println("MPが足りません");
			return true;
		}else {
			System.out.println(character.getName() + "は" + skillWord);
			for (Monster monster : Battle.monster_List) {
				this.monster = monster;
				dmg = Math.max(1,
						(character.getMagicPower() + this.magicPower + random.nextInt(4) - monster.getDefPower()));
				if (monster.getHp() - dmg <= 0) {
					result = dmg + (monster.getHp() - dmg);
				} else {
					result = dmg;
				}
				character.setMp(this.mpCost);
				monster.setDmg(result);
				dmg_Result(dmg);
				return false;
			}
		}
		return true;
	}
}
