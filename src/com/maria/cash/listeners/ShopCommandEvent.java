package com.maria.cash.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.maria.cash.Main;
import com.maria.cash.models.CashSettings;

public class ShopCommandEvent implements Listener {

	protected Main main;

	private CashSettings cashSettings;

	public ShopCommandEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		cashSettings = main.getCashSettings();
	}

	@EventHandler
	void playerExecuteShopCommand(PlayerCommandPreprocessEvent e) {
		String message = e.getMessage();

		String shopCommand = cashSettings.getShopCommand();

		for (String aliasesShop : cashSettings.getAliasesShop()) {
			if (message.equalsIgnoreCase(shopCommand) || message.equalsIgnoreCase(aliasesShop)) {
				e.setMessage("/scashbymaria");
				break;
			}

		}

	}

}
