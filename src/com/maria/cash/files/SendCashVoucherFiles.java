package com.maria.cash.files;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.utils.DateManager;

public class SendCashVoucherFiles {

	private FileConfiguration config;

	public SendCashVoucherFiles() {
		config = DateManager.getConfig("enviar vale", "menus");
	}

	public static void createVoucherCashFile() {
		File file = DateManager.getFile("enviar vale", "menus");
		if (!file.exists()) {
			DateManager.createConfig("enviar vale", "menus");

			Bukkit.getConsoleSender().sendMessage("§6[sCash] §fArquivo §6enviar vale.yml §fcriado com sucesso");
		}

	}

	public FileConfiguration getConfig() {
		return config;
	}

}
