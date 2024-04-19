package fr.tathan.provincesounds;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.Handler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class PlaySoundHandler extends Handler {
    public static final Factory FACTORY = new Factory();
    public static class Factory extends Handler.Factory<PlaySoundHandler> {
        @Override
        public PlaySoundHandler create(Session session) {

            return new PlaySoundHandler(session);
        }
    }
    public PlaySoundHandler(Session session) {
        super(session);
    }

    private static final long SOUND_THRESHOLD = 1000 * 2;
    private long lastSound;

    @Override
    public boolean onCrossBoundary(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet, Set<ProtectedRegion> entered, Set<ProtectedRegion> exited, MoveType moveType) {
        boolean allowed = toSet.testState(player, FlagsRegistry.PLAY_MUSIC_ENTER);
        Player bukkitPlayer = BukkitAdapter.adapt(player);
        FileConfiguration config = ProvinceSounds.getPlugin(ProvinceSounds.class).getConfig();


        if (allowed) {

            final String sound = toSet.queryValue(player, FlagsRegistry.MUSIC_TO_PLAY);
            long now = System.currentTimeMillis();
                if ((now - lastSound) > SOUND_THRESHOLD && sound != null && !sound.isEmpty()) {
                    if(config.getBoolean("stop-sounds")) {
                        bukkitPlayer.stopAllSounds();
                    }

                    if(sound.contains("playlist:")) {
                        String playlist = sound.split(":")[1];
                        config.getConfigurationSection("playlists").getKeys(false).forEach(key -> {
                            if(key.equals(playlist)) {
                                List<String> stringList = config.getConfigurationSection("randoms").getStringList(key);
                                Utils.playSound(bukkitPlayer, Utils.getRandomSound(stringList));
                            }
                        });
                    } else {
                        Utils.playSound(bukkitPlayer, sound);
                    }
                }
            lastSound = now;
        }
        return true;
    }

}
