package me.thatonedevil.test.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class MenuListener implements Listener {

    @EventHandler

    public void onClick(InventoryClickEvent e) {

        if(ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.GREEN.toString() + ChatColor.BOLD + "Menu"
        ) && e.getCurrentItem() != null) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();
                switch (e.getSlot()){
                    case 0:
                        break;
                    case 20:
                        // Random tp
                        Random random = new Random();
                        Player target = (Player) Bukkit.getOnlinePlayers().toArray()[random.nextInt(Bukkit.getOnlinePlayers().size())];
                        player.teleport(target);
                        player.sendMessage(ChatColor.RED + "You Teleported to " + target.getName());
                        break;
                    case 22:
                        // Kys
                        player.setHealth(0);
                        player.sendMessage(ChatColor.RED + "You killed your self!");
                        break;
                    case 24:
                        // Clear inv
                        player.closeInventory();
                        player.getInventory().clear();
                        player.sendMessage(ChatColor.RED + "You cleared your inventory!");
                        return;

                }
            player.closeInventory();

        }
    }
}
