package monster;

public class DragonKing extends Monster {
	int deathFireMpCost;

	public DragonKing() {
		this.name = "龍王";
		this.hp = 99;
		this.mp = 22;
		this.power = 22;
		this.def_Power = 11;
		this.ex = 9999;
		this.deathFireMpCost = 10;
		this.attackWord = "死の炎を吹いた";
		this.attackMissWord = "死の炎を吹こうとした";
		System.out.println("なんと、" + this.name + "が現れた！！");
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
			if(this.getMp() >= this.deathFireMpCost) {
				super.attackAll(attackWord, deathFireMpCost);;
				break;
			}else {	//MPが足りない場合
				super.attackAll(attackMissWord, deathFireMpCost);;
			}
			break;
		}

	}

}
