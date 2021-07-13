package me.fiirewiinter.hundreddaysx.commands;

import me.fiirewiinter.hundreddaysx.Main;
import me.fiirewiinter.hundreddaysx.utils.Storage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    public TestCommand(Main plugin) {
        plugin.getCommand("test").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        switch (args[0]) {
            case "pvp": {
                sender.sendMessage("PVP in world is " + Bukkit.getWorld("world").getPVP());
                break;
            }
            case "set_bool": {
                Storage.set(args[1], Boolean.parseBoolean(args[2]));
                sender.sendMessage("YUP! Set " + args[1] + " to " + args[2]);
                break;
            }
            case "get": {
                Object obj = Storage.getConfig().get(args[1]);
                if (obj == null) {
                    sender.sendMessage("Key " + args[1] + " doesn't exist lol");
                    break;
                }
                sender.sendMessage("Key: " + args[1]);
                sender.sendMessage("Value: " + obj);
                sender.sendMessage("Type: " + obj.getClass());
                break;
            }
            case "time": {
                Player p = (Player) sender;
                p.getWorld().setFullTime(0L);
                break;
            }
            case "debug": {
                if (Storage.get_bool("debug")) {
                    Storage.set("debug", false);
                    sender.sendMessage("Disabled debug");
                } else {
                    Storage.set("debug", true);
                    sender.sendMessage("Enabled debug");
                }
            }
            default: {
                break;
            }
        }
        return false;
    }
}
