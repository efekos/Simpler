package me.efekos.simpler.commands.syntax;

import me.efekos.simpler.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class ListArgument extends Argument {
    private String holder;
    private ArrayList<String> values;

    public ListArgument(String holder, String ...values) {
        this.holder = holder;
        this.values.addAll(Arrays.asList(values));

    }

    @Override
    public String getPlaceHolder() {
        return holder;
    }

    @Override
    public ArrayList<ArgumentResult> getList(Player player, String current) {
        return Utils.fromStreamToArrayList(this.values.stream()
                .filter(s -> s.startsWith(current))
                .map(s -> new ArgumentResult().setName(s).setValue(s))
        );
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
