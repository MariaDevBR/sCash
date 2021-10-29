package com.maria.cash.shop;

import java.util.List;

import org.bukkit.inventory.ItemStack;

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

	public ItemStack getIcon() {
		return icon;
	}

	public void setIcon(ItemStack icon) {
		this.icon = icon;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getBroadcastActionBar() {
		return broadcastActionBar;
	}

	public void setBroadcastActionBar(String broadcastActionBar) {
		this.broadcastActionBar = broadcastActionBar;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public List<String> getCommands() {
		return commands;
	}

	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

	public List<String> getBroadcastMessage() {
		return broadcastMessage;
	}

	public void setBroadcastMessage(List<String> broadcastMessage) {
		this.broadcastMessage = broadcastMessage;
	}

	public boolean isBroadcast() {
		return broadcast;
	}

	public void setBroadcast(boolean broadcast) {
		this.broadcast = broadcast;
	}

	public boolean isChat() {
		return chat;
	}

	public void setChat(boolean chat) {
		this.chat = chat;
	}

	public boolean isActionBar() {
		return actionBar;
	}

	public void setActionBar(boolean actionBar) {
		this.actionBar = actionBar;
	}

}
