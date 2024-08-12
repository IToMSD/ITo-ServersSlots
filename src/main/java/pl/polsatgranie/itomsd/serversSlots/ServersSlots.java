package pl.polsatgranie.itomsd.serversSlots;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServersSlots extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getLogger().info("""
                
                ------------------------------------------------------------
                |                                                          |
                |      _  _______        __     __    _____   ____         |
                |     | ||___ ___|      |  \\   /  |  / ____| |  _ \\        |
                |     | |   | |   ___   | |\\\\ //| | | (___   | | \\ \\       |
                |     | |   | |  / _ \\  | | \\_/ | |  \\___ \\  | |  ) )      |
                |     | |   | | | (_) | | |     | |  ____) | | |_/ /       |
                |     |_|   |_|  \\___/  |_|     |_| |_____/  |____/        |
                |                                                          |
                |                                                          |
                ------------------------------------------------------------
                |                 +==================+                     |
                |                 |   ServersSlots   |                     |
                |                 |------------------|                     |
                |                 |        1.0       |                     |
                |                 |------------------|                     |
                |                 |  PolsatGraniePL  |                     |
                |                 +==================+                     |
                ------------------------------------------------------------
                """);
        this.saveDefaultConfig();
        this.getCommand("serverslots").setExecutor(new Commands(this));
        Bukkit.getPluginManager().registerEvents(this, this);
        setMaxPlayers(getConfig().getInt("max-players"));
    }

    public void setMaxPlayers(int amount) {
        getConfig().set("max-players", amount);
        saveConfig();
    }

    public int getMaxPlayers() {
        return getConfig().getInt("max-players");
    }

    public void reloadPluginConfig() {
        reloadConfig();
        setMaxPlayers(getConfig().getInt("max-players"));
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (Bukkit.getOnlinePlayers().size() >= getMaxPlayers() && !event.getPlayer().hasPermission("itomsd.serversslots.bypass")) {
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, ChatColor.translateAlternateColorCodes('&', getConfig().getString("full_server", "Server is full")));
        }
    }
}
