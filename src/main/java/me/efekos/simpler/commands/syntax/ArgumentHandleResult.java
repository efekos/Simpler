/*
 * Copyright (c) 2024 efekos
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

package me.efekos.simpler.commands.syntax;

import javax.annotation.Nullable;

/**
 * Represents a result you will probably get from {@link Argument#handleCorrection(String)}. Results have a basic value describing their succession and a reason for their failure. Well, if they had any.
 */
public class ArgumentHandleResult {
    /**
     * Becomes true if the result is success, leading the executor to next argument/command execution
     */
    private final boolean pass;
    /**
     * If {@link #pass} is false, this is guaranteed to be not null. A reason for a failure of the result.
     */
    @Nullable
    private final String reason;

    /**
     * Returns {@code true} if the command executor passes (gave a correct argument for) whatever you get this result from.
     *
     * @return {@code true} if this result is passed. {@code false} otherwise
     */
    public boolean isPassed() {
        return pass;
    }

    /**
     * Returns the reason of why this result does not pass, if it doesn't.
     *
     * @return A reason if this result didn't passed.
     */
    public @org.jetbrains.annotations.Nullable String getReason() {
        return reason;
    }

    /**
     * Returns {@code true} if this result has a reason. {@code false} otherwise.
     *
     * @return Whether this handle result has a reason (probably for failing)
     */
    public boolean hasReason() {
        return reason != null;
    }

    /**
     * Private constructor.
     *
     * @param pass   Is this result passed?
     * @param reason Reason of the failure on this result.
     */
    private ArgumentHandleResult(boolean pass, @org.jetbrains.annotations.Nullable String reason) {
        this.pass = pass;
        this.reason = reason;
    }

    /**
     * Constructs a new {@link ArgumentHandleResult}, leading to success.
     *
     * @return An instance of a {@link ArgumentHandleResult} with {@link #isPassed()} being {@code true}.
     */
    public static ArgumentHandleResult success() {
        return new ArgumentHandleResult(true, null);
    }

    /**
     * Constructs a new {@link ArgumentHandleResult}, leading to failure with the reason given.
     *
     * @param reason A reason on the failure that this result will cause.
     * @return An instance of a {@link ArgumentHandleResult} with a reason and {@link #isPassed()} being {@code false}
     */
    public static ArgumentHandleResult fail(String reason) {
        return new ArgumentHandleResult(false, reason);
    }
}