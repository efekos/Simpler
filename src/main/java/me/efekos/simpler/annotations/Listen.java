package me.efekos.simpler.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to tell Simple that this method should be called when something about the {@link me.efekos.simpler.items.CustomItem} happens.<br>
 * Every method annotated with {@link Listen} must follow the instructions below:
 * <ul>
 *     <li>
 *         Your method must have only one argument. It can be one of the types below:
 *          <ul>
 *              <li>EntityItemPickupEvent</li>
 *              <li>PlayerInteractEvent</li>
 *              <li>PlayerDropItemEvent</li>
 *              <li>PlayerItemBreakEvent</li>
 *              <li>PlayerItemDamageEvent</li>
 *              <li>PlayerItemMendEvent</li>
 *              <li>PlayerItemConsumeEvent</li>
 *              <li>PlayerItemHeldEvent</li>
 *          </ul>
 *     </li>
 *     <li>Your method must be public</li>
 *     <li>Your method must return void</li>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Listen { }
