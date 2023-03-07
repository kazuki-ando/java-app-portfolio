package field;

import equipment.StartOfSword;
import equipment.StartOfWand;
import equipment.Weapon;

public class StartOfTown extends Town {
	Weapon startOfSword;
	Weapon startOfWand;

	public StartOfTown() {
		this.townName = "始まりの街";
		townGuide();
	}

	@Override
	void townGuide() {
		do {
			System.out.println("-----[始まりの街]-----");
			System.out.println("[1] 宿屋");
			System.out.println("[2] 武器屋");
			System.out.println("[0] 街を出る");
			System.out.print("どこに行きますか？：");
			int ans = sc.nextInt();
			switch (ans) {
			case 1:
				inn();
				continue;
			case 2:
				setWeapons();
				weaponEquipmentShop();
				continue;
			case 0:
				return;
			}
		} while (true);

	}

	@Override
	void setWeapons() {
		this.startOfSword = new StartOfSword();
		this.startOfWand = new StartOfWand();

		this.weapons.add(startOfSword);
		this.weapons.add(startOfWand);

	}

}
