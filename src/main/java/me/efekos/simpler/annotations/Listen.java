/*
 * Copyright (c) 2023 efekos
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
 *              <li>{@link org.bukkit.event.player.PlayerInteractEvent}</li>
 *              <li>{@link org.bukkit.event.entity.EntityPickupItemEvent}</li>
 *              <li>{@link org.bukkit.event.player.PlayerDropItemEvent}</li>
 *              <li>{@link org.bukkit.event.player.PlayerItemBreakEvent}</li>
 *              <li>{@link org.bukkit.event.player.PlayerItemDamageEvent}</li>
 *              <li>{@link org.bukkit.event.player.PlayerItemConsumeEvent}</li>
 *              <li>{@link org.bukkit.event.player.PlayerItemMendEvent}</li>
 *              <li>{@link org.bukkit.event.player.PlayerItemHeldEvent}</li>
 *          </ul>
 *     </li>
 *     <li>Your method must be public</li>
 *     <li>Your method must return void</li>
 * </ul>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Listen { }
