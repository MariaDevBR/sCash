package com.maria.cash.services.placeholder.registry;

import org.bukkit.Bukkit;

import com.maria.cash.Main;
import com.maria.cash.services.placeholder.CashPlaceHolder;

public class PlaceHolderRegistry {

	protected Main main;

	public PlaceHolderRegistry(Main main) {
		this.main = main;
	}

	public void register() {
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
			Bukkit.getConsoleSender().sendMessage("§6[sCash] §cNão foi possível encontrar o §6PlaceholderAPI");
			return;

		}
		Bukkit.getConsoleSender().sendMessage("§6[sCash] §aPlaceholderAPI §fHookado com sucesso");
		new CashPlaceHolder(main).register();
	}

}
