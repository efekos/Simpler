/*
 * Copyright (c) 2023 efekos
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
     * Returns the {@link Player} who this data belongs to
     * @param owner Final owner of this data.
     */
    public MenuData(Player owner) {
        this.owner = owner;
    }

    /**
     * Returns a menu {@link Stack} used to store the history of menus {@link #owner} visited.
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
     * Returns the {@link Player} who owns this data.
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
     * Removes the last menu from menu history and returns it.
     * @return Last menu the owner visited.
     */
    public Menu lastMenu(){
        menuHistory.pop();
        return menuHistory.pop();
    }
}
