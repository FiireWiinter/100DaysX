package me.fiirewiinter.hundreddaysx.gui;

import me.fiirewiinter.hundreddaysx.Main;
import me.fiirewiinter.hundreddaysx.utils.Storage;
import me.fiirewiinter.hundreddaysx.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MainGUI {
    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 5 * 9;
    private static Main plugin;

    public MainGUI(Main plugin) {
        MainGUI.plugin = plugin;
        inventory_name = Utils.chat("&c&l100DaysX GUI");
        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);
        if (Storage.get_bool("sleeping")) {
            Utils.createItem(inv, "lime_stained_glass_pane", 1, 10, "&l&o&n&aCurrently Enabled");
            Utils.createItem(inv, "blue_bed", 1, 11, "&l&o&bDisable Sleeping", "&dAllow/Deny players to sleep");
        } else {
            Utils.createItem(inv, "red_stained_glass_pane", 1, 10, "&l&o&n&4Currently Disabled");
            Utils.createItem(inv, "blue_bed", 1, 11, "&l&o&bEnable Sleeping", "&dAllow/Deny players to sleep");
        }

        if (Storage.get_bool("pvp.enabled")) {
            Utils.createItem(inv, "lime_stained_glass_pane", 1, 13, "&l&o&n&aCurrently Enabled");
            Utils.createItem(inv, "diamond_sword", 1, 14, "&l&o&bDisable PVP Now", "&dSelf Explanatory");
            Utils.createItem(inv, "diamond_sword", 1, 15, "&l&o&bDisable PVP in 60s", "&dSelf Explanatory");
            Utils.createItem(inv, "diamond_sword", 1, 16, "&l&o&bDisable PVP on day X ", "&bDisable PVP on day X (current day here)", "&dWill open up a pop up");
        } else {
            Utils.createItem(inv, "red_stained_glass_pane", 1, 13, "&l&o&n&4Currently Disabled");
            Utils.createItem(inv, "wooden_sword", 1, 14, "&l&o&bEnable PVP Now", "&dSelf Explanatory");
            Utils.createItem(inv, "wooden_sword", 1, 15, "&l&o&bEnable PVP in 60s", "&dSelf Explanatory");
            Utils.createItem(inv, "wooden_sword", 1, 16, "&l&o&bEnable PVP on day X ", "&dDisable PVP on day X (current day here)", "&dWill open up a pop up");
        }

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        switch (slot) {
            case 11: {
                if (Storage.get_bool("sleeping")) {
                    Storage.set("sleeping", false);
                } else {
                    Storage.set("sleeping", true);
                }

                p.openInventory(GUI(p));
            }
            case 14: {
                boolean prepared = Storage.get_bool("prepared");
                boolean started = Storage.get_bool("started");
                boolean paused = Storage.get_bool("paused");
                if (paused || !started) {
                    p.sendMessage("I'm sorry, but you can't run this right now, due to the following:");
                    if (prepared) {
                        p.sendMessage("There is no pvp allowed in the prep phase. (You can use a different plugin to toggle it, IF you really need it)");
                        break;
                    }
                    if (paused) {
                        p.sendMessage("The 100DaysX game is currently paused. (You can use a different plugin to toggle it, IF you really need it)");
                        break;
                    }
                    p.sendMessage("A 100DaysX game is currently not running");
                    break;
                }
                if (Storage.get_bool("pvp.enabled")) {
                    Storage.set("pvp.enabled", false);
                    p.getWorld().setPVP(false);
                    Utils.titleEveryone("&bPVP has been disabled", "&dYou are safe... for now", 10, 100, 10);
                } else {
                    Storage.set("pvp.enabled", true);
                    p.getWorld().setPVP(true);
                    Utils.titleEveryone("&c&l&oPVP has been enabled", "&c&l&o&4Run...", 10, 100, 10);
                }

                Storage.set("pvp.waiting", false);
                p.openInventory(GUI(p));
                break;
            }
            case 15: {
                if (Storage.get_bool("pvp.waiting")) {
                    p.sendMessage(Utils.chat("&bThis is awkward... I am already waiting 60 seconds to enable/disable PVP. If you want to change it right now, click on the first option."));
                } else if (Storage.get_bool("pvp.enabled")) {
                    Storage.set("pvp.waiting", true);
                    Utils.titleEveryone("&bDisabling PVP in 60 seconds", "&dYou are almost safe", 10, 100, 10);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        if (Storage.get_bool("pvp.waiting")) {
                            Bukkit.getWorld(Storage.get_str("world")).setPVP(false);
                            Storage.set("pvp.enabled", false);
                            Storage.set("pvp.waiting", false);
                            Utils.titleEveryone("&bPVP has been disabled", "&dYou are safe... for now", 10, 100, 10);
                        }
                    }, 20L * 60);
                } else {
                    Storage.set("pvp.waiting", true);
                    Utils.titleEveryone("&bEnabling PVP in 60 seconds", "&c&l&oRun and seek shelter", 10, 100, 10);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        if (Storage.get_bool("pvp.waiting")) {
                            Bukkit.getWorld(Storage.get_str("world")).setPVP(true);
                            Storage.set("pvp.enabled", true);
                            Storage.set("pvp.waiting", false);
                            Utils.titleEveryone("&c&l&oPVP has been enabled", "&c&l&o&4Run...", 10, 100, 10);
                        }
                    }, 20L * 60);
                }
                break;
            }
            case 16: {
                p.sendMessage("Not implemented");
                break;
            }
            // add bossbar, pause and cancel here
            default:
                break;
        }

    }
}
