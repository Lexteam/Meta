/*
 * This file is part of MetaAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015-2016, Lexteam <http://www.lexteam.xyz/>
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
package xyz.lexteam.meta.api.value;

import com.google.common.collect.Maps;
import xyz.lexteam.meta.api.key.Key;

import java.util.Map;
import java.util.Optional;

public final class ValueRegistry {

    private static Map<Key, ValueProcessor> processors = Maps.newHashMap();

    public static void registerProcessor(ValueProcessor processor) {
        processors.put(processor.getKey(), processor);
    }

    public static <T> Optional<T> get(ValueHolder container, Key<Value<T>> key) {
        return processors.get(key).getValueFromContainer(container);
    }

    public static <T> boolean supports(ValueHolder container, Key<Value<T>> key) {
        return processors.get(key).supports(container);
    }

    public static <T> boolean offer(ValueHolder container, Key<Value<T>> key, T value) {
        return processors.get(key).offer(container, value);
    }
}
