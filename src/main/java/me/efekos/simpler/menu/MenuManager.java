package me.efekos.simpler.menu;

import me.efekos.simpler.events.MenuEvents;
import me.efekos.simpler.exception.NoPluginException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.security.InvalidParameterException;
import java.util.HashMap;

/**
 * Static manager class for {@link Menu}s.
 */
public class MenuManager {
    /**
     * A map used to store a {@link MenuData} for every {@link Player}.
     */
    private static final HashMap<Player,MenuData> menuDataStore = new HashMap<>();
    /**
     * Used to quickly do setup checks
     */
    private static boolean isSetup = false;
    /**
     * An instance of the plugin that is currently using this manager.
     */
    private static JavaPlugin plugin;

    /**
     * Set up the {@link MenuManager} by giving it a {@link JavaPlugin}.
     * @param plugin Instance of your plugin.
     */
    public static void setPlugin(JavaPlugin plugin) {
        MenuManager.plugin = plugin;
        if(!isSetup){
            plugin.getServer().getPluginManager().registerEvents(new MenuEvents(),plugin);
            isSetup = true;
        }
    }

    /**
     * Get someone's menu data outside the menus, so you can change some data.
     * @param player {@link Player} who you need to get menu data.
     * @return {@link MenuData} stored for the {@link Player} given.
     */
    public static MenuData getMenuData(Player player){
        MenuData data = menuDataStore.get(player);
        if(data==null){
            menuDataStore.put(player,new MenuData(player));
            data = menuDataStore.get(player);
        }

        return data;
    }

    /**
     * Updates menu data of someone. Recommended to use right before {@link #Open(Player, Class)} if the data is changed from the code.
     * @param player {@link Player} who has a different {@link MenuData}.
     * @param newMenuData new instance of {@link MenuData} to replace with old one.
     * @throws InvalidParameterException If new {@link MenuData}'s owner is not the {@link Player} given.
     */
    public static void updateMenuData(Player player,MenuData newMenuData) throws InvalidParameterException{
        try{
            if(!newMenuData.getOwner().getUniqueId().equals(player.getUniqueId())) throw new InvalidParameterException("Owners does not match.");
            menuDataStore.put(player, newMenuData);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Opens a menu for a {@link Player}.
     * @param p {@link Player} that will see this menu.
     * @param menuClazz {@link Class} reference of your {@link Menu}.
     */
    public static void Open(Player p , Class<? extends Menu> menuClazz){
        try {
            if(!isSetup) throw new NoPluginException("Call method me.efekos.simpler.menu.MenuManager#setPlugin first.");

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
