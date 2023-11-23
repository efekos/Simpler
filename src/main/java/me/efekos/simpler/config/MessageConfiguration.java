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

package me.efekos.simpler.config;

import me.efekos.simpler.commands.syntax.ArgumentPriority;

public class MessageConfiguration {
    /**
     * No-Permission message. Appears when the player tried to execute a {@link me.efekos.simpler.commands.BaseCommand} or a {@link me.efekos.simpler.commands.SubCommand}, but he doesn't have the permission for it.
     */
    public final String NO_PERMISSION;
    /**
     * Usage message. Appears when the player used a command wrong according to its {@link me.efekos.simpler.commands.syntax.Syntax}.
     */
    public final String USAGE;
    /**
     * Only-Player message. Appears when the console tried to use a command that is player-only.
     */
    public final String ONLY_PLAYER;
    /**
     * A reason text that is provided by Simpler. Appears when the player didn't provide a {@link me.efekos.simpler.commands.syntax.ArgumentPriority#REQUIRED} argument.
     */
    public final String USAGE_REASON_REQUIRED;

    /**
     * Appears when the player provides an invalid value to {@link me.efekos.simpler.commands.syntax.impl.ListArgument}.
     */
    public final String LIST_ARG_NIL;
    /**
     * Appears when the player provides a number smaller than {@link Integer#MIN_VALUE} to {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument}.
     */
    public final String NUM_ARG_IMIN;
    /**
     * Appears when the player provides a number bigger than {@link Integer#MAX_VALUE} to {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument}.
     */
    public final String NUM_ARG_IMAX;
    /**
     * Appears when the player provides a number smaller than minimum value sett in {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument#IntegerArgument(String, ArgumentPriority, int, int)}.
     */
    public final String NUM_ARG_MIN;
    /**
     * Appears when the player provides a number bigger than maximum value sett in {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument#IntegerArgument(String, ArgumentPriority, int, int)}.
     */
    public final String NUM_ARG_MAX;
    /**
     * Appears when the player provides a NaN to a {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument}.
     */
    public final String NUM_ARG_NAN;
    /**
     * Appears when the player provides an invalid player name to {@link me.efekos.simpler.commands.syntax.impl.PlayerArgument}.
     */
    public final String PLR_ARG_NAP;
    /**
     * Appears when the player provides a string shorter than minimum length sett in {@link me.efekos.simpler.commands.syntax.impl.StringArgument#StringArgument(String, ArgumentPriority, int, int)}.
     */
    public final String STR_ARG_SHT;
    /**
     * Appears when the player provides a string longer than maximum length sett in {@link me.efekos.simpler.commands.syntax.impl.StringArgument#StringArgument(String, ArgumentPriority, int, int)}.
     */
    public final String STR_ARG_LNG;

    /**
     * Appears as the name of the button that leads the player to the next page of a {@link me.efekos.simpler.menu.PaginatedMenu}.
     */
    public final String PAGINATED_MENU_NEXT;
    /**
     * Appears as the name of the button that leads the player to the previous page of a {@link me.efekos.simpler.menu.PaginatedMenu}.
     */
    public final String PAGINATED_MENU_PREV;
    /**
     * Appears as the name of the item that indicates the current page in a {@link me.efekos.simpler.menu.PaginatedMenu}.
     */
    public final String PAGINATED_MENU_PAGE;

    /**
     * Constructs a new {@link MessageConfiguration} for your use. {@link MessageConfiguration.Builder} is more recommended though, due to it is more understandable than constructor.
     * @param NO_PERMISSION No-Permission message. Appears when the player tried to execute a {@link me.efekos.simpler.commands.BaseCommand} or a {@link me.efekos.simpler.commands.SubCommand}, but he doesn't have the permission for it.
     * @param USAGE Usage message. Appears when the player used a command wrong according to its {@link me.efekos.simpler.commands.syntax.Syntax}.
     * @param ONLY_PLAYER Only-Player message. Appears when the console tried to use a command that is player-only.
     * @param USAGE_REASON_REQUIRED A reason text that is provided by Simpler. Appears when the player didn't provide a {@link me.efekos.simpler.commands.syntax.ArgumentPriority#REQUIRED} argument.
     * @param LIST_ARG_NIL Appears when the player provides an invalid value to {@link me.efekos.simpler.commands.syntax.impl.ListArgument}.
     * @param NUM_ARG_IMIN Appears when the player provides a number smaller than {@link Integer#MIN_VALUE} to {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument}.
     * @param NUM_ARG_IMAX Appears when the player provides a number smaller than {@link Integer#MAX_VALUE} to {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument}.
     * @param NUM_ARG_MIN Appears when the player provides a number smaller than maximum value sett in {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument#IntegerArgument(String, ArgumentPriority, int, int)}.
     * @param NUM_ARG_MAX Appears when the player provides a number bigger than maximum value sett in {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument#IntegerArgument(String, ArgumentPriority, int, int)}.
     * @param NUM_ARG_NAN Appears when the player provides a NaN to a {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument}.
     * @param PLR_ARG_NAP Appears when the player provides an invalid player name to {@link me.efekos.simpler.commands.syntax.impl.PlayerArgument}.
     * @param STR_ARG_SHT Appears when the player provides a string shorter than minimum length sett in {@link me.efekos.simpler.commands.syntax.impl.StringArgument#StringArgument(String, ArgumentPriority, int, int)}.
     * @param STR_ARG_LNG Appears when the player provides a string longer than maximum length sett in {@link me.efekos.simpler.commands.syntax.impl.StringArgument#StringArgument(String, ArgumentPriority, int, int)}.
     */
    private MessageConfiguration(String NO_PERMISSION, String USAGE, String ONLY_PLAYER, String USAGE_REASON_REQUIRED, String LIST_ARG_NIL, String NUM_ARG_IMIN, String NUM_ARG_IMAX, String NUM_ARG_MIN, String NUM_ARG_MAX, String NUM_ARG_NAN, String PLR_ARG_NAP, String STR_ARG_SHT, String STR_ARG_LNG, String PAGINATED_MENU_NEXT, String PAGINATED_MENU_PREV, String PAGINATED_MENU_PAGE) {
        this.NO_PERMISSION = NO_PERMISSION;
        this.USAGE = USAGE;
        this.ONLY_PLAYER = ONLY_PLAYER;
        this.USAGE_REASON_REQUIRED = USAGE_REASON_REQUIRED;
        this.LIST_ARG_NIL = LIST_ARG_NIL;
        this.NUM_ARG_IMIN = NUM_ARG_IMIN;
        this.NUM_ARG_IMAX = NUM_ARG_IMAX;
        this.NUM_ARG_MIN = NUM_ARG_MIN;
        this.NUM_ARG_MAX = NUM_ARG_MAX;
        this.NUM_ARG_NAN = NUM_ARG_NAN;
        this.PLR_ARG_NAP = PLR_ARG_NAP;
        this.STR_ARG_SHT = STR_ARG_SHT;
        this.STR_ARG_LNG = STR_ARG_LNG;
        this.PAGINATED_MENU_NEXT = PAGINATED_MENU_NEXT;
        this.PAGINATED_MENU_PREV = PAGINATED_MENU_PREV;
        this.PAGINATED_MENU_PAGE = PAGINATED_MENU_PAGE;
    }

    public static class Builder {
        /**
         * No-Permission message. Appears when the player tried to execute a {@link me.efekos.simpler.commands.BaseCommand} or a {@link me.efekos.simpler.commands.SubCommand}, but he doesn't have the permission for it.
         */
        private String noPerm = "&cYou do not have permission to do that!";
        /**
         * Usage message. Appears when the player used a command wrong according to its {@link me.efekos.simpler.commands.syntax.Syntax}.
         */
        private String usage = "&cInvalid usage: %reason%. Use &b%usage%";
        /**
         * Only-Player message. Appears when the console tried to use a command that is player-only.
         */
        private String onlyPlayer = "&cThis command only can be used by a player!";
        /**
         * A reason text that is provided by Simpler. Appears when the player didn't provide a {@link me.efekos.simpler.commands.syntax.ArgumentPriority#REQUIRED} argument.
         */
        private String usageRequiredReason = "&c%argument% is required";

        /**
         * Appears when the player provides an invalid value to {@link me.efekos.simpler.commands.syntax.impl.ListArgument}.
         */
        private String listArgumentNotValid = "%given% is an invalid value";
        /**
         * Appears when the player provides a number smaller than {@link Integer#MIN_VALUE} to {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument}.
         */
        private String numberArgumentIntMin = "%given% is smaller than minimum limit (-2147483648)";
        /**
         * Appears when the player provides a number bigger than {@link Integer#MAX_VALUE} to {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument}.
         */
        private String numberArgumentIntMax = "%given% is bigger than maximum limit (-2147483647)";
        /**
         * Appears when the player provides a number smaller than minimum value sett in {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument#IntegerArgument(String, ArgumentPriority, int, int)}.
         */
        private String numberArgumentMin = "%given% is smaller than %minValue%";
        /**
         * Appears when the player provides a number bigger than maximum value sett in {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument#IntegerArgument(String, ArgumentPriority, int, int)}.
         */
        private String numberArgumentMax = "%given% is bigger than %maxValue%";
        /**
         * Appears when the player provides a NaN to a {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument}.
         */
        private String numberArgumentNaN = "%given% is not a number";
        /**
         * Appears when the player provides an invalid player name to {@link me.efekos.simpler.commands.syntax.impl.PlayerArgument}.
         */
        private String playerArgumentNotPlayer = "%given% is not a player";
        /**
         * Appears when the player provides a string shorter than minimum length sett in {@link me.efekos.simpler.commands.syntax.impl.StringArgument#StringArgument(String, ArgumentPriority, int, int)}.
         */
        private String stringArgumentShort = "%given% is too short (minimum %min% characters)";
        /**
         * Appears when the player provides a string longer than maximum length sett in {@link me.efekos.simpler.commands.syntax.impl.StringArgument#StringArgument(String, ArgumentPriority, int, int)}.
         */
        private String stringArgumentLong = "%given% is too long (maximum %max% characters)";
        /**
         * Appears as the name of the button that leads the player to the next page of a {@link me.efekos.simpler.menu.PaginatedMenu}.
         */
        private String paginatedMenuNext = "&eNext";
        /**
         * Appears as the name of the button that leads the player to the previous page of a {@link me.efekos.simpler.menu.PaginatedMenu}.
         */
        private String paginatedMenuPrev = "&ePrev";
        /**
         * Appears as the name of the item that indicates the current page in a {@link me.efekos.simpler.menu.PaginatedMenu}.
         */
        private String paginatedMenuPage = "&ePage %page% / %max%";

        /**
         * Changes the no-permission message. Which is a message that appears when the player tried to execute a {@link me.efekos.simpler.commands.BaseCommand} or a {@link me.efekos.simpler.commands.SubCommand}, but he doesn't have the permission for it.
         * @param noPerm Your No-Permission message to replace with default one.
         * @return Itself after changing the no-permission message.
         */
        public Builder noPermission(String noPerm) {
            this.noPerm = noPerm;
            return this;
        }

        /**
         * Changes the usage message, which is a message that appears when the player used a command wrong according to its {@link me.efekos.simpler.commands.syntax.Syntax}.
         * @param usage Your Usage message to replace with default one.
         * @return Itself after changing the usage message.
         */
        public Builder usage(String usage) {
            this.usage = usage;
            return this;
        }

        /**
         * Changes the only-player message, which is a message that appears when the console tried to use a command that is player-only.
         * @param onlyPlayer Your Only-Player message to replace with default one.
         * @return Itself after changing the only-player message.
         */
        public Builder onlyPlayer(String onlyPlayer) {
            this.onlyPlayer = onlyPlayer;
            return this;
        }

        /**
         * Changes the 'Next' shown at the buttons of {@link me.efekos.simpler.menu.PaginatedMenu}s.
         * @param paginatedMenuNext Your paginated menu's 'Next' button message to replace with the default one.
         * @return Itself after changing the paginated menu text.
         */
        public Builder paginatedMenuNext(String paginatedMenuNext) {
            this.paginatedMenuNext = paginatedMenuNext;
            return this;
        }

        /**
         * Changes the 'Prev' shown at the buttons of {@link me.efekos.simpler.menu.PaginatedMenu}s.
         * @param paginatedMenuPrev Your paginated menu's 'Prev' button message to replace with the default one.
         * @return Itself after changing the paginated menu text.
         */
        public Builder paginatedMenuPrev(String paginatedMenuPrev) {
            this.paginatedMenuPrev = paginatedMenuPrev;
            return this;
        }

        /**
         * Changes the 'Page 1/?' shown at the buttons of {@link me.efekos.simpler.menu.PaginatedMenu}s.
         * @param paginatedMenuPage Your paginated menu's page message to replace with the default one. %page% is the
         *                          current page, %max% is the amount of pages.
         * @return Itself after changing the paginated menu text.
         */
        public Builder paginatedMenuPage(String paginatedMenuPage) {
            this.paginatedMenuPage = paginatedMenuPage;
            return this;
        }

        /**
         * Changes the required message, which is a message that appears when the player didn't provide a {@link me.efekos.simpler.commands.syntax.ArgumentPriority#REQUIRED} argument.
         * @param required new required message.
         * @return Itself after changing the required message.
         */
        public Builder required(String required){
            this.usageRequiredReason = required;
            return this;
        }

        /**
         * Appears when a {@link me.efekos.simpler.commands.syntax.impl.ListArgument} got an invalid value.
         * @param listArgumentNotValid New message.
         * @return itself for continuing to build.
         */
        public Builder listArgumentNotValid(String listArgumentNotValid) {
            this.listArgumentNotValid = listArgumentNotValid;
            return this;
        }

        /**
         * Appears when a {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument} got a value smaller than {@link Integer#MIN_VALUE}.
         * @param numberArgumentIntMin New message
         * @return itself for continuing to build.
         */
        public Builder numberArgumentIntMin(String numberArgumentIntMin) {
            this.numberArgumentIntMin = numberArgumentIntMin;
            return this;
        }

        /**
         * Appears when a {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument} got a value bigger than {@link Integer#MAX_VALUE}.
         * @param numberArgumentIntMax New message.
         * @return itself for continuing to build.
         */
        public Builder numberArgumentIntMax(String numberArgumentIntMax) {
            this.numberArgumentIntMax = numberArgumentIntMax;
            return this;
        }

        /**
         * Appears when a {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument} got a value smaller than its min.
         * @param numberArgumentMin New message.
         * @return itself for continuing to build.
         */
        public Builder numberArgumentMin(String numberArgumentMin) {
            this.numberArgumentMin = numberArgumentMin;
            return this;
        }

        /**
         * Appears when a {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument} got a value bigger than its max.
         * @param numberArgumentMax New message.
         * @return itself for continuing to build.
         */
        public Builder numberArgumentMax(String numberArgumentMax) {
            this.numberArgumentMax = numberArgumentMax;
            return this;
        }

        /**
         * Appears when a {@link me.efekos.simpler.commands.syntax.impl.IntegerArgument} got a value that isn't an {@link Integer}.
         * @param numberArgumentNaN New message.
         * @return itself for continuing to build.
         */
        public Builder numberArgumentNaN(String numberArgumentNaN) {
            this.numberArgumentNaN = numberArgumentNaN;
            return this;
        }

        /**
         * Appears when a {@link me.efekos.simpler.commands.syntax.impl.PlayerArgument} got an invalid player name.
         * @param playerArgumentNotPlayer New message.
         * @return itself for continuing to build.
         */
        public Builder playerArgumentNotPlayer(String playerArgumentNotPlayer) {
            this.playerArgumentNotPlayer = playerArgumentNotPlayer;
            return this;
        }

        /**
         * Appears when a {@link me.efekos.simpler.commands.syntax.impl.StringArgument} got a value shorter than it's min length.
         * @param stringArgumentShort New message.
         * @return itself for continuing to build.
         */
        public Builder stringArgumentShort(String stringArgumentShort) {
            this.stringArgumentShort = stringArgumentShort;
            return this;
        }

        /**
         * Appears when a {@link me.efekos.simpler.commands.syntax.impl.StringArgument} got a value longer than it's max length.
         * @param stringArgumentLong New message.
         * @return itself for continuing to build.
         */
        public Builder stringArgumentLong(String stringArgumentLong) {
            this.stringArgumentLong = stringArgumentLong;
            return this;
        }

        /**
         * Builds a {@link MessageConfiguration} using this builder.
         * @return {@link MessageConfiguration} built.
         */
        public MessageConfiguration build(){
            return new MessageConfiguration(noPerm,usage,onlyPlayer,usageRequiredReason,listArgumentNotValid,numberArgumentIntMin,numberArgumentIntMax,numberArgumentMin,numberArgumentMax,numberArgumentNaN,playerArgumentNotPlayer,stringArgumentShort,stringArgumentLong,paginatedMenuNext,paginatedMenuPrev,paginatedMenuPage);
        }
    }
}
