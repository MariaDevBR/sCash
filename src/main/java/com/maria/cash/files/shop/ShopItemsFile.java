package com.maria.cash.files.shop;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.utils.DateManager;

public class ShopItemsFile {

	private FileConfiguration config;

	public ShopItemsFile() {
		config = DateManager.getConfig("shop itens", "menus");
	}

	public static void createShopItemsFile() {
		File file = DateManager.getFile("shop itens", "menus");
		if (!file.exists()) {
			DateManager.createConfig("shop itens", "menus");

			Bukkit.getConsoleSender().sendMessage("§6[sCash] §fArquivo §6shop itens.yml §fcriado com sucesso");
		}

	}

	public FileConfiguration getConfig() {
		return config;
	}

}
