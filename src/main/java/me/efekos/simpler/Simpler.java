package me.efekos.simpler;

import me.efekos.simpler.commands.CommandManager;
import me.efekos.simpler.commands.CommandTree;
import me.efekos.simpler.commands.node.impl.LabelNode;
import me.efekos.simpler.commands.node.impl.StringArgumentNode;
import me.efekos.simpler.config.MessageConfiguration;
import me.efekos.simpler.items.ItemManager;
import me.efekos.simpler.items.TestItem;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class of Simpler.
 */
public final class Simpler extends JavaPlugin {
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

    public static NamespacedKey TEST_ITEM_KEY;

    @Override
    public void onEnable() {

        try {
            CommandManager.registerCommandTree(this,new CommandTree("test","test command","test.test")
                    .addChild(new LabelNode("hello")
                            .addChild(new StringArgumentNode().setExecutive(context -> {
                                Player player = context.getSenderAsPlayer();

                                ItemManager.giveItem(player,new TestItem(8,"cool_item"));
                            })
                    )
            ));

            TEST_ITEM_KEY = new NamespacedKey(this,"test_item");

            ItemManager.setPlugin(this);
            ItemManager.registerItem(TEST_ITEM_KEY, TestItem.class);

            ItemManager.loadCustomItems();

        } catch (Exception e){
            e.printStackTrace();
        }

        //this thing simply makes a /home set <pos> command with nms. I'll use it later 👀👀
        //CommandDispatcher<CommandListenerWrapper> dispatcher = MinecraftServer.getServer().vanillaCommandDispatcher.a();

        //
        //dispatcher.register(net.minecraft.commands.CommandDispatcher.a("home")
        //        .then(net.minecraft.commands.CommandDispatcher.a("set")
        //                .then(net.minecraft.commands.CommandDispatcher.a("pos", ArgumentPosition.a())
        //                        .executes(commandContext -> {
        //                            commandContext.getSource().a(IChatBaseComponent.a("sdf"));
        //                            return 1;
        //                        })
        //                )
        //        )
        //);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        ItemManager.saveCustomItems();
    }
}
