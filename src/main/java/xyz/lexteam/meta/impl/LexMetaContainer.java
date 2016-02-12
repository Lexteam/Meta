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
package xyz.lexteam.meta.impl;

import com.google.common.collect.Maps;
import xyz.lexteam.meta.api.MetaContainer;
import xyz.lexteam.meta.api.MetaQuery;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LexMetaContainer implements MetaContainer {

    private Map<String, Object> queries = Maps.newHashMap();

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<MetaQuery> getQueries() {
        return this.queries.keySet().stream().map(MetaQuery::of).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<MetaQuery, Object> getValues() {
        Map<MetaQuery, Object> values = Maps.newHashMap();
        for (MetaQuery query : this.getQueries()) {
            values.put(query, this.queries.get(query));
        }
        return values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(MetaQuery query) {
        return this.queries.containsKey(query.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Object> get(MetaQuery query) {
        if (this.contains(query)) {
            return Optional.of(this.queries.get(query.getName()));
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MetaContainer set(MetaQuery query, Object value) {
        this.queries.put(query.getName(), value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(MetaQuery query) {
        this.queries.remove(query.getName());
    }
}
