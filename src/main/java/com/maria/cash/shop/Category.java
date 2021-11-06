package com.maria.cash.shop;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
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

}
