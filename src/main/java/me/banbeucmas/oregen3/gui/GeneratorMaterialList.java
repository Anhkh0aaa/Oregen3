package me.banbeucmas.oregen3.gui;

import me.banbeucmas.oregen3.Oregen3;
import me.banbeucmas.oregen3.data.MaterialChooser;
import me.banbeucmas.oregen3.utils.PluginUtils;
import me.banbeucmas.oregen3.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GeneratorMaterialList implements InventoryHolder, InventoryHandler {
    private static final Pattern CHANCE = Pattern.compile("%chance%", Pattern.LITERAL);
    private final Inventory inv;

    public GeneratorMaterialList(final Location location, final OfflinePlayer player) {
        int size = 9;
        final MaterialChooser mc = PluginUtils.getChooser(location);
        final Map<Material, Double> chances = mc.getChances();
        if (chances.size() > size) {
            size *= chances.size() / size + 1;
        }

        final FileConfiguration config = Oregen3.getPlugin().getConfig();
        inv = Bukkit.createInventory(this, size,
                                     StringUtils.getColoredString(config.getString("messages.gui.title"), player));

        chances.forEach((key, value) -> {
            final ItemStack display = new ItemStack(key);
            final ItemMeta meta = display.getItemMeta();
            final double chance = value;

            final List<String> lore = config.getStringList("messages.gui.block.lore")
                    .stream()
                    .map(s -> CHANCE.matcher(s).replaceAll(Matcher.quoteReplacement(Double.toString(chance))))
                    .map(s -> StringUtils.getColoredString(s, player))
                    .collect(Collectors.toList());

            meta.setLore(lore);
            display.setItemMeta(meta);

            inv.addItem(display);
        });
    }

    public GeneratorMaterialList(final UUID uuid, final OfflinePlayer player) {
        int size = 9;
        final MaterialChooser mc = PluginUtils.getChooser(uuid);
        final Map<Material, Double> chances = mc.getChances();
        if (chances.size() > size) {
            size *= chances.size() / size + 1;
        }

        final FileConfiguration config = Oregen3.getPlugin().getConfig();
        inv = Bukkit.createInventory(this, size,
                                     StringUtils.getColoredString(config.getString("messages.gui.title"), player));

        chances.forEach((key, value) -> {
            final ItemStack display = new ItemStack(key);
            final ItemMeta meta = display.getItemMeta();
            final double chance = value;

            final List<String> lore = config.getStringList("messages.gui.block.lore")
                    .stream()
                    .map(s -> CHANCE.matcher(s).replaceAll(Matcher.quoteReplacement(Double.toString(chance))))
                    .map(s -> StringUtils.getColoredString(s, player))
                    .collect(Collectors.toList());

            meta.setLore(lore);

            inv.addItem(display);
        });
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    @Override
    public void onClick(final InventoryClickEvent event) {

    }
}
