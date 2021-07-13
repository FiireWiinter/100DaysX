package me.fiirewiinter.hundreddaysx.utils;

import me.fiirewiinter.hundreddaysx.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class Utils {
    private static Main plugin;

    public Utils(Main plugin) {
        Utils.plugin = plugin;
    }

    // Format color strings
    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    // Logging
    public static void info(String msg) {
        plugin.getLogger().log(Level.INFO, msg);
    }

    public static void severe(String msg) {
        plugin.getLogger().log(Level.SEVERE, msg);
    }

    public static void warning(String msg) {
        plugin.getLogger().log(Level.WARNING, msg);
    }

    public static void debug(String msg) {
        if (Storage.getConfig().getBoolean("debug")) {
            Player p = Bukkit.getPlayer(UUID.fromString("a9085607-f473-45f1-90ec-7ad71d697853")); // FiireWiinter
            if (p != null) {
                p.sendMessage(Utils.chat(msg));
            }
        }
    }

    // Create a item for GUIs
    public static ItemStack createItem(Inventory inv, String material, int amount, int invSlot, String displayName, String... loreString) {
        List<String> lore = new ArrayList();
        ItemStack item = new ItemStack(Material.matchMaterial(material), amount);
        return getItemStack(inv, invSlot, displayName, lore, item, loreString);
    }

    // Create a item for GUIs with special bytes
    public static ItemStack createItemByte(Inventory inv, String material, int byteId, int amount, int invSlot, String displayName, String... loreString) {
        List<String> lore = new ArrayList();
        ItemStack item = new ItemStack(Material.matchMaterial(material), amount, (short) byteId);
        return getItemStack(inv, invSlot, displayName, lore, item, loreString);
    }

    // Add meta and add to inventory
    private static ItemStack getItemStack(Inventory inv, int invSlot, String displayName, List<String> lore, ItemStack item, String[] loreString) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(chat(displayName));

        for (String s : loreString) {
            lore.add(chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot, item);
        return item;
    }

    // Send every player on the server a title
    public static void titleEveryone(String title, String subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) {
        for (Player player : Bukkit.getOnlinePlayers())
            player.sendTitle(chat(title), chat(subtitle), fadeInTicks, stayTicks, fadeOutTicks);
    }

    // Send a title to only a player
    public static void titlePlayer(Player player, String title, String subtitle, int fadeInSecs, int staySecs, int fadeOutSecs) {
        player.sendTitle(chat(title), chat(subtitle), fadeInSecs * 20, staySecs * 20, fadeOutSecs * 20);
    }
}
