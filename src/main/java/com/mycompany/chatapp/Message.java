/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {

    private String messageId;
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageHash;

    // Constructor
    public Message(int messageNumber, String recipientCell, String messageText) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageId = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Default Constructor
    public Message() {}

    // 1. Generate Random 10-digit Message ID
    public String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // 2. Check Message ID (Max 10 Chars)
    public boolean checkMessageID() {
        return messageId != null && messageId.length() <= 10;
    }

    // 3. Check Recipient Cell Number (Max 10 Chars, Starts with code)
    public String checkRecipientCell(String recipient) {
        // Reusing SA phone validation rules (+27 / 27)
        String saPhoneRegex = "^(\\+27|27)[0-9]{8,9}$";
        if (recipient != null && recipient.matches(saPhoneRegex) && recipient.length() <= 10) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // 4. Check Message Length (Max 250 Chars)
    public String validateMessageLength(String message) {
        if (message != null && message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // 5. Create Message Hash (Format: 00:0:HITHANKS in ALL CAPS)
    public String createMessageHash() {
        if (messageId == null || messageText == null || messageText.trim().isEmpty()) {
            return "";
        }
        
        // Extract first 2 digits of Message ID
        String firstTwoDigits = messageId.length() >= 2 ? messageId.substring(0, 2) : "00";
        
        // Extract first and last words
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        
        String hash = firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // 6. Handle Send Message Action Options
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press O to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option selected.";
        }
    }

    // 7. Store Message as JSON File with Reference Attribution
    public void storeMessage() {
        // Reference Attribution: Standard Java File I/O for lightweight JSON output
        String jsonContent = "{\n" +
                "  \"messageId\": \"" + messageId + "\",\n" +
                "  \"messageNumber\": " + messageNumber + ",\n" +
                "  \"recipientCell\": \"" + recipientCell + "\",\n" +
                "  \"messageText\": \"" + messageText + "\",\n" +
                "  \"messageHash\": \"" + messageHash + "\"\n" +
                "}";

        try (FileWriter file = new FileWriter("message_" + messageId + ".json")) {
            file.write(jsonContent);
            file.flush();
        } catch (IOException e) {
            System.err.println("Error storing JSON file: " + e.getMessage());
        }
    }

    // 8. Print Message Details Format
    public String printMessages() {
        return "Message ID: " + messageId +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipientCell +
               "\nMessage: " + messageText;
    }

    // Getters and Setters
    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
}