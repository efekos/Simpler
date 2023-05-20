package me.efekos.simpler.egs.menu;

import me.efekos.simpler.menu.Menu;
import me.efekos.simpler.menu.MenuData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class Stats extends Menu {

    public Stats(MenuData data) {
        super(data);
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public int getRows() {
        return 6;
    }

    @Override
    public String getTitle() {
        return "Statistics of "+owner.getName();
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem().getType() == Material.PAPER) {
            back();
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

    }

    @Override
    public void fill() {
        getInventory().setItem(12,createItem(Material.GRASS_BLOCK, ChatColor.GREEN+"SkyBlock Stats",
                ChatColor.GOLD+"Island Level: 4",
                ChatColor.GOLD+"Rank: Master",
                ChatColor.GOLD+"Total Blocks: 69,243",
                ChatColor.GOLD+"Total Spawners: 32"
        ));
        getInventory().setItem(13,createItem(Material.IRON_SWORD, ChatColor.GREEN+"KitPVP",
                ChatColor.GOLD+"Kits Unlocked: 14/18",
                ChatColor.GOLD+"Kills: 264",
                ChatColor.GOLD+"Deaths: 48",
                ChatColor.GOLD+"Assists: 22"
        ));
        getInventory().setItem(14,createItem(Material.RED_BED, ChatColor.GREEN+"BedWars",
                ChatColor.GOLD+"Wins: 26",
                ChatColor.GOLD+"Loses: 12",
                ChatColor.GOLD+"Broke Beds: 26",
                ChatColor.GOLD+"Aces: 7",
                ChatColor.GOLD+"Massacres: 3",
                ChatColor.GOLD+"Kills: 812",
                ChatColor.GOLD+"Assists: 494"
        ));
        getInventory().setItem(21,createItem(Material.EGG, ChatColor.GREEN+"EggWars",ChatColor.GOLD+"Current Players: 124",ChatColor.YELLOW+"Click to Join Lobby"));
        getInventory().setItem(22,createItem(Material.IRON_PICKAXE, ChatColor.GREEN+"Survival",ChatColor.GOLD+"Current Players: 12",ChatColor.YELLOW+"Click to Join"));
        getInventory().setItem(23,createItem(Material.BOOKSHELF, ChatColor.GREEN+"Hide And Seek",ChatColor.GOLD+"Current Players: 26",ChatColor.YELLOW+"Click to Join Lobby"));
        getInventory().setItem(49,createItem(Material.PAPER,ChatColor.YELLOW+"Back"));
        getInventory().setItem(40,createSkull(owner, ChatColor.GREEN+"Your Statistics",
                ChatColor.GOLD+"Level: 26",
                ChatColor.GOLD+"Rank: Bronze VI",
                ChatColor.GOLD+"Time Spent: 36:12:45",
                ChatColor.GOLD+"Games Played: 268",
                ChatColor.GOLD+"Total Coin: 139,481.25",
                ChatColor.GOLD+"Total EXP: 38,913.232"
        ));

        fillEmptyWith(createItem(Material.BLACK_STAINED_GLASS_PANE," "));

    }
}
