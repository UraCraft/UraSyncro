package fr.uracraft.urasyncro.Events;

import fr.uracraft.urasyncro.Player.PlayerInventory;
import fr.uracraft.urasyncro.UraSyncro;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class PlayerJoinQuit implements Listener {

    public PlayerJoinQuit(UraSyncro uraSyncro) {
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        File playerDataFile = new File("plugins/UraSyncro/PlayerData/" + player.getUniqueId() + ".yml");
        if(!playerDataFile.exists()) {
            try {
                playerDataFile.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            player.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
            player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
            player.getInventory().addItem(new ItemStack(Material.LEATHER_HELMET));
            player.getInventory().addItem(new ItemStack(Material.LEATHER_CHESTPLATE));
            player.getInventory().addItem(new ItemStack(Material.LEATHER_LEGGINGS));
            player.getInventory().addItem(new ItemStack(Material.LEATHER_BOOTS));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        String w = e.getPlayer().getLocation().getWorld().getName();

        if(w.equalsIgnoreCase("Minage01") || w.equalsIgnoreCase("Minage02") || w.equalsIgnoreCase("Minage03") || w.equalsIgnoreCase("Alpha") || w.equalsIgnoreCase("Gamma")) {
            PlayerInventory.save(e.getPlayer());//save_inventory
        }
    }
}