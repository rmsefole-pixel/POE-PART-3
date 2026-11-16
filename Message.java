package za.co.quickchat;

public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;

    public Message() {
        // Required for straightforward JSON deserialisation.
    }

    public Message(String messageID, String recipient, String message, String messageHash) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = messageHash;
    }

    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getMessageHash() { return messageHash; }
}
