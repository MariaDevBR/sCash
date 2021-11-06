package com.maria.cash;

import com.maria.cash.listeners.*;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.maria.cash.api.CashAPI;
import com.maria.cash.commands.CashCommand;
import com.maria.cash.commands.ShopCommand;
import com.maria.cash.files.CashVoucherFile;
import com.maria.cash.files.MessagesFile;
import com.maria.cash.files.SendCashVoucherFiles;
import com.maria.cash.files.SoundsFile;
import com.maria.cash.files.shop.CategoriesFile;
import com.maria.cash.files.shop.ShopItemsFile;
import com.maria.cash.inventories.InventoryCategorySelect;
import com.maria.cash.inventories.InventorySendVoucher;
import com.maria.cash.managers.ActivateCashVoucherManager;
import com.maria.cash.managers.CashVoucherManager;
import com.maria.cash.managers.StackCashVouchersManager;
import com.maria.cash.methods.BuyItemsMethods;
import com.maria.cash.methods.CashMethods;
import com.maria.cash.methods.CategoryMethods;
import com.maria.cash.models.CashSettings;
import com.maria.cash.models.Messages;
import com.maria.cash.models.SendCashVoucher;
import com.maria.cash.models.Sounds;
import com.maria.cash.models.VoucherCash;
import com.maria.cash.models.shop.CategoriesModel;
import com.maria.cash.services.SQLite;
import com.maria.cash.services.placeholder.registry.PlaceHolderRegistry;
import com.maria.cash.shop.CategoryManager;
import com.maria.cash.shop.ShopItemsManager;
import com.maria.cash.utils.DateManager;
import com.maria.cash.utils.checkers.UpdateCheck;

@Getter
public class Main extends JavaPlugin {

    private SQLite sqlite;
    private CashAPI cashAPI;

    private UpdateCheck updateCheck;

    private CashVoucherFile cashVoucherFile;
    private MessagesFile messagesFile;
    private SoundsFile soundsFile;
    private SendCashVoucherFiles sendCashVoucherFiles;
    private CategoriesFile categoriesFile;
    private ShopItemsFile shopItemsFile;

    private CashSettings cashSettings;
    private VoucherCash voucherCash;
    private Messages messages;
    private Sounds sounds;
    private SendCashVoucher sendCashVoucher;
    private CategoriesModel categoriesModel;

    private InventorySendVoucher inventorySendVoucher;
    private InventoryCategorySelect inventoryCategorySelect;

    private CashMethods cashMethods;
    private CategoryMethods categoryMethods;
    private BuyItemsMethods buyItemMethods;
    private CashVoucherManager cashVoucherManager;
    private StackCashVouchersManager stackCashVouchers;
    private ActivateCashVoucherManager activateCashVoucher;

    private CategoryManager categoryManager;
    private ShopItemsManager shopItemsManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        ConsoleCommandSender consoleMessage = Bukkit.getConsoleSender();
        consoleMessage.sendMessage("§6[" + getDescription().getName() + "] §fIniciado com sucesso");
        consoleMessage.sendMessage("§6[" + getDescription().getName() + "] §fEntre em meu Discord");
        consoleMessage.sendMessage("§6[" + getDescription().getName() + "] §fDiscord: §6https://discord.gg/ysQMPe5tPh");
        consoleMessage.sendMessage("§6[" + getDescription().getName() + "] §fCriado por §6Maria_BR");
        setupSQL();
        registerObjects();
        registerFunctions();
        loadPlaceholders();
        checkUpdate();
    }

    @Override
    public void onDisable() {
        ConsoleCommandSender consoleMessage = Bukkit.getConsoleSender();
        consoleMessage.sendMessage("§6[" + getDescription().getName() + "] §fDesligado");
        consoleMessage.sendMessage("§6[" + getDescription().getName() + "] §fEntre em meu Discord");
        consoleMessage.sendMessage("§6[" + getDescription().getName() + "] §fDiscord: §6https://discord.gg/ysQMPe5tPh");
        consoleMessage.sendMessage("§6[" + getDescription().getName() + "] §fCriado por §6Maria_BR");

        sqlite.closeConnection();
    }

    private void registerFunctions() {
        new CashCommand(this);
        new ShopCommand(this);
        new PlayerEvents(this);
        new CashVoucherEvent(this);
        new ShopCommandEvent(this);
        new MenuCategorySelectEvent(this);
        new BuyItemsEvent(this);
        new MenuSendVoucherEvent(this);
        new SendVoucherEvent(this);
    }

    private void registerObjects() {
        DateManager.createFolder("menus");

        CashVoucherFile.createVoucherCashFile();
        MessagesFile.createMessagesFile();
        SoundsFile.createSoundsFile();
        SendCashVoucherFiles.createVoucherCashFile();
        CategoriesFile.createCategoriesFile();
        ShopItemsFile.createShopItemsFile();

        cashVoucherFile = new CashVoucherFile();
        messagesFile = new MessagesFile();
        soundsFile = new SoundsFile();
        sendCashVoucherFiles = new SendCashVoucherFiles();
        categoriesFile = new CategoriesFile();
        shopItemsFile = new ShopItemsFile();

        voucherCash = new VoucherCash(this);
        messages = new Messages(this);
        sounds = new Sounds(this);
        sendCashVoucher = new SendCashVoucher(this);
        categoriesModel = new CategoriesModel(this);

        cashMethods = new CashMethods(this);
        cashVoucherManager = new CashVoucherManager(this);
        stackCashVouchers = new StackCashVouchersManager(this);
        activateCashVoucher = new ActivateCashVoucherManager(this);

        categoryManager = new CategoryManager(categoriesFile.getConfig());
        shopItemsManager = new ShopItemsManager(shopItemsFile.getConfig());

        categoryMethods = new CategoryMethods(this);
        buyItemMethods = new BuyItemsMethods(this);

        inventorySendVoucher = new InventorySendVoucher(this);
        inventoryCategorySelect = new InventoryCategorySelect(this);
    }

    public SQLite getSQLite() {
        return sqlite;
    }

    private void setupSQL() {
        DateManager.createFolder("cache");

        cashSettings = new CashSettings(this);

        sqlite = new SQLite(this);
        sqlite.createTables();

        cashAPI = new CashAPI(this);
    }

    private void loadPlaceholders() {
        new PlaceHolderRegistry(this).register();
    }

    @SuppressWarnings("deprecation")
    private void checkUpdate() {
        updateCheck = new UpdateCheck(this, 97248);
        if (updateCheck.getUpdateCheckerResult().equals(UpdateCheck.UpdateCheckerResult.OUT_DATED)) {
            updateCheck.messageOutOfDated(Bukkit.getConsoleSender());
            new UpdateEvent(this);

        } else
            Bukkit.getConsoleSender()
                    .sendMessage("§6[" + getDescription().getName() + "] §fNão há nenhuma atualização no momento.");

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            if (updateCheck.getUpdateCheckerResult().equals(UpdateCheck.UpdateCheckerResult.OUT_DATED)) {
                updateCheck.messageOutOfDated(Bukkit.getConsoleSender());

            }
        }, 288000, 288000);

    }

    public String sendMessage(CommandSender p, String message) {
        if (getConfig().getBoolean("Prefix ativar"))
            p.sendMessage(cashSettings.getPrefix() + " " + message);

        else
            p.sendMessage(message);

        return message;
    }

    public boolean hasPermission(CommandSender p, String permission) {
        if (!p.hasPermission("scash." + permission)) {
            sendMessage(p, messages.getNoPermission());
            sendSound(p, sounds.getNoPermission());
            return true;

        }
        return false;
    }

    public void sendSound(CommandSender s, Sound sound) {
        if (!(s instanceof Player))
            return;

        Player p = (Player) s;
        if (getConfig().getBoolean("Sons ativar"))
            p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
    }

}
