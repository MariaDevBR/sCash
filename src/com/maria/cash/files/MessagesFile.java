package com.maria.cash.files;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.utils.DateManager;

public class MessagesFile {

	private FileConfiguration config;

	public MessagesFile() {
		config = DateManager.getConfig("mensagens");
	}

	public static void createMessagesFile() {
		File file = DateManager.getFile("mensagens");
		if (!file.exists()) {
			DateManager.createConfig("mensagens");

			Bukkit.getConsoleSender().sendMessage("§6[sCash] §fArquivo §6mensagens.yml §fcriado com sucesso");
		}

	}

	public FileConfiguration getConfig() {
		return config;
	}

}
