package uk.jamierocks.meta.impl.friend;

import uk.jamierocks.meta.api.value.Value;

public abstract class FriendValue<V> extends Friend implements Value<V> {

    public V get(Friend F) throws IllegalArgumentException {
        if(isFriend(F)) {
            this.get();
        }
        else{
            throw new IllegalArgumentException("not a friend of "+this.getClass().getName());
        }
        return null;
    }

}
