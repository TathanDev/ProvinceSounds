package fr.tathan.regionjukebox;

import com.sk89q.worldguard.protection.flags.*;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.bukkit.Sound;

public class FlagsRegistry {

    public static StateFlag PLAY_MUSIC_ENTER;
    public static StringFlag MUSIC_TO_PLAY;
    private static FlagRegistry registry;
    public FlagsRegistry(FlagRegistry registry) {
        this.registry = registry;
    }

    public static void registerFlags() {
        StateFlag flag = new StateFlag("play-music-enter", true, RegionGroup.NON_MEMBERS);
        StringFlag flag2 = new StringFlag("music-to-play", "BLOCK_NOTE_BLOCK_HARP");
        register(flag);
        register(flag2);
        PLAY_MUSIC_ENTER = flag; // only set our field if there was no error
        MUSIC_TO_PLAY = flag2;
    }

    public static void register( Flag<?> var1) {
        try {
            registry.register(var1);
        } catch (FlagConflictException e) {
            throw new RuntimeException(var1.getName() + " flag already exists ! Bro is using the same plugin twice ?!");

        }
    }
}
