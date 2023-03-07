package battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import character.Character;
import character.Hero;
import main_system.GameResult;
import monster.Chimera;
import monster.Matango;
import monster.Monster;

public class Battle {
	Random random = new Random();
	String battle_Situation = "続行";
	int turn_Num = 1;
	int partyDeath;
	int monsterDeath;
	protected int random_Member;
	protected int random_Mclass;

	GameResult gameResult;
	Monster monster;
	public static List<Monster> monster_List = new ArrayList<>();

	// フィールドにパーティとモンスターをセットし
	// 戦闘プログラムへ移行するコンストラクタ
	// アイテム未所持
	public Battle() {
		monster_List = new ArrayList<>();
		this.monsterDeath = 0;
		Hero.battleEx = 0;
		Hero.battleGold = 0;
		set_Mlist(); // モンスターをプレイヤーレベルに応じてランダム数セット
		fight(); // バトルシステムの繰り返し処理
		battle_Fin(); //バトル結果処理
	}
	//龍王戦のコンストラクタ
	public Battle(Monster monster, int totalTrun) {
		monster_List = new ArrayList<>();
		this.monster = monster;
		monster_List.add(this.monster);
		this.monsterDeath = 0;
		Hero.battleEx = 0;
		Hero.battleGold = 0;
		
		while (true) {
			fight(); // バトルシステムの繰り返し処理
			if (battle_Situation.equals("勝利")) {
				gameResult = new GameResult();
				gameResult.Clear(totalTrun); // ゲームクリア
			} else if (battle_Situation.equals("逃走")) {
				System.out.println("しかし、龍王からは逃げられない！");
				battle_Situation = "続行";
			} else {
				battle_Fin();
			}
		}
	}

	// 出現モンスターを決めるメソッド
	public void set_Mlist() {
		// 出現モンスターリスト
		Matango matango;
		Chimera chimera;
		// モンスター出現数を乱数で決定（最大で4体まで）
		random_Member = Math.max(1, random.nextInt(Math
				.min(Hero.party.get(0).get_Level(), 5)));
		// モンスターの種類を乱数で決定（種類は勇者レベルに応じる）
		for (int i = 0; i < random_Member; i++) {
			random_Mclass = random.nextInt(
					Hero.party.get(0).get_Level() + 3);
			if (random_Mclass == 0) {
				matango = new Matango();
				Battle.monster_List.add(matango);
			} else if (random_Mclass == 1
					&& Hero.party.get(0).get_Level() >= 3) {
				chimera = new Chimera();
				Battle.monster_List.add(chimera);
			} else {
				if (Hero.party.get(0).get_Level() < 3) {
					matango = new Matango();
					Battle.monster_List.add(matango);
				} else {
					chimera = new Chimera();
					Battle.monster_List.add(chimera);
				}
			}
		}
	}

	// 戦闘メソッド
	public void fight() {
		while (battle_Situation.equals("続行")) {
			partyDeath = 0;
			statusPrint();
			switch (turn_Num) {
			case 1: // パーティのターンメソッド
				party_Turn();
				// 逃げるコマンド以外なら状況判定メソッド
				if (!battle_Situation.equals("逃走")) {
					battle_Situation = battle_Judge(); // 勝敗判定
				}
				break;
			case 2: // モンスターのHPが1以上で、モンスターのターンメソッド
				if (this.monsterDeath != Battle.monster_List.size()) {
					monster_Turn();
				}
				battle_Situation = battle_Judge(); // 勝敗判定
				break;
			}
		}
	}

	// 戦闘状況の判定メソッド
	public String battle_Judge() {
		if (partyDeath == Hero.party.size()) {
			return "全滅";
		} else if (this.monsterDeath == Battle.monster_List
				.size()) {
			return "勝利";
		}
		return "続行";
	}

	// ターン開始時のステータス表示メソッド
	public void statusPrint() {
		System.out.println(" "); // 改行
		System.out.println("【パーティー】");
		for (int i = 0; i < Hero.party.size(); i++) {
			System.out.println("["
					+ Hero.party.get(i).getJob() + "] HP："
					+ Hero.party.get(i).getHp() + " MP："
					+ Hero.party.get(i).getMp());
		}
		System.out.println("【モンスター】");
		for (int i = 0; i < Battle.monster_List
				.size(); i++) {
			System.out.println("["
					+ Battle.monster_List.get(i).getName()
					+ "] HP："
					+ Battle.monster_List.get(i).getHp());
		}
	}

	// パーティーのターンメソッド
	public void party_Turn() {
		// パーティ人数の回数、味方のターンメソッド動作
		for (Character character : Hero.party) {
			// パーティ内でHPが0の者をカウント
			if (character.getHp() == 0) {
				++partyDeath;
				// モンスターのリストがnullになったら繰り返しを終了
			} else if (Battle.monster_List == null
					|| battle_Situation.equals("勝利")) {
				break;
			} else if (battle_Situation.equals("続行")) {
				// モンスターが生存時、ターンコマンド選択
				if (this.monsterDeath != monster_List
						.size()) {
					while (true) {
						String ans = character.battle_Cmd(); // コマンド選択メソッド
						if (ans.equals("逃げる")) { // 逃げる場合のみ
							character.run();
							this.battle_Situation = "逃走";
							break;
						} else {
							break;
						}
					}
				} else {
					break;
				}
			}
		}
		turn_Num += 1;
	}

	// モンスターのターンメソッド
	public void monster_Turn() {
		for (Monster monster : Battle.monster_List) {
			monster.attack(); // モンスターのアタック処理
			if (monster.attacked_Target().getHp() == 0) { // ダメージ処理後にHPが0になったらカウント
				++partyDeath;
			}
		}
		turn_Num -= 1; // ターンを勇者側へ
	}

	public void battle_Fin() {
		// 戦闘終了時の分岐
		switch (battle_Situation) {
		case "勝利":
			battleResult();
			System.out.println("～～～～バトル終了～～～～");
			break;
		case "逃走":
			System.out.println("～～～～バトル終了～～～～");
			break;
		case "全滅":
			gameResult = new GameResult();
			gameResult.wipe();
		}
	}

	public void battleResult() {
		// 倒したモンスターが複数の場合に表示
		if (this.monsterDeath >= 2) {
			System.out.println("モンスターたちをやっつけた！");
		}
		// パーティメンバー数で表記処理を分岐
		if (Hero.party.size() == 1) {
			System.out.println(
					"勇者は" + Hero.battleEx + "の経験値を獲得");
		} else {
			System.out.println(
					"勇者たちは" + Hero.battleEx + "の経験値を獲得");
		}
		System.out.println(
				Hero.battleGold + "ゴールドを獲得");
		// 獲得した経験値をパーティメンバに振分処理
		for (Character character : Hero.party) {
			character.setEx();
			character.ex_table();
		}
		Hero.setPartyGold(Hero.battleGold);
	}
}
