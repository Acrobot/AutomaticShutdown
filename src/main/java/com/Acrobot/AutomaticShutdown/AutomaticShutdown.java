package com.Acrobot.AutomaticShutdown;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * @author Acrobot
 */
public class AutomaticShutdown extends JavaPlugin {
    private static Plugin plugin;

    public void onEnable() {
        Timer timer = new Timer();
        plugin = this;

        timer.schedule(new ShutdownTask(), getNextRestartTime());
        getLogger().info("Scheduled restart on " + getNextRestartTime().toString());
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public Date getNextRestartTime() {
        Calendar midnight = getMidnight();
        Calendar noon = getNoon();

        Calendar currentTime = Calendar.getInstance();

        if (noon.after(currentTime)) {
            return noon.getTime();
        } else {
            return midnight.getTime();
        }
    }

    public Calendar getMidnight() {
        Calendar midnight = Calendar.getInstance();

        midnight.set(Calendar.HOUR_OF_DAY, 23);
        midnight.set(Calendar.MINUTE, 59);
        midnight.set(Calendar.SECOND, 59);

        return midnight;
    }

    public Calendar getNoon() {
        Calendar noon = Calendar.getInstance();

        noon.set(Calendar.HOUR_OF_DAY, 12);
        noon.set(Calendar.MINUTE, 0);
        noon.set(Calendar.SECOND, 0);

        return noon;
    }
}
