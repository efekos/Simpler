package me.efekos.simpler.menu;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Stack;

/**
 * Represents a temporary data for communication between any code and {@link Menu} classes.
 */
public class MenuData {
    /**
     * Owner of this data.
     */
    private final Player owner;
    /**
     * A history of the {@link Menu}s visited by the owner.
     */
    private final Stack<Menu> menuHistory = new Stack<>();
    /**
     * A map that can store any type as the data.
     */
    private final HashMap<String,Object> data = new HashMap<>();

    /**
     * @param owner Final owner of this data.
     */
    public MenuData(Player owner) {
        this.owner = owner;
    }

    /**
     * @return A history of the {@link Menu}s visited by the owner.
     */
    public Stack<Menu> getMenuHistory() {
        return menuHistory;
    }

    /**
     * Gets a value from the stored data, using the key given.
     * @param key Key to search for.
     * @return Whatever found.
     */
    public Object get(String key){
        return data.get(key);
    }

    /**
     * Sets a value for the key given.
     * @param key Key to use.
     * @param value Value to enter.
     */
    public void set(String key,Object value){
        data.put(key,value);
    }

    /**
     * @return Owner of this data.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Adds a menu to the menu history of this data.
     * @param menu Instance of the {@link Menu}.
     */
    public void addMenu(Menu menu){
        menuHistory.push(menu);
    }

    /**
     * @return Last menu the owner visited.
     */
    public Menu lastMenu(){
        menuHistory.pop();
        return menuHistory.pop();
    }
}
