package me.thatonedevil.test.events;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.thatonedevil.test.Main.format;


public class JoinLeaveEvent implements Listener {

    public static void onJoin(PlayerJoinEvent e ) {
        Player player = e.getPlayer();

        if (player.isOp()) {
            TextComponent text = new TextComponent("§cClick her for plugin info!");
            text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/info"));
            text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(" Plugin info \n  Click here ")));

            player.spigot().sendMessage(text);

        }

        if (!player.hasPlayedBefore()) {
            e.setJoinMessage(format("&bWelcome " + player.getDisplayName() + " &bTo the server!"));
            player.sendTitle(format("&bWelcome " + player.getDisplayName()), format(" &bTo the server!"), 2, 50, 2);
        } else {
            e.setJoinMessage(format("&bWelcome Back" + player.getDisplayName() + " &bTo the server!"));
            player.sendTitle(format("&bWelcome Back" + player.getDisplayName()), format(" &bTo the server!"), 2, 50, 2);

        }
    }
    public static void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.RED + "Goodbye " + e.getPlayer().getDisplayName());
        player.sendTitle((ChatColor.RED + "Goodbye " + e.getPlayer().getDisplayName()), null, 2, 50, 2);

    }

}
