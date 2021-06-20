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

    public static FileConfiguration getConfig() {
        if (dataConfig == null) {
            reloadConfig();
        }
        return dataConfig;
    }

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

    private void saveDefaultConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "data.yml");
        }

        if (!configFile.exists()) {
            plugin.saveResource("data.yml", false);
        }
    }

    public static String get_str(String key) {
        return getConfig().getString(key);
    }

    public static boolean get_bool(String key) {
        return getConfig().getBoolean(key);
    }

    public static void set(String key, Object val) {
        getConfig().set(key, val);
        saveConfig();
    }
}
