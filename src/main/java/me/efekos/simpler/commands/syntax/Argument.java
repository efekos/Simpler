package me.efekos.simpler.commands.syntax;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class Argument {

    abstract public String getPlaceHolder();

    abstract public ArrayList<ArgumentResult> getList(Player player, String current);

    abstract public ArgumentPriority getPriority();

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
