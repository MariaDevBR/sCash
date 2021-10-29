package com.maria.cash.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.maria.cash.Main;
import com.maria.cash.managers.SendVoucherManager;
import com.maria.cash.models.SendCashVoucher;

public class MenuSendVoucherEvent implements Listener {

	protected Main main;

	private SendCashVoucher sendCashVoucher;

	public MenuSendVoucherEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		sendCashVoucher = main.getSendCashVoucher();
	}

	@EventHandler
	void playerSendVoucher(InventoryClickEvent e) {
		if (e.getInventory() == null)
			return;

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
			return;

		if (!e.getInventory().getName().equals(sendCashVoucher.getMenuName()))
			return;

		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();

		String name = e.getCurrentItem().getItemMeta().getDisplayName().replace("&", "§");
		String nameItem = sendCashVoucher.getName();

		if (nameItem.equalsIgnoreCase(name))
			SendVoucherManager.sendCashVoucher(p);
	}

}
