package me.thatonedevil.test.events;

import me.thatonedevil.test.Main;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.thatonedevil.test.Main.format;

public class Fish implements Listener {

    private final Main main;

    public Fish(Main main) {
        this.main = main;
    }

    @EventHandler

    public void onFish(PlayerFishEvent e) {

        Player player = e.getPlayer();
        Item item = (Item) e.getCaught();
        if (item == null) return;
        ItemStack itemStack = item.getItemStack();

        switch (itemStack.getType()) {
            case COD:
                fish(player, itemStack, 5, 6f, "&f&lCOMMON", "&c&lCOD");
                player.playEffect(item.getLocation(), Effect.VILLAGER_PLANT_GROW, 100);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 5, 1);
                armorStand(player, item.getLocation(), itemStack,"&c&lCOD");
                break;
            case SALMON:
                fish(player, itemStack, 10, 14f, "&f&lCOMMON", "&c&lSALMON");
                player.playEffect(item.getLocation(), Effect.VILLAGER_PLANT_GROW, 100);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 5, 1);
                armorStand(player, item.getLocation(), itemStack, "&c&lSALMON");
                break;
            case PUFFERFISH:
                fish(player, itemStack, 50, 13f, "&6&lLEGENDARY", "&6&lPUFFERFISH");
                player.playEffect(item.getLocation(), Effect.VILLAGER_PLANT_GROW, 100);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 5, 1);
                armorStand(player, item.getLocation(), itemStack, "&6&lPUFFERFISH");
                break;
            case TROPICAL_FISH:
                fish(player, itemStack, 100, 13f, "&9&lRARE", "&6&lTROPICAL FISH");
                player.playEffect(item.getLocation(), Effect.DRAGON_BREATH, 100);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 5, 1);
                armorStand(player, item.getLocation(), itemStack, "&6&lTROPICAL FISH");
                break;
            case TRIPWIRE_HOOK:
                fish(player, itemStack, 100, 0f, "&7&lUNCOMMON", "&6&lTRIPWIRE HOOK");
                player.playEffect(item.getLocation(), Effect.DRAGON_BREATH, 100);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 5, 1);
                armorStand(player, item.getLocation(), itemStack, "&6&lTRIPWIRE HOOK");
                break;
            default:
                player.playEffect(item.getLocation(), Effect.VILLAGER_PLANT_GROW, 100);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 5, 1);
                armorStand(player, item.getLocation(), itemStack, itemStack.toString());
                break;

        }

    }

    private void armorStand(Player player, Location loc, ItemStack item, String name) {
        ArmorStand armorStand = (ArmorStand) Bukkit.getWorld("world").spawnEntity(loc, EntityType.ARMOR_STAND);
        armorStand.setInvisible(true);
        armorStand.getEquipment().getHelmet().setType(item.getType());
        armorStand.setHelmet(item);
        armorStand.setCustomName(format("&b&l❤ " + ChatColor.DARK_AQUA + name + " &b&l❤ "));
        armorStand.setCustomNameVisible(true);
        armorStand.setInvulnerable(true);

        armorStand.setGravity(false);

        Bukkit.getScheduler().runTaskLater(main, () -> {
            armorStand.setHealth(0);

        },80);
    }


    private void fish(Player player, ItemStack entity, Integer price, Float weight, String rarity, String name) {
        ItemMeta meta = entity.getItemMeta();

        meta.setDisplayName(format("&b&l❤ " + ChatColor.DARK_AQUA + name + " &b&l❤ "));
        List<String> lore = new ArrayList<>();
        lore.add(format("&0"));
        lore.add(format("&7| Caught by &f" + player.getDisplayName()));
        lore.add(format("&7| Price: &a$" + price));
        lore.add(format("&7| Weight: &b" + weight + "&bkg"));
        lore.add(format("&0"));
        lore.add(format(rarity));
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        entity.setItemMeta(meta);
        player.sendTitle(format("&3You have caught a&f"), format("&b&l⚡ ") + ChatColor.DARK_AQUA + format(name) + format(" &b&l⚡"), 2, 50, 2);

    }

}
