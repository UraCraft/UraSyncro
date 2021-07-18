package fr.uracraft.urasyncro.Events;

import fr.uracraft.urasyncro.Configuration.ConfigFile;
import fr.uracraft.urasyncro.Player.PlayerInventory;
import fr.uracraft.urasyncro.UraSyncro;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerChangeWorld implements Listener {

    public PlayerChangeWorld(UraSyncro uraSyncro) {
    }

    @EventHandler
    public void onChange(PlayerChangedWorldEvent e) {

        Player player = e.getPlayer();
        String playerWorld = e.getPlayer().getLocation().getWorld().getName();
        String playerFrom = e.getFrom().getName();

        if (ConfigFile.worldIsLobby(playerWorld)) {
            if (ConfigFile.worldIsSyncronized(playerFrom)) {
                PlayerInventory.save(player);
            }
            PlayerInventory.clear(player);
            player.getInventory().setItem(4, new ItemStack(Material.valueOf(ConfigFile.getItem())));
        } else if (ConfigFile.worldIsSyncronized(playerWorld)) {
            PlayerInventory.clear(player);
            PlayerInventory.load(player);
        } else {
            if (ConfigFile.worldIsLobby(playerFrom) || ConfigFile.worldIsSyncronized(playerFrom)) {
                PlayerInventory.clear(player);
            }
        }
    }
}
