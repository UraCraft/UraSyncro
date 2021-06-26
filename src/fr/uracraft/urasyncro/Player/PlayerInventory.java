package fr.uracraft.urasyncro.Player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class PlayerInventory {

    public static void save(Player player) {
        //Save player's XP
        PlayerData.writeFile(player, player.getName() + ".xp", player.getExp());
        PlayerData.writeFile(player, player.getName() + "." + ".level", player.getLevel());

        //Save player's Heal
        PlayerData.writeFile(player, player.getName() + "." + ".heal", player.getHealth());
        PlayerData.writeFile(player, player.getName() + "." + ".food", player.getFoodLevel());

        //Save player's Potion Effects
        PlayerData.writeFile(player, player.getName() + "." + ".effect", null);
        PlayerData.writeFile(player, player.getName() + "." + ".effect.nb", player.getActivePotionEffects().size());
        int effectnb = 0;
        for (PotionEffect effect : player.getActivePotionEffects()) {
            PlayerData.writeFile(player, player.getName() + "." + ".effect." + effectnb, effect);
            effectnb++;
        }

        //Save armour
        PlayerData.writeFile(player, player.getName() + ".armour.helmet", player.getInventory().getHelmet());
        PlayerData.writeFile(player, player.getName() + ".armour.chestplate", player.getInventory().getChestplate());
        PlayerData.writeFile(player, player.getName() + ".armour.leggings", player.getInventory().getLeggings());
        PlayerData.writeFile(player, player.getName() + ".armour.boots", player.getInventory().getBoots());

        //Iterate through player's inventory
        ItemStack[] contents = player.getInventory().getContents();
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] != null && contents[i].getType() != Material.AIR) {
                //Only save if there is something in the slot
                PlayerData.writeFile(player, player.getName() + ".inventory." + i, contents[i]);
            } else {
                //If slot is empty, delete it from the config
                PlayerData.writeFile(player, player.getName() + ".inventory." + i, null);
            }
        }
    }

    public static void load(Player player) {
        //Create player's inventory
        ItemStack[] contents = new ItemStack[36];
        for (int i = 0; i < contents.length; i++) {
            ItemStack x = PlayerData.readFileItemStack(player, player.getName() + ".inventory." + i);
            if (x != null) contents[i] = x;
        }

        //Set player's inventory
        player.getInventory().setContents(contents);

        //Restore player's armour
        player.getInventory().setHelmet(PlayerData.readFileItemStack(player, player.getName() + ".armour.helmet"));
        player.getInventory().setChestplate(PlayerData.readFileItemStack(player, player.getName() + ".armour.chestplate"));
        player.getInventory().setLeggings(PlayerData.readFileItemStack(player, player.getName() + ".armour.leggings"));
        player.getInventory().setBoots(PlayerData.readFileItemStack(player, player.getName() + ".armour.boots"));

        //Restore player's XP
        player.setExp(Float.parseFloat(PlayerData.readFile(player, player.getName() + ".xp")));
        player.setLevel(Integer.parseInt(PlayerData.readFile(player, player.getName() + ".level")));

        //Restore player's Heal
        player.setHealth(Double.parseDouble(PlayerData.readFile(player, player.getName() + "." + ".heal")));
        player.setFoodLevel(Integer.parseInt(PlayerData.readFile(player, player.getName() + "." + ".food")));

        //Restore player's Potion Effects
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        for (int i = 0; i < Integer.parseInt(PlayerData.readFile(player, player.getName() + ".effect.nb")); i++) {
            player.addPotionEffect((PotionEffect) PlayerData.readFileObject(player, player.getName() + "." + ".effect." + i));
        }
    }

    public static void clear(Player player) {
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.setExp(0);
        player.setFoodLevel(20);
        player.setLevel(0);
        player.setHealth(20);
        for (PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());
    }
}
