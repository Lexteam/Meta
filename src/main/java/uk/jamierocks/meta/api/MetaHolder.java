/*
 * This file is part of MetaAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015, Jamie Mansfield <https://github.com/jamierocks>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.jamierocks.meta.api;

import uk.jamierocks.meta.api.key.Key;
import uk.jamierocks.meta.api.manipulator.MetaManipulator;
import uk.jamierocks.meta.api.value.Value;
import uk.jamierocks.meta.api.value.ValueHolder;
import uk.jamierocks.meta.api.value.ValueRegistry;

import java.util.Optional;

/**
 * Represents an object which holds meta.
 *
 * @author Jamie Mansfield
 */
public interface MetaHolder extends ValueHolder {

    /**
     * {@inheritDoc}
     */
    default <T> Optional<T> get(Key<Value<T>> key) {
        return ValueRegistry.get(this, key);
    }

    /**
     * {@inheritDoc}
     */
    default <T> boolean supports(Key<Value<T>> key) {
        return ValueRegistry.supports(this, key);
    }

    /**
     * {@inheritDoc}
     */
    default <T> boolean offer(Key<Value<T>> key, T value) {
        return ValueRegistry.offer(this, key, value);
    }

    /**
     * Obtains the requested meta.
     *
     * @param clazz the clazz of the required type.
     * @param <T> the type.
     * @return the meta.
     */
    default <T extends MetaManipulator> T obtain(Class<T> clazz) {
        return MetaRegistry.get(this, clazz);
    }

    /**
     * Checks to see if this owner supports that meta type.
     *
     * @param clazz the clazz of the required type.
     * @param <T> the type.
     * @return {@code true} if it supports that meta.
     */
    default <T extends MetaManipulator> boolean supports(Class<T> clazz) {
        return MetaRegistry.supports(this, clazz);
    }

    default <T extends MetaManipulator> boolean apply(T meta) {
        return MetaRegistry.apply(this, meta);
    }
}
