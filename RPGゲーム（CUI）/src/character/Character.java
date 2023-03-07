package character;

import static battle.Battle.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import battle.Battle;
import equipment.Protecter;
import equipment.Weapon;
import item.Item;
import magic.Magic;
import monster.Monster;

/**
 * @author user
 *
 */
public abstract class Character {
	Scanner sc = new Scanner(System.in);
	// ステータスフィールド
	protected String name;
	protected String job;
	protected Weapon weapon;
	protected Protecter protecter;
	protected int lebel = 1;
	protected int hp;
	protected int maxHp;
	protected int mp;
	protected int maxMp;
	protected int total_Ex = 0;
	protected int power;
	protected int defPower;
	protected int magicPower;
	protected int attackPoint;
	protected int defPoint;
	protected boolean turn_Hantei;
	protected Monster monster;
	protected List<Magic> magicList = new ArrayList<>(); // 習得した魔法リスト
	protected List<Weapon> weaponList = new ArrayList<>(); // キャラごとの武器リスト
	protected List<Protecter> protecterList = new ArrayList<>(); // キャラごとの防具リスト
	private int monster_KillCount;
	// staticフィールド
	public static int partyGold; //パーティの所持金
	public static int battleEx; // 戦闘終了時に獲得予定の経験値変数
	public static int battleGold; //戦闘時の獲得ゴールド
	public static List<Character> party = new ArrayList<>();
	private static List<Item> item_List = new ArrayList<>();

	// GetterとSetter
	public String getJob() {
		return this.job;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public Protecter getProtecter() {
		return this.protecter;
	}

	public void setHp(int heal_Point) {
		this.hp += heal_Point;
	}

	public int getHp() {
		return this.hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int hp) {
		this.maxHp += hp;
	}

	public void setMp(int mp) {
		this.mp -= mp;
	}

	public int getMp() {
		return this.mp;
	}

	public int getMaxMp() {
		return maxMp;
	}

	public void setMaxMp(int mp) {
		this.maxMp += mp;
	}

	public void setDefPower(int defPower) {
		this.defPower += defPower;
	}

	public int getDefPower() {
		return this.defPower;
	}

	public int getMagicPower() {
		return magicPower;
	}

	public int possible_HealPoint() {
		return this.maxHp - this.hp;
	}

	public int get_Level() {
		return this.lebel;
	}

	public int getAttackPoint() {
		if (this.weapon != null) {
			return this.power + weapon.getPower();
		} else {
			return this.power;
		}
	}

	public int getDefPoint() {
		if (this.protecter != null) {
			return this.defPower + protecter.getDefPower();
		} else {
			return this.defPower;
		}
	}

	public void setDmg(int dm) {
		this.hp -= dm;
	}

	public Monster attacked_Target() {
		return this.monster;
	}

	public void setItem(Item item) {
		Hero.getItem_List().add(item);
	}

	public static List<Item> getItem_List() {
		return item_List;
	}

	public static void setItem_List(List<Item> item_List) {
		Character.item_List = item_List;
	}

	public List<Weapon> getWeaponList() {
		return this.weaponList;
	}

	public List<Protecter> getProtecterList() {
		return this.protecterList;
	}

	public void setTurn_Hantei(boolean ans) {
		this.turn_Hantei = ans;
	}

	public void setEx() {
		this.total_Ex += Hero.battleEx;
	}

	public static int getPartyGold() {
		return partyGold;
	}

	public static void setPartyGold(int money) {
		Character.partyGold += money;
	}

	// 戦闘時コマンドメソッド
	public String battle_Cmd() {
		turn_Hantei = true;
		String reword = "続行";
		String[] cmd_Wiz = { "[1]：攻撃", "[2]：魔法", "[3]：アイテムを使う", "[4]：逃げる",
				"（数字で入力）" };
		// コマンドの表示
		while (turn_Hantei) {
			System.out.println(); // 改行
			System.out.println("-----" + this.name + "のターン-----");
			for (String str : cmd_Wiz) {
				System.out.println(str);
			}
			// コマンドの選択分岐
			int i = sc.nextInt();
			switch (i) {
			case 1:
				attack_Target();
				reword = "攻撃";
				break;
			case 2:
				if (this.magicList.size() == 0) {
					System.out.println("【魔法を覚えていません】");
					continue;
				}
				choose_magic();
				reword = "魔法";
				break;
			case 3:
				if (Hero.getItem_List().size() == 0) {
					System.out.println("【アイテムを所有していません】");
					continue;
				}
				choose_Item();
				reword = "アイテム";
				break;
			case 4:
				turn_Hantei = false;
				reword = "逃げる";
				break;
			default:
				break;
			}
		}
		return reword;
	}

	public void attack_Target() {
		// モンスターの生き残りを抽出
		List<Monster> monster_Suvlist = new ArrayList<>();
		for (Monster monster : Battle.monster_List) {
			if (monster.getHp() != 0) {
				monster_Suvlist.add(monster);
			}
		}
		// 攻撃対象を選択
		while (turn_Hantei) {
			if (Battle.monster_List.size() != 1) {
				int num = 1;
				int input = 0;
				for (Monster monster : monster_Suvlist) {
					System.out.println("[" + num + "]" + monster.getName()
							+ " HP：" + monster.getHp());
					num++;
				}
				System.out.println("[0]：キャンセル");
				System.out.println("");
				System.out.print("どのモンスターを攻撃しますか？：");
				input = sc.nextInt() - 1;
				if (input < 0) {
					return;
				} else if (monster_Suvlist == null) {
					return;
				} else if (input >= monster_Suvlist.size()) {
					continue;
				} else {
					this.monster = monster_Suvlist.get(input);
					attack_Result();
				}
			} else {
				this.monster = monster_Suvlist.get(0);
				attack_Result();
			}
		}
	}

	public void attack_Result() {
		// ダメージ処理
		int attack_Result = 0;
		System.out.println(this.name + "の攻撃");
		int dmg = Math.max(1, (this.attackPoint + new Random().nextInt(5)
				- this.monster.getDefPower()));
		if (this.monster.getHp() - dmg <= 0) {
			attack_Result = dmg + (this.monster.getHp() - dmg);
		} else {
			attack_Result = dmg;
		}
		this.monster.setDmg(attack_Result);
		dmg_Result(dmg);
		setTurn_Hantei(false);
	}

	public void dmg_Result(int dmg) {
		System.out.println(getName() + "は" + this.monster.getName() + "に" + dmg
				+ "のダメージを与えた！");
		if (this.monster.getHp() == 0) {
			System.out
					.println(getName() + "は" + this.monster.getName() + "を倒した");
			setKillCount();
			monster_List.remove(this.monster);
			Hero.battleEx += this.monster.getEx();
			Hero.battleGold += this.monster.getDropGold();
		}
	}

	//
	public void choose_magic() {
		int input = 1;
		System.out.println("【魔法リスト】");
		for (Magic magic : magicList) {
			System.out.println("[" + input + "]" + magic.getName() + " 消費MP："
					+ magic.getMpCost());
			input++;
		}
		System.out.println("[0]キャンセル");
		System.out.println();
		// 使う魔法を決定
		while (turn_Hantei) {
			System.out.print("どの魔法を使用しますか？：");
			input = sc.nextInt() - 1;
			if (magicList.size() <= input) {
				continue;
			} else if (input < 0) {
				break;
			} else {
				if ((this.mp - magicList.get(input).getMpCost()) >= 0) {
					setTurn_Hantei(magicList.get(input).magic_Use(this));
				} else {
					System.out.println("MPが足りません");
					break;
				}
			}
			break;
		}
	}

	// アイテム表示と選択
	public void choose_Item() {
		int input = 0;
		int number = 1;
		// アイテムリスト表示
		System.out.println("----所有アイテム一覧----");
		for (Item i : Hero.getItem_List()) {
			System.out.println("[" + number + "]" + i.getName());
			number++;
		}
		System.out.println("[0]キャンセル");
		System.out.println();
		// 使用アイテム選択
		while (turn_Hantei) {
			System.out.print("どのアイテムを使用しますか？：");
			input = sc.nextInt() - 1;
			if (Hero.getItem_List().size() <= input) {
				continue;
			} else if (input < 0) {
				return;
			} else {
				break;
			}
		}
		setTurn_Hantei(Hero.getItem_List().get(input).use());
	}

	public void run() {
		if (Hero.party.size() == 1) {
			System.out.println(this.name + "は逃げ出した");
		} else {
			System.out.println(this.name + "たちは逃げ出した");
		}
	}

	public void setKillCount() {
		this.monster_KillCount++;
	}

	public int getKillCount() {
		return this.monster_KillCount;
	}

	public void show_Status() {
		System.out.println("-----" + this.name + "のステータス-----");
		System.out.println("レベル：" + this.lebel);
		System.out.println("HP：" + this.hp + "/" + this.maxHp);
		System.out.println("MP：" + this.mp + "/" + this.maxMp);
		System.out.println("ちから：" + this.power);
		System.out.println("たいりょく：" + this.defPower);
		System.out.println("まりょく：" + this.magicPower);
		System.out.println("攻撃力：" + (this.attackPoint = getAttackPoint()));
		System.out.println("防御力：" + (this.defPoint = getDefPoint()));
		// 武器装備ありなら表示
		if (weapon != null) {
			System.out.println("武器：" + this.weapon.getName());
		}
		// 防具装備ありなら表示
		if (protecter != null) {
			System.out.println("防具：" + this.protecter.getName());
		}
		System.out.println("所持ゴールド：" + Hero.partyGold);
	}

	public void ex_table() {
		if (this.total_Ex >= 60 && get_Level() < 8) {
			level_Up();
		} else if (this.total_Ex >= 45 && get_Level() < 7) {
			level_Up();
		} else if (this.total_Ex >= 32 && get_Level() < 6) {
			level_Up();
		} else if (this.total_Ex >= 20 && get_Level() < 5) {
			level_Up();
		} else if (this.total_Ex >= 10 && get_Level() < 4) {
			level_Up();
		} else if (this.total_Ex >= 5 && get_Level() < 3) {
			level_Up();
		} else if (this.total_Ex >= 2 && get_Level() < 2) {
			level_Up();
		} else {
			return;
		}
	}

	// キャラ毎にレベルアップ時の内容を記述
	abstract void level_Up();
}
