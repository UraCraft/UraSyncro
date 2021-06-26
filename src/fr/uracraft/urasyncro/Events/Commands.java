package fr.uracraft.urasyncro.Events;

import fr.uracraft.urasyncro.Player.PlayerInventory;
import fr.uracraft.urasyncro.UraSyncro;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Commands implements Listener {

    public Commands(UraSyncro uraSyncro) {
    }

    @EventHandler
    public void commandIsSend(PlayerCommandPreprocessEvent e) {

        Player p = e.getPlayer();
        String msg = e.getMessage();
        String[] args = msg.split(" ");

        if(args[0].equalsIgnoreCase("/urasyncro")){
            p.sendMessage("Le plugin fonctionne correctement");
            e.setCancelled(true);
        }
        if(args[0].equalsIgnoreCase("/urasave")){
            p.sendMessage("Sauvegarde effectuée");
            PlayerInventory.save(p);
            e.setCancelled(true);
        }
        if(args[0].equalsIgnoreCase("/uraload")){
            p.sendMessage("Chargement effectuée");
            PlayerInventory.load(p);
            e.setCancelled(true);
        }
    }
}
