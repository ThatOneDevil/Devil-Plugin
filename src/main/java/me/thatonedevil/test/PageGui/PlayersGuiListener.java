package me.thatonedevil.test.PageGui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayersGuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e ){
        if (e.getInventory() != null && e.getCurrentItem() != null && e.getView().getTitle().contains("Online Player's")){
            int page = Integer.parseInt(e.getInventory().getItem(0).getItemMeta().getLocalizedName());
            if (e.getRawSlot() == 0 && e.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE)) {
                new PlayerGui((Player) e.getWhoClicked(), page-1);
            } else if (e.getRawSlot() == 0 && e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                new PlayerGui((Player) e.getWhoClicked(), page+1);
            }
            e.setCancelled(true);

        }
    }

}
