package me.thatonedevil.test.commands;

import me.thatonedevil.test.Main;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Reply implements CommandExecutor {

    private Main main;

    public Reply(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length >= 1) {
                if (main.getRecentMessages().containsKey(player.getUniqueId())) {
                    UUID uuid = main.getRecentMessages().get(player.getUniqueId());
                    if (Bukkit.getPlayer(uuid) != null){
                        Player target = Bukkit.getPlayer(uuid);

                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < args.length; i++){
                            builder.append(args[i]).append(" ");
                        }
                        player.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', "&bTo " + target.getName() + " &7»" + ChatColor.of("#276fe3") + " " + builder));
                        target.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', "&bFrom " + player.getName() + " &7»" + ChatColor.of("#276fe3") + " " + builder));

                    } else {
                        player.sendMessage(ChatColor.RED + "The person you have messaged has gone offline!");
                    }

                } else {
                    player.sendMessage(ChatColor.RED + "You haven't messaged anyone yet!");
                }

            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Use /message <player> <message>");
            }
        }
        return false;
    }
}
