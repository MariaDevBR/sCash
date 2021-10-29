package com.maria.cash.models;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.Main;
import com.maria.cash.files.SoundsFile;

public class Sounds {

	protected Main main;

	private SoundsFile soundsFile;
	private FileConfiguration config;

	private Sound noPermission;
	private Sound incorrectCommand;

	private Sound balance;
	private Sound othersBalance;

	private Sound invalidPlayer;
	private Sound invalidNumber;
	private Sound cashInsufficient;
	private Sound littleCash;

	private Sound sendCash;
	private Sound receivedCash;

	private Sound addCash;
	private Sound removeCash;
	private Sound setCash;

	private Sound voucher;
	private Sound giveVoucher;

	private Sound stackVoucherCash;
	private Sound activateVoucherCash;

	private Sound sendVoucher;
	private Sound actionCanceled;
	private Sound voucherSended;

	private Sound selectCategory;

	private Sound buyItem;

	public Sounds(Main main) {
		this.main = main;

		soundsFile = main.getSoundsFile();
		config = soundsFile.getConfig();

		noPermission = Sound.valueOf(config.getString("Sem permissao"));
		incorrectCommand = Sound.valueOf(config.getString("Comando incorreto"));

		balance = Sound.valueOf(config.getString("Saldo"));
		othersBalance = Sound.valueOf(config.getString("Saldo outros"));

		invalidPlayer = Sound.valueOf(config.getString("Player invalido"));
		invalidNumber = Sound.valueOf(config.getString("Numero invalido"));
		cashInsufficient = Sound.valueOf(config.getString("Cash insuficiente"));
		littleCash = Sound.valueOf(config.getString("Pouco cash"));

		sendCash = Sound.valueOf(config.getString("Cash enviado"));
		receivedCash = Sound.valueOf(config.getString("Cash recebido"));

		addCash = Sound.valueOf(config.getString("Cash adicionado"));
		removeCash = Sound.valueOf(config.getString("Cash removido"));
		setCash = Sound.valueOf(config.getString("Cash setado"));

		voucher = Sound.valueOf(config.getString("Vale"));
		giveVoucher = Sound.valueOf(config.getString("Vale givado"));

		stackVoucherCash = Sound.valueOf(config.getString("Stack vales"));
		activateVoucherCash = Sound.valueOf(config.getString("Vale ativado"));

		sendVoucher = Sound.valueOf(config.getString("Enviar vale"));
		actionCanceled = Sound.valueOf(config.getString("Acao cancelada"));
		voucherSended = Sound.valueOf(config.getString("Vale enviado"));

		selectCategory = Sound.valueOf(config.getString("Categoria selecionada"));

		buyItem = Sound.valueOf(config.getString("Item comprado"));
	}

	public Sound getNoPermission() {
		return noPermission;
	}

	public void setNoPermission(Sound noPermission) {
		this.noPermission = noPermission;
	}

	public Sound getIncorrectCommand() {
		return incorrectCommand;
	}

	public void setIncorrectCommand(Sound incorrectCommand) {
		this.incorrectCommand = incorrectCommand;
	}

	public Sound getBalance() {
		return balance;
	}

	public void setBalance(Sound balance) {
		this.balance = balance;
	}

	public Sound getOthersBalance() {
		return othersBalance;
	}

	public void setOthersBalance(Sound othersBalance) {
		this.othersBalance = othersBalance;
	}

	public Sound getInvalidPlayer() {
		return invalidPlayer;
	}

	public void setInvalidPlayer(Sound invalidPlayer) {
		this.invalidPlayer = invalidPlayer;
	}

	public Sound getInvalidNumber() {
		return invalidNumber;
	}

	public void setInvalidNumber(Sound invalidNumber) {
		this.invalidNumber = invalidNumber;
	}

	public Sound getCashInsufficient() {
		return cashInsufficient;
	}

	public void setCashInsufficient(Sound cashInsufficient) {
		this.cashInsufficient = cashInsufficient;
	}

	public Sound getLittleCash() {
		return littleCash;
	}

	public void setLittleCash(Sound littleCash) {
		this.littleCash = littleCash;
	}

	public Sound getSendCash() {
		return sendCash;
	}

	public void setSendCash(Sound sendCash) {
		this.sendCash = sendCash;
	}

	public Sound getReceivedCash() {
		return receivedCash;
	}

	public void setReceivedCash(Sound receivedCash) {
		this.receivedCash = receivedCash;
	}

	public Sound getAddCash() {
		return addCash;
	}

	public void setAddCash(Sound addCash) {
		this.addCash = addCash;
	}

	public Sound getRemoveCash() {
		return removeCash;
	}

	public void setRemoveCash(Sound removeCash) {
		this.removeCash = removeCash;
	}

	public Sound getSetCash() {
		return setCash;
	}

	public void setSetCash(Sound setCash) {
		this.setCash = setCash;
	}

	public Sound getVoucher() {
		return voucher;
	}

	public void setVoucher(Sound voucher) {
		this.voucher = voucher;
	}

	public Sound getGiveVoucher() {
		return giveVoucher;
	}

	public void setGiveVoucher(Sound giveVoucher) {
		this.giveVoucher = giveVoucher;
	}

	public Sound getStackVoucherCash() {
		return stackVoucherCash;
	}

	public void setStackVoucherCash(Sound stackVoucherCash) {
		this.stackVoucherCash = stackVoucherCash;
	}

	public Sound getActivateVoucherCash() {
		return activateVoucherCash;
	}

	public void setActivateVoucherCash(Sound activateVoucherCash) {
		this.activateVoucherCash = activateVoucherCash;
	}

	public Sound getSendVoucher() {
		return sendVoucher;
	}

	public void setSendVoucher(Sound sendVoucher) {
		this.sendVoucher = sendVoucher;
	}

	public Sound getActionCanceled() {
		return actionCanceled;
	}

	public void setActionCanceled(Sound actionCanceled) {
		this.actionCanceled = actionCanceled;
	}

	public Sound getVoucherSended() {
		return voucherSended;
	}

	public void setVoucherSended(Sound voucherSended) {
		this.voucherSended = voucherSended;
	}

	public Sound getSelectCategory() {
		return selectCategory;
	}

	public void setSelectCategory(Sound selectCategory) {
		this.selectCategory = selectCategory;
	}

	public Sound getBuyItem() {
		return buyItem;
	}

	public void setBuyItem(Sound buyItem) {
		this.buyItem = buyItem;
	}

}
