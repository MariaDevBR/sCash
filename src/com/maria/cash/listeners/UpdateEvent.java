package com.maria.cash.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.maria.cash.Main;
import com.maria.cash.utils.checkers.UpdateCheck;

public class UpdateEvent implements Listener {

	protected Main main;

	public UpdateEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	void staffJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		UpdateCheck updateCheck = main.getUpdateCheck();

		String pluginName = main.getDescription().getName();
		String version = main.getDescription().getVersion();

		if (p.hasPermission("scash.admin")) {
			p.sendMessage("");
			p.sendMessage("�6[" + pluginName + "] �fH� uma nova atualiza��o");
			p.sendMessage("�6[" + pluginName + "] �fdispon�vel para Download.");
			p.sendMessage("");
			p.sendMessage("�6[" + pluginName + "] �fSua Vers�o: �6" + version);
			p.sendMessage("�6[" + pluginName + "] �fNova Vers�o: �6" + updateCheck.getLastVersion());
			p.sendMessage("");
			p.sendMessage("�6[" + pluginName + "] �fDownload:");
			p.sendMessage("�e" + updateCheck.getResourceURL());
			p.sendMessage("");
		}

	}

}
