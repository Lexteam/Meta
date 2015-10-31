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

import uk.jamierocks.meta.api.manipulator.MetaManipulator;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Represents an object, that holds all the data in a {@link MetaManipulator}.
 *
 * @author Jamie Mansfield
 */
public interface MetaContainer {

    /**
     * Gets all the queries associated with this container.
     *
     * @return all the queries.
     */
    Set<MetaQuery> getQueries();

    /**
     * Gets all the values.
     *
     * @return all the values.
     */
    Map<MetaQuery, Object> getValues();

    /**
     * Checks if this container contains the given query.
     *
     * @param query the given query.
     * @return {@code true} if this container contains the given query, {@code false} otherwise.
     */
    boolean contains(MetaQuery query);

    /**
     * Gets the requested data from the given query.
     *
     * @param query the given query.
     * @return an {@link Optional} representation of the requested data.
     */
    Optional<Object> get(MetaQuery query);

    default <T> Optional<T> getType(MetaQuery query, Class<T> type) {
        Optional<Object> value = this.get(query);

        if (!value.isPresent()) {
            return Optional.empty();
        }

        if (value.get().getClass() != type) {
            return Optional.empty();
        }

        return Optional.of((T) value.get());
    }

    default Optional<String> getString(MetaQuery query) {
        return this.getType(query, String.class);
    }

    default Optional<Boolean> getBoolean(MetaQuery query) {
        return this.getType(query, Boolean.class);
    }

    default Optional<Integer> getInteger(MetaQuery query) {
        return this.getType(query, Integer.class);
    }

    default Optional<Double> getDouble(MetaQuery query) {
        return this.getType(query, Double.class);
    }

    default Optional<Long> getLong(MetaQuery query) {
        return this.getType(query, Long.class);
    }

    default Optional<Short> getShort(MetaQuery query) {
        return this.getType(query, Short.class);
    }

    MetaContainer set(MetaQuery query, Object value);

    void remove(MetaQuery query);
}
