//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.fiirewiinter.hundreddaysx.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import me.fiirewiinter.hundreddaysx.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {
    private static Main plugin;

    public Utils(Main plugin) {
        Utils.plugin = plugin;
    }

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void info(String msg) {
        plugin.getLogger().log(Level.INFO, msg);
    }

    public static void severe(String msg) {
        plugin.getLogger().log(Level.SEVERE, msg);
    }

    public static void warning(String msg) {
        plugin.getLogger().log(Level.WARNING, msg);
    }

    public static ItemStack createItem(Inventory inv, String material, int amount, int invSlot, String displayName, String... loreString) {
        List<String> lore = new ArrayList();
        ItemStack item = new ItemStack(Material.matchMaterial(material), amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(chat(displayName));
        String[] var9 = loreString;
        int var10 = loreString.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            String s = var9[var11];
            lore.add(chat(s));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot, item);
        return item;
    }

    public static ItemStack createItemByte(Inventory inv, String material, int byteId, int amount, int invSlot, String displayName, String... loreString) {
        List<String> lore = new ArrayList();
        ItemStack item = new ItemStack(Material.matchMaterial(material), amount, (short)byteId);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(chat(displayName));
        String[] var10 = loreString;
        int var11 = loreString.length;

        for(int var12 = 0; var12 < var11; ++var12) {
            String s = var10[var12];
            lore.add(chat(s));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot, item);
        return item;
    }

    public static void titleEveryone(String title, String subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) {
        Iterator var5 = Bukkit.getOnlinePlayers().iterator();

        while(var5.hasNext()) {
            Player player = (Player)var5.next();
            player.sendTitle(chat(title), chat(subtitle), fadeInTicks, stayTicks, fadeOutTicks);
        }

    }

    public static void titlePlayer(Player player, String title, String subtitle, int fadeInSecs, int staySecs, int fadeOutSecs) {
        player.sendTitle(chat(title), chat(subtitle), fadeInSecs * 20, staySecs * 20, fadeOutSecs * 20);
    }
}
