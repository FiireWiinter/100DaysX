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
        if (args.length == 1 && args[0].equals("pvp")) {
            sender.sendMessage("PVP in world is " + Bukkit.getWorld("world").getPVP());
            return true;

        } else if (args.length == 3 && args[0].equals("set") && (args[2].equals("true") || args[2].equals("false"))) {
            Storage.set(args[0], Boolean.parseBoolean(args[1]));
            sender.sendMessage("YUP! Set " + args[0] + " to " + args[1]);
            return true;

        } else  if (args.length == 1 && args[0].equals("time")) {
            Player p = (Player) sender;
            p.getWorld().setFullTime(0L);
            return true;
        }
        return false;
    }
}
