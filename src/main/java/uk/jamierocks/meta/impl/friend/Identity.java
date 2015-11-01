package uk.jamierocks.meta.impl.friend;

/**
 * use
 */
class Identity {

    /**
     * @param friend the class which identity will identify
     */
    Identity(Friend friend){
        type  = friend.getClass().getName();
    }

    private String type;

    protected String getType(){return type;}

}