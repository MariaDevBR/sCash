package com.maria.cash.files;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.utils.DateManager;

public class SoundsFile {

	private FileConfiguration config;

	public SoundsFile() {
		config = DateManager.getConfig("sons");
	}

	public static void createSoundsFile() {
		File file = DateManager.getFile("sons");
		if (!file.exists()) {
			DateManager.createConfig("sons");

			Bukkit.getConsoleSender().sendMessage("§6[sCash] §fArquivo §6sons.yml §fcriado com sucesso");
		}

	}

	public FileConfiguration getConfig() {
		return config;
	}

}
