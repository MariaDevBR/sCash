package com.maria.cash.models;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.Main;
import com.maria.cash.files.SendCashVoucherFiles;

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

	public String getMenuName() {
		return menuName;
	}

	public int getSizeHots() {
		return sizeHots;
	}

	public String getName() {
		return name;
	}

	public String getSkull() {
		return skull;
	}

	public List<String> getLore() {
		return lore;
	}

	public Material getMaterial() {
		return material;
	}

	public int getData() {
		return data;
	}

	public int getSlot() {
		return slot;
	}

	public boolean isCustomSkull() {
		return customSkull;
	}

	public boolean isGlow() {
		return glow;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setSizeHots(int sizeHots) {
		this.sizeHots = sizeHots;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSkull(String skull) {
		this.skull = skull;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public void setData(int data) {
		this.data = data;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public void setCustomSkull(boolean customSkull) {
		this.customSkull = customSkull;
	}

	public void setGlow(boolean glow) {
		this.glow = glow;
	}

}
