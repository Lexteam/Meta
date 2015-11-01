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

package uk.jamierocks.meta.impl.friend;
import java.util.Set;

public abstract class Friend {

    public Friend(){
        id = new Identity(this);
    }

    /**
     * Adds a friend to the class.
     * @param F friend to add
     */
    public void addFriend(Friend F){
        FriendsOf.add(F.getIdentity().getType());
    }

    /**
     * @param F Friend to check.
     * @returns true if F is a friend to this.
     */
    public boolean isFriend(Friend F){
        return FriendsOf.contains(F.getIdentity().getType());
    }


    /**
     * @return the identity of the class.
     */

    Identity getIdentity(){
        return  id;
    }

    static private Identity id;

    static private Set<String> friendsOf;
}
