package com.Acrobot.AutomaticShutdown;

import org.bukkit.Bukkit;

import java.util.TimerTask;

/**
 * @author Acrobot
 */
public class ShutdownTask extends TimerTask {

    @Override
    public void run() {
        runCountDown();
    }

    private void runCountDown() {
        for (final Stage stage : Stage.values()) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(AutomaticShutdown.getPlugin(), new ShutdownRunnable(stage), 20L * stage.getDelay());
        }
    }

    public static void showMessage(String message) {
        if (!message.isEmpty()) {
            Bukkit.broadcastMessage(message);
        }
    }

    public static void executeCommand(String command) {
        if (!command.isEmpty()) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }

    class ShutdownRunnable implements Runnable {
        private Stage stage;

        public ShutdownRunnable(Stage stage) {
            this.stage = stage;
        }

        public void run() {
            showMessage(stage.getMessage());
            executeCommand(stage.getCommand());
        }
    }

    enum Stage {
        SECONDS_10("Server restarting in 10 seconds!", 0),
        SECONDS_5("Server restarting in 5 seconds!", 5),
        SAVE_ALL("Saving the map!", "save-all", 5),
        STOP("Stopping the server!", "stop", 10);

        private String message;
        private String command;
        private int delay;

        Stage() {
            this("");
        }

        Stage(String message) {
            this(message, 5);
        }

        Stage(String message, int delay) {
            this(message, "", delay);
        }

        Stage(String message, String command) {
            this(message, command, 3);
        }

        Stage(String message, String command, int delay) {
            this.delay = delay;
            this.message = message;
            this.command = command;
        }

        public String getMessage() {
            return message;
        }

        public int getDelay() {
            return delay;
        }

        public String getCommand() {
            return command;
        }
    }
}
