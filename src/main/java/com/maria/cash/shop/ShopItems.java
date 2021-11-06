package com.maria.cash.shop;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class ShopItems {

	private ItemStack icon;
	private Category category;
	private String broadcastActionBar;
	private double price;
	private int slot;
	private List<String> commands;
	private List<String> broadcastMessage;
	private boolean broadcast;
	private boolean chat;
	private boolean actionBar;

	public ShopItems(ItemStack icon, Category category, String broadcastActionBar, double price, int slot,
			List<String> commands, List<String> broadcastMessage, boolean broadcast, boolean chat, boolean actionBar) {
		this.icon = icon;
		this.category = category;
		this.broadcastActionBar = broadcastActionBar;
		this.price = price;
		this.slot = slot;
		this.commands = commands;
		this.broadcastMessage = broadcastMessage;
		this.broadcast = broadcast;
		this.chat = chat;
		this.actionBar = actionBar;
	}

}
