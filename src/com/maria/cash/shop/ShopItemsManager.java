package com.maria.cash.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import com.maria.cash.Main;
import com.maria.cash.utils.Format;
import com.maria.cash.utils.ItemBuilder;
import com.maria.cash.utils.SkullAPI;

public class ShopItemsManager {

	private static Map<String, ShopItems> shopItemsMap;

	private FileConfiguration shopItemsFile;

	public ShopItemsManager(FileConfiguration shopItemsFile) {
		shopItemsMap = new HashMap<>();

		this.shopItemsFile = shopItemsFile;

		setupShopItems();
	}

	public static void createShopItem(String path, ShopItems shopItems) {
		shopItemsMap.put(path, shopItems);
	}

	public Map<String, ShopItems> getShopItems() {
		return shopItemsMap;
	}

	public ShopItems getShopItems(ItemStack icon) {
		return shopItemsMap.entrySet().stream().map(Map.Entry::getValue).filter(it -> it.getIcon().isSimilar(icon))
				.findFirst().orElse(null);
	}

	private void setupShopItems() {
		for (String path : shopItemsFile.getConfigurationSection("Itens").getKeys(false)) {
			ConfigurationSection key = shopItemsFile.getConfigurationSection("Itens." + path);

			String categoryName = key.getString("Categoria");
			String name = key.getString("Nome").replace("&", "§");
			String broadcastActionBar = key.getString("Anuncio actionbar").replace("&", "§");
			String skull = key.getString("Skull");

			double price = key.getDouble("Valor");

			List<String> lore = key.getStringList("Lore");
			lore = lore.stream().map(l -> l.replace("&", "§").replace("{cash}", Format.format(price)))
					.collect(Collectors.toList());
			List<String> commands = key.getStringList("Comandos");
			commands = commands.stream().map(c -> c.replace("&", "§")).collect(Collectors.toList());
			List<String> broadcastMessage = key.getStringList("Anuncio chat");
			broadcastMessage = broadcastMessage.stream().map(bc -> bc.replace("&", "§")).collect(Collectors.toList());

			Material material = Material.valueOf(key.getString("Material").split(":")[0]);
			int data = Integer.parseInt(key.getString("Material").split(":")[1]);
			int slot = key.getInt("Slot");

			boolean customSkull = key.getBoolean("Custom skull");
			boolean glow = key.getBoolean("Glow");
			boolean broadcast = key.getBoolean("Anunciar compra");
			boolean chat = key.getBoolean("Tipo anuncio.Chat");
			boolean actionBar = key.getBoolean("Tipo anuncio.ActionBar");

			ItemStack item = new ItemBuilder(material, 1, data).setName(name).setLore(lore).addGlow(glow).build();
			ItemStack skullItem = new ItemBuilder(SkullAPI.getSkull(skull)).setName(name).setLore(lore).build();

			ItemStack icon = !customSkull ? item : skullItem;

			Category category = Main.getPlugin(Main.class).getCategoryManager().getCategory(categoryName);
			if (category != null) {
				ShopItems shopItems = new ShopItems(icon, category, broadcastActionBar, price, slot, commands,
						broadcastMessage, broadcast, chat, actionBar);

				category.getShopItems().add(shopItems);
				createShopItem(path, shopItems);
			}

		}

	}

}
