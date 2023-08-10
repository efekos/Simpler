package me.efekos.simpler.exception;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Gets thrown when there is no plugin given using {@link me.efekos.simpler.items.ItemManager#setPlugin(JavaPlugin)} and {@link me.efekos.simpler.menu.MenuManager#setPlugin(JavaPlugin)}.
 */
public class NoPluginException extends Exception{
    public NoPluginException() {
    }

    public NoPluginException(String message) {
        super(message);
    }

    public NoPluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPluginException(Throwable cause) {
        super(cause);
    }

    public NoPluginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
