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
package uk.jamierocks.meta.api.manipulator;

import uk.jamierocks.meta.api.MetaContainer;
import uk.jamierocks.meta.api.MetaHolder;
import uk.jamierocks.meta.api.MetaManager;
import uk.jamierocks.meta.api.value.ValueHolder;

/**
 * Represents meta a {@link MetaHolder} can hold.
 *
 * @author Jamie Mansfield
 */
public interface MetaManipulator extends ValueHolder {

    /**
     * Gets a {@link MetaContainer} representation of this manipulator.
     *
     * @return a {@link MetaContainer}.
     */
    MetaContainer toContainer();

    /**
     * Applies the meta from the given container.
     *
     * @param container the given container.
     */
    default void applyContainer(MetaContainer container) {
        MetaManager.apply(container, this);
    }
}
