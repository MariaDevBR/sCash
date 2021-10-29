package com.maria.cash.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.maria.cash.Main;
import com.maria.cash.methods.BuyItemsMethods;
import com.maria.cash.shop.Category;
import com.maria.cash.shop.CategoryManager;
import com.maria.cash.shop.ShopItems;
import com.maria.cash.shop.ShopItemsManager;

public class BuyItemsEvent implements Listener {

	protected Main main;

	private CategoryManager categoryManager;
	private ShopItemsManager shopItemsManager;

	private BuyItemsMethods buyItemsMethods;

	public BuyItemsEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		categoryManager = main.getCategoryManager();
		shopItemsManager = main.getShopItemsManager();

		buyItemsMethods = main.getBuyItemMethods();
	}

	@EventHandler
	void playerBuyItemAtShop(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();

		String title = e.getInventory().getTitle();

		Category category = categoryManager.findByInventoryTitle(title);

		if (category == null)
			return;

		e.setCancelled(true);
		if (item == null || item.getType() == Material.AIR || !item.hasItemMeta()
				|| !item.getItemMeta().hasDisplayName())
			return;

		ShopItems shopItems = shopItemsManager.getShopItems(item);
		if (!buyItemsMethods.hasItems(p, shopItems))
			return;

		buyItemsMethods.buyItem(p, shopItems);
	}

}
