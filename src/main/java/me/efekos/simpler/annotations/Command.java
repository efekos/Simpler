package me.efekos.simpler.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
/**
 Used to give information about a command class. A {@link me.efekos.simpler.commands.BaseCommand},{@link me.efekos.simpler.commands.CoreCommand} and {@link me.efekos.simpler.commands.SubCommand} requires {@link Command} annotation to be used.
 */
public @interface Command {
    /**
     * @return Name of this command
     */
    String name();

    /**
     * @return Description of this command
     */
    String description();

    /**
     * @return Permission required from player to use this command. If not specified, every player can use this command without any permission
     */
    String permission() default "";

    /**
     * @return Is this command player only?
     * If true, the console can't use this command. If false, console can use this command too
     */
    boolean playerOnly() default false;
}
