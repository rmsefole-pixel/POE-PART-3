package za.co.quickchat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class QuickChatPart3Test {
    private QuickChatPart3 app;

    @BeforeEach
    void setUp() {
        app = new QuickChatPart3();
        app.populateAssessmentTestData();
    }

    @Test
    void sentMessagesArrayIsCorrectlyPopulated() {
        assertEquals(2, app.getSentMessages().size());
        assertEquals("Did you get the cake?", app.getSentMessages().get(0).getMessage());
        assertEquals("It is dinner time!", app.getSentMessages().get(1).getMessage());
    }

    @Test
    void longestMessageIsReturned() {
        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            app.getLongestMessage()
        );
    }

    @Test
    void searchByMessageIDReturnsCorrectMessage() {
        Message result = app.searchMessageID("0000000004");
        assertNotNull(result);
        assertEquals("It is dinner time!", result.getMessage());
    }

    @Test
    void searchRecipientReturnsSentAndStoredMessages() {
        List<Message> results = app.searchRecipient("+27838884567");
        assertEquals(2, results.size());
        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            results.get(0).getMessage()
        );
        assertEquals("Ok, I am leaving without you.", results.get(1).getMessage());
    }

    @Test
    void deleteStoredMessageUsingHash() {
        assertTrue(app.deleteMessageByHash("02:2:WHERE TIME"));
        assertEquals(1, app.getStoredMessages().size());
        assertNull(app.searchMessageID("0000000002"));
    }

    @Test
    void reportContainsDetailsOfEverySentMessage() {
        String report = app.displaySentMessageReport();
        assertTrue(report.contains("01:1:DIDCAKE"));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("04:4:IT TIME"));
        assertTrue(report.contains("0838884567"));
        assertTrue(report.contains("It is dinner time!"));
    }
}
