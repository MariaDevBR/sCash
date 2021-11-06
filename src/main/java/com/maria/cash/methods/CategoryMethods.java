package com.maria.cash.methods;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.maria.cash.Main;
import com.maria.cash.models.Messages;
import com.maria.cash.models.Sounds;
import com.maria.cash.shop.Category;

public class CategoryMethods {

	protected Main main;

	private Messages messages;
	private Sounds sounds;

	public CategoryMethods(Main main) {
		this.main = main;

		messages = main.getMessages();
		sounds = main.getSounds();
	}

	public boolean hasCategory(Player player, Category category) {
		if (category == null) {
			main.sendMessage(player, "§cNão foi possível encontrar esta categoria.");
			main.sendSound(player, Sound.VILLAGER_NO);
			return false;

		}
		return true;
	}

	public void selectCategory(Player player, Category category) {
		String name = category.getIcon().getItemMeta().getDisplayName().replace("&", "�");

		List<String> message = messages.getSelectCategory();

		message.forEach(msg -> player.sendMessage(msg.replace("{categoria}", name)));
		main.sendSound(player, sounds.getSelectCategory());

		if (category.getCommand().equalsIgnoreCase("nulo"))
			openInventory(player, category);

		else
			categoryCommand(player, category);
	}

	public void openInventory(Player player, Category category) {
		Inventory inventory = category.getInventory();

		category.getShopItems().forEach(si -> {
			inventory.setItem(si.getSlot(), si.getIcon());
		});

		player.openInventory(inventory);
	}

	public void categoryCommand(Player player, Category category) {
		player.closeInventory();
		player.chat("/" + category.getCommand());
	}

}
