package me.efekos.simpler.commands.syntax;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Used for your custom arguments. Extends this class for any type of argument you want.
 */
public abstract class Argument {

    /**
     * @return The placeholder of this argument. Placeholder is usually a one-word string that represents what this argument should be. For example: `\<player\>` argument should be the name of an {@link org.bukkit.OfflinePlayer}. We are able to understand this, because that argument's placeholder is "player", meaning we should enter someone's name there.
     */
    abstract public String getPlaceHolder();

    /**
     * Generates a list of suggestions about this argument.
     * @param player Player who needs suggestions for this argument.
     * @param current Current string player entered so far.
     * @return List of the strings player will see.
     */
    abstract public List<String> getList(Player player, String current);

    /**
     * @return Priority of this argument. You can make your argument an optional or required argument using this.
     */
    abstract public ArgumentPriority getPriority();

    /**
     * Makes sure that the argument player wrote is valid.
     * @param given The string that someone wrote as a value for this argument
     * @return Is the given argument valid?
     */
    abstract public boolean handleCorrection(String given);

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     * @apiNote In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * The string output is not necessarily stable over time or across
     * JVM invocations.
     * @implSpec The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     */
    @Override
    public String toString() {
        String res = "";

        switch (getPriority()){
            case OPTIONAL:
                res = "["+getPlaceHolder()+"]";
                break;
            case REQUIRED:
                res = "<"+getPlaceHolder()+">";
                break;
        }
        return res;
    }
}
