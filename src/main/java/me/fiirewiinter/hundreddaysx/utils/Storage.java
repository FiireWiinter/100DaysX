package me.fiirewiinter.hundreddaysx.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import me.fiirewiinter.hundreddaysx.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Storage {
    private static Main plugin;
    private static FileConfiguration dataConfig = null;
    private static File configFile = null;

    public Storage(Main plugin) {
        Storage.plugin = plugin;
        saveDefaultConfig();
    }

    // Reload the config, in case shit broke
    public static void reloadConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "data.yml");
        }

        dataConfig = YamlConfiguration.loadConfiguration(configFile);
        InputStream defaultStream = plugin.getResource("data.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            dataConfig.setDefaults(defaultConfig);
        }
    }

    // Retreive the config
    public static FileConfiguration getConfig() {
        if (dataConfig == null) {
            reloadConfig();
        }
        return dataConfig;
    }

    // Save the config to a file
    private static void saveConfig() {
        if (dataConfig == null || configFile == null) {
            return;
        }
        try {
            getConfig().save(configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, e);
        }
    }

    // If the default config doesn't exist, create it
    private void saveDefaultConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "data.yml");
        }

        if (!configFile.exists()) {
            plugin.saveResource("data.yml", false);
        }
    }

    // Fetch a string from the config
    public static String get_str(String key) {
        String str = getConfig().getString(key);
        Utils.debug("&l&dGET STRING KEY &l&b" + key + "&l&d VALUE &l&b" + str);
        return str;
    }

    // Fetch a boolean from the config
    public static boolean get_bool(String key) {
        boolean bool = getConfig().getBoolean(key);
        Utils.debug("&l&dGET BOOLEAN KEY &l&b" + key + "&l&d VALUE &l&b" + bool);
        return bool;
    }

    // Set a value in the config
    public static void set(String key, Object val) {
        getConfig().set(key, val);
        saveConfig();
        Utils.debug("&l&dSET &l&b" + key + "&l&d TO &l&b" + val + "&l&d TYPE &l&b" + val.getClass());
    }
}
