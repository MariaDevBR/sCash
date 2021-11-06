package com.maria.cash.services.placeholder;

import org.bukkit.entity.Player;

import com.maria.cash.Main;
import com.maria.cash.utils.Format;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class CashPlaceHolder extends PlaceholderExpansion {

	protected Main main;

	public CashPlaceHolder(Main main) {
		this.main = main;
	}

	@Override
	public String getAuthor() {
		return "Maria_BR";
	}

	@Override
	public String getIdentifier() {
		return main.getName();
	}

	@Override
	public String getVersion() {
		return main.getDescription().getVersion();
	}

	@Override
	public String onPlaceholderRequest(Player p, String params) {
		if (params.equalsIgnoreCase("amount")) {
			double cash = main.getCashAPI().getCash(p);
			String cashFormatted = Format.formatNumber(cash);

			return cashFormatted;

		}
		return "-/-";
	}

}
