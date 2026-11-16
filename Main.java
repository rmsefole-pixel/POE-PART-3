package za.co.quickchat;

public class Main {
    public static void main(String[] args) {
        QuickChatPart3 app = new QuickChatPart3();
        app.populateAssessmentTestData();

        System.out.println("Sent messages:");
        app.displaySentSenderAndRecipient().forEach(System.out::println);

        System.out.println("\nLongest message:");
        System.out.println(app.getLongestMessage());

        System.out.println("\nFull report:");
        System.out.println(app.displaySentMessageReport());
    }
}
