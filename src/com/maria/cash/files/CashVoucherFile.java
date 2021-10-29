package com.maria.cash.files;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.utils.DateManager;

public class CashVoucherFile {

	private FileConfiguration config;

	public CashVoucherFile() {
		config = DateManager.getConfig("vale cash");
	}

	public static void createVoucherCashFile() {
		File file = DateManager.getFile("vale cash");
		if (!file.exists()) {
			DateManager.createConfig("vale cash");

			Bukkit.getConsoleSender().sendMessage("§6[sCash] §fArquivo §6vale cash.yml §fcriado com sucesso");
		}

	}

	public FileConfiguration getConfig() {
		return config;
	}

}
