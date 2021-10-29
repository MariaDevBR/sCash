package com.maria.cash.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.maria.cash.Main;
import com.maria.cash.models.VoucherCash;
import com.maria.cash.utils.Format;
import com.maria.cash.utils.ItemBuilder;
import com.maria.cash.utils.SkullAPI;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagDouble;

public class CashVoucherManager {

	protected Main main;

	private VoucherCash voucherCash;

	public CashVoucherManager(Main main) {
		this.main = main;

		voucherCash = main.getVoucherCash();
	}

	public void giveVoucherCash(Player p, double value, int quantity) {
		ItemStack item;

		String name = voucherCash.getName();
		String skull = voucherCash.getSkull();
		List<String> lore = voucherCash.getLore();
		lore = lore.stream().map(l -> l.replace("{quantia}", Format.format(value))).collect(Collectors.toList());

		Material material = voucherCash.getMaterial();
		int data = voucherCash.getData();

		boolean customSkull = voucherCash.isCustomSkull();
		boolean glow = voucherCash.isGlow();

		if (customSkull)
			item = new ItemStack(SkullAPI.getSkull(skull));

		else
			item = new ItemStack(material, 1, (short) data);

		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		itemCompound.set("sCash_Amount", new NBTTagDouble(value));
		nmsItem.setTag(itemCompound);

		ItemStack craftItem = CraftItemStack.asBukkitCopy(nmsItem);
		ItemStack itemBuilded = new ItemBuilder(craftItem).setName(name).setLore(lore).addGlow(glow).build();

		itemBuilded.setAmount(quantity);
		p.getInventory().addItem(itemBuilded);
	}

	public double getCashItem(ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		double cash = itemCompound.getDouble("sCash_Amount");

		return cash;
	}

	public void removeItem(Player player) {
		ItemStack item = player.getItemInHand();

		if (item.getAmount() <= 1)
			player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(Material.AIR));

		else
			item.setAmount(item.getAmount() - 1);
	}

}
