package me.efekos.simpler;

import me.efekos.simpler.commands.CommandManager;
import me.efekos.simpler.egs.commands.GiveCookied;
import me.efekos.simpler.items.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Simpler extends JavaPlugin {
    private final String customItemDataPath = getDataFolder().getAbsolutePath()+"\\CustomItemData.json";

    @Override
    public void onEnable() {
        try {
            CommandManager.registerBaseCommand(this, GiveCookied.class);
            ItemManager.setPlugin(this);
            ItemManager.loadItemData(customItemDataPath);
        } catch (Exception e) {
      e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ItemManager.saveItemData(customItemDataPath);
    }
}
