package com.maria.cash.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.maria.cash.Main;
import com.maria.cash.models.Messages;
import com.maria.cash.models.Sounds;
import com.maria.cash.utils.Format;

public class SendVoucherManager {

	protected static Main main = Main.getPlugin(Main.class);

	private static final CashVoucherManager cashVoucherManager = main.getCashVoucherManager();

	private static final Messages messages = main.getMessages();
	private static final Sounds sounds = main.getSounds();

	private static final List<Player> playersSend = new ArrayList<>();

	public static void sendCashVoucher(Player p) {
		playersSend.add(p);

		ItemStack item = p.getItemInHand().clone();
		double cash = cashVoucherManager.getCashItem(item);

		List<String> message = messages.getSendVoucher();

		message.forEach(msg -> p.sendMessage(msg.replace("{cash}", Format.formatNumber(cash))));
		main.sendSound(p, sounds.getSendVoucher());

		p.closeInventory();
	}

	public static void cancel(Player p) {
		playersSend.remove(p);

		List<String> message = messages.getActionCanceled();

		message.forEach(msg -> p.sendMessage(msg));
		main.sendSound(p, sounds.getActionCanceled());
	}

	public static void invalidPlayer(Player p) {
		playersSend.remove(p);

		main.sendMessage(p, messages.getInvalidPlayer());
		main.sendSound(p, sounds.getInvalidPlayer());
	}

	public static void voucherCashSent(Player p, Player target) {
		playersSend.remove(p);

		ItemStack item = p.getItemInHand().clone();
		double amount = cashVoucherManager.getCashItem(item);

		List<String> messageSended = messages.getVoucherSended();
		List<String> messageReceived = messages.getVoucherReceived();

		messageSended.forEach(msg -> p.sendMessage(msg.replace("{quantia}", Format.formatNumber(amount)).replace("{player}", target.getName())));
		messageReceived.forEach(msg -> target.sendMessage(msg.replace("{quantia}", Format.formatNumber(amount)).replace("{player}", p.getName())));

		main.sendSound(p, sounds.getVoucherSended());
		main.sendSound(target, sounds.getReceivedCash());

		cashVoucherManager.removeItem(p);
		cashVoucherManager.giveVoucherCash(target, amount, 1);
	}

	public static boolean hasCanceled(Player p, String message) {
		if (message.equalsIgnoreCase("cancelar")) {
			cancel(p);
			return true;

		}
		return false;
	}

	public static boolean playerIsOnline(Player p, Player target) {
		if (target == null) {
			invalidPlayer(p);
			return false;

		}
		return true;
	}

	public static boolean yourSelf(Player p, Player target) {
		if (p == target) {
			playersSend.remove(p);

			main.sendMessage(p, messages.getYourSelf());
			main.sendSound(p, sounds.getInvalidPlayer());
			return true;

		}
		return false;
	}

	public static List<Player> getPlayersSend() {
		return playersSend;
	}

}
