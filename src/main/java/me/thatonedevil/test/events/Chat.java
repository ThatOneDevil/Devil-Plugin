package me.thatonedevil.test.events;


import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        e.setMessage(translate(e.getMessage()));
    }

    private Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    private String translate(String input) {
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String color = input.substring(matcher.start(), matcher.end());
            input = input.replace(color, ChatColor.of(color) + "");
            matcher = pattern.matcher(input);
        }
        return input;
    }
}
