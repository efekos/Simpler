package me.efekos.simpler.items;

import me.efekos.simpler.Simpler;
import me.efekos.simpler.events.PlayerEvents;
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

import java.util.ArrayList;

public class ItemManager {
    public static NamespacedKey itemTypeKey;

    private static JavaPlugin plugin;
    private static ArrayList<CustomItemRegister> registers = new ArrayList<>();
    private static boolean isSetup;

    public static void setPlugin(JavaPlugin plugin) {
        ItemManager.plugin = plugin;
        itemTypeKey = new NamespacedKey(plugin,"item_id");
    }

    public static void registerItem(String name, Class<? extends CustomItem> itemClass){
        try{
            if(plugin==null) throw new NoPluginException("Call method me.efekos.simpler.items.ItemManager#setPlugin first.");

            if(!isSetup){
                plugin.getServer().getPluginManager().registerEvents(new PlayerEvents(),plugin);
                isSetup = true;
            }

            registers.add(new CustomItemRegister()
                    .setId(name)
                    .setClazz(itemClass)
            );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<CustomItemRegister> getRegisters() {
        return registers;
    }

    public static void giveItem(@NotNull Player player, Material type){
        player.getInventory().addItem(new ItemStack(type,1));
    }

    public static void giveItem(@NotNull Player player, ItemStack item){
        player.getInventory().addItem(item);
    }

    public static void giveItem(@NotNull Player player, @NotNull CustomItem item){
        ItemStack stack = new ItemStack(item.getMaterial());
        ItemMeta meta = item.getDefaultMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(itemTypeKey, PersistentDataType.STRING,item.getId());
        stack.setItemMeta(meta);
        player.getInventory().addItem(stack);
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
