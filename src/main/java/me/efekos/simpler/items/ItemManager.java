package me.efekos.simpler.items;

import me.efekos.simpler.Simpler;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class ItemManager {
    public static NamespacedKey itemTypeKey = new NamespacedKey(Simpler.getPlugin(),"item_id");

    private static ArrayList<CustomItemRegister> registers = new ArrayList<>();

    public static void registerItem(String name,Class<? extends CustomItem> itemClass){
        registers.add(new CustomItemRegister()
                .setId(name)
                .setClazz(itemClass)
        );
    }

    public static ArrayList<CustomItemRegister> getRegisters() {
        return registers;
    }

    public static void giveItem(Player player, Material type){
        player.getInventory().addItem(new ItemStack(type,1));
    }

    public static void giveItem(Player player,ItemStack item){
        player.getInventory().addItem(item);
    }

    public static void giveItem(Player player,CustomItem item){
        ItemStack stack = new ItemStack(item.getMaterial());
        ItemMeta meta = item.getDefaultMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(itemTypeKey, PersistentDataType.STRING,item.getId());
        stack.setItemMeta(meta);
        player.getInventory().addItem(stack);
    }

    public static void giveSkull(Player player, OfflinePlayer skullOwner){
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        meta.setOwningPlayer(skullOwner);

        stack.setItemMeta(meta);

        player.getInventory().addItem(stack);
    }

    public static void giveSkull(Player player,Player skullOwner){
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        meta.setOwningPlayer(skullOwner);
        stack.setItemMeta(meta);

        player.getInventory().addItem(stack);
    }
}
