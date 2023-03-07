package equipment;

import character.Hero;

public abstract class Armor implements Protecter {
	// ステータスフィールド
	String name; // 防具の名前
	int defPower; // 防具の防御力
	Hero hero; // 装備キャラ
	int price; // 防具の値段
	// GetterとSetter

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getDefPower() {
		return this.defPower;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void equipProtecter() {
		hero.setArmor(this);
		System.out.println(hero.getName() + "は" + this.name + "を装備した");
	}

	@Override
	public void removeProtecter() {
		hero.setArmor(null);
		System.out.println(hero.getName() + "は" + this.name + "を外した");
	}
}
