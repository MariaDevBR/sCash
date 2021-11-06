package com.maria.cash.models;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.Main;
import com.maria.cash.files.CashVoucherFile;

@Getter
@Setter
public class VoucherCash {

    protected Main main;

    private CashVoucherFile cashVoucherFile;
    private FileConfiguration config;

    private String name;
    private String skull;
    private List<String> lore;
    private Material material;
    private int data;
    private boolean customSkull;
    private boolean glow;

    public VoucherCash(Main main) {
        this.main = main;

        cashVoucherFile = main.getCashVoucherFile();
        config = cashVoucherFile.getConfig();

        name = config.getString("Vale cash.Item.Nome").replace("&", "§");
        skull = config.getString("Vale cash.Item.Skull");
        lore = config.getStringList("Vale cash.Item.Lore");
        lore = lore.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());
        material = Material.valueOf(config.getString("Vale cash.Item.Material").split(":")[0]);
        data = Integer.parseInt(config.getString("Vale cash.Item.Material").split(":")[1]);

        customSkull = config.getBoolean("Vale cash.Item.Custom skull");
        glow = config.getBoolean("Vale cash.Item.Glow");
    }

}
