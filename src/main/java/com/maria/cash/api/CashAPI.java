package com.maria.cash.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.entity.Player;

import com.maria.cash.Main;
import com.maria.cash.services.SQLite;

public class CashAPI {

    protected Main main;

    private final SQLite sql;

    public CashAPI(Main main) {
        this.main = main;

        sql = main.getSQLite();
    }

    public String getValue(String table, String column, Object value, int columnLabel) {
        String stringValue = null;

        try {
            ResultSet resultSet;
            PreparedStatement preparedStatement = sql.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE " + column + "='" + value + "'");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                stringValue = resultSet.getString(columnLabel);

        } catch (Exception ignored) {}

        return stringValue;
    }

    public boolean hasAccount(Player p) {
        String account = getValue("scash", "player", p.getName(), 1);
        return account != null;
    }

    public boolean hasAccountByName(String playerName) {
        String account = getValue("scash", "player", playerName, 1);
        return account != null;
    }

    public double addCash(Player p, double value) {
        if (hasAccount(p)) {
            double cash = getCash(p);
            double cashTotal = value + cash;

            sql.executeQuery("UPDATE scash SET cash=" + cashTotal + " WHERE player='" + p.getName() + "'");

        } else {
            sql.createAccount(p);
            addCash(p, value);

        }
        return value;
    }

    public double addCashByName(String playerName, double value) {
        if (hasAccountByName(playerName)) {
            double cash = getCashByName(playerName);
            double cashTotal = value + cash;

            sql.executeQuery("UPDATE scash SET cash=" + cashTotal + " WHERE player='" + playerName + "'");

        }
        return value;
    }

    public double getCash(Player p) {
        double cash = 0.0;
        String playerCash = getValue("scash", "player", p.getName(), 2);

        if (hasAccount(p))
            cash = Double.parseDouble(playerCash);

        else
            sql.createAccount(p);

        return cash;
    }

    public double getCashByName(String playerName) {
        double cash = 0.0;
        String playerCash = getValue("scash", "player", playerName, 2);

        if (hasAccountByName(playerName))
            cash = Double.parseDouble(playerCash);

        return cash;
    }

    public double setCash(Player p, double value) {
        if (hasAccount(p)) {
            double cash = getCash(p);
            cash = value;

            sql.executeQuery("UPDATE scash SET cash=" + cash + " WHERE player='" + p.getName() + "'");

        } else {
            sql.createAccount(p);
            setCash(p, value);

        }
        return value;
    }

    public double setCashByName(String playerName, double value) {
        if (hasAccountByName(playerName)) {
            double cash = getCashByName(playerName);
            cash = value;

            sql.executeQuery("UPDATE scash SET cash=" + cash + " WHERE player='" + playerName + "'");

        }
        return value;
    }

    public double removeCash(Player p, double value) {
        if (hasAccount(p)) {
            double cash = getCash(p);
            double cashFinal = cash - value;

            sql.executeQuery("UPDATE scash SET cash=" + cashFinal + " WHERE player='" + p.getName() + "'");

        } else {
            sql.createAccount(p);
            removeCash(p, value);

        }
        return value;
    }

    public double removeCashByName(String playerName, double value) {
        if (hasAccountByName(playerName)) {
            double cash = getCashByName(playerName);
            double cashFinal = cash - value;

            sql.executeQuery("UPDATE scash SET cash=" + cashFinal + " WHERE player='" + playerName + "'");

        }
        return value;
    }

    public boolean hasCash(Player p, double amount) {
        return getCash(p) >= amount;
    }

    public boolean hasCashByName(String playerName, double amount) {
        return getCashByName(playerName) >= amount;
    }

    public void deleteAccount(Player p) {
        sql.executeQuery("DELETE FROM scash WHERE player='" + p.getName() + "'");
    }

}
