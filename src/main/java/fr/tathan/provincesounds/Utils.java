package fr.tathan.provincesounds;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    public void playPlaylist(ArrayList<String> list) {
        for (String sound : list) {
            Sound.valueOf(sound);
        }
    }

    public static String getRandomSound(List<String> list) {
        Random random = new Random();
        String sound = list.get(random.nextInt(list.size()));
        ProvinceSounds.getInstance().getLogger().info("Playing random sound " + list + " and the sound is " + sound + "!");
        return sound;
    }

    public static void playSound(Player player, String sound) {
        try {
            Sound soundToPlay = Sound.valueOf(sound);
            player.playSound(player, soundToPlay, 1, 1);

        } catch (IllegalArgumentException e) {
            player.playSound(player, sound, 1, 1);

        }
    }

}
