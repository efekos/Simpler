package me.efekos.simpler.menu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class MenuManager {
    private static HashMap<Player,MenuData> menuDataStore = new HashMap<>();

    public static void Open(Player p ,Class<? extends Menu> menuClazz){
        MenuData data = menuDataStore.get(p);
        if(data==null){
            menuDataStore.put(p,new MenuData(p));
            data = menuDataStore.get(p);
        }

        try{
            Constructor<? extends Menu> constructor = menuClazz.getConstructor(MenuData.class);
            constructor.setAccessible(true);

            Menu menu = constructor.newInstance(data);

            menu.open();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
