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
            Utils.createItem(inv, "barrier", 1, 10, "&l&o&bDisable Whitelist", "&dEnable/Disable the whitelist when starting the prep phase");
            Utils.createItem(inv, "lime_stained_glass_pane", 1, 19, "&l&o&n&4Disabled on start");
        } else {
            Utils.createItem(inv, "barrier", 1, 10, "&l&o&bEnable Whitelist", "&dEnable/Disable the whitelist when starting the prep phase");
            Utils.createItem(inv, "red_stained_glass_pane", 1, 19, "&l&o&n&4Disabled on start");
        }

        // Enable pvp on start of 100 Days
        if (Storage.get_bool("pvp.enabled")) {
            Utils.createItem(inv, "diamond_sword", 1, 12, "&l&o&bDisable PVP on start");;
            Utils.createItem(inv, "lime_stained_glass_pane", 1, 21, "&l&o&n&aEnabled on start");
        } else {
            Utils.createItem(inv, "diamond_sword", 1, 12, "&l&o&bEnable PVP on start");
            Utils.createItem(inv, "red_stained_glass_pane", 1, 21, "&l&o&n&4Disabled on start");
        }

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        String var4;
        switch(slot) {
        case 1:
            var4 = "hi";
            break;
        case 2:
            var4 = "hello";
        }

    }
}
