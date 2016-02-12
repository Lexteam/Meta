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

import xyz.lexteam.meta.api.MetaContainer;
import xyz.lexteam.meta.api.key.Key;
import xyz.lexteam.meta.api.value.Value;
import xyz.lexteam.meta.impl.LexMetaContainer;

public abstract class AbstractSingularMeta<T> extends AbstractMeta {

    private final Key<Value<T>> key;
    private T value;

    public AbstractSingularMeta(Key<Value<T>> key, T value) {
        this.key = key;
        this.value = value;
        this.registerGettersAndSetters();
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    protected void registerGettersAndSetters() {
        this.registerGetter(this.key, AbstractSingularMeta.this::getValue);
        this.registerSetter(this.key, AbstractSingularMeta.this::setValue);
    }

    @Override
    public MetaContainer toContainer() {
        return new LexMetaContainer()
                .set(this.key.getQuery(), this.value);
    }
}
