QUICKCHAT PART 3 STARTER PROJECT
================================

This is a standalone Part 3 starter project using Java 17, Maven, Gson and JUnit 5.
It is NOT an integration of your existing Parts 1 and 2 because those source files
were not supplied in this chat.

OPEN IN NETBEANS
1. Extract this ZIP file.
2. Open NetBeans.
3. Choose File > Open Project.
4. Select the extracted QuickChat_Part3_Starter folder.
5. Allow Maven to download the dependencies.
6. Run za.co.quickchat.Main to see a sample output.

RUN THE UNIT TESTS
1. Right-click the project.
2. Choose Test.
   Or use a terminal in the project folder and run: mvn test

JSON EXAMPLE
The file storedMessages-example.json demonstrates the expected JSON object structure.
To load it in Java, use:
    app.loadStoredMessages(Path.of("storedMessages-example.json"));

IMPORTANT NOTES
- The assignment calls these items arrays. This starter uses ArrayList collections
  because the number of messages changes as users send, store or disregard messages.
- The test message IDs and hashes are illustrative placeholders. Connect your
  Part 2 ID and hash-generation methods before final submission.
- The assignment's longest-message expected result includes stored messages.
- The provided recipient-search method searches sent and stored messages, as requested.
- The delete method deletes a stored message by its hash.
- The report lists all sent messages only.
- Sender is shown as "Developer" because the supplied Part 3 test data does not
  provide a separate sender field for every message.

This is a learning starter. Run the tests, review the code, and adapt it to your
own Parts 1 and 2 before submitting.
