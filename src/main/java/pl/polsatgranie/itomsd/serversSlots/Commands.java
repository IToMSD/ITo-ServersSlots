package pl.polsatgranie.itomsd.serversSlots;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
    private final ServersSlots plugin;

    public Commands(ServersSlots plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (!sender.hasPermission("itomsd.serversslots.admin")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("no_permission")));
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadPluginConfig();
                sender.sendMessage("§aReloaded.");
                return true;
            }

            try {
                int maxPlayers = Integer.parseInt(args[0]);
                if (maxPlayers < 0) {
                    sender.sendMessage("§cThe number of players must be additional.");
                    return true;
                }

                plugin.setMaxPlayers(maxPlayers);
                sender.sendMessage("§aThe maximum number of players has been set to " + maxPlayers);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cNumber of players must be a number.");
            }

            return true;
        } else {
            sender.sendMessage("§cUsage: /" + label + " [<amount>|reload]");
            return false;
        }
    }
}