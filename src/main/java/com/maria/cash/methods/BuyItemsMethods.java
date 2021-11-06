package com.maria.cash.methods;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.maria.cash.Main;
import com.maria.cash.api.CashAPI;
import com.maria.cash.models.Sounds;
import com.maria.cash.shop.ShopItems;
import com.maria.cash.utils.ActionBarAPI;

public class BuyItemsMethods {

    protected Main main;

    private CashAPI cashAPI;
    private CashMethods cashMethods;

    private Sounds sounds;

    public BuyItemsMethods(Main main) {
        this.main = main;

        cashAPI = main.getCashAPI();
        cashMethods = main.getCashMethods();

        sounds = main.getSounds();
    }

    public void buyItem(Player player, ShopItems shopItems) {
        double price = shopItems.getPrice();

        if (!cashMethods.hasCash(player, price))
            return;

        List<String> message = shopItems.getBroadcastMessage();
        String actionBar = shopItems.getBroadcastActionBar().replace("{player}", player.getName());

        for (String commands : shopItems.getCommands())
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("{player}", player.getName()));

        if (shopItems.isBroadcast()) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (shopItems.isChat())
                    message.forEach(msg -> players.sendMessage(msg.replace("{player}", player.getName())));

                if (shopItems.isActionBar())
                    ActionBarAPI.sendActionBar(actionBar, players);
            }

        }
        cashAPI.removeCash(player, price);
        main.sendSound(player, sounds.getBuyItem());
    }

    public boolean hasItems(Player player, ShopItems shopItems) {
        if (shopItems == null) {
            main.sendMessage(player, "§cEste item não existe.");
            main.sendSound(player, Sound.VILLAGER_NO);
            return false;

        }
        return true;
    }

}
