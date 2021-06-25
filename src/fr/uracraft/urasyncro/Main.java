package fr.uracraft.urasyncro;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.uracraft.urasyncro.Main;

public class Main extends JavaPlugin {
	
	public void onEnable() {
	    saveDefaultConfig();
	    PluginManager pm = getServer().getPluginManager();
	    pm.registerEvents(new WorldSyncronisation(this), this);
	}
	
	public void onDisable() {
		
	}
	
	private boolean sectionExists(String section) {
		return this.getConfig().getConfigurationSection(section) != null;
	}
	
	private boolean sectionEmpty(String section) {
		return !sectionExists(section) || this.getConfig().getConfigurationSection(section).getKeys(false).size() == 0;
	}
	
	//SAVE INVENTORY
		public Main instance(Player player, String inventory) {
			
			//Iterate through player's inventory
			ItemStack[] contents = player.getInventory().getContents();
			for (int i = 0; i < contents.length; i++) {
				if (contents[i] != null && contents[i].getType() != Material.AIR) {
					//Only save if there is something in the slot
					this.getConfig().set(player.getUniqueId() + "." + inventory + "." + i, contents[i]);
				} else {
					//If slot is empty, delete it from the config
					this.getConfig().set(player.getUniqueId() + "." + inventory + "." + i, null);
				}
			}
			
			//Save armour
			this.getConfig().set(player.getUniqueId() + "." + inventory + ".armour.helmet", player.getInventory().getHelmet());
			this.getConfig().set(player.getUniqueId() + "." + inventory + ".armour.chestplate", player.getInventory().getChestplate());
			this.getConfig().set(player.getUniqueId() + "." + inventory + ".armour.leggings", player.getInventory().getLeggings());
			this.getConfig().set(player.getUniqueId() + "." + inventory + ".armour.boots", player.getInventory().getBoots());
			
			//Save player's XP
			this.getConfig().set(player.getUniqueId() + "." + inventory + ".xp", player.getExp());
			
			//Save config to file
			this.saveConfig();
			
			return null;
		}
	
	//LOAD INVENTORY
		public Main instance2(Player player, String inventory) {
			
			//Create player's inventory
			ItemStack[] contents = new ItemStack[36];
			for (int i = 0; i < contents.length; i++) {
				ItemStack x = this.getConfig().getItemStack(player.getUniqueId() + "." + inventory + "." + i, contents[i]);
				if (x != null) contents[i] = x;
			}
			
			//Set player's inventory
			player.getInventory().setContents(contents);
			
			//Restore player's armour
			player.getInventory().setHelmet(this.getConfig().getItemStack(player.getUniqueId() + "." + inventory + ".armour.helmet"));
			player.getInventory().setChestplate(this.getConfig().getItemStack(player.getUniqueId() + "." + inventory + ".armour.chestplate"));
			player.getInventory().setLeggings(this.getConfig().getItemStack(player.getUniqueId() + "." + inventory + ".armour.leggings"));
			player.getInventory().setBoots(this.getConfig().getItemStack(player.getUniqueId() + "." + inventory + ".armour.boots"));

			//Restore player's XP
			player.setExp(Float.parseFloat(this.getConfig().getString(player.getUniqueId() + "." + inventory + ".xp")));
			return null;
		}
		
		public Main instance4(Player player, String inventory) {
			if(!player.getLocation().getWorld().getName().equalsIgnoreCase("lobby")) {
			if (sectionEmpty(player.getUniqueId() + "." + inventory)) {
				
				this.getConfig().createSection(player.getUniqueId() + "." + inventory);
				
				player.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
				player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
				player.getInventory().addItem(new ItemStack(Material.LEATHER_HELMET));
				player.getInventory().addItem(new ItemStack(Material.LEATHER_CHESTPLATE));
				player.getInventory().addItem(new ItemStack(Material.LEATHER_LEGGINGS));
				player.getInventory().addItem(new ItemStack(Material.LEATHER_BOOTS));
				
				this.saveConfig();
			}
			}
			return null;
		}
}