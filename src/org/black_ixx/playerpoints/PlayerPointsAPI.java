package org.black_ixx.playerpoints;

import com.maria.cash.Main;
import com.maria.cash.api.CashAPI;

public class PlayerPointsAPI {

	private Main main;
	private CashAPI cashAPI;

	public PlayerPointsAPI() {
		main = Main.getPlugin(Main.class);

		cashAPI = main.getCashAPI();
	}

	public PlayerPointsAPI(PlayerPoints plugin) {
		main = Main.getPlugin(Main.class);

		cashAPI = main.getCashAPI();
	}

	public int look(String name) {
		return (int) cashAPI.getCashByName(name);
	}

	public boolean take(String name, int amount) {
		if (!cashAPI.hasCashByName(name, amount))
			return false;

		cashAPI.removeCashByName(name, amount);
		return true;
	}

	public boolean give(String name, int amount) {
		if (!cashAPI.hasCashByName(name, amount))
			return false;

		cashAPI.addCashByName(name, amount);
		return true;
	}

	public boolean pay(String name, String target, int amount) {
		if (!take(name, amount))
			return false;

		return give(target, amount);
	}

	public boolean set(String name, int amount) {
		if (!cashAPI.hasAccountByName(name))
			return false;

		cashAPI.setCashByName(name, amount);
		return true;
	}

	public boolean reset(String name, int amount) {
		return set(name, 0);
	}

}
