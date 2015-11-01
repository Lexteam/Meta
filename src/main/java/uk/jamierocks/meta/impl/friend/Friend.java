
package uk.jamierocks.meta.impl.friend;
import java.util.Set;

public abstract class Friend {

    public Friend(){
        id = new Identity(this);
    }

    /**
     * adds a friend to the class
     * @param F friend to add
     */
    public void addFriend(Friend F){
        FriendsOf.add(F.getIdentity().getType());
    }

    /**
     * @param F Friend to check
     * @returns true if F is a friend to this.
     */
    public boolean isFriend(Friend F){
        return FriendsOf.contains(F.getIdentity().getType());
    }


    /**
     * @return the identity of the class
     */

    Identity getIdentity(){
        return  id;
    }

    static private Identity id;

    static private Set<String> friendsOf;
}
