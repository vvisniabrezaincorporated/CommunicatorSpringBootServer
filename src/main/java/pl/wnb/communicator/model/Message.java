package pl.wnb.communicator.model;

public class Message {

    private String userID;
    private String fromUserID;
    private String message;



    public Message() {
    }

    public Message(String userID, String fromUserID, String message) {
        this.userID = userID;
        this.fromUserID = fromUserID;
        this.message = message;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(String fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
