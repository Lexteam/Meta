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
package xyz.lexteam.meta.impl.manipulator;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import xyz.lexteam.meta.api.key.Key;
import xyz.lexteam.meta.api.manipulator.MetaManipulator;
import xyz.lexteam.meta.api.value.Value;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractMeta implements MetaManipulator {

    private final Map<Key<?>, Consumer<Object>> keySetterMap = Maps.newHashMap();
    private final Map<Key<?>, Supplier<?>> keyGetterMap = Maps.newHashMap();

    protected abstract void registerGettersAndSetters();

    protected final void registerGetter(Key<?> key, Supplier<?> function) {
        this.keyGetterMap.put(Preconditions.checkNotNull(key), Preconditions.checkNotNull(function));
    }

    protected final <T> void registerSetter(Key<Value<T>> key, Consumer<T> function) {
        this.keySetterMap.put(Preconditions.checkNotNull(key), Preconditions.checkNotNull((Consumer) function));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Optional<T> get(Key<Value<T>> key) {
        if (!supports(key)) {
            return Optional.empty();
        }
        return Optional.of((T) this.keyGetterMap.get(key).get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> boolean supports(Key<Value<T>> key) {
        return this.keySetterMap.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> boolean offer(Key<Value<T>> key, T value) {
        if (this.supports(key)) {
            this.keySetterMap.get(key).accept(value);
            return true;
        }
        return false;
    }
}
