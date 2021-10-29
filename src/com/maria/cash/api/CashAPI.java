package com.maria.cash.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import com.maria.cash.Main;
import com.maria.cash.services.SQLite;

public class CashAPI {

	protected Main main;

	private SQLite sql;

	public CashAPI(Main main) {
		this.main = main;

		sql = main.getSQLite();
	}

	public boolean hasAccount(Player p) {
		try {
			PreparedStatement preparedStatement = sql.getConnection()
					.prepareStatement("SELECT * FROM scash WHERE player='" + p.getName() + "'");
			ResultSet resultSet = preparedStatement.executeQuery();

			return resultSet.next();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
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

	public double getCash(Player p) {
		double cash = 0.0;

		if (hasAccount(p)) {
			try {
				PreparedStatement preparedStatement = sql.getConnection()
						.prepareStatement("SELECT cash FROM scash WHERE player='" + p.getName() + "'");
				ResultSet resultSet = preparedStatement.executeQuery();

				cash = resultSet.getDouble("cash");

			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		} else
			sql.createAccount(p);

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

	public boolean hasCash(Player p, double amount) {
		if (getCash(p) >= amount)
			return true;

		return false;
	}

	public void deleteAccount(Player p) {
		sql.executeQuery("DELETE FROM scash WHERE player='" + p.getName() + "'");
	}

}
