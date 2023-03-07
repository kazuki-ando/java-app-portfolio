package field;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import character.Character;
import character.Hero;
import character.Wizard;
import equipment.StartOfSword;
import equipment.StartOfWand;
import equipment.Sword;
import equipment.Wand;
import equipment.Weapon;

public abstract class Town {
	Scanner sc = new Scanner(System.in);
	String townName;
	int innPrice = 10; //初期値（街ごとに変更）
	List<Character> party;
	List<Weapon> weapons = new ArrayList<>();

	public String getName() {
		return townName;
	}

	public void setName(String name) {
		this.townName = name;
	}

	public int getInnPrice() {
		return innPrice;
	}

	public void setInnPrice(int innPrice) {
		this.innPrice = innPrice;
	}

	public void inn() {
		int innPriceResult = -(this.innPrice * Hero.party.size());
		System.out.println("-----宿屋-----");
		System.out.println("優しそうな店主「いらっしゃい、1人" + this.innPrice + "ゴールドだよ」");
		System.out.print("泊まっていきますか？（y、n）");
		do {
			String ans = sc.next();
			switch (ans) {
			case "y":
				System.out.println("みんなぐっすり眠った");
				System.out.println("HPとMPが全回復した");
				for (Character character : Hero.party) {
					character.setHp(character.getMaxHp() - character.getHp());
					character.setMp(character.getMaxMp() - character.getMp());
				}
				Hero.setPartyGold(innPriceResult);
				return;
			case "n":
				System.out.println("優しそうな店主「そうかい、また来なよ」");
				return;
			default:
				continue;
			}
		} while (true);
	}

	public void weaponEquipmentShop() {
		System.out.println("こわもての店主「…。らっしゃい…。」");
		System.out.println("-----武器屋-----");
		do {
			int num = 1;
			for (Weapon weapon : this.weapons) {
				System.out.println("[" + num + "] " + weapon.getName() + " "
						+ weapon.getPrice() + "ゴールド");
				num++;
			}
			System.out.println("[0] キャンセル");
			System.out.println("（所持ゴールド：" +  Hero.getPartyGold() + "）");
			System.out.print("どの商品を買いますか？：");
			int ans = sc.nextInt() - 1;
			System.out.println("");
			if (ans < 0) {
				System.out.println("こわもての店主「…。また来いよ…。」");
				break;
			} else if (ans >= this.weapons.size()) {
				continue;
			} else {
				weaponTotal(this.weapons.get(ans).getName(),
						this.weapons.get(ans).getPrice(), ans);
			}
		} while (true);
	}

	public void weaponTotal(String name, int price, int index) {
		if ((Hero.getPartyGold() - price) < 0) {
			System.out.println("お金が足りません");
			return;
		} else {
			switch (name) {
			case "始まりの剣":
				Sword startOfSword = new StartOfSword((Hero) Hero.party.get(0));
				Hero.setPartyGold(-(price));
				startOfSword.getSword();
				this.weapons.remove(index);
				break;
			case "始まりの杖":
				if (Hero.party.size() > 1) {
					Wand startOfWand = new StartOfWand(
							(Wizard) Hero.party.get(1));
					Hero.setPartyGold(-(price));
					startOfWand.getWand();
					this.weapons.remove(index);
				} else {
					System.out.println("装備できるキャラがいません");
					break;
				}
			}
		}
	}

	public Town() {
		this.party = Hero.party;
	}

	//街のガイドメソッド
	abstract void townGuide();

	//武器屋リストを街ごとに変更
	abstract void setWeapons();
}
