package me.efekos.simpler.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to separate the {@link org.bukkit.event.player.PlayerInteractEvent}s on a {@link me.efekos.simpler.items.CustomItem}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LeftClick {
}
