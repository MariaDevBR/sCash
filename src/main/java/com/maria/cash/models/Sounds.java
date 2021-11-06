package com.maria.cash.models;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.Main;
import com.maria.cash.files.SoundsFile;

@Getter
@Setter
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

}
