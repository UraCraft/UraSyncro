package fr.uracraft.urasyncro;

import fr.uracraft.urasyncro.Configuration.ConfigFile;
import fr.uracraft.urasyncro.Events.Commands;
import fr.uracraft.urasyncro.Events.PlayerChangeWorld;
import fr.uracraft.urasyncro.Events.PlayerJoinQuit;
import fr.uracraft.urasyncro.Player.PlayerData;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public class UraSyncro extends JavaPlugin {

    public void onEnable() {
        PlayerData.createFolder("PlayerData");
        ConfigFile.createFile();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Commands(this), this);
        pm.registerEvents(new PlayerJoinQuit(this), this);
        pm.registerEvents(new PlayerChangeWorld(this), this);
        getLogger().log(Level.INFO, "Plugin enable !");
    }

    public void onDisable() {
        getLogger().log(Level.INFO, "Plugin disable !");
    }
}
