package me.efekos.simpler.commands.syntax;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class StringArgument extends Argument{
    private String holder;
    private ArgumentPriority priority;
    private int minLength;
    private int maxLength;

    public StringArgument(String holder, ArgumentPriority priority, int minLength, int maxLength) {
        this.holder = holder;
        this.priority = priority;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public String getPlaceHolder() {
        return holder;
    }

    @Override
    public ArrayList<ArgumentResult> getList(Player player, String current) {
        return new ArrayList<>();
    }

    @Override
    public ArgumentPriority getPriority() {
        return priority;
    }

    @Override
    public boolean handleCorrection(String given) {
        return given.length()>minLength&&given.length()<maxLength;
    }
}
