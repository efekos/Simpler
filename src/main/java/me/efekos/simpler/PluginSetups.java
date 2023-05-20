package me.efekos.simpler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class PluginSetups {

    public static boolean checkUpdates(JavaPlugin plugin,int resourceId) {
        AtomicBoolean has = new AtomicBoolean(false);
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {

                    String ver = scanner.next();
                    String pluginVer = plugin.getDescription().getVersion();

                    has.set(ver.equals(pluginVer));
                }
            } catch (Exception exception) {
                plugin.getLogger().info("Unable to check for updates: " + exception.getMessage());
            }
        });
        return has.get();
    }

    public static Metrics setupMetrics(JavaPlugin plugin,int serviceId){
        return new Metrics(plugin,serviceId);
    }
}
