package me.efekos.simpler.commands.syntax;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a syntax for a command. Every {@link Argument} is being used at tab completion and handling argument validness. A {@link Syntax} can be used to generate usage for the command. Required for {@link me.efekos.simpler.commands.BaseCommand} and {@link me.efekos.simpler.commands.SubCommand}s.
 */
public class Syntax {
    /**
     * A list of the arguments list syntax will have.
     */
    private List<Argument> arguments;

    public Syntax() {
        arguments = new ArrayList<>();
    }

    /**
     * @return A lst of the arguments this syntax has.
     */
    public List<Argument> getArguments() {
        return arguments;
    }

    /**
     * Adds an argument to the list.
     * @param arg Argument to add
     * @return The {@link Syntax} itself, making it able to repeat this method for multiple arguments.
     */
    public Syntax withArgument(Argument arg){
        arguments.add(arg);
        return this;
    }
}
