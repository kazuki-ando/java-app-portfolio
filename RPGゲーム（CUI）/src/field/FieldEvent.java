package field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import battle.Battle;
import character.Character;
import character.Hero;
import character.Wizard;
import equipment.Armor;
import equipment.LegendOfSword;
import equipment.Protecter;
import equipment.StartOfArmor;
import equipment.Sword;
import equipment.Weapon;
import item.Item;
import item.Yakusou;
import monster.DragonKing;
import monster.Monster;

public class FieldEvent {
	Scanner sc = new Scanner(System.in);
	Random random = new Random();
	// フィールド変数
	boolean game_Process = true;
	int totalTurn = 0;
	int north_Count;
	int west_Count;
	int south_Count;
	int east_Count;
	Wizard wiz;
	Battle bt;
	List<String> helpList = new ArrayList<>();
	List<Weapon> weaponList = new ArrayList<>();
	List<Protecter> protecterList = new ArrayList<>();

	// 1度しか発生するべきでないイベント用カウンター変数
	int heroStartingSet = 0; // 始まりの剣
	int soa = 0; // 始まりの杖
	int los = 0; // 伝説の剣
	int wizJoin = 0;// 魔法使い加入

	// 勇者の名前を決めるイベント
	public String hero_name() {
		System.out.println("勇者を召喚して冒険にでかけよう");
		System.out.print("まずは勇者の名前を決めてね：");
		String name = sc.next();
		return name;
	}

	// フィールドでの行動ループ
	public int field_Action() {
		while (this.game_Process) {
			System.out.println(); // 改行
			place_print(); // 座標表示
			fieldAction_print(); // フィールドで可能なアクションリスト表示
			String process_Input = sc.next(); // アクション選択
			switch (process_Input) { // アクション分岐
			case "1":
			case "2":
			case "3":
			case "4":
				work_count(process_Input);
				++this.totalTurn; // 歩数カウント
				help_Event(); // 総ターン数ごとヒントイベント
				fieldEvent(); // アイテムドロップ判定
				party_Event(); // 仲間イベント
				monster_Encount(); // エンカウント判定
				continue;
			case "i":
				item();
				continue;
			case "e":
				equipment();
				continue;
			case "s":
				status();
				continue;
			case "h":
				help();
				continue;
			case "go":
				this.game_Process = false;
				break;
			default:
				System.out.println("[もう一度、入力してください]");
				continue;
			}
		}
		return this.totalTurn;
	}

	// 座標を表示するメソッド
	private void place_print() {
		System.out.print("【現在地】>> ");
		// 座標0地点
		if ((north_Count | west_Count | south_Count
				| east_Count) == 0) {
			System.out.println("始まりの地");
			return;
		}
		// 座標が変動時
		// カウントが1以上で座標を表示
		if (north_Count != 0) {
			System.out.print("北 " + north_Count + " ");
		}
		if (west_Count != 0) {
			System.out.print("東 " + west_Count + " ");
		}
		if (south_Count != 0) {
			System.out.print("南 " + south_Count + " ");
		}
		if (east_Count != 0) {
			System.out.print("西 " + east_Count + " ");
		}
		System.out.println("");
	}

	// フィールドで選択できるアクションリスト表示
	private void fieldAction_print() {
		System.out.println("-----" + (totalTurn + 1) + "ターン目-----");
		System.out.println("[1：北] " + "[2：東] " + "[3：南] " + "[4：西] ");
		System.out.print("[i：アイテム]" + "[e：装備]" + "[s：ステータス]"
				+ "[h：冒険ヘルプ]" + "（go：ゲーム終了)：");
	}

	// 座標カウントメソッド
	private void work_count(String num) {
		System.out.println(""); // 改行
		// 進んだ方向でスイッチ
		switch (num) {
		case "1":
			System.out.println("勇者は北へ一歩進んだ");
			// 南が1以上の場合
			if (south_Count >= 1) {
				--this.south_Count;
				// 南が0の時
			} else if (south_Count == 0) {
				++this.north_Count;
			}
			break;
		case "2":
			System.out.println("勇者は東へ一歩進んだ");
			if (east_Count >= 1) {
				--this.east_Count;
			} else if (east_Count == 0) {
				++this.west_Count;
			}
			break;
		case "3":
			System.out.println("勇者は南へ一歩進んだ");
			if (north_Count >= 1) {
				--this.north_Count;
			} else if (north_Count == 0) {
				++this.south_Count;
			}
			break;
		case "4":
			System.out.println("勇者は西へ一歩進んだ");
			if (west_Count >= 1) {
				--this.west_Count;
			} else if (west_Count == 0) {
				++this.east_Count;
			}
			break;
		}
	}

	// 到達座標や歩数カウントでの発生イベント
	private void help_Event() {
		String help = "";
		String tizu = "地図を拾った";
		String tabibito = "旅人たちの会話が聞こえてきた";
		boolean b = false;
		// 1度しか発生するべきでないイベントはカウンター変数を利用

		// イベント一覧
		// 始まりの剣の地図
		if (totalTurn == 1) {
			System.out.println("");
			System.out.println(tizu);
			help = "北2東1の場所に街があるようだ";
			b = true;
		}
		// 魔法使いの噂
		if (totalTurn == 4) {
			System.out.println("");
			System.out.println(tabibito);
			help = "「南3東4の場所で魔法使いが仲間を探してたなぁ」";
			b = true;
		}
		// 始まりの杖の地図
		if (totalTurn == 15) {
			System.out.println("");
			System.out.println(tizu);
			help = "「南5西1の場所に宝箱あり」と書いてある";
			b = true;
		}

		// 伝説の剣の地図
		if (totalTurn == 25) {
			System.out.println("");
			System.out.println(tabibito);
			help = "「なにやら南の方に伝説の武器があるらしいぞ」";
			b = true;
		}

		// 伝説の剣の地図
		if (totalTurn == 33) {
			System.out.println("");
			System.out.println(tabibito);
			help = "「伝説の武器は南20にあることがわかったそうだ」";
			b = true;
		}

		if (totalTurn == 40) {
			System.out.println("");
			System.out.println(tizu);
			help = "どうやら北20に龍王の城がありそうだ。";
			b = true;
		}

		// 上記、ヘルプ発動時のみ実行
		if (b) {
			System.out.println(help);
			helpList.add(help);
		}

	}

	// 仲間獲得イベント
	private void party_Event() {
		// 魔法使い加入イベント
		if (south_Count == 3 && west_Count == 4 && wizJoin == 0) {
			wiz_fellow();
			wizJoin++;
		}
	}

	// アイテムや街イベント
	private void fieldEvent() {
		// 毎ターン判定イベント
		// やくそうランダム獲得
		int i = random.nextInt(9);
		if (i == 7) {
			Item yakusou = new Yakusou(Hero.party.get(0));
			System.out.println("なんと、やくそうを手に入れた");
		}
		// 始まりの街イベント
		if (north_Count == 2 && west_Count == 1) {
			System.out.println("始まりの街だ！");
			do {
				System.out.print("街の中に入りますか？（y、n）");
				String ans = sc.next();
				switch (ans) {
				case "y":
					StartOfTown sot = new StartOfTown();
					return;
				case "n":
					return;
				default:
					continue;
				}
			} while (true);

		}
		// 1度のみ発生するイベントリスト
		// 始まりの鎧ゲットイベント
		if (south_Count == 5 && east_Count == 1 && soa == 0) {
			System.out.println("宝箱を見つけた");
			Armor startOfArmor = new StartOfArmor((Hero) Hero.party.get(0));
			soa++;
		}
		// 伝説の剣のゲットイベント
		if (south_Count == 20
				&& (north_Count | west_Count | east_Count) == 0
				&& los == 0) {
			System.out.println("宝箱を見つけた");
			Sword legendOfSword = new LegendOfSword(
					(Hero) Hero.party.get(0));
			los++;
		}
	}

	// エンカウント判定
	private void monster_Encount() {
		int i = new Random().nextInt(5);
		if (north_Count == 20) {// 北20で龍王とのバトルへ移行
			System.out.println("");
			Monster dKing = new DragonKing();
			this.bt = new Battle(dKing, this.totalTurn);
		} else if (i == 4) { // 乱数4でエンカウント
			System.out.println("");
			System.out.println("モンスターだ！");
			// バトルメソッドへ移行し、コンストラクタのみで処理
			this.bt = new Battle();
		} else {
			return;
		}

	}

	// 魔法使いを仲間にするイベント
	public void wiz_fellow() {
		System.out.println("おや？魔法使いが仲間になりたがっている");
		while (true) {
			System.out.print("魔法使いを仲間にしますか？（y,ｎ）：");
			String ans = sc.next();
			if (ans.equals("y")) {
				System.out.println("魔法使いが仲間になりました");
				wiz = new Wizard("まほまほ");
				System.out.println("");
				break;
			} else if (ans.equals("n")) {
				System.out.print("もう仲間にできませんが本当によろしいですか？（y、n）：");
				ans = sc.next();
				if (ans.equals("y")) {
					System.out.println("魔法使いは淋しそうに去っていった");
					break;
				} else {
					continue;
				}
			} else {
				System.out.println("もう一度、入力してください");
				continue;
			}
		}
	}

	// アイテム使用アクション
	public void item() {
		int num = 1;
		int choose_num;
		boolean cmd = true;
		if (Hero.getItem_List().size() == 0) {
			System.out.println("アイテムをもっていません");
			return;
		}
		System.out.println("-----アイテム-----");
		for (Item item : Hero.getItem_List()) {
			System.out.println("[" + num + "] " + item.getName());
		}
		System.out.println("[0]キャンセル");
		while (cmd) {
			System.out.print("使用するアイテムを選択：");
			choose_num = sc.nextInt() - 1;
			if (choose_num >= Hero.getItem_List().size()) {
				continue;
			} else if (choose_num < 0) {
				return;
			} else {
				cmd = Hero.getItem_List().get(choose_num).use();
			}
		}
	}

	private void equipment() {
		int eNum;
		int wNum = 1;
		int choose_num;

		// 装備を確認するメンバーを選択
		while (true) {
			int num = 1;
			System.out.println("-----パーティメンバー-----");
			for (Character character : Hero.party) {
				System.out
						.println("[" + num + "] " + character.getName());
				num++;
			}
			System.out.println("[0]キャンセル");
			System.out.print("だれの装備を確認しますか？：");
			num = sc.nextInt();
			if (num == 0) {
				return;
			} else if (num > (Hero.party.size()) || num < 0) {
				continue;
			} else {
				num--;
				this.weaponList = Hero.party.get(num).getWeaponList();
				this.protecterList = Hero.party.get(num).getProtecterList();
				if (weaponList.size() == 0
						&& this.protecterList.size() == 0) {
					System.out.println("装備をもっていません");
					continue;
				}
				System.out.println(Hero.party.get(num).getName()
						+ "の武器と防具のどちらを確認しますか？");
				System.out.println("[1]：武器");
				System.out.println("[2]：防具");
				System.out.println("[0]キャンセル");
				while (true) {
					eNum = sc.nextInt();
					if (eNum == 1) {
						weapon(num);
					} else if (eNum == 2) {
						protecter(num);
					} else if (eNum == 0) {
						break;
					} else {
						continue;
					}
					break;
				}
			}
		}
	}

	// 武器アクション
	public void weapon(int num) {
		int cNum = num;
		int weaponNum = 1;
		int choose_num;
		boolean cmd = true;

		// メンバーの持つ装備を表示
		System.out.println("-----"
				+ Hero.party.get(cNum).getName() + "の武器-----");
		for (Weapon weapon : this.weaponList) {
			System.out.println(
					"[" + weaponNum + "] " + weapon.getName());
			weaponNum++;
		}
		System.out.println("[0]キャンセル");
		// 装備を選択（装備する、外す）
		while (cmd) {
			System.out.print("武器を選択してください：");
			choose_num = sc.nextInt() - 1;
			if (choose_num >= this.weaponList.size()) {
				continue;
			} else if (choose_num < 0) {
				return;
			} else {
				if (Hero.party.get(cNum).getWeapon() == null) {
					this.weaponList.get(choose_num).equip_Weapon();
					cmd = false;
				} else if (Hero.party.get(cNum).getWeapon() != null
						&& Hero.party.get(cNum).getWeapon().getName()
								.equals(this.weaponList
										.get(choose_num)
										.getName())) {
					this.weaponList.get(choose_num).remove_Weapon();
					cmd = false;
				} else if (Hero.party.get(cNum).getWeapon() != null
						&& !Hero.party.get(cNum).getWeapon().getName()
								.equals(this.weaponList
										.get(choose_num)
										.getName())) {
					System.out.print("現在、"
							+ Hero.party.get(cNum).getWeapon()
									.getName()
							+ "を装備していますが"
							+ this.weaponList.get(choose_num)
									.getName()
							+ "に変更しますか？（y、n）：");
					String ans = sc.next();
					switch (ans) {
					case "y":
						Hero.party.get(cNum).getWeapon()
								.remove_Weapon();
						this.weaponList.get(choose_num)
								.equip_Weapon();
						cmd = false;
						break;
					case "n":
						return;
					default:
						continue;
					}
				}
			}
		}
	}

	// 防具アクション
	public void protecter(int num) {
		int cNum = num;
		int pNum = 1;
		int choose_num;
		boolean cmd = true;

		// メンバーの持つ装備を表示
		System.out.println("-----"
				+ Hero.party.get(cNum).getName() + "の防具-----");
		for (Protecter protecter : this.protecterList) {
			System.out.println(
					"[" + pNum + "] " + protecter.getName());
			pNum++;
		}
		System.out.println("[0]キャンセル");
		// 装備を選択（装備する、外す）
		while (cmd) {
			System.out.print("武器を選択：");
			choose_num = sc.nextInt() - 1;
			if (choose_num >= this.protecterList.size()) {
				continue;
			} else if (choose_num < 0) {
				return;
			} else {
				if (Hero.party.get(cNum).getProtecter() == null) {
					this.protecterList.get(choose_num)
							.equipProtecter();
					cmd = false;
				} else if (Hero.party.get(cNum).getProtecter() != null
						&& Hero.party.get(cNum).getProtecter()
								.getName()
								.equals(this.protecterList
										.get(choose_num)
										.getName())) {
					this.protecterList.get(choose_num)
							.removeProtecter();
					cmd = false;
				} else if (Hero.party.get(cNum).getProtecter() != null
						&& !Hero.party.get(cNum).getProtecter()
								.getName()
								.equals(this.protecterList
										.get(choose_num)
										.getName())) {
					System.out.print("現在、"
							+ Hero.party.get(cNum).getProtecter()
									.getName()
							+ "を装備していますが"
							+ this.protecterList.get(choose_num)
									.getName()
							+ "に変更しますか？（y、n）：");
					String ans = sc.next();
					switch (ans) {
					case "y":
						Hero.party.get(cNum).getProtecter()
								.removeProtecter();
						this.protecterList.get(choose_num)
								.equipProtecter();
						cmd = false;
						break;
					case "n":
						return;
					default:
						continue;
					}
				}
			}
		}
	}

	// キャラステータス表示アクション
	public void status() {
		int num = 1;
		System.out.println("-----パーティ一覧-----");
		for (Character character : Hero.party) {
			System.out
					.println("[" + num + "] " + character.getName());
			num++;
		}
		System.out.println("[0]キャンセル");
		while (true) {
			System.out.print("誰のステータスを確認しますか？：");
			int choose_Num = sc.nextInt() - 1;
			if (choose_Num >= Hero.party.size()) {
				continue;
			} else if (choose_Num < 0) {
				return;
			} else {
				Hero.party.get(choose_Num).show_Status();
				return;
			}
		}
	}

	// 獲得したヘルプの表示メソッド
	public void help() {
		System.out.println("");
		System.out.println("-----冒険のヒント一覧-----");
		if (this.helpList.size() == 0) {
			System.out.println("まだヒントを獲得していません");
		} else {
			helpList.forEach(s -> System.out.println(s)); // ラムダ式拡張for文
		}
	}
}
