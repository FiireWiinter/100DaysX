//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.fiirewiinter.hundreddaysx.gui;

import me.fiirewiinter.hundreddaysx.Main;
import me.fiirewiinter.hundreddaysx.utils.Storage;
import me.fiirewiinter.hundreddaysx.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class MainGUI {
    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 45;
    private static Main plugin;

    public MainGUI(Main plugin) {
        MainGUI.plugin = plugin;
        inventory_name = Utils.chat("&c&l100DaysX GUI");
        inv = Bukkit.createInventory((InventoryHolder)null, inv_rows);
    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory((InventoryHolder)null, inv_rows, inventory_name);
        if (Storage.get_bool("sleeping")) {
            Utils.createItem(inv, "lime_stained_glass_pane", 1, 10, "&l&o&n&aCurrently Enabled", new String[0]);
            Utils.createItem(inv, "blue_bed", 1, 11, "&l&o&bDisable Sleeping", new String[]{"&dAllow/Deny players to sleep"});
        } else {
            Utils.createItem(inv, "red_stained_glass_pane", 1, 10, "&l&o&n&4Currently Disabled", new String[0]);
            Utils.createItem(inv, "blue_bed", 1, 11, "&l&o&bEnable Sleeping", new String[]{"&dAllow/Deny players to sleep"});
        }

        if (Storage.get_bool("pvp.enabled")) {
            Utils.createItem(inv, "lime_stained_glass_pane", 1, 13, "&l&o&n&aCurrently Enabled", new String[0]);
            Utils.createItem(inv, "diamond_sword", 1, 14, "&l&o&bDisable PVP Now", new String[]{"&dSelf Explanatory"});
            Utils.createItem(inv, "diamond_sword", 1, 15, "&l&o&bDisable PVP in 60s", new String[]{"&dSelf Explanatory"});
            Utils.createItem(inv, "diamond_sword", 1, 16, "&l&o&bDisable PVP on day X ", new String[]{"&bDisable PVP on day X (current day here)", "&dWill open up a pop up"});
        } else {
            Utils.createItem(inv, "red_stained_glass_pane", 1, 13, "&l&o&n&4Currently Disabled", new String[0]);
            Utils.createItem(inv, "diamond_sword", 1, 14, "&l&o&bEnable PVP Now", new String[]{"&dSelf Explanatory"});
            Utils.createItem(inv, "diamond_sword", 1, 15, "&l&o&bEnable PVP in 60s", new String[]{"&dSelf Explanatory"});
            Utils.createItem(inv, "diamond_sword", 1, 16, "&l&o&bEnable PVP on day X ", new String[]{"&dDisable PVP on day X (current day here)", "&dWill open up a pop up"});
        }

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        switch(slot) {
        case 11:
            if (Storage.get_bool("sleeping")) {
                Storage.set("sleeping", false);
            } else {
                Storage.set("sleeping", true);
            }

            p.openInventory(GUI(p));
        case 12:
        case 13:
        default:
            break;
        case 14:
            if (Storage.get_bool("pvp.enabled")) {
                Storage.set("pvp.enabled", false);
                p.getWorld().setPVP(false);
            } else {
                Storage.set("pvp.enabled", true);
                p.getWorld().setPVP(true);
            }

            Storage.set("pvp.waiting", false);
            p.openInventory(GUI(p));
            break;
        case 15:
            if (Storage.get_bool("pvp.waiting")) {
                p.sendMessage(Utils.chat("&bThis is awkward... I am already waiting 60 seconds to enable/disable PVP. If you want to change it right now, click on the first option."));
            } else if (Storage.get_bool("pvp.enabled")) {
                Storage.set("pvp.waiting", true);
                Utils.titleEveryone("&bDisabling PVP in 60 seconds", "&dYou are almost safe", 10, 100, 10);
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    Bukkit.getWorld(Storage.get_str("world")).setPVP(false);
                    Storage.set("pvp.enabled", false);
                    Storage.set("pvp.waiting", false);
                    Utils.titleEveryone("&bPVP has been disabled", "&dYou are safe... for now", 10, 100, 10);
                }, 1200L);
            } else {
                Storage.set("pvp.waiting", true);
                Utils.titleEveryone("&bEnabling PVP in 60 seconds", "&c&l&oRun and seek shelter", 10, 100, 10);
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    Bukkit.getWorld(Storage.get_str("world")).setPVP(true);
                    Storage.set("pvp.enabled", true);
                    Storage.set("pvp.waiting", false);
                    Utils.titleEveryone("&c&l&oPVP has been enabled", "&c&l&o&4Run...", 10, 100, 10);
                }, 1200L);
            }
        }

    }
}
