# Introduction
Simpler is an API to help you making Minecraft plugins. It has a lot of features that will make developing a plugin much faster.

For example, here is a feed command made with Spigot API,

````java
import org.bukkit.command.Command;

class feed extends Command {
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("myPlugin.commands.feed")) {
                p.setFoodLevel(20);
                p.sendMessage("Successfully feed!");
            } else {
                p.sendMessage("You do not have permission to do that!");
            }
        } else {
            sender.sendMessage("This command only can be used by a player!");
        }
        return true;
    }
}
````
And this is a feed command with same functionality, but made with Simpler.

````java
    import me.efekos.simpler.annotations.Command;
    import me.efekos.simpler.commands.BaseCommand;
    
    @Command(name = "feed", description = "Feed yourself!", playerOnly = true, permission = "myPlugin.command.feed")
    class feed extends BaseCommand {
        /**
         * @param player Player that sent the command.
         * @param args Arguments given by sender.
         */
        @Override
        public void onPlayerUse(Player player, String[] args) {
            player.setFoodLevel(20);
            player.sendMessage("Successsfully feed!");
        }
    }
````