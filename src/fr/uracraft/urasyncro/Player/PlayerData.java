package fr.uracraft.urasyncro.Player;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayerData {

    public static void createFolder(String folder){
        Path path = Paths.get("plugins/UraSyncro/" + folder);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(Player player, String path, Object value){
        File playerDataFile = new File("plugins/UraSyncro/PlayerData/" + player.getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        playerData.set(path, value);
        try {
            playerData.save(playerDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(Player player, String path){
        File playerDataFile = new File("plugins/UraSyncro/PlayerData/" + player.getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        return playerData.getString(path);
    }

    public static Object readFileObject(Player player, String path){
        File playerDataFile = new File("plugins/UraSyncro/PlayerData/" + player.getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        return playerData.get(path);
    }

    public static ItemStack readFileItemStack(Player player, String path){
        File playerDataFile = new File("plugins/UraSyncro/PlayerData/" + player.getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        return playerData.getItemStack(path);
    }
}
