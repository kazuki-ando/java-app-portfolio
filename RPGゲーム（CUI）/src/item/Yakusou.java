package item;

import java.util.Random;
import java.util.Scanner;

import character.Character;
import character.Hero;

public class Yakusou extends Item {
	private String name = "やくそう";
	final int heal_HP = 10;
	Scanner sc = new Scanner(System.in);
	private static int yakusou_Counter;

	public Yakusou(Character c) {
		c.setItem(this);
		yakusou_Counter++;
	}


	@Override
	public boolean use(){
		int num = 1;
		boolean heal_Fin = true;

		//回復するメンバーを表示
		System.out.println("[現在のHP]");
		for(Character character : Hero.party) {
			System.out.println(
				"["
				+ num
				+ "]"
				+ character.getName()
				+ "のHP："
				+ character.getHp());
			num++;
		}
		System.out.println("[0]キャンセル");
		System.out.println();
		//回復するメンバーを決定
		while(heal_Fin) {
			System.out.print("誰を回復しますか？：");
			int input = sc.nextInt() - 1;
			if(Hero.party.size() <= input) {
				continue;
			}else if(input < 0){
				return true;
			}else if(Hero.party.get(input).getHp() == 0) {
				System.out.println(
						Hero.party.get(input).getName()
						+ "は戦闘不能のため、回復できません"
						);
				continue;
			}else {
				//薬草の回復値を決定
				int yakusou_Point = heal_HP + new Random().nextInt(3);
				int heal_Point;
				//実際の回復値、またはHPの最大値までに必要な値の
				//どちらか少ない方をHPに加算する
				//（HPの最大値を超えない）
				Hero.party.get(input).setHp(
						heal_Point = Math.min(yakusou_Point,
								Hero.party.get(input).possible_HealPoint()
								));
				System.out.println(
						Hero.party.get(input).getName()
						+ "はHPが"
						+ heal_Point
						+ "回復した！"
						);
				Hero.getItem_List().remove(this);
				yakusou_Counter--;
				return false;
			}
		}
		return true;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
