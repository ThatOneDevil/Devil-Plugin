package me.thatonedevil.test;

import me.thatonedevil.test.PageGui.PlayersCommand;
import me.thatonedevil.test.commands.*;
import me.thatonedevil.test.events.Chat;
import me.thatonedevil.test.events.Fish;
import me.thatonedevil.test.events.JoinLeaveEvent;
import me.thatonedevil.test.events.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {

    private HashMap<UUID, UUID> recentMessages;

    @Override
    public void onEnable() {

        System.out.println("The plugin has started!");

        Fish Fish = new Fish(this);

        Bukkit.getPluginManager().registerEvents(new Chat(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new Info.MenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinLeaveEvent(), this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new Fish(this), this);

        getCommand("punish").setExecutor(new PunishCommands());
        getCommand("punish").setTabCompleter(new PunishCommands.punishTabCompleter());
        getCommand("pmessage").setExecutor(new Message(this));
        getCommand("preply").setExecutor(new Reply(this));
        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("info").setExecutor(new Info());
        getCommand("players").setExecutor(new PlayersCommand());

        recentMessages = new HashMap<>();

    }

    public HashMap<UUID, UUID> getRecentMessages() { return recentMessages; }

    public static String format(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        recentMessages.remove(e.getPlayer().getUniqueId());
    }


}