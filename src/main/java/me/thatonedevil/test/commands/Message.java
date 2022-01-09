package me.thatonedevil.test.commands;

import me.thatonedevil.test.Main;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message implements CommandExecutor {
    private Main main;

    public Message(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length >= 2 ) {
                if(Bukkit.getPlayerExact(args[0]) != null){
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++){
                        builder.append(args[i]).append(" ");
                    }
                    player.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', "&bTo " + target.getName() + " &7»" + ChatColor.of("#276fe3") + " " + builder));
                    target.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', "&bFrom " + player.getName() + " &7»" + ChatColor.of("#276fe3") + " " + builder));

                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());

                } else{
                    player.sendMessage(ChatColor.RED + "This player is not found!");
                }

            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Use /message <player> <message>");
            }

        }

        return false;
    }
}
