package me.efekos.simpler;

import me.efekos.simpler.annotations.Register;
import me.efekos.simpler.commands.BaseCommand;
import me.efekos.simpler.commands.CommandManager;
import me.efekos.simpler.commands.CoreCommand;
import me.efekos.simpler.exception.InvalidAnnotationException;
import me.efekos.simpler.items.CustomItem;
import me.efekos.simpler.items.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InvalidClassException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class Simpler extends JavaPlugin {
    private static Simpler plugin;

    public static Simpler getPlugin() {
       return plugin;
    }

    @Override
    public void onEnable() {
       plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Set up the entire thing easily!
     * @param plugin The plugin to use it with registering
     * @throws InvalidAnnotationException If the annotations of a class is missing or wrong.
     * @throws InvalidClassException If one of the classes annotated with @Register is wrong.
     */
    public static void setup(JavaPlugin plugin) throws InvalidAnnotationException,InvalidClassException {
            Class<?>[] registers = Utils.getAllClassesAnnotatedWith(Register.class);

            try{
                for (Class<?> register:registers){
                    switch (register.getAnnotation(Register.class).value()){
                        case ITEM_TYPE:
                            if(register.isAssignableFrom(CustomItem.class)){
                                Class<? extends CustomItem> item = (Class<? extends CustomItem>) register;

                                Constructor<? extends CustomItem> itemConstructor = item.getConstructor();
                                itemConstructor.setAccessible(true);
                                CustomItem newItem = itemConstructor.newInstance();

                                ItemManager.registerItem(newItem.getId(), item);
                            } else {
                                throw new InvalidClassException(register.getName() + " Must extend me.efekos.simpler.items.CustomItem");
                            }
                            break;
                        case CORE_COMMAND:
                            if(register.isAssignableFrom(CoreCommand.class)){
                                Class<? extends CoreCommand> command = (Class<? extends CoreCommand>) register;
                                CommandManager.registerCoreCommand(plugin,command);
                            } else {
                                throw new InvalidClassException(register.getName() + " Must extend me.efekos.simpler.commands.CoreCommand");
                            }
                            break;
                        case BASE_COMMAND:
                            if(register.isAssignableFrom(BaseCommand.class)){
                                Class<? extends BaseCommand> command = (Class<? extends BaseCommand>) register;
                                CommandManager.registerBaseCommand(plugin,command);
                            } else {
                                throw new InvalidClassException(register.getName() + " Must extend me.efekos.simpler.commands.BaseCommand");
                            }
                            break;
                    }
                }
            }catch (NoSuchFieldException |IllegalAccessException |NoSuchMethodException|InvocationTargetException|InstantiationException e){
                e.printStackTrace();
            }
    }
}
