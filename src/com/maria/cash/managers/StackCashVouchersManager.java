package com.maria.cash.managers;

import java.util.Map;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.maria.cash.Main;
import com.maria.cash.models.Messages;
import com.maria.cash.models.Sounds;
import com.maria.cash.utils.Format;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class StackCashVouchersManager {

	protected Main main;

	private CashVoucherManager cashVoucherManager;

	private Messages messages;
	private Sounds sounds;

	public StackCashVouchersManager(Main main) {
		this.main = main;

		cashVoucherManager = main.getCashVoucherManager();

		messages = main.getMessages();
		sounds = main.getSounds();
	}

	public void stackCash(Player p, ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		if (!itemCompound.hasKey("sCash_Amount"))
			return;

		stackSucess(p, item);
	}

	private void stackSucess(Player p, ItemStack item) {
		double cash = cashVoucherManager.getCashItem(item);
		int amountItems = getTotalAmount(p, p.getInventory(), item);

		cash = amountItems;

		removeItem(p.getInventory(), item, amountItems);
		cashVoucherManager.giveVoucherCash(p, cash, 1);

		for (String stackVouchers : messages.getStackVouchersCash())
			p.sendMessage(stackVouchers.replace("{quantia}", Format.formatNumber(cash)));

		main.sendSound(p, sounds.getStackVoucherCash());
	}

	public int getTotalAmount(Player p, Inventory inventory, ItemStack item) {
		int amount = 0;

		for (ItemStack items : inventory.all(item.getType()).values()) {
			net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(items);
			NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

			if (itemCompound.hasKey("sCash_Amount")) {
				if (items == item)
					continue;

				int itemsAmount = items.getAmount();
				double cash = cashVoucherManager.getCashItem(items) * itemsAmount;
				amount += cash;
			}

		}
		return amount;
	}

	public void removeItem(Inventory inventory, ItemStack item, int amount) {
		for (Map.Entry<Integer, ? extends ItemStack> entry : inventory.all(item.getType()).entrySet()) {
			net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(entry.getValue());
			NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

			if (itemCompound.hasKey("sCash_Amount")) {
				ItemStack currentItem = CraftItemStack.asBukkitCopy(nmsItem);

				if (currentItem.getAmount() <= amount) {
					amount -= currentItem.getAmount();
					inventory.clear(entry.getKey().intValue());

				} else {
					currentItem.setAmount(currentItem.getAmount() - amount);
					amount = 0;
				}

			}
			if (amount == 0)
				break;
		}

	}

}
