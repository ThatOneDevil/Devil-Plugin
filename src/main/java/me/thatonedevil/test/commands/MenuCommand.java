package me.thatonedevil.test.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            Inventory inv = Bukkit.createInventory(player, 45, ChatColor.GREEN.toString() + ChatColor.BOLD + "Menu");

            // Teleport
            ItemStack teleport = new ItemStack(Material.ENDER_PEARL);
            ItemMeta teleportMeta = teleport.getItemMeta();
            teleportMeta.setDisplayName(ChatColor.BLUE + "Teleport to random player");
            teleportMeta.setLore(Arrays.asList("", ChatColor.GRAY + "Teleports to a random player"));
            teleport.setItemMeta(teleportMeta);
            // Kill
            ItemStack kill = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta killMeta = teleport.getItemMeta();
            killMeta.setDisplayName(ChatColor.RED + "Kill your self");
            killMeta.setLore(Arrays.asList("", ChatColor.GRAY + "Kills your self"));
            kill.setItemMeta(killMeta);
            // Clear Inventory
            ItemStack clear = new ItemStack(Material.BUCKET);
            ItemMeta clearMeta = teleport.getItemMeta();
            clearMeta.setDisplayName(ChatColor.GOLD + "Clear's your inventory");
            clearMeta.setLore(Arrays.asList("", ChatColor.GRAY + "Clear's your inventory"));
            clear.setItemMeta(clearMeta);

            // Close
            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta closeMeta = teleport.getItemMeta();
            closeMeta.setDisplayName(ChatColor.RED + "Close menu");
            close.setItemMeta(closeMeta);

            // Frame
            ItemStack frame = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            ItemMeta frameMeta = frame.getItemMeta();
            frameMeta.setDisplayName(ChatColor.GRAY + "");
            frame.setItemMeta(frameMeta);

            for(int i : new int[]{1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44}){
                inv.setItem(i, frame);
            }

            inv.setItem(20, teleport);
            inv.setItem(22, kill);
            inv.setItem(24, clear);
            inv.setItem(0, close);

            player.openInventory(inv);

        }

        return false;
    }

}
