package main_system;

import character.Hero;

public class GameResult {
	public void wipe() {
		System.out.println("勇者たちは全滅した…");
		System.out.println("GAMEOVER");
		System.exit(0);
	}

	public void Clear(int totalTurn) {
		System.out.println("龍王を倒し世界は平和を取り戻した！");
		System.out.println("-----最終結果-----");
		System.out.println("　クリアターン数：" + totalTurn);
		System.out.println("モンスター討伐数：" + Hero.party.get(0).getKillCount());
		System.out.println("【 - GAME CLEAR - 】");
		System.exit(0);
	}
}
