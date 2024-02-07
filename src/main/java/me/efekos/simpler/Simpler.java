package me.efekos.simpler;

import me.efekos.simpler.commands.*;
import me.efekos.simpler.config.MessageConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.List;
import java.util.Set;

/**
 * Main class of Simpler.
 */
public final class Simpler extends JavaPlugin{
    /**
     * Main configuration for messages that Simpler uses.
     */
    private static MessageConfiguration configuration = new MessageConfiguration.Builder().build();

    /**
     * Returns the message configuration Simpler is using right now.
     * @return Message config of Simpler.
     */
    public static MessageConfiguration getMessageConfiguration() {
        return configuration;
    }

    /**
     * Changes the configuration with the configuration given.
     * @param configuration New configuration that you want Simpler to use.
     */
    public static void changeMessageConfiguration(MessageConfiguration configuration) {
        Simpler.configuration = configuration;
    }

    public static void registerCommands(JavaPlugin plugin){
        Reflections reflections = new Reflections(plugin.getClass().getPackageName());

        Set<Class<?>> commandClasses = reflections.getTypesAnnotatedWith(Command.class);
        Set<Class<?>> subCommandClasses = reflections.getTypesAnnotatedWith(SubOf.class);


        for (Class<?> commandClass : commandClasses) {
            try {
                if(commandClass.getSuperclass()==null) throw new RuntimeException("Command classes must extend something, "+commandClass.getName()+" doesn't");

                if(commandClass.getSuperclass()== BaseCommand.class) CommandManager.registerBaseCommand(plugin, ((Class<? extends BaseCommand>) commandClass));
                else if (commandClass.getSuperclass()== CoreCommand.class){

                    List<Class<?>> subs = subCommandClasses.stream()
                            .filter(aClass -> aClass.getAnnotation(SubOf.class).value() == commandClass)
                            .filter(aClass -> aClass.getSuperclass() == SubCommand.class)
                            .toList();

                    CommandManager.registerCoreCommand(plugin,commandClass.asSubclass(CoreCommand.class), subs.toArray(Class[]::new));

                } else if (commandClass.getSuperclass()== SubCommand.class){
                    if(!subCommandClasses.contains(commandClass)) throw new RuntimeException("Sub commands must contain @SubOf, "+commandClass.getName()+" doesn't.");
                }

                for (Class<?> aClass : reflections.getTypesAnnotatedWith(CommandTreeHandler.class)) {
                    if(aClass.getSuperclass()!= CommandTreeHandler.class) throw new RuntimeException("@CommandTreeHandlers must extend TreeCommandHandler, "+aClass.getName()+" doesn't");

                    TreeCommandHandler handler = (TreeCommandHandler) aClass.getConstructor().newInstance();

                    CommandManager.registerCommandTree(plugin,handler.getTree());
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //TODO: NMS commands
    //TODO: finish guides
}
