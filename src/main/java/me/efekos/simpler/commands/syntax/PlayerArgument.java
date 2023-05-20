package me.efekos.simpler.commands.syntax;

import me.efekos.simpler.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerArgument extends Argument {
    @Override
    public String getPlaceHolder() {
        return "player";
    }

    @Override
    public ArrayList<ArgumentResult> getList(Player player, String current) {
        Collection<? extends Player> players = player.getServer().getOnlinePlayers();
        ArrayList<ArgumentResult> argumentResults = new ArrayList<>();

        players.stream().map(Player::getName).forEach(s -> argumentResults.add(new ArgumentResult()
                .setName(s)
                .setValue(s)
        ));

        return Utils.fromStreamToArrayList(
                argumentResults.stream()
                        .filter(argumentResult -> argumentResult.getName().startsWith(current))
        );
    }

    @Override
    public boolean handleCorrection(String given) {
        OfflinePlayer p = Bukkit.getServer().getPlayer(given);
        if(p == null) return false;

        return p.getName().equals(given);
    }

    @Override
    public ArgumentPriority getPriority() {
        return ArgumentPriority.REQUIRED;
    }
}
