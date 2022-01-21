package me.thatonedevil.test.PageGui;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayersCommand implements CommandExecutor {



    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            new PlayerGui((Player) sender, 1);
        }
        return false;

    }

}
