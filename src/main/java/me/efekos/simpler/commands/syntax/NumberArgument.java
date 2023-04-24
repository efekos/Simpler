package me.efekos.simpler.commands.syntax;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class NumberArgument extends Argument {
    private final String holder;
    private final int min;
    private final int max;

    public NumberArgument(String holder, int min, int max) {
        this.holder = holder;
        this.min = min;
        this.max = max;
    }

    @Override
    public String getPlaceHolder() {
        return holder;
    }

    @Override
    public ArrayList<ArgumentResult> getList(Player player, String current) {
        return null;
    }

    @Override
    public ArgumentPriority getPriority() {
        return ArgumentPriority.REQUIRED;
    }

    @Override
    public boolean handleCorrection(String given) {
        if(Integer.parseInt(given)>-999999999){
            int i = Integer.parseInt(given);
            return i<=max && i>=min;
        }
        return false;
    }
}
