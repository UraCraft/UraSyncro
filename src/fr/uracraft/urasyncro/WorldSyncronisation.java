package fr.uracraft.urasyncro;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class WorldSyncronisation implements Listener {

	private Main instance;

    public WorldSyncronisation(Main pluginInstance) {
        this.instance = pluginInstance;
    }
    
    @EventHandler
	public void onCommande(PlayerCommandPreprocessEvent e) {
		
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String[] args = msg.split(" ");
		
		if(args[0].equalsIgnoreCase("/UraSyncro")){
			p.sendMessage("Le plugin fonctionne correctement");
			e.setCancelled(true);
		}
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
        		this.ClearInventoryAndStuff(player);
            	player.getInventory().setItem(4, selector);
                player.setGameMode(GameMode.SURVIVAL);
        	}else {
        		instance.instance(player, player.getName());//save_inventory
        		this.ClearInventoryAndStuff(player);
        		player.getInventory().setItem(4, selector);
        		player.setGameMode(GameMode.SURVIVAL);
        	}
        }
        if( w.equalsIgnoreCase("Minage01") || w.equalsIgnoreCase("Minage02") || w.equalsIgnoreCase("Minage03") || 
        	w.equalsIgnoreCase("Alpha") || w.equalsIgnoreCase("Gamma")){
        
        	this.ClearInventoryAndStuff(player);
        	instance.instance2(e.getPlayer(), e.getPlayer().getName());//load_inventory
        }
    }
    
    @EventHandler
    public void onDisconect(PlayerQuitEvent e) {
    	
    	String w = e.getPlayer().getLocation().getWorld().getName();
    	
    	if(w.equalsIgnoreCase("Minage01") || w.equalsIgnoreCase("Minage02") || w.equalsIgnoreCase("Minage03") || w.equalsIgnoreCase("Alpha") || w.equalsIgnoreCase("Gamma")) {
    		instance.instance(e.getPlayer(), e.getPlayer().getName());//save_inventory
    	}
    }
    
    @EventHandler
    public void onChange2(PlayerChangedWorldEvent e) {

        String w = e.getPlayer().getLocation().getWorld().getName();
        
        if(w.equalsIgnoreCase("builder")) {
        	e.getPlayer().getInventory().clear();
        	e.getPlayer().setGameMode(GameMode.CREATIVE);
        }
    }
    
    public String ClearInventoryAndStuff(Player player) {
    	player.getInventory().clear();
    	player.getInventory().setHelmet(null);
    	player.getInventory().setChestplate(null);
    	player.getInventory().setLeggings(null);
    	player.getInventory().setBoots(null);
    	player.setExp(0);
		return null;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
	public void onConnect(PlayerJoinEvent e) {
		instance.instance4(e.getPlayer(), e.getPlayer().getName());
	}
}
