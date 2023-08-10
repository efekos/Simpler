package me.efekos.simpler.config;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

/**
 * Can be used as a YamlConfiguration. Adds some extra features to default configuration.
 */
public class Config {

    /**
     * Name of the resource that will be cloned from plugin resources. This makes it really easy to make custom configs. All you have to do it change the resource name.
     */
    private String resourceName;

    /**
     * A file that will be used to load configuration from.
     */
    private File file;

    /**
     * Main configuration of this config.
     */
    private FileConfiguration fileConfiguration;

    /**
     * The plugin that uses this config.
     */
    private JavaPlugin plugin;

    /**
     * Can be used as a YamlConfiguration. Adds some extra features to default configuration.
     * @param resourceName Name of the resource you want to use as this config. Make sure that your resource name ends with `.yml` and you have a resource with the exact same name on your 'resources' folder.
     * @param plugin An instance of your plugin.
     */
    public Config(String resourceName, JavaPlugin plugin) {
        this.resourceName = resourceName;
        this.plugin = plugin;
    }

    /**
     * Loads the default configuration file to your plugin's data folder. Recommended to use inside {@link JavaPlugin#onEnable()}.
     */
    public void setup(){
        file = new File(plugin.getDataFolder(),resourceName);

        if(!file.exists()){
            try{
                plugin.saveResource(resourceName, false);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * @return The {@link FileConfiguration} itself for cases that you need the original methods.
     */
    public FileConfiguration get(){
        return  fileConfiguration;
    }

    /**
     * Finds a {@link String} from the configuration
     * @param path Path to the {@link String} you want.
     * @param def Default {@link String} to be returned in case nothing found in path. There are good reasons behind this parameter being required.
     * @return Whatever found.
     */
    public String getString(String path,String def){
        return fileConfiguration.getString(path,def);
    }

    /**
     * Finds an {@link Integer} from the configuration
     * @param path Path to the {@link Integer} you want.
     * @param def Default {@link Integer} to be returned in case nothing found in path. There are good reasons behind this parameter being required.
     * @return Whatever found.
     */
    public int getInt(String path,Integer def){
        return fileConfiguration.getInt(path,def);
    }

    /**
     * Finds a {@link Boolean} from the configuration
     * @param path Path to the {@link Boolean} you want.
     * @param def Default {@link Boolean} to be returned in case nothing found in path. There are good reasons behind this parameter being required.
     * @return Whatever found.
     */
    public boolean getBoolean(String path,Boolean def){
        return fileConfiguration.getBoolean(path,def);
    }

    /**
     * Finds a {@link List<String>} from the configuration
     * @param path Path to the {@link List<String>} you want.
     * @return Whatever found.
     */
    public List<String> getStringList(String path){
        return fileConfiguration.getStringList(path);
    }

    /**
     * Finds a {@link Location} from the configuration
     * @param path Path to the {@link Location} you want.
     * @param def Default {@link Location} to be returned in case nothing found in path.
     * @return Whatever found.
     */
    public Location getLocation(String path, Location def){
        return fileConfiguration.getLocation(path,def);
    }

    /**
     * Finds a {@link Location} from the configuration
     * @param path Path to the {@link Location} you want.
     * @return Whatever found.
     */
    public Location getLocation(String path){
        return fileConfiguration.getLocation(path);
    }

    /**
     * Finds a {@link Double} from the configuration
     * @param path Path to the {@link Double} you want.
     * @param def Default {@link Double} to be returned in case nothing found in path. There are good reasons behind this parameter being required.
     * @return Whatever found.
     */
    public double getDouble(String path,double def){
        return fileConfiguration.getDouble(path,def);
    }

    /**
     * Reloads the config by reading the file again.
     */
    public void reload(){
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
}
