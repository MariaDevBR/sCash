package com.maria.cash.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.Main;

@Getter
@Setter
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

}
