package com.maria.cash.models;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.Main;

public class CashSettings {

	protected Main main;

	private FileConfiguration config;

	private String prefix;

	private String shopCommand;
	private List<String> aliasesShop;

	private double startCash;

	private boolean leftButton;

	public CashSettings(Main main) {
		this.main = main;

		config = main.getConfig();

		prefix = config.getString("Prefix").replace("&", "§");

		shopCommand = config.getString("Comando shop");
		aliasesShop = config.getStringList("Aliases shop");

		startCash = config.getDouble("Cash inicial");

		leftButton = config.getBoolean("Vale cash.Botao esquerdo");
	}

	public String getPrefix() {
		return prefix;
	}

	public String getShopCommand() {
		return shopCommand;
	}

	public List<String> getAliasesShop() {
		return aliasesShop;
	}

	public double getStartCash() {
		return startCash;
	}

	public boolean isLeftButton() {
		return leftButton;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setShopCommand(String shopCommand) {
		this.shopCommand = shopCommand;
	}

	public void setStartCash(double startCash) {
		this.startCash = startCash;
	}

	public void setLeftButton(boolean leftButton) {
		this.leftButton = leftButton;
	}

}
