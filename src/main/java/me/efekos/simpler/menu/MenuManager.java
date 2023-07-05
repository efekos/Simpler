package me.efekos.simpler.menu;

import me.efekos.simpler.events.MenuEvents;
import me.efekos.simpler.exception.NoPluginException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class MenuManager {
    private static final HashMap<Player,MenuData> menuDataStore = new HashMap<>();
    private static boolean isSetup = false;
    private static JavaPlugin plugin;

    public static void setPlugin(JavaPlugin plugin) {
        MenuManager.plugin = plugin;
    }

    public static MenuData getMenuData(Player player){
        MenuData data = menuDataStore.get(player);
        if(data==null){
            menuDataStore.put(player,new MenuData(player));
            data = menuDataStore.get(player);
        }

        return data;
    }

    public static void updateMenuData(Player player,MenuData newMenuData){
        menuDataStore.put(player, newMenuData);
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
