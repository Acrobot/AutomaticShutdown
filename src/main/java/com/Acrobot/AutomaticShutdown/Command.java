package com.Acrobot.AutomaticShutdown;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * @author Acrobot
 */
public class Command implements Listener, CommandExecutor {
    @EventHandler
    public static void onCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage();

        if (command.equals("/stop")) {
            event.setCancelled(true);

            Bukkit.getScheduler().scheduleSyncDelayedTask(AutomaticShutdown.getPlugin(), new ShutdownTask());
        }
    }

    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String alias, String[] args) {
        if (!"safe-stop".equals(command.getName())) {
            return true;
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(AutomaticShutdown.getPlugin(), new ShutdownTask());

        return true;
    }
}
