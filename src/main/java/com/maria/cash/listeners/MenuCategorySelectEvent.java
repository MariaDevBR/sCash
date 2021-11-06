package com.maria.cash.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.maria.cash.Main;
import com.maria.cash.methods.CategoryMethods;
import com.maria.cash.models.shop.CategoriesModel;
import com.maria.cash.shop.Category;
import com.maria.cash.shop.CategoryManager;

public class MenuCategorySelectEvent implements Listener {

	protected Main main;

	private CategoryManager categoryManager;
	private CategoriesModel categoriesModel;
	private CategoryMethods categoryMethods;

	public MenuCategorySelectEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		categoryManager = main.getCategoryManager();
		categoriesModel = main.getCategoriesModel();
		categoryMethods = main.getCategoryMethods();
	}

	@EventHandler
	void playerSelectCategory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();

		String title = e.getView().getTitle();

		if (title.equals(categoriesModel.getMenuName())) {
			e.setCancelled(true);

			if (item == null || item.getType() == Material.AIR || !item.hasItemMeta()
					|| !item.getItemMeta().hasDisplayName())
				return;

			Category category = categoryManager.getCategory(item);
			if (!categoryMethods.hasCategory(p, category))
				return;

			categoryMethods.selectCategory(p, category);
		}

	}

}
