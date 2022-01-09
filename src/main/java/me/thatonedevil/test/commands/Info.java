package me.thatonedevil.test.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class Info implements CommandExecutor {

    public static String format(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory inv = Bukkit.createInventory(player, 27, format("&6&lPlugin Info"));

            // Info
            ItemStack info = new ItemStack(Material.BOOK);
            ItemMeta infoMeta = info.getItemMeta();
            infoMeta.setDisplayName(format("&7Information!"));
            infoMeta.setLore(Arrays.asList("", format("&7This plugin is made for testing and improving java and spigot api skills"), format("&7This plugin is still in &cDevelopment, and has a high chance of &cBugs")));
            info.setItemMeta(infoMeta);

            // Version
            ItemStack version = new ItemStack(Material.NETHER_STAR);
            ItemMeta versionMeta = version.getItemMeta();
            versionMeta.setDisplayName(format("&7Version!"));
            versionMeta.setLore(Arrays.asList("", format("&7Current version of this plugin is &c1.0")));
            version.setItemMeta(versionMeta);

            // Author
            ItemStack Author = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta playerHeadMeta = (SkullMeta) Author.getItemMeta();
            playerHeadMeta.setDisplayName(format("&7Author!"));
            playerHeadMeta.setLore(Arrays.asList("", format("&7Author of this plugin is &cThatOneDevil")));
            playerHeadMeta.setOwningPlayer(Bukkit.getOfflinePlayer("ThatOneDevil"));
            Author.setItemMeta(playerHeadMeta);

            // Border
            ItemStack frame = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta frameMeta = frame.getItemMeta();
            frameMeta.setDisplayName(format("&7"));

            for(int i : new int[]{0,1,2,3,4,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26}){
                inv.setItem(i, frame);
            }

            inv.setItem(11, info);
            inv.setItem(13, version);
            inv.setItem(15, Author);

            player.openInventory(inv);


        }
        return false;
    }

    public static class MenuListener implements Listener {

        @EventHandler

        public void onClick(InventoryClickEvent e) {

            if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(format("&6&lPlugin Info")) && e.getCurrentItem() != null) {
                e.setCancelled(true);
            }
        }
    }
}
