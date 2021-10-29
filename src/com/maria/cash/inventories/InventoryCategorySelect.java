package com.maria.cash.inventories;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.maria.cash.Main;
import com.maria.cash.models.shop.CategoriesModel;
import com.maria.cash.shop.CategoryManager;

public class InventoryCategorySelect {

	protected Main main;

	private CategoryManager categoryManager;
	private CategoriesModel categoriesModel;

	private String menuName;
	private int sizeHots;

	public InventoryCategorySelect(Main main) {
		this.main = main;

		categoryManager = main.getCategoryManager();
		categoriesModel = main.getCategoriesModel();

		menuName = categoriesModel.getMenuName();
		sizeHots = categoriesModel.getSizeHots();
	}

	public Inventory selectCategory(Player p) {
		Inventory inv = Bukkit.createInventory(null, sizeHots * 9, menuName);

		categoryManager.getCategories().entrySet().stream().map(Map.Entry::getValue).forEach(category -> {
			if (p.hasPermission(category.getPermission()) || p.hasPermission("scash.admin"))
				inv.setItem(category.getSlot(), category.getIcon());
		});

		return inv;
	}

}
