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
package uk.jamierocks.meta.api.builder;

import com.google.common.collect.Maps;
import uk.jamierocks.meta.api.manipulator.MetaManipulator;

import java.util.Map;
import java.util.Optional;

/**
 * Static access to builders.
 *
 * @author Jamie Mansfield
 */
public final class Builders {

    private static final Map<Class, MetaManipulatorBuilder> builders = Maps.newHashMap();

    public static void registerBuilder(MetaManipulatorBuilder builder) {
        builders.put(builder.getType(), builder);
    }

    /**
     * Gets the manipulator builder for said type.
     *
     * @param type the type.
     * @param <T> the type also.
     * @return the builder, as an {@link Optional}.
     */
    public static <T extends MetaManipulator> Optional<MetaManipulatorBuilder<T>> getBuilder(Class<T> type) {
        if (builders.containsKey(type)) {
            return Optional.of(builders.get(type));
        }
        return Optional.empty();
    }
}
