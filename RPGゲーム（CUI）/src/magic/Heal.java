package magic;

import java.util.Random;

import character.Character;
import character.Hero;

public class Heal extends Magic {
	int heal_Point = 5;

	public Heal() {
		this.name = "ヒール";
		this.mpCost = 2;
	}

	@Override
	public boolean magic_Use(Character character) {
		int num = 1;

		// 回復するメンバーを表示
		System.out.println("【現在のHP】");
		for (Character ch : Hero.party) {
			System.out.println("[" + num + "]" + ch.getName() + "のHP："
					+ ch.getHp());
			num++;
		}
		System.out.println("[0]キャンセル");
		System.out.println();
		// 回復するメンバーを決定
		while (true) {
			System.out.print("誰を回復しますか？：");
			int input = sc.nextInt() - 1;
			if (Hero.party.size() <= input) {
				continue;
			} else if (input < 0) {
				return true;
			} else if (Hero.party.get(input).getHp() == 0) {
				System.out.println(
						Hero.party.get(input).getName() + "は戦闘不能のため、回復できません");
				continue;
			} else {
				// ヒールの回復値を決定
				heal_Point = heal_Point + new Random().nextInt(3);
				int heal_Result;
				// 実際の回復値、またはHPの最大値までに必要な値の
				// どちらか少ない方をHPに加算する
				// （HPの最大値を超えない）
				Hero.party.get(input)
						.setHp(heal_Result = Math.min(heal_Point,
								Hero.party.get(input).possible_HealPoint()));
				System.out.println(Hero.party.get(input).getName() + "はHPが"
						+ heal_Result + "回復した！");
				//回復値をHPへ反映
				Hero.party.get(input).setHp(heal_Result);
				//消費MPを反映
				character.setMp(this.mpCost);
				return false;
			}
		}
	}
}
