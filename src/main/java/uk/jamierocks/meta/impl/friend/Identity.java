package uk.jamierocks.meta.impl.friend;

/**
 * Created by ethan on 31/10/15.
 */

class Identity {

    Identity(Friend friend){
        type  = friend.getClass().getName();
    }

    private String type;

    protected String getType(){return type;}

}