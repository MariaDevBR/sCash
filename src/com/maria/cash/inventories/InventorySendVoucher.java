package com.maria.cash.inventories;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.maria.cash.Main;
import com.maria.cash.managers.CashVoucherManager;
import com.maria.cash.models.SendCashVoucher;
import com.maria.cash.utils.Format;
import com.maria.cash.utils.ItemBuilder;
import com.maria.cash.utils.SkullAPI;

public class InventorySendVoucher {

	protected Main main;

	private CashVoucherManager cashVoucherManager;

	private SendCashVoucher sendCashVoucher;

	private String menuName;
	private int sizeHots;

	public InventorySendVoucher(Main main) {
		this.main = main;

		cashVoucherManager = main.getCashVoucherManager();

		sendCashVoucher = main.getSendCashVoucher();

		menuName = sendCashVoucher.getMenuName();
		sizeHots = sendCashVoucher.getSizeHots();
	}

	public Inventory sendCashVoucherMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, sizeHots * 9, menuName);

		inv.setItem(sendCashVoucher.getSlot(), getItem(p));

		return inv;
	}

	private ItemStack getItem(Player p) {
		ItemStack item;

		ItemStack hand = p.getItemInHand();
		double cash = cashVoucherManager.getCashItem(hand);

		String skull = sendCashVoucher.getSkull();
		String name = sendCashVoucher.getName();
		List<String> lore = sendCashVoucher.getLore();
		lore = lore.stream().map(l -> l.replace("{cash}", Format.format(cash))).collect(Collectors.toList());

		Material material = sendCashVoucher.getMaterial();
		int data = sendCashVoucher.getData();

		boolean customSkull = sendCashVoucher.isCustomSkull();
		boolean glow = sendCashVoucher.isGlow();

		if (customSkull)
			item = new ItemBuilder(SkullAPI.getSkull(skull)).setName(name).setLore(lore).build();

		else
			item = new ItemBuilder(material, 1, data).setName(name).setLore(lore).addGlow(glow).build();

		return item;
	}

}
