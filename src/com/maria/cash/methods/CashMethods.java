package com.maria.cash.methods;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maria.cash.Main;
import com.maria.cash.api.CashAPI;
import com.maria.cash.models.Messages;
import com.maria.cash.models.Sounds;
import com.maria.cash.utils.Format;

public class CashMethods {

	protected Main main;

	private CashAPI cashAPI;

	private Messages messages;
	private Sounds sounds;

	public CashMethods(Main main) {
		this.main = main;

		cashAPI = main.getCashAPI();

		messages = main.getMessages();
		sounds = main.getSounds();
	}

	public boolean hasPlayer(CommandSender p, Player target) {
		if (target == null) {
			main.sendMessage(p, messages.getInvalidPlayer());
			main.sendSound(p, sounds.getInvalidPlayer());
			return true;

		}
		return false;
	}

	public boolean hasNumber(CommandSender p, double amount) {
		if (amount < 1) {
			main.sendMessage(p, messages.getInvalidNumber());
			main.sendSound(p, sounds.getInvalidNumber());
			return true;

		}
		return false;
	}

	public boolean isNumber(CommandSender p, String[] a, int arg) {
		if (!NumberUtils.isNumber(a[arg])) {
			main.sendMessage(p, messages.getInvalidNumber());
			main.sendSound(p, sounds.getInvalidNumber());
			return false;

		}
		return true;
	}

	public boolean invalidArgs(CommandSender p, String[] a, int arg) {
		if (a.length != arg) {
			commandIncorrect(p);
			return true;

		}
		return false;
	}

	public boolean hasArgs(String[] a, String arg) {
		return a[0].equalsIgnoreCase(arg);
	}

	public boolean littleCash(CommandSender p, double amount, double cashTarget) {
		if (amount > cashTarget) {
			main.sendMessage(p, messages.getLittleCash());
			main.sendSound(p, sounds.getLittleCash());
			return true;

		}
		return false;
	}

	public boolean amountIsZero(CommandSender player, double amount) {
		if (amount <= 0) {
			main.sendMessage(player, messages.getInvalidNumber());
			main.sendSound(player, sounds.getInvalidNumber());
			return true;

		}
		return false;
	}

	public boolean isPlayer(CommandSender p, Player target) {
		if (p == target) {
			main.sendMessage(p, messages.getYourSelf());
			main.sendSound(p, sounds.getInvalidPlayer());
			return true;

		}
		return false;
	}

	public boolean hasCash(Player player, double amount) {
		double cash = cashAPI.getCash(player);

		if (!cashAPI.hasCash(player, amount)) {
			main.sendMessage(player, messages.getCashInsufficient().replace("{necessario}", Format.format(amount))
					.replace("{cash}", Format.format(cash)));
			main.sendSound(player, sounds.getCashInsufficient());
			return false;

		}
		return true;
	}

	public void commandIncorrect(CommandSender p) {
		if (!p.hasPermission("scash.admin"))
			for (String message : messages.getCommands())
				p.sendMessage(message);

		else
			for (String message : messages.getCommandsAdmin())
				p.sendMessage(message);

		main.sendSound(p, sounds.getIncorrectCommand());
	}

	public void viewCash(Player player, double cash) {
		List<String> balance = messages.getBalance();

		balance.forEach(msg -> player.sendMessage(msg.replace("{cash}", Format.format(cash))));
		main.sendSound(player, sounds.getBalance());
	}

	public void viewCashOthers(CommandSender player, Player target, double cash) {
		List<String> othersBalance = messages.getOthersBalance();

		othersBalance.forEach(msg -> player
				.sendMessage(msg.replace("{cash}", Format.format(cash)).replace("{player}", target.getName())));

		main.sendSound(player, sounds.getOthersBalance());
	}

	public void sendCash(Player player, Player target, double amount) {
		List<String> sendCash = messages.getSendCash();
		List<String> receivedCash = messages.getReceivedCash();

		sendCash.forEach(msg -> player
				.sendMessage(msg.replace("{quantia}", Format.format(amount)).replace("{player}", target.getName())));

		receivedCash.forEach(msg -> target
				.sendMessage(msg.replace("{quantia}", Format.format(amount)).replace("{player}", player.getName())));

		main.sendSound(player, sounds.getSendCash());
		main.sendSound(target, sounds.getReceivedCash());
	}

	public void voucherCash(Player player, double amount, int quantity) {
		List<String> voucher = messages.getVoucher();

		voucher.forEach(msg -> player.sendMessage(
				msg.replace("{cash}", Format.format(amount)).replace("{quantia}", Format.format(quantity))));

		main.sendSound(player, sounds.getVoucher());
	}

	public void giveVoucherCash(CommandSender sender, Player target, double amount, int quantity) {
		String voucher = messages.getGiveVoucher();

		main.sendMessage(sender, voucher.replace("{cash}", Format.format(amount))
				.replace("{quantia}", Format.format(quantity)).replace("{player}", target.getName()));

		main.sendSound(sender, sounds.getGiveVoucher());
	}

}
