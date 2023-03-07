package monster;

public class Chimera extends Monster {
	int fireMpCost;

	public Chimera() {
		this.name = "キメラ";
		this.hp = 30;
		this.mp = 3;
		this.power = 6;
		this.def_Power = 2;
		this.ex = 4;
		this.dropGold = 10;
		this.fireMpCost = 3;
		this.attackWord = "炎を吹いた";
		this.attackMissWord = "炎を吹こうとした";
		System.out.println(this.name + "が現れた！！");
	}

	@Override
	public void attack() {
		int num = random.nextInt(4);
		switch (num) {
		case 0:
		case 1:
		case 2:
			super.attack();
			break;
		case 3: // 一定の確率で火を吹く攻撃
			if(this.getMp() >= this.fireMpCost) {
				super.attackAll(attackWord, fireMpCost);;
				break;
			}else {	//MPが足りない場合
				super.attackAll(attackMissWord, fireMpCost);;
			}
			break;
		}
	}
}
