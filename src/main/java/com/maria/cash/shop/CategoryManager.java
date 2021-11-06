package com.maria.cash.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.maria.cash.utils.ItemBuilder;
import com.maria.cash.utils.SkullAPI;

public class CategoryManager {

	private static Map<String, Category> categoriesMap;

	private final FileConfiguration categoriesFile;

	public CategoryManager(FileConfiguration categoriesFile) {
		categoriesMap = new HashMap<>();

		this.categoriesFile = categoriesFile;

		setupCategories();
	}

	public void createCategory(String path, Category category) {
		categoriesMap.put(path, category);
	}

	public Map<String, Category> getCategories() {
		return categoriesMap;
	}

	public Category getCategory(ItemStack icon) {
		return categoriesMap.entrySet().stream().map(Map.Entry::getValue).filter(it -> it.getIcon().isSimilar(icon))
				.findFirst().orElse(null);
	}

	public Category findByInventoryTitle(String title) {
		return categoriesMap.entrySet().stream().map(Map.Entry::getValue)
				.filter(ct -> ct.getInventory().getTitle().equals(title)).findFirst().orElse(null);
	}

	public Category getCategory(String key) {
		return categoriesMap.get(key);
	}

	private void setupCategories() {
		for (String path : categoriesFile.getConfigurationSection("Categorias").getKeys(false)) {
			ConfigurationSection key = categoriesFile.getConfigurationSection("Categorias." + path);

			String title = key.getString("Titulo").replace("&", "§");
			String name = key.getString("Nome").replace("&", "§");
			String skull = key.getString("Skull");
			String permission = key.getString("Permissao");
			String command = key.getString("Comando");
			List<String> lore = key.getStringList("Lore");
			lore = lore.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());
			Material material = Material.valueOf(key.getString("Material").split(":")[0]);
			int data = Integer.parseInt(key.getString("Material").split(":")[1]);
			int size = key.getInt("Tamanho");
			int slot = key.getInt("Slot");
			boolean customSkull = key.getBoolean("Custom skull");
			boolean glow = key.getBoolean("Glow");

			ItemStack icon = !customSkull
					? new ItemBuilder(material, 1, data).setName(name).setLore(lore).addGlow(glow).build()
					: new ItemBuilder(SkullAPI.getSkull(skull)).setName(name).setLore(lore).build();

			Inventory inv = Bukkit.createInventory(null, size * 9, title);

			Category category = new Category(icon, slot, permission, command, inv);
			createCategory(path, category);
		}

	}

}
