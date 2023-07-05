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

public class ItemManager {
    public static NamespacedKey itemTypeKey;
    public static NamespacedKey itemUuidKey;

    private static boolean isSetup;
    private static HashMap<UUID,CustomItem> items = new HashMap<>();

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

    public static HashMap<UUID,CustomItem> getItems() {
        return items;
    }

    public static void setPlugin(JavaPlugin plugin) {
        itemTypeKey = new NamespacedKey(plugin, "item_id");
        itemUuidKey = new NamespacedKey(plugin, "item_uuid");

        plugin.getServer().getPluginManager().registerEvents(new ItemEvents(), plugin);

        isSetup = true;
    }

    public static void giveItem(@NotNull Player player, Material type) {
        player.getInventory().addItem(new ItemStack(type, 1));
    }

    public static void giveItem(@NotNull Player player, ItemStack item) {
        player.getInventory().addItem(item);
    }

    public static void giveItem(@NotNull Player player, @NotNull CustomItem item) {
        try {
            if (!isSetup) {
                throw new NoPluginException("Call me.efekos.simpler.items.ItemManager.setPlugin(org.bukkit.plugin.java.JavaPlugin) first.");
            } else {
                ItemStack stack = new ItemStack(item.getMaterial());
                ItemMeta meta = item.getDefaultMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                container.set(itemTypeKey, PersistentDataType.STRING, item.getId());
                UUID itemId = UUID.randomUUID();

                container.set(itemUuidKey, PersistentDataType.STRING, itemId.toString());
                item.setUniqueItemId(itemId);
                items.put(itemId, item);


                stack.setItemMeta(meta);
                player.getInventory().addItem(stack);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static @Nullable ItemStack createItem(@NotNull CustomItem item) {
        try {
            if (!isSetup) {
                throw new NoPluginException("Call me.efekos.simpler.items.ItemManager.setPlugin(org.bukkit.plugin.java.JavaPlugin) first.");
            } else {
                ItemStack stack = new ItemStack(item.getMaterial());
                ItemMeta meta = item.getDefaultMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                container.set(itemTypeKey, PersistentDataType.STRING, item.getId());
                UUID itemId = UUID.randomUUID();

                container.set(itemUuidKey, PersistentDataType.STRING, itemId.toString());
                item.setUniqueItemId(itemId);
                items.put(itemId, item);


                stack.setItemMeta(meta);
                return stack;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void giveSkull(@NotNull Player player, OfflinePlayer skullOwner){
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        meta.setOwningPlayer(skullOwner);

        stack.setItemMeta(meta);

        player.getInventory().addItem(stack);
    }

    public static void giveSkull(@NotNull Player player, Player skullOwner){
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        meta.setOwningPlayer(skullOwner);
        stack.setItemMeta(meta);

        player.getInventory().addItem(stack);
    }
}
