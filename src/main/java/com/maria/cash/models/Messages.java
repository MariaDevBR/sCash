package com.maria.cash.models;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.Main;
import com.maria.cash.files.MessagesFile;

@Getter
@Setter
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
        actionCanceled = config.getStringList("Acao cancelada");
        actionCanceled = actionCanceled.stream().map(aC -> aC.replace("&", "§")).collect(Collectors.toList());
        voucherSended = config.getStringList("Vale enviado");
        voucherSended = voucherSended.stream().map(vS -> vS.replace("&", "§")).collect(Collectors.toList());
        voucherReceived = config.getStringList("Vale recebido");
        voucherReceived = voucherReceived.stream().map(vS -> vS.replace("&", "§")).collect(Collectors.toList());

        selectCategory = config.getStringList("Categoria selecionada");
        selectCategory = selectCategory.stream().map(sC -> sC.replace("&", "§")).collect(Collectors.toList());
    }

}
