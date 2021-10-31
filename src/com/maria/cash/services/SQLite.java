package com.maria.cash.services;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.maria.cash.Main;
import com.maria.cash.models.CashSettings;

public class SQLite {

	protected Main main;

	private Connection connection;

	private CashSettings cashSettings;

	public SQLite(Main main) {
		this.main = main;

		openConnection();

		cashSettings = main.getCashSettings();
	}

	public void openConnection() {
		File file = new File("plugins/sCash/cache/database.db");
		String url = "jdbc:sqlite:" + file;

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(url);

			Bukkit.getConsoleSender().sendMessage("�6[sCash] �fConex�o com �6SQLite �faberta com sucesso");

		} catch (SQLException | ClassNotFoundException e) {
			Bukkit.getConsoleSender()
					.sendMessage("�6[sCash] �cOcorreu um erro ao tentar fazer conex�o com �6SQLite�c. Erro:");
			e.printStackTrace();
		}

	}

	public void closeConnection() {
		if (connection == null)
			return;

		try {
			connection.close();

			Bukkit.getConsoleSender().sendMessage("�6[sCash] �fConex�o com �6SQLite �ffechada com sucesso");

		} catch (SQLException e) {
			Bukkit.getConsoleSender()
					.sendMessage("�6[sCash] �cOcorreu um erro ao tentar fechar a conex�o com �6SQLite�c. Erro:");
			e.printStackTrace();
		}

	}

	public boolean executeQuery(String query) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.execute();
			preparedStatement.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void createAccount(Player p) {
		double startCash = cashSettings.getStartCash();

		executeQuery("INSERT INTO scash VALUES ('" + p.getName() + "', '" + startCash + "')");

		Bukkit.getConsoleSender()
				.sendMessage("�f[DEBUG] �6[sCash] �fPlayer �6" + p.getName() + " �fsalvo no banco de dados");
	}

	public void createTables() {
		executeQuery("CREATE TABLE IF NOT EXISTS `scash` (player VARCHAR(24), cash DOUBLE)");
	}

	public Connection getConnection() {
		return connection;
	}

}
