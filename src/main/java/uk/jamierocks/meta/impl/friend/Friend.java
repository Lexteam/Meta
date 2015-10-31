/*
 * Copyright (c) 2015, Jamie Mansfield <https://www.jamierocks.uk>
 * Copyright (c) 2015, Tom Drever <https://github.com/CharlesStewart>
 * Copyright (c) 2015, Ethan Riley <https://github.com/EthanRiley>
 *
 * All Rights Reserved.
 */
package uk.jamierocks.meta.impl.friend;

import java.lang.reflect.Type;
import java.util.Set;

public abstract class Friend {

    public Friend(){
        id = new Identity(this);
    }

    public void addFriend(Friend F){
        FriendsOf.add(F.getIdentity().getType());
    }

    public boolean isFriend(Friend F){
        return FriendsOf.contains(F.getIdentity().getType());
    }

    Identity getIdentity(){
        return  id;
    }

    static private Identity id;

    static private Set<String> FriendsOf;
}
