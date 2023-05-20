package me.efekos.simpler.egs;

import me.efekos.simpler.annotations.Command;
import me.efekos.simpler.commands.BaseCommand;
import me.efekos.simpler.commands.syntax.PlayerArgument;
import me.efekos.simpler.commands.syntax.Syntax;
import me.efekos.simpler.egs.items.testcompass;
import me.efekos.simpler.items.ItemManager;
import me.efekos.simpler.menu.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Command(name = "feed",description = "Feed someone!",permission = "simpler.egs.feed")
public class feed extends BaseCommand {

    public feed(@NotNull String name) {
        super(name);
    }

    public feed(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    /**
     * Used for handling tab completion, making examples and providing valid usages for this command. You can create your own arguments with a class extends {@link me.efekos.simpler.commands.syntax.Argument}. This is really helpful at making sure your arguments are good.
     *
     * @return A Syntax class for this command.
     */
    @Override
    public @NotNull Syntax getSyntax() {
        return new Syntax()
                .withArgument(new PlayerArgument());
    }

    /**
     * Fires when a player executes this command.
     *
     * @param player Player that sent the command.
     * @param args   The args given by sender.
     */
    @Override
    public void onPlayerUse(Player player, String[] args) {
        Player p = Bukkit.getServer().getPlayer(args[0]);
        p.setFoodLevel(20);
        player.sendMessage("Success!");
        ItemManager.giveItem(player, new testcompass());
    }

    /**
     * Fires when the console executes this command
     *
     * @param sender The console
     * @param args   Args given by sender
     */
    @Override
    public void onConsoleUse(ConsoleCommandSender sender, String[] args) {
        Player p = Bukkit.getServer().getPlayer(args[0]);
        p.setFoodLevel(20);
        sender.sendMessage("Success!");
    }
}
