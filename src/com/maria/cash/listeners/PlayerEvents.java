package com.maria.cash.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.maria.cash.Main;
import com.maria.cash.api.CashAPI;
import com.maria.cash.services.SQLite;

public class PlayerEvents implements Listener {

	protected Main main;

	private SQLite sql;
	private CashAPI cashAPI;

	public PlayerEvents(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		sql = main.getSQLite();
		cashAPI = main.getCashAPI();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	void playerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if (!cashAPI.hasAccount(p))
			sql.createAccount(p);
	}

}
