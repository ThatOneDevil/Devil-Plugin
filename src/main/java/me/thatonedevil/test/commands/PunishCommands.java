package me.thatonedevil.test.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class PunishCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length == 3) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = (Player) Bukkit.getPlayer(args[0]);

                    switch (args[1].toLowerCase()) {
                        case "ban":
                            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), ChatColor.RED + "You have been Banned for\nPermanently " + args[2], null, null);
                            target.kickPlayer(ChatColor.RED + "You have been Banned for " + args[2]);
                            break;
                        case "tempban":
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.HOUR, 12);
                            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), ChatColor.RED + "You have been Banned for\n12 Hours" + args[2], calendar.getTime(), null);
                            target.kickPlayer(ChatColor.RED + "You have been Banned for " + args[2]);
                            break;
                        case "kick":
                            target.kickPlayer(ChatColor.RED + "You have been kicked for " + args[2]);
                            break;
                        default:
                            player.sendMessage(ChatColor.RED + "Invalid usage! Use /punish <player name> <kick/ban/tempban <reason>");
                    }
                }else {
                    player.sendMessage(ChatColor.RED + "This player is not online!");
                }

            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Use /punish <player name> <kick/ban/tempban> <reason>");
            }

        }
        return false;

    }
    public static class punishTabCompleter implements TabCompleter {

        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

            if (args.length == 2){
                return StringUtil.copyPartialMatches(args[1], Arrays.asList("ban", "tempban", "kick"), new ArrayList<>());
            } else if (args.length == 3){
                return StringUtil.copyPartialMatches(args[2], Arrays.asList("hacking", "spamming"), new ArrayList<>());
            } else if (args.length == 1) {
                List<String> names = new ArrayList<>();
                for (Player player : Bukkit.getOnlinePlayers()){
                    names.add(player.getName());
                }
                return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());
            }
            return new ArrayList<>();
        }
    }

}
