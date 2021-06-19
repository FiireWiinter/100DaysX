//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.fiirewiinter.hundreddaysx.gui;

import me.fiirewiinter.hundreddaysx.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class TestGUI {
    public Inventory inv;
    public String inventory_name;
    public int inv_rows = 45;

    public TestGUI() {
    }

    public void initialize() {
        this.inventory_name = Utils.chat("&l&lTest Gui");
        this.inv = Bukkit.createInventory((InventoryHolder)null, this.inv_rows);
    }

    public Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory((InventoryHolder)null, this.inv_rows, this.inventory_name);
        Utils.createItem(this.inv, "rail", 1, 13, "&cTest Item", new String[]{"&7Lore Line 1", "&1Lore Line 2", "&4Lore Line 3"});
        toReturn.setContents(this.inv.getContents());
        return toReturn;
    }

    public void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (slot == 13) {
            p.sendMessage(Utils.chat("&aYou have successfully made a GUI"));
        }

    }
}
