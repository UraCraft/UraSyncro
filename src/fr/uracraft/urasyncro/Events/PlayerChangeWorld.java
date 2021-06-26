package fr.uracraft.urasyncro.Events;

import fr.uracraft.urasyncro.Player.PlayerInventory;
import fr.uracraft.urasyncro.UraSyncro;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PlayerChangeWorld implements Listener {

    public PlayerChangeWorld(UraSyncro uraSyncro) {
    }

    @EventHandler
    public void onChange(PlayerChangedWorldEvent e) {

        ItemStack selector = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta selectorM = selector.getItemMeta();
        selectorM.setDisplayName(ChatColor.GREEN+"Voyageur Universel");
        selectorM.setLore(Arrays.asList("Te permet de voyager entre", "les différents mondes !"));
        selector.setItemMeta(selectorM);

        String w = e.getPlayer().getLocation().getWorld().getName();
        Player player = e.getPlayer();

        if(w.equalsIgnoreCase("lobby")) {
            if(e.getPlayer().getGameMode() == GameMode.CREATIVE) {
                PlayerInventory.clear(player);
                player.getInventory().setItem(4, selector);
                player.setGameMode(GameMode.SURVIVAL);
            }else {
                PlayerInventory.save(player);//save_inventory
                PlayerInventory.clear(player);
                player.getInventory().setItem(4, selector);
                player.setGameMode(GameMode.SURVIVAL);
            }
        }
        if( w.equalsIgnoreCase("Minage01") || w.equalsIgnoreCase("Minage02") || w.equalsIgnoreCase("Minage03") ||
                w.equalsIgnoreCase("Alpha") || w.equalsIgnoreCase("Gamma")){

            PlayerInventory.clear(player);
           //LoadInventory.loadInventory(e.getPlayer(), e.getPlayer().getName());//load_inventory
        }
    }
}
