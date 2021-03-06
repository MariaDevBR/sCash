package com.maria.cash.commands;

import com.maria.cash.api.events.CashSentEvent;
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

    private final CashAPI cashAPI;

    private final Messages messages;
    private final Sounds sounds;

    private final CashMethods cashMethods;
    private final CashVoucherManager cashVoucherManager;

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

            amount = cashMethods.parseDouble(p, a, 2, amount);
            if (cashMethods.amountIsZero(p, amount))
                return true;

            if (!cashMethods.hasCash(p, amount))
                return true;

            CashSentEvent sentCashEvent = new CashSentEvent(p, target, amount);
            Bukkit.getPluginManager().callEvent(sentCashEvent);

            if (!sentCashEvent.isCancelled()) {
                cashAPI.removeCash(p, amount);
                cashAPI.addCash(target, amount);

                cashMethods.sendCash(p, target, amount);

            }
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

            if (!cashMethods.isNumber(p, a, 2))
                return true;

            amount = cashMethods.parseDouble(p, a, 1, amount);
            int quantity = Integer.parseInt(a[2]);

            double amountTotal = amount * quantity;

            if (!cashMethods.hasCash(p, amountTotal))
                return true;

            cashVoucherManager.giveVoucherCash(p, amount, quantity);
            cashAPI.removeCash(p, amountTotal);

            cashMethods.voucherCash(p, amountTotal, quantity);
            return true;

        }
        if (cashMethods.hasArgs(a, "add") || cashMethods.hasArgs(a, "adicionar") || cashMethods.hasArgs(a, "give")) {
            if (cashMethods.invalidArgs(s, a, 3))
                return true;

            if (main.hasPermission(s, "admin"))
                return true;

            Player target = Bukkit.getPlayerExact(a[1]);
            if (cashMethods.hasPlayer(s, target))
                return true;

            amount = cashMethods.parseDouble(s, a, 2, amount);
            if (cashMethods.amountIsZero(s, amount))
                return true;

            cashAPI.addCash(target, amount);
            main.sendMessage(s, messages.getAddCash().replace("{quantia}", Format.formatNumber(amount)).replace("{player}", target.getName()));
            main.sendSound(s, sounds.getAddCash());
            return true;

        }
        if (cashMethods.hasArgs(a, "remove") || cashMethods.hasArgs(a, "remover") || cashMethods.hasArgs(a, "retirar")) {
            if (cashMethods.invalidArgs(s, a, 3))
                return true;

            if (main.hasPermission(s, "admin"))
                return true;

            Player target = Bukkit.getPlayerExact(a[1]);
            if (cashMethods.hasPlayer(s, target))
                return true;

            amount = cashMethods.parseDouble(s, a, 2, amount);
            if (cashMethods.amountIsZero(s, amount))
                return true;

            double cashTarget = cashAPI.getCash(target);
            String amountFormatted = amount >= cashTarget ? Format.formatNumber(cashTarget) : Format.formatNumber(amount);

            cashAPI.removeCash(target, amount >= cashTarget ? cashTarget : amount);
            main.sendMessage(s, messages.getRemoveCash().replace("{quantia}", amountFormatted).replace("{player}", target.getName()));
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

            amount = cashMethods.parseDouble(s, a, 2, amount);

            if (cashMethods.amountIsZero(s, amount))
                return true;

            cashAPI.setCash(target, amount);
            main.sendMessage(s, messages.getSetCash().replace("{quantia}", Format.formatNumber(amount)).replace("{player}", target.getName()));
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

            amount = cashMethods.parseDouble(s, a, 2, amount);
            int quantity = Integer.parseInt(a[3]);

            double amountTotal = amount * quantity;

            if (cashMethods.hasNumber(s, amount) || cashMethods.hasNumber(s, quantity))
                return true;

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
            main.sendMessage(s, "??aPlugin recarregado com sucesso.");

        }
        return false;
    }

}
