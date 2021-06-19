//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.fiirewiinter.hundreddaysx.gui;

import me.fiirewiinter.hundreddaysx.Main;
import me.fiirewiinter.hundreddaysx.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class PrepareGUI {
    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 27;
    private static Main plugin;

    public PrepareGUI(Main plugin) {
        PrepareGUI.plugin = plugin;
        inventory_name = Utils.chat("&c&lPreparation Phase");
        inv = Bukkit.createInventory((InventoryHolder)null, inv_rows);
    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory((InventoryHolder)null, inv_rows, inventory_name);
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
