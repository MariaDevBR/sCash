package com.maria.cash.models;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.Main;
import com.maria.cash.files.MessagesFile;

public class Messages {

	protected Main main;

	private MessagesFile messagesFile;
	private FileConfiguration config;

	private String noPermission;

	private List<String> commands, commandsAdmin;

	private List<String> balance, othersBalance;

	private String invalidPlayer;
	private String yourSelf;
	private String invalidNumber;
	private String cashInsufficient;
	private String littleCash;

	private List<String> sendCash;
	private List<String> receivedCash;

	private String addCash;
	private String removeCash;
	private String setCash;

	private List<String> voucher;
	private String giveVoucher;

	private List<String> stackVouchersCash;
	private List<String> activateVoucherCash;

	private List<String> sendVoucher;
	private List<String> actionCanceled;
	private List<String> voucherSended;
	private List<String> voucherReceived;

	private List<String> selectCategory;

	public Messages(Main main) {
		this.main = main;

		messagesFile = main.getMessagesFile();
		config = messagesFile.getConfig();

		noPermission = config.getString("Sem permissao").replace("&", "§");

		String command = main.getCashSettings().getShopCommand();

		commands = config.getStringList("Comandos");
		commands = commands.stream().map(c -> c.replace("&", "§").replace("<command>", command))
				.collect(Collectors.toList());
		commandsAdmin = config.getStringList("Comandos admin");
		commandsAdmin = commandsAdmin.stream().map(c -> c.replace("&", "§").replace("<command>", command))
				.collect(Collectors.toList());

		balance = config.getStringList("Saldo");
		balance = balance.stream().map(b -> b.replace("&", "§")).collect(Collectors.toList());
		othersBalance = config.getStringList("Saldo outros");
		othersBalance = othersBalance.stream().map(oB -> oB.replace("&", "§")).collect(Collectors.toList());

		invalidPlayer = config.getString("Player invalido").replace("&", "§");
		yourSelf = config.getString("Voce mesmo").replace("&", "§");
		invalidNumber = config.getString("Numero invalido").replace("&", "§");
		cashInsufficient = config.getString("Cash insuficiente").replace("&", "§");
		littleCash = config.getString("Pouco cash").replace("&", "§");

		sendCash = config.getStringList("Cash enviado");
		sendCash = sendCash.stream().map(sC -> sC.replace("&", "§")).collect(Collectors.toList());
		receivedCash = config.getStringList("Cash recebido");
		receivedCash = receivedCash.stream().map(rC -> rC.replace("&", "§")).collect(Collectors.toList());

		addCash = config.getString("Cash adicionado").replace("&", "§");
		removeCash = config.getString("Cash removido").replace("&", "§");
		setCash = config.getString("Cash setado").replace("&", "§");

		voucher = config.getStringList("Vale");
		voucher = voucher.stream().map(v -> v.replace("&", "§")).collect(Collectors.toList());
		giveVoucher = config.getString("Vale givado").replace("&", "§");

		stackVouchersCash = config.getStringList("Stack vales");
		stackVouchersCash = stackVouchersCash.stream().map(sVC -> sVC.replace("&", "§")).collect(Collectors.toList());

		activateVoucherCash = config.getStringList("Vale ativado");
		activateVoucherCash = activateVoucherCash.stream().map(aVC -> aVC.replace("&", "§"))
				.collect(Collectors.toList());

		sendVoucher = config.getStringList("Enviar vale");
		sendVoucher = sendVoucher.stream().map(sV -> sV.replace("&", "§")).collect(Collectors.toList());
		actionCanceled = config.getStringList("Açao cancelada");
		actionCanceled = actionCanceled.stream().map(aC -> aC.replace("&", "§")).collect(Collectors.toList());
		voucherSended = config.getStringList("Vale enviado");
		voucherSended = voucherSended.stream().map(vS -> vS.replace("&", "§")).collect(Collectors.toList());
		voucherReceived = config.getStringList("Vale recebido");
		voucherReceived = voucherReceived.stream().map(vS -> vS.replace("&", "§")).collect(Collectors.toList());

		selectCategory = config.getStringList("Categoria selecionada");
		selectCategory = selectCategory.stream().map(sC -> sC.replace("&", "§")).collect(Collectors.toList());
	}

	public String getNoPermission() {
		return noPermission;
	}

	public List<String> getCommands() {
		return commands;
	}

	public List<String> getCommandsAdmin() {
		return commandsAdmin;
	}

	public List<String> getBalance() {
		return balance;
	}

	public List<String> getOthersBalance() {
		return othersBalance;
	}

	public String getInvalidPlayer() {
		return invalidPlayer;
	}

	public String getYourSelf() {
		return yourSelf;
	}

	public String getInvalidNumber() {
		return invalidNumber;
	}

	public String getCashInsufficient() {
		return cashInsufficient;
	}

	public String getLittleCash() {
		return littleCash;
	}

	public List<String> getSendCash() {
		return sendCash;
	}

	public List<String> getReceivedCash() {
		return receivedCash;
	}

	public String getAddCash() {
		return addCash;
	}

	public String getRemoveCash() {
		return removeCash;
	}

	public String getSetCash() {
		return setCash;
	}

	public List<String> getVoucher() {
		return voucher;
	}

	public String getGiveVoucher() {
		return giveVoucher;
	}

	public List<String> getStackVouchersCash() {
		return stackVouchersCash;
	}

	public List<String> getActivateVoucherCash() {
		return activateVoucherCash;
	}

	public List<String> getSendVoucher() {
		return sendVoucher;
	}

	public List<String> getActionCanceled() {
		return actionCanceled;
	}

	public List<String> getVoucherSended() {
		return voucherSended;
	}

	public List<String> getVoucherReceived() {
		return voucherReceived;
	}

	public List<String> getSelectCategory() {
		return selectCategory;
	}

	public void setNoPermission(String noPermission) {
		this.noPermission = noPermission;
	}

	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

	public void setCommandsAdmin(List<String> commandsAdmin) {
		this.commandsAdmin = commandsAdmin;
	}

	public void setBalance(List<String> balance) {
		this.balance = balance;
	}

	public void setOthersBalance(List<String> othersBalance) {
		this.othersBalance = othersBalance;
	}

	public void setInvalidPlayer(String invalidPlayer) {
		this.invalidPlayer = invalidPlayer;
	}

	public void setYourSelf(String yourSelf) {
		this.yourSelf = yourSelf;
	}

	public void setInvalidNumber(String invalidNumber) {
		this.invalidNumber = invalidNumber;
	}

	public void setCashInsufficient(String cashInsufficient) {
		this.cashInsufficient = cashInsufficient;
	}

	public void setLittleCash(String littleCash) {
		this.littleCash = littleCash;
	}

	public void setSendCash(List<String> sendCash) {
		this.sendCash = sendCash;
	}

	public void setReceivedCash(List<String> receivedCash) {
		this.receivedCash = receivedCash;
	}

	public void setAddCash(String addCash) {
		this.addCash = addCash;
	}

	public void setRemoveCash(String removeCash) {
		this.removeCash = removeCash;
	}

	public void setSetCash(String setCash) {
		this.setCash = setCash;
	}

	public void setVoucher(List<String> voucher) {
		this.voucher = voucher;
	}

	public void setGiveVoucher(String giveVoucher) {
		this.giveVoucher = giveVoucher;
	}

	public void setStackVouchersCash(List<String> stackVouchersCash) {
		this.stackVouchersCash = stackVouchersCash;
	}

	public void setActivateVoucherCash(List<String> activateVoucherCash) {
		this.activateVoucherCash = activateVoucherCash;
	}

	public void setSendVoucher(List<String> sendVoucher) {
		this.sendVoucher = sendVoucher;
	}

	public void setActionCanceled(List<String> actionCanceled) {
		this.actionCanceled = actionCanceled;
	}

	public void setVoucherSended(List<String> voucherSended) {
		this.voucherSended = voucherSended;
	}

	public void setVoucherReceived(List<String> voucherReceived) {
		this.voucherReceived = voucherReceived;
	}

	public void setSelectCategory(List<String> selectCategory) {
		this.selectCategory = selectCategory;
	}

}
