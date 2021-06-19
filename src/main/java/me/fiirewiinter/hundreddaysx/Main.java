package me.fiirewiinter.hundreddaysx;

import me.fiirewiinter.hundreddaysx.commands.HundredDX;
import me.fiirewiinter.hundreddaysx.commands.PauseCommand;
import me.fiirewiinter.hundreddaysx.commands.PrepareCommand;
import me.fiirewiinter.hundreddaysx.commands.StartCommand;
import me.fiirewiinter.hundreddaysx.commands.StopCommand;
import me.fiirewiinter.hundreddaysx.commands.TestCommand;
import me.fiirewiinter.hundreddaysx.listeners.BedEnter;
import me.fiirewiinter.hundreddaysx.listeners.InventoryClick;
import me.fiirewiinter.hundreddaysx.gui.MainGUI;
import me.fiirewiinter.hundreddaysx.gui.PrepareGUI;
import me.fiirewiinter.hundreddaysx.utils.Storage;
import me.fiirewiinter.hundreddaysx.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public Main() {
    }

    public void onEnable() {
        System.out.println("Loading 100DaysX");
        System.out.println("Loading 100DaysX Utilities");
        new Storage(this);
        new Utils(this);
        Utils.info("Loading 100DaysX GUIs");
        new MainGUI(this);
        new PrepareGUI(this);
        Utils.info("Loading 100DaysX Commands");
        new HundredDX(this);
        new PrepareCommand(this);
        new StartCommand(this);
        new StopCommand(this);
        new PauseCommand(this);
        Utils.info("Loading 100DaysX Listeners");
        new InventoryClick(this);
        new BedEnter(this);
        new TestCommand(this);
        boolean waiting = Storage.get_bool("pvp.waiting");
        boolean pvp = Storage.get_bool("pvp.enabled");
        String world = Storage.get_str("world");

        try {
            if (pvp && waiting) {
                Bukkit.getWorld(world).setPVP(false);
                Storage.set("pvp.enabled", false);
            } else if (!pvp && waiting) {
                Bukkit.getWorld(world).setPVP(true);
                Storage.set("pvp.enabled", true);
            }

            Storage.set("pvp.waiting", false);
        } catch (NullPointerException var5) {
            Utils.severe("100DaysX ISSUE DETECTED. WORLD " + Storage.get_str("world") + " NO LONGER EXISTS! STOPPING EVERYTHING!");
            Storage.set("prepared", false);
            Storage.set("started", false);
            Storage.set("paused", false);
        }

        Utils.info("100DaysX has been loaded");
    }

    public void onDisable() {
        Utils.info("Goodbye :(   - 100DaysX");
    }
}
