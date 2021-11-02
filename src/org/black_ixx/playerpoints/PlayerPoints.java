package org.black_ixx.playerpoints;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerPoints extends JavaPlugin {

	private final PlayerPointsAPI API = new PlayerPointsAPI();

	@Override
	public void onEnable() {
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage("§6[sCash] §aPlayerPoints-Adapter §finiciado com sucesso");
	}

	public PlayerPointsAPI getAPI() {
		return API;
	}

	public static PlayerPoints getInstance() {
		return getPlugin(PlayerPoints.class);
	}

}
