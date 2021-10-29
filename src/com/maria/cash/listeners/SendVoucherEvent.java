package com.maria.cash.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.maria.cash.Main;
import com.maria.cash.managers.SendVoucherManager;

public class SendVoucherEvent implements Listener {

	protected Main main;

	public SendVoucherEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	void playerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String message = e.getMessage();

		if (!SendVoucherManager.getPlayersSend().contains(p))
			return;

		e.setCancelled(true);
		if (SendVoucherManager.hasCanceled(p, message))
			return;

		Player target = Bukkit.getPlayerExact(message);
		if (!SendVoucherManager.playerIsOnline(p, target))
			return;

		if (SendVoucherManager.yourSelf(p, target))
			return;

		SendVoucherManager.voucherCashSended(p, target);
	}

}
