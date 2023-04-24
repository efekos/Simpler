package me.efekos.simpler.commands.syntax;

import java.util.ArrayList;

public class Syntax {
    private ArrayList<Argument> arguments;

    public Syntax() {}

    public ArrayList<Argument> getArguments() {
        return arguments;
    }

    public Syntax withArgument(Argument arg){
        arguments.add(arg);
        return this;
    }
}
