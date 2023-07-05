package me.efekos.simpler.menu;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Stack;

public class MenuData {
    private final Player owner;
    private final Stack<Menu> menuHistory = new Stack<>();
    private final HashMap<String,Object> data = new HashMap<>();

    public MenuData(Player owner) {
        this.owner = owner;
    }

    public Stack<Menu> getMenuHistory() {
        return menuHistory;
    }

    public Object get(String key){
        return data.get(key);
    }

    public void set(String key,Object value){
        data.put(key,value);
    }

    public Player getOwner() {
        return owner;
    }

    public void addMenu(Menu menu){
        menuHistory.push(menu);
    }

    public Menu lastMenu(){
        menuHistory.pop();
        return menuHistory.pop();
    }
}
