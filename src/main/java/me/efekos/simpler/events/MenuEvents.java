package me.efekos.simpler.events;

import me.efekos.simpler.menu.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

/**
 * Should be registered as one of your plugin's listeners so {@link Menu}s will work.
 */
public class MenuEvents implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        InventoryHolder holder = e.getInventory().getHolder();

        if(holder instanceof Menu){
            Menu pusula = (Menu) holder;

            if(e.getCurrentItem()==null){
                e.setCancelled(pusula.cancelAllClicks());
                return;
            }
            pusula.onClick(e);
            if(!e.isCancelled()&&pusula.cancelAllClicks()) e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMenuOpen(InventoryOpenEvent e){
        InventoryHolder holder = e.getInventory().getHolder();

        if(holder instanceof Menu){
            Menu pusula = (Menu) holder;

            pusula.onOpen(e);
        }
    }

    @EventHandler
    public void onMenuClose(InventoryCloseEvent e){
        InventoryHolder holder = e.getInventory().getHolder();

        if(holder instanceof Menu){
            Menu pusula = (Menu) holder;

            pusula.onClose(e);
        }
    }
}
