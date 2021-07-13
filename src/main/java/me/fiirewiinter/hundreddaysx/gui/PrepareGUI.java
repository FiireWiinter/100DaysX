package me.fiirewiinter.hundreddaysx.gui;

import me.fiirewiinter.hundreddaysx.Main;
import me.fiirewiinter.hundreddaysx.utils.Storage;
import me.fiirewiinter.hundreddaysx.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PrepareGUI {
    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 4 * 9;
    private static Main plugin;

    public PrepareGUI(Main plugin) {
        PrepareGUI.plugin = plugin;
        inventory_name = Utils.chat("&c&lPreparation Phase");
        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        // Disable whitelist option (temp storage)
        if (Storage.get_bool("temp.whitelist")) {
            Utils.createItem(inv, "barrier", 1, 10, "&l&o&bDisable Whitelist", "&dDisable/Enable the whitelist when starting the prep phase");
            Utils.createItem(inv, "lime_stained_glass_pane", 1, 19, "&l&o&n&aWhitelist disabled on start");
        } else {
            Utils.createItem(inv, "barrier", 1, 10, "&l&o&bDon't disable Whitelist", "&dDisable/Enable the whitelist when starting the prep phase");
            Utils.createItem(inv, "red_stained_glass_pane", 1, 19, "&l&o&n&4Whitelist enabled on start");
        }

        // Enable pvp on start of 100 Days
        if (Storage.get_bool("pvp.enabled")) {
            Utils.createItem(inv, "diamond_sword", 1, 12, "&l&o&bDisable PVP on start");
            Utils.createItem(inv, "lime_stained_glass_pane", 1, 21, "&l&o&n&aEnabled on start");
        } else {
            Utils.createItem(inv, "wooden_sword", 1, 12, "&l&o&bEnable PVP on start");
            Utils.createItem(inv, "red_stained_glass_pane", 1, 21, "&l&o&n&4Disabled on start");
        }

        // TP to spawn when entering prep and during prep
        if (Storage.get_bool("temp.tp_spawn")) {
            Utils.createItem(inv, "minecart", 1, 14, "&l&o&bTeleport everyone to spawn during prep", "&dThis will TP everyone currently on the server and", "&deveryone who joins during prep to spawn when enabled");
            Utils.createItem(inv, "lime_stained_glass_pane", 1, 23, "&l&o&n&aTeleport to spawn");
        } else {
            Utils.createItem(inv, "minecart", 1, 14, "&l&o&bTeleport everyone to spawn during prep", "&dThis will TP everyone currently on the server and", "&deveryone who joins during prep to spawn when enabled");
            Utils.createItem(inv, "red_stained_glass_pane", 1, 21, "&l&o&n&4Don't teleport to spawn");
        }

        // Start the prep
        // TODO

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        switch(slot) {
            // Disable/Don't disable whitelist when starting
            case 10: {
                if (Storage.get_bool("temp.whitelist")) {
                    Storage.set("temp.whitelist", false);
                } else {
                    Storage.set("temp.whitelist", true);
                }
                p.openInventory(GUI(p));
                break;
            }

            // Enable/Disable PVP on start
            case 12: {
                if (Storage.get_bool("pvp.enabled")) {
                    Storage.set("pvp.enabled", false);
                } else {
                    Storage.set("pvp.enabled", true);
                }
                p.openInventory(GUI(p));
                break;
            }
        }
    }
}
