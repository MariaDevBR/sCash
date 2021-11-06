package com.maria.cash.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maria.cash.Main;
import com.maria.cash.inventories.InventoryCategorySelect;
import com.maria.cash.models.Sounds;

public class ShopCommand implements CommandExecutor {

	protected Main main;

	private InventoryCategorySelect inventoryCategorySelect;

	private Sounds sounds;

	public ShopCommand(Main main) {
		this.main = main;

		main.getCommand("scashbymaria").setExecutor(this);

		inventoryCategorySelect = main.getInventoryCategorySelect();

		sounds = main.getSounds();
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] a) {
		if (!(s instanceof Player))
			return true;

		Player p = (Player) s;
		p.openInventory(inventoryCategorySelect.selectCategory(p));
		main.sendSound(p, sounds.getSelectCategory());
		return false;
	}

}
