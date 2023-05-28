package me.efekos.simpler.menu;

import me.efekos.simpler.events.MenuEvents;
import me.efekos.simpler.exception.NoPluginException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class MenuManager {
    private static HashMap<Player,MenuData> menuDataStore = new HashMap<>();
    private static boolean isSetup = false;
    private static JavaPlugin plugin;

    public static void setPlugin(JavaPlugin plugin) {
        MenuManager.plugin = plugin;
    }

    public static void Open(Player p , Class<? extends Menu> menuClazz){
        try {
            if(plugin==null) throw new NoPluginException("Call method me.efekos.simpler.menu.MenuManager#setPlugin first.");

            if(!isSetup){
               plugin.getServer().getPluginManager().registerEvents(new MenuEvents(),plugin);
               isSetup = true;
            }

            MenuData data = menuDataStore.get(p);
            if(data==null){
                menuDataStore.put(p,new MenuData(p));
                data = menuDataStore.get(p);
            }

            Constructor<? extends Menu> constructor = menuClazz.getConstructor(MenuData.class);
            constructor.setAccessible(true);

            Menu menu = constructor.newInstance(data);

            menu.open();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
