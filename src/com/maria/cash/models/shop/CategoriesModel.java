package com.maria.cash.models.shop;

import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.Main;
import com.maria.cash.files.shop.CategoriesFile;

public class CategoriesModel {

	protected Main main;

	private CategoriesFile categoriesFile;
	private FileConfiguration config;

	private String menuName;
	private int sizeHots;

	public CategoriesModel(Main main) {
		this.main = main;

		categoriesFile = main.getCategoriesFile();
		config = categoriesFile.getConfig();

		menuName = config.getString("Nome menu").replace("&", "§");
		sizeHots = config.getInt("Tamanho menu");
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getSizeHots() {
		return sizeHots;
	}

	public void setSizeHots(int sizeHots) {
		this.sizeHots = sizeHots;
	}

}
