/*
 * Copyright (c) 2024 efekos
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

package me.efekos.simpler.items;

import com.google.gson.Gson;
import me.efekos.simpler.events.ItemEvents;
import me.efekos.simpler.exception.NoPluginException;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

/**
 * Static manager for {@link CustomItem}s. You can register your {@link CustomItem}s using this class.
 */
public class ItemManager {
    /**
     * Used to store a {@link CustomItem}'s type to the {@link ItemStack}.
     */
    public static NamespacedKey itemTypeKey;

    /**
     * Used to store a {@link CustomItem}'s unique id to the {@link ItemStack}.
     */
    public static NamespacedKey itemUuidKey;

    /**
     * Used for the checks about setup is vaild.
     */
    private static boolean isSetup;

    /**
     * A map of the {@link CustomItem} instances. You can get any {@link CustomItem} using their unique id ({@link CustomItem#getUniqueItemId()}).
     */
    private static HashMap<UUID,CustomItem> items = new HashMap<>();

    /**
     * Saves the item data to the path given. Uses Gson and '.json' format for saving. You can load all the item data using {@link #loadItemData(String)} later.
     * @param path Path to the save file. Must end with '.json'.
     */
    public static void saveItemData(String path) {
        try {
            if (!isSetup) {
                throw new NoPluginException("Call me.efekos.simpler.items.ItemManager.setPlugin(org.bukkit.plugin.java.JavaPlugin) first.");
            }

            Gson gson = new Gson();
            File file = new File(path);
            file.getParentFile().mkdir();
            file.createNewFile();

            Writer writer = new FileWriter(path, false);
            gson.toJson(items, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the item data from the path given.
     * @param path Path to the save file. Must end with '.json'.
     */
    public static void loadItemData(String path) {
        try {
            if (!isSetup) {
                throw new NoPluginException("Call me.efekos.simpler.items.ItemManager.setPlugin(org.bukkit.plugin.java.JavaPlugin) first.");
            }

            Gson gson = new Gson();
            File file = new File(path);
            if (file.exists()) {
                Reader reader = new FileReader(file);
                items = gson.fromJson(reader, HashMap.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(items);
    }

    /**
     * Returns all the items exists so far.
     * @return A {@link HashMap} of the items.
     */
    public static HashMap<UUID,CustomItem> getItems() {
        return items;
    }

    /**
     * Sets up {@link ItemManager} for your plugin.
     * @param plugin An instance of your {@link JavaPlugin}.
     */
    public static void setPlugin(JavaPlugin plugin) {
        itemTypeKey = new NamespacedKey(plugin, "item_id");
        itemUuidKey = new NamespacedKey(plugin, "item_uuid");

        plugin.getServer().getPluginManager().registerEvents(new ItemEvents(), plugin);

        isSetup = true;
    }

    /**
     * Gives someone an {@link ItemStack} of the {@link Material} given.
     * @param player Someone to give an item.
     * @param type Type of the item you want to give.
     */
    public static void giveItem(@NotNull Player player, Material type) {
        player.getInventory().addItem(new ItemStack(type, 1));
    }

    /**
     * Adds an {@link ItemStack} to someone's inventory.
     * @param player Someone to give the stack.
     * @param item {@link ItemStack} you want to give.
     */
    public static void giveItem(@NotNull Player player, ItemStack item) {
        player.getInventory().addItem(item);
    }

    /**
     * Generates an {@link ItemStack} for the {@link CustomItem} given, and gives it to the {@link Player} given.
     * @param player Someone to give the generated {@link ItemStack}
     * @param item An instance of the {@link CustomItem} you want to give.
     */
    public static void giveItem(@NotNull Player player, @NotNull CustomItem item) {
        try {
            if (!isSetup) {
                throw new NoPluginException("Call me.efekos.simpler.items.ItemManager.setPlugin(org.bukkit.plugin.java.JavaPlugin) first.");
            } else {
                ItemStack stack = new ItemStack(item.getMaterial());
                ItemMeta meta = item.getDefaultMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                container.set(itemTypeKey, PersistentDataType.STRING, item.getId());

                container.set(itemUuidKey, PersistentDataType.STRING, item.getUniqueItemId().toString());
                items.put(item.getUniqueItemId(), item);


                stack.setItemMeta(meta);
                player.getInventory().addItem(stack);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates an {@link ItemStack} for the {@link CustomItem} given.
     * @param item An instance of the {@link CustomItem} you want to generate.
     * @return Generated {@link ItemStack}.
     */
    public static @Nullable ItemStack createItem(@NotNull CustomItem item) {
        try {
            if (!isSetup) {
                throw new NoPluginException("Call me.efekos.simpler.items.ItemManager.setPlugin(org.bukkit.plugin.java.JavaPlugin) first.");
            } else {
                ItemStack stack = new ItemStack(item.getMaterial());
                ItemMeta meta = item.getDefaultMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                container.set(itemTypeKey, PersistentDataType.STRING, item.getId());

                container.set(itemUuidKey, PersistentDataType.STRING, item.getUniqueItemId().toString());
                items.put(item.getUniqueItemId(), item);


                stack.setItemMeta(meta);
                return stack;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gives someone the skull of the {@link OfflinePlayer} given.
     * @param player {@link Player} who you want to give a skull.
     * @param skullOwner {@link OfflinePlayer} who will be the owner of this skull.
     */
    public static void giveSkull(@NotNull Player player, OfflinePlayer skullOwner){
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        meta.setOwningPlayer(skullOwner);

        stack.setItemMeta(meta);

        player.getInventory().addItem(stack);
    }

    /**
     * Gives someone the skull of the {@link Player} given.
     * @param player {@link Player} who you want to give a skull.
     * @param skullOwner {@link Player} who will be the owner of this skull.
     */
    public static void giveSkull(@NotNull Player player, Player skullOwner){
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        meta.setOwningPlayer(skullOwner);
        stack.setItemMeta(meta);

        player.getInventory().addItem(stack);
    }
}
