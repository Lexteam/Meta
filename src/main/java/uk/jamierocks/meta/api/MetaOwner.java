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
import uk.jamierocks.meta.api.value.ValueManager;

import java.util.Optional;

/**
 * Represents a meta owner.
 *
 * @author Jamie Mansfield
 */
public interface MetaOwner {

    /**
     * Gets a value from it's key.
     *
     * @param key the specified key.
     * @param <T> the value type.
     * @return the value.
     */
    default <T> Optional<T> get(Key<Value<T>> key) {
        return ValueManager.get(this, key);
    }

    /**
     * Gets a value from it's key, and if it doesn't exist the gets null.
     *
     * @param key the specified key.
     * @param <T> the value type.
     * @return the value.
     */
    default <T> T getOrNull(Key<Value<T>> key) {
        return this.get(key).orElse(null);
    }

    /**
     * Checks to see if this owner supports that key.
     *
     * @param key the specified key.
     * @param <T> the value type.
     * @return the value.
     */
    default <T> boolean supports(Key<Value<T>> key) {
        return ValueManager.supports(this, key);
    }

    /**
     * Offers the given value to the value processor.
     *
     * @param key the key of which you are offering to.
     * @param value the value you wish to set.
     * @param <T> the value type.
     * @return {@code true} if the value was set.
     */
    default <T> boolean offer(Key<Value<T>> key, T value) {
        return ValueManager.offer(this, key, value);
    }

    /**
     * Gets the requested meta.
     *
     * @param clazz the clazz of the required type.
     * @param <T> the type.
     * @return the meta.
     */
    default <T extends MetaManipulator> T obtainMeta(Class<T> clazz) {
        return MetaManager.get(this, clazz);
    }

    /**
     * Checks to see if this owner supports that meta type.
     *
     * @param clazz the clazz of the required type.
     * @param <T> the type.
     * @return {@code true} if it supports that meta.
     */
    default <T extends MetaManipulator> boolean supportsMeta(Class<T> clazz) {
        return MetaManager.supports(this, clazz);
    }

    default <T extends MetaManipulator> boolean applyMeta(T meta) {
        return MetaManager.apply(this, meta);
    }
}
