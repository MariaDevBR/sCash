package com.maria.cash.models;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.Main;
import com.maria.cash.files.SendCashVoucherFiles;

@Getter
@Setter
public class SendCashVoucher {

	protected Main main;

	private SendCashVoucherFiles sendCashVoucherFiles;
	private FileConfiguration config;

	private String menuName;
	private int sizeHots;

	private String name;
	private String skull;
	private List<String> lore;
	private Material material;
	private int data;
	private int slot;
	private boolean customSkull;
	private boolean glow;

	public SendCashVoucher(Main main) {
		this.main = main;

		sendCashVoucherFiles = main.getSendCashVoucherFiles();
		config = sendCashVoucherFiles.getConfig();

		menuName = config.getString("Nome menu").replace("&", "§");
		sizeHots = config.getInt("Tamanho menu");

		name = config.getString("Menu.Item.Enviar vale.Nome").replace("&", "§");
		skull = config.getString("Menu.Item.Enviar vale.Skull");
		lore = config.getStringList("Menu.Item.Enviar vale.Lore");
		lore = lore.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());
		material = Material.valueOf(config.getString("Menu.Item.Enviar vale.Material").split(":")[0]);
		data = Integer.parseInt(config.getString("Menu.Item.Enviar vale.Material").split(":")[1]);
		slot = config.getInt("Menu.Item.Enviar vale.Slot");

		customSkull = config.getBoolean("Menu.Item.Enviar vale.Custom skull");
		glow = config.getBoolean("Menu.Item.Enviar vale.Glow");
	}

}
