package com.maria.cash.shop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Category {

	private ItemStack icon;
	private int slot;
	private String permission, command;
	private Inventory inventory;
	private List<ShopItems> shopItems;

	public Category(ItemStack icon, int slot, String permission, String command, Inventory inventory) {
		this.icon = icon;
		this.slot = slot;
		this.permission = permission;
		this.command = command;
		this.inventory = inventory;
		this.shopItems = new ArrayList<>();
	}

	public ItemStack getIcon() {
		return icon;
	}

	public void setIcon(ItemStack icon) {
		this.icon = icon;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<ShopItems> getShopItems() {
		return shopItems;
	}

	public void setShopItems(List<ShopItems> shopItems) {
		this.shopItems = shopItems;
	}

}
