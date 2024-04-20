package fr.tathan.provincesounds.commands;

import fr.tathan.provincesounds.ProvinceSounds;
import fr.tathan.provincesounds.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class ProvinceSoundCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            return false;
        }

        if(strings[0].equalsIgnoreCase("random")) {
            if(strings.length == 1) {
                sendMessage(commandSender, "You need to specify a random list!");
                return false;
            }

            FileConfiguration config = ProvinceSounds.getInstance().getConfig();
            String playlist = strings[1];

            List<String> soundList = config.getConfigurationSection("playlists").getStringList(playlist);

            if(soundList == null || soundList.isEmpty()) {
                sendMessage(commandSender, "The " + playlist + " list does not exist!");
                sendMessage(commandSender, "Available lists: " + config.getConfigurationSection("playlists").getKeys(false));
                return true;
            }
            Utils.playSound((Player) commandSender, Utils.getRandomSound(soundList));
            return true;
        }
        return false;
    }

    public void sendMessage(CommandSender commandSender, String message) {
        commandSender.sendMessage("§a[§3ProvinceSounds§a]§f " + message);

    }
}
