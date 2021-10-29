package com.maria.cash.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.maria.cash.Main;
import com.maria.cash.inventories.InventorySendVoucher;
import com.maria.cash.managers.ActivateCashVoucherManager;
import com.maria.cash.managers.StackCashVouchersManager;
import com.maria.cash.models.CashSettings;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class CashVoucherEvent implements Listener {

	protected Main main;

	private CashSettings cashSettings;

	private InventorySendVoucher inventorySendVoucher;

	private StackCashVouchersManager stackCashVouchers;
	private ActivateCashVoucherManager activateCashVoucher;

	public CashVoucherEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		cashSettings = main.getCashSettings();

		inventorySendVoucher = main.getInventorySendVoucher();

		stackCashVouchers = main.getStackCashVouchers();
		activateCashVoucher = main.getActivateCashVoucher();
	}

	@EventHandler
	void playerUseCashVoucher(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		Action action = e.getAction();

		if (item == null || item.getType() == Material.AIR)
			return;

		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		if (!itemCompound.hasKey("sCash_Amount"))
			return;

		if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			if (p.isSneaking())
				stackCashVouchers.stackCash(p, item);

			else
				activateCashVoucher.activateVoucher(p, item);

			e.setCancelled(true);
			return;

		}
		if (cashSettings.isLeftButton())
			if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)
				p.openInventory(inventorySendVoucher.sendCashVoucherMenu(p));
	}

}
