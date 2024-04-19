package fr.tathan.provincesounds;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.session.SessionManager;
import fr.tathan.provincesounds.commands.ProvinceSoundCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class ProvinceSounds extends JavaPlugin {

    @Override
    public void onEnable() {
        SessionManager sessionManager = WorldGuard.getInstance().getPlatform().getSessionManager();
        sessionManager.registerHandler(PlaySoundHandler.FACTORY, null);
        this.getCommand("provincesounds").setExecutor(new ProvinceSoundCommand());
}

    @Override
    public void onLoad() {
        saveDefaultConfig();
        FlagsRegistry flagsRegistry = new FlagsRegistry(WorldGuard.getInstance().getFlagRegistry());
        flagsRegistry.registerFlags();
    }

    public static ProvinceSounds getInstance() {
        return ProvinceSounds.getPlugin(ProvinceSounds.class);
    }

//    public void addToConfig() {
//        HashMap map = new HashMap<String, String>();
//        map.put("playlist-1", string);
//        map.put("playlist-2", string);
//
//        config.createSection("playlists", map);
//    }
}
