package me.efekos.simpler.egs.menu;

import me.efekos.simpler.menu.Menu;
import me.efekos.simpler.menu.MenuData;
import me.efekos.simpler.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class MainMenu extends Menu {

    public MainMenu(MenuData data) {
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
        return "Game Selector";
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if(event.getCurrentItem().getType()==Material.PLAYER_HEAD){
            MenuManager.Open(owner,Stats.class);
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
        getInventory().setItem(12,createItem(Material.GRASS_BLOCK, ChatColor.GREEN+"SkyBlock",ChatColor.GOLD+"Current Players: 4",ChatColor.YELLOW+"Click to Join"));
        getInventory().setItem(13,createItem(Material.IRON_SWORD, ChatColor.GREEN+"KitPVP",ChatColor.GOLD+"Current Players: 17",ChatColor.YELLOW+"Click to Join"));
        getInventory().setItem(14,createItem(Material.RED_BED, ChatColor.GREEN+"BedWars",ChatColor.GOLD+"Current Players: 53",ChatColor.YELLOW+"Click to Join Lobby"));
        getInventory().setItem(21,createItem(Material.EGG, ChatColor.GREEN+"EggWars",ChatColor.GOLD+"Current Players: 124",ChatColor.YELLOW+"Click to Join Lobby"));
        getInventory().setItem(22,createItem(Material.IRON_PICKAXE, ChatColor.GREEN+"Survival",ChatColor.GOLD+"Current Players: 12",ChatColor.YELLOW+"Click to Join"));
        getInventory().setItem(23,createItem(Material.BOOKSHELF, ChatColor.GREEN+"Hide And Seek",ChatColor.GOLD+"Current Players: 26",ChatColor.YELLOW+"Click to Join Lobby"));
        getInventory().setItem(40,createSkull(owner, ChatColor.GREEN+"Your Stats",
                ChatColor.GOLD+"Level: 26",
                ChatColor.GOLD+"Games Played: 32",
                ChatColor.GOLD+"Games Won: 16",
                ChatColor.GOLD+"Games Lost: 16",
                ChatColor.GOLD+"",
                ChatColor.UNDERLINE+""+ChatColor.YELLOW+"Click To See Advanced Statistics"
                ));

        fillEmptyWith(createItem(Material.BLACK_STAINED_GLASS_PANE,ChatColor.RED+""));
    }
}
