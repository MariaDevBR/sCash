package com.maria.cash.files.shop;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.utils.DateManager;

public class CategoriesFile {

	private FileConfiguration config;

	public CategoriesFile() {
		config = DateManager.getConfig("categorias", "menus");
	}

	public static void createCategoriesFile() {
		File file = DateManager.getFile("categorias", "menus");
		if (!file.exists()) {
			DateManager.createConfig("categorias", "menus");

			Bukkit.getConsoleSender().sendMessage("§6[sCash] §fArquivo §6categorias.yml §fcriado com sucesso");
		}

	}

	public FileConfiguration getConfig() {
		return config;
	}

}
