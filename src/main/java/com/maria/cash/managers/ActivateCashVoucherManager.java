package com.maria.cash.managers;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.maria.cash.Main;
import com.maria.cash.api.CashAPI;
import com.maria.cash.models.Messages;
import com.maria.cash.models.Sounds;
import com.maria.cash.utils.Format;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class ActivateCashVoucherManager {

    protected Main main;

    private CashAPI cashAPI;

    private CashVoucherManager cashVoucherManager;

    private Messages messages;
    private Sounds sounds;

    public ActivateCashVoucherManager(Main main) {
        this.main = main;

        cashAPI = main.getCashAPI();

        cashVoucherManager = main.getCashVoucherManager();

        messages = main.getMessages();
        sounds = main.getSounds();
    }

    public void activateVoucher(Player p, ItemStack item) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

        if (!itemCompound.hasKey("sCash_Amount"))
            return;

        double cash = cashVoucherManager.getCashItem(item);

        sucessActivate(p, cash);
    }

    private void sucessActivate(Player p, double amount) {
        cashVoucherManager.removeItem(p);
        cashAPI.addCash(p, amount);

        for (String activateVoucher : messages.getActivateVoucherCash())
            p.sendMessage(activateVoucher.replace("{quantia}", Format.formatNumber(amount)));

        main.sendSound(p, sounds.getActivateVoucherCash());
    }

}
