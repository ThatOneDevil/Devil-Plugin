package me.thatonedevil.test.PageGui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

import static me.thatonedevil.test.Main.format;

public class PlayerGui {

    public PlayerGui(Player player, int page) {
        Inventory gui = Bukkit.createInventory(null, 54, format("&cOnline Player's &7- " + page));
        player.openInventory(gui);

        List<ItemStack> allItems = new ArrayList<>();

        ItemStack left;
        ItemMeta leftMeta;
        if (PlayerPageUtil.isPageValid(allItems, page+ + 1, 52)) {
            left = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            leftMeta = left.getItemMeta();
            leftMeta.setDisplayName(format("&aGo to page left!"));
        } else {
            left = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            leftMeta = left.getItemMeta();
            leftMeta.setDisplayName(format("&cNo page to go to!"));
        }
        leftMeta.setLocalizedName(page + "");

        left.setItemMeta(leftMeta);
        gui.setItem(0, left);

        ItemStack right;
        ItemMeta rightMeta;
        if (PlayerPageUtil.isPageValid(allItems, page - 1, 52)) {
            right = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            rightMeta = right.getItemMeta();
            rightMeta.setDisplayName(format("&aGo to page right!"));
        } else {
            right = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            rightMeta = right.getItemMeta();
            rightMeta.setDisplayName(format("&cNo page to go to!"));
        }
        right.setItemMeta(rightMeta);
        gui.setItem(8, right);

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();

        for(Player Forplayer : Bukkit.getOnlinePlayers()){
            playerHeadMeta.setDisplayName(format(Forplayer.getName()));
            playerHeadMeta.setOwningPlayer(Bukkit.getOfflinePlayer(Forplayer.getUniqueId()));
            playerHead.setItemMeta(playerHeadMeta);
            allItems.add(playerHead);
        }

        for (ItemStack is : PlayerPageUtil.getPageItems(allItems, page, 52)){
            gui.setItem(gui.firstEmpty(), is);
        }

    }
}
