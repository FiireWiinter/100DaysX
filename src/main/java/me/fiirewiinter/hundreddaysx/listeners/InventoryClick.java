package me.fiirewiinter.hundreddaysx.listeners;

import me.fiirewiinter.hundreddaysx.Main;
import me.fiirewiinter.hundreddaysx.gui.MainGUI;
import me.fiirewiinter.hundreddaysx.gui.PrepareGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    private Main plugin;

    public InventoryClick(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();
        // Check if the opened inventory is one of the GUIs
        if (title.equals(MainGUI.inventory_name)) {
            // Cancel the event, resulting in the item not being removed
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            MainGUI.clicked((Player)e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
        } else if (title.equals(PrepareGUI.inventory_name)) {
            // Cancel the event, resulting in the item not being removed
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            PrepareGUI.clicked((Player)e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
        }
    }
}
