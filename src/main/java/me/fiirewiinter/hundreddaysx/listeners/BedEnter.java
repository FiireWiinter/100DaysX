//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.fiirewiinter.hundreddaysx.listeners;

import me.fiirewiinter.hundreddaysx.Main;
import me.fiirewiinter.hundreddaysx.utils.Storage;
import me.fiirewiinter.hundreddaysx.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedEnter implements Listener {
    private static Main plugin;

    public BedEnter(Main plugin) {
        BedEnter.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEnter(PlayerBedEnterEvent e) {
        if (Storage.get_bool("prepared") || Storage.get_bool("started") && !Storage.get_bool("sleeping") || Storage.get_bool("paused")) {
            e.setCancelled(true);
            Player p = e.getPlayer();
            p.sendMessage(Utils.chat("&l&o&cYou are not allowed to sleep! Respawn point has been set tho."));
        }

    }
}
