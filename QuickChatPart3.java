package za.co.quickchat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QuickChatPart3 {
    // ArrayList is used because the number of messages can change at runtime.
    private final List<Message> sentMessages = new ArrayList<>();
    private final List<Message> disregardedMessages = new ArrayList<>();
    private final List<Message> storedMessages = new ArrayList<>();
    private final List<String> messageHashes = new ArrayList<>();
    private final List<String> messageIDs = new ArrayList<>();

    public void addMessage(Message message, String status) {
        if (message == null || status == null) {
            throw new IllegalArgumentException("Message and status are required.");
        }

        switch (status.trim().toLowerCase()) {
            case "sent" -> sentMessages.add(message);
            case "stored" -> storedMessages.add(message);
            case "disregard", "disregarded" -> disregardedMessages.add(message);
            default -> throw new IllegalArgumentException("Unknown message status: " + status);
        }

        if (!messageIDs.contains(message.getMessageID())) {
            messageIDs.add(message.getMessageID());
        }
        if (!messageHashes.contains(message.getMessageHash())) {
            messageHashes.add(message.getMessageHash());
        }
    }

    public void loadStoredMessages(Path jsonFile) throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Message>>() {}.getType();

        try (Reader reader = Files.newBufferedReader(jsonFile)) {
            List<Message> loaded = gson.fromJson(reader, listType);
            storedMessages.clear();

            if (loaded != null) {
                for (Message message : loaded) {
                    storedMessages.add(message);
                    if (!messageIDs.contains(message.getMessageID())) {
                        messageIDs.add(message.getMessageID());
                    }
                    if (!messageHashes.contains(message.getMessageHash())) {
                        messageHashes.add(message.getMessageHash());
                    }
                }
            }
        }
    }

    public List<String> displaySentSenderAndRecipient() {
        List<String> details = new ArrayList<>();
        for (Message message : sentMessages) {
            details.add("Sender: Developer | Recipient: " + message.getRecipient());
        }
        return details;
    }

    // The assignment's expected longest-message test includes messages 1–4,
    // including stored and disregarded messages.
    public String getLongestMessage() {
        String longest = "";
        for (Message message : getAllMessages()) {
            if (message.getMessage() != null && message.getMessage().length() > longest.length()) {
                longest = message.getMessage();
            }
        }
        return longest;
    }

    public Message searchMessageID(String id) {
        for (Message message : getAllMessages()) {
            if (message.getMessageID().equals(id)) {
                return message;
            }
        }
        return null;
    }

    public List<Message> searchRecipient(String recipient) {
        List<Message> matches = new ArrayList<>();
        for (Message message : sentMessages) {
            if (message.getRecipient().equals(recipient)) matches.add(message);
        }
        for (Message message : storedMessages) {
            if (message.getRecipient().equals(recipient)) matches.add(message);
        }
        return matches;
    }

    public boolean deleteMessageByHash(String hash) {
        for (Message message : new ArrayList<>(storedMessages)) {
            if (message.getMessageHash().equals(hash)) {
                storedMessages.remove(message);
                messageHashes.remove(hash);
                messageIDs.remove(message.getMessageID());
                return true;
            }
        }
        return false;
    }

    public String displaySentMessageReport() {
        StringBuilder report = new StringBuilder("===== SENT MESSAGE REPORT =====\n");
        for (Message message : sentMessages) {
            report.append("Message Hash: ").append(message.getMessageHash()).append('\n');
            report.append("Recipient: ").append(message.getRecipient()).append('\n');
            report.append("Message: ").append(message.getMessage()).append('\n');
            report.append("------------------------------\n");
        }
        return report.toString();
    }

    public void populateAssessmentTestData() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();

        addMessage(new Message("0000000001", "+27834557896",
                "Did you get the cake?", "01:1:DIDCAKE"), "sent");
        addMessage(new Message("0000000002", "+27838884567",
                "Where are you? You are late! I have asked you to be on time.",
                "02:2:WHERE TIME"), "stored");
        addMessage(new Message("0000000003", "+27834484567",
                "Yohoooo, I am at your gate.", "03:3:YOHO GATE"), "disregard");
        addMessage(new Message("0000000004", "0838884567",
                "It is dinner time!", "04:4:IT TIME"), "sent");
        addMessage(new Message("0000000005", "+27838884567",
                "Ok, I am leaving without you.", "05:5:OK YOU"), "stored");
    }

    private List<Message> getAllMessages() {
        List<Message> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);
        return all;
    }

    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getDisregardedMessages() { return disregardedMessages; }
    public List<Message> getStoredMessages() { return storedMessages; }
    public List<String> getMessageHashes() { return messageHashes; }
    public List<String> getMessageIDs() { return messageIDs; }
}
