package fr.tathan.provincesounds;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.session.SessionManager;
import fr.tathan.provincesounds.commands.ProvinceSoundCommand;
import fr.tathan.provincesounds.metrics.Metrics;
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

        int pluginId = 21663;
        Metrics metrics = new Metrics(this, pluginId);

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

}
