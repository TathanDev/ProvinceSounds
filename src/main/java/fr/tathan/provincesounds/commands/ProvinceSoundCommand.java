package fr.tathan.provincesounds.commands;

import fr.tathan.provincesounds.ProvinceSounds;
import fr.tathan.provincesounds.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ProvinceSoundCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            return false;
        }

        if(strings[0].equalsIgnoreCase("random")) {
            if(strings.length == 1) {
                commandSender.sendMessage("You need to specify a random list!");
                return false;
            }

            FileConfiguration config = ProvinceSounds.getInstance().getConfig();
            String random = strings[1];

            Utils.playSound((Player) commandSender, Utils.getRandomSound(config.getConfigurationSection("randoms").getStringList(random)));
            return true;
        }
        return false;
    }
}
