package com.maria.cash.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.maria.cash.Main;
import com.maria.cash.api.CashAPI;
import com.maria.cash.managers.CashVoucherManager;
import com.maria.cash.methods.CashMethods;
import com.maria.cash.models.Messages;
import com.maria.cash.models.Sounds;
import com.maria.cash.utils.Format;

public class CashCommand implements CommandExecutor {

	protected Main main;

	private CashAPI cashAPI;

	private Messages messages;
	private Sounds sounds;

	private CashMethods cashMethods;
	private CashVoucherManager cashVoucherManager;

	public CashCommand(Main main) {
		this.main = main;

		main.getCommand("cash").setExecutor(this);

		messages = main.getMessages();
		sounds = main.getSounds();

		cashMethods = main.getCashMethods();
		cashVoucherManager = main.getCashVoucherManager();

		cashAPI = main.getCashAPI();
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] a) {
		if (a.length == 0) {
			if (!(s instanceof Player))
				return true;

			Player p = (Player) s;
			double cash = cashAPI.getCash(p);

			cashMethods.viewCash(p, cash);
			return true;

		}
		if (cashMethods.hasArgs(a, "ajuda") || cashMethods.hasArgs(a, "help")) {
			cashMethods.commandIncorrect(s);
			return true;

		}
		if (cashMethods.hasArgs(a, "ver") || cashMethods.hasArgs(a, "view")) {
			if (cashMethods.invalidArgs(s, a, 2))
				return true;

			Player target = Bukkit.getPlayerExact(a[1]);

			if (cashMethods.hasPlayer(s, target))
				return true;

			double cashTarget = cashAPI.getCash(target);
			cashMethods.viewCashOthers(s, target, cashTarget);
			return true;

		}
		double amount = 0.0;
		if (cashMethods.hasArgs(a, "enviar") || cashMethods.hasArgs(a, "pay") || cashMethods.hasArgs(a, "send")) {
			if (!(s instanceof Player))
				return true;

			Player p = (Player) s;
			if (cashMethods.invalidArgs(p, a, 3))
				return true;

			if (main.hasPermission(p, "enviar"))
				return true;

			Player target = Bukkit.getPlayerExact(a[1]);
			if (cashMethods.hasPlayer(p, target))
				return true;

			if (cashMethods.isPlayer(p, target))
				return true;

			if (!cashMethods.isNumber(p, a, 2))
				return true;

			amount = Double.parseDouble(a[2]);
			if (cashMethods.amountIsZero(p, amount))
				return true;

			if (!cashMethods.hasCash(p, amount))
				return true;

			cashAPI.removeCash(p, amount);
			cashAPI.addCash(target, amount);

			cashMethods.sendCash(p, target, amount);
			return true;

		}
		if (cashMethods.hasArgs(a, "vale") || cashMethods.hasArgs(a, "vales") || cashMethods.hasArgs(a, "valecash")) {
			if (!(s instanceof Player))
				return true;

			Player p = (Player) s;
			if (cashMethods.invalidArgs(p, a, 3))
				return true;

			if (main.hasPermission(p, "valecash"))
				return true;

			if (!cashMethods.isNumber(p, a, 1) || !cashMethods.isNumber(p, a, 2))
				return true;

			amount = Double.parseDouble(a[1]);
			int quantity = Integer.parseInt(a[2]);

			double amountTotal = amount * quantity;

			if (!cashMethods.hasCash(p, amountTotal))
				return true;

			cashVoucherManager.giveVoucherCash(p, amount, quantity);
			cashAPI.removeCash(p, amountTotal);

			cashMethods.voucherCash(p, amountTotal, quantity);
			return true;

		}
		if (cashMethods.hasArgs(a, "add") || cashMethods.hasArgs(a, "adicionar")) {
			if (cashMethods.invalidArgs(s, a, 3))
				return true;

			if (main.hasPermission(s, "admin"))
				return true;

			Player target = Bukkit.getPlayerExact(a[1]);
			if (cashMethods.hasPlayer(s, target))
				return true;

			if (!cashMethods.isNumber(s, a, 2))
				return true;

			amount = Double.parseDouble(a[2]);

			if (cashMethods.amountIsZero(s, amount))
				return true;

			cashAPI.addCash(target, amount);
			main.sendMessage(s, messages.getAddCash().replace("{quantia}", Format.format(amount)).replace("{player}",
					target.getName()));
			main.sendSound(s, sounds.getAddCash());
			return true;

		}
		if (cashMethods.hasArgs(a, "remove") || cashMethods.hasArgs(a, "remover")
				|| cashMethods.hasArgs(a, "retirar")) {
			if (cashMethods.invalidArgs(s, a, 3))
				return true;

			if (main.hasPermission(s, "admin"))
				return true;

			Player target = Bukkit.getPlayerExact(a[1]);
			if (cashMethods.hasPlayer(s, target))
				return true;

			if (!cashMethods.isNumber(s, a, 2))
				return true;

			amount = Double.parseDouble(a[2]);

			if (cashMethods.amountIsZero(s, amount))
				return true;

			double cashTarget = cashAPI.getCash(target);
			if (cashMethods.littleCash(s, amount, cashTarget))
				return true;

			cashAPI.removeCash(target, amount);
			main.sendMessage(s, messages.getRemoveCash().replace("{quantia}", Format.format(amount)).replace("{player}",
					target.getName()));
			main.sendSound(s, sounds.getRemoveCash());
			return true;

		}
		if (cashMethods.hasArgs(a, "set") || cashMethods.hasArgs(a, "setar") || cashMethods.hasArgs(a, "definir")) {
			if (cashMethods.invalidArgs(s, a, 3))
				return true;

			if (main.hasPermission(s, "admin"))
				return true;

			Player target = Bukkit.getPlayerExact(a[1]);
			if (cashMethods.hasPlayer(s, target))
				return true;

			if (!cashMethods.isNumber(s, a, 2))
				return true;

			amount = Double.parseDouble(a[2]);

			if (cashMethods.amountIsZero(s, amount))
				return true;

			cashAPI.setCash(target, amount);
			main.sendMessage(s, messages.getSetCash().replace("{quantia}", Format.format(amount)).replace("{player}",
					target.getName()));
			main.sendSound(s, sounds.getSetCash());
			return true;

		}
		if (cashMethods.hasArgs(a, "givevale") || cashMethods.hasArgs(a, "darvale")) {
			if (cashMethods.invalidArgs(s, a, 4))
				return true;

			if (main.hasPermission(s, "admin"))
				return true;

			Player target = Bukkit.getPlayerExact(a[1]);

			if (cashMethods.hasPlayer(s, target))
				return true;

			if (!cashMethods.isNumber(s, a, 2) || !cashMethods.isNumber(s, a, 3))
				return true;

			amount = Double.parseDouble(a[2]);
			int quantity = Integer.parseInt(a[3]);

			double amountTotal = amount * quantity;

			cashVoucherManager.giveVoucherCash(target, amount, quantity);
			cashMethods.giveVoucherCash(s, target, amountTotal, quantity);
			return true;

		}
		if (cashMethods.hasArgs(a, "reload") || cashMethods.hasArgs(a, "rl")) {
			Bukkit.getPluginManager().disablePlugin(main);
			Bukkit.getScheduler().cancelTasks(main);
			Bukkit.getServicesManager().unregisterAll(main);
			HandlerList.unregisterAll(main);

			Bukkit.getPluginManager().enablePlugin(main);

			main.reloadConfig();

			s.sendMessage("§6§lCASH §8» §aPlugin recarregado com sucesso.");

		}
		return false;
	}

}
