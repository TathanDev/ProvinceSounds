package fr.tathan.provincesounds;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.session.SessionManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ProvinceSounds extends JavaPlugin {

    @Override
    public void onEnable() {
        SessionManager sessionManager = WorldGuard.getInstance().getPlatform().getSessionManager();
        sessionManager.registerHandler(PlaySoundHandler.FACTORY, null);
    }

    @Override
    public void onLoad() {
        FlagsRegistry flagsRegistry = new FlagsRegistry(WorldGuard.getInstance().getFlagRegistry());
        flagsRegistry.registerFlags();
    }

    public ProvinceSounds getInstance() {
        return this;
    }

}
