package me.efekos.simpler.commands.syntax;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListArgument extends Argument {
    private String holder;
    private List<String> values = new ArrayList<>();

    public ListArgument(String holder, String ...values) {
        this.holder = holder;
        this.values.addAll(Arrays.asList(values));

    }

    @Override
    public String getPlaceHolder() {
        return holder;
    }

    @Override
    public List<String> getList(Player player, String current) {
        return values;
    }

    @Override
    public boolean handleCorrection(String given) {
        return values.contains(given);
    }

    @Override
    public ArgumentPriority getPriority() {
        return ArgumentPriority.OPTIONAL;
    }
}
