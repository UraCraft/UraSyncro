package fr.uracraft.urasyncro.Configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigFile {

    static List<String> worlds = new ArrayList<String>();

    public static void createFile() {
        File configurationFile = new File("plugins/UraSyncro/Configuration.yml");
        if (!configurationFile.exists()) {
            try {
                configurationFile.createNewFile();
                InputStream in = ConfigFile.class.getResourceAsStream("/fr/uracraft/urasyncro/ressources/config.yml");
                try {
                    OutputStream out = new FileOutputStream(configurationFile);
                    try {
                        byte[] buf = new byte[8196];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                    } finally {
                        out.close();
                    }
                } finally {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Boolean worldIsSyncronized(String world) {
        File configurationFile = new File("plugins/UraSyncro/Configuration.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(configurationFile);
        for (int i = 1; i < 10; i++) {
            worlds.add(configuration.getString("world" + i));
        }
        if (worlds.contains(world)) {
            return true;
        }
        worlds.clear();
        return false;
    }

    public static Boolean worldIsLobby(String world) {
        File configurationFile = new File("plugins/UraSyncro/Configuration.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(configurationFile);
        if (configuration.get("lobby").equals(world)) {
            return true;
        }
        return false;
    }

    public static String getItem() {
        File configurationFile = new File("plugins/UraSyncro/Configuration.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(configurationFile);
        return configuration.getString("item");
    }
}
