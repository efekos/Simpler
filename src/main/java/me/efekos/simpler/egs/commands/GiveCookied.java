package me.efekos.simpler.egs.commands;

import me.efekos.simpler.annotations.Command;
import me.efekos.simpler.commands.BaseCommand;
import me.efekos.simpler.commands.syntax.Syntax;
import me.efekos.simpler.egs.items.ClickerCokkie;
import me.efekos.simpler.items.ItemManager;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Command(name = "givecookie",description = "Give you cookied")
public class GiveCookied extends BaseCommand {
    @Override
    public @NotNull Syntax getSyntax() {
        return new Syntax();
    }

    public GiveCookied(@NotNull String name) {
        super(name);
    }

    public GiveCookied(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public void onPlayerUse(Player player, String[] args) {
        ItemManager.giveItem(player,new ClickerCokkie());
    }

    @Override
    public void onConsoleUse(ConsoleCommandSender sender, String[] args) {

    }
}
