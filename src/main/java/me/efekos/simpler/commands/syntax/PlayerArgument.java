package me.efekos.simpler.commands.syntax;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerArgument extends Argument {
    @Override
    public String getPlaceHolder() {
        return "player";
    }

    @Override
    public List<String> getList(Player player, String current) {
        Collection<? extends Player> players = player.getServer().getOnlinePlayers();
        List<String> argumentResults = new ArrayList<>();

        players.stream().map(Player::getName).forEach(s -> argumentResults.add(s));

        return argumentResults;
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
