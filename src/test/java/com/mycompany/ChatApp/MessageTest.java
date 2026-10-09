/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message();
        String validText = "Hi Mike, can you join us for dinner tonight?";
        assertEquals("Message ready to send.", msg.validateMessageLength(validText));
    }

    @Test
    public void testMessageLengthFailure() {
        Message msg = new Message();
        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longText.append("a");
        }
        String result = msg.validateMessageLength(longText.toString());
        assertTrue(result.contains("Message exceeds 250 characters by 10"));
    }

    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message();
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell("+27718693002"));
    }

    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message();
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell("08575975889"));
    }

    @Test
    public void testMessageHashCreation() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        msg.setMessageId("0012345678"); // Force 00 ID for test matching
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    @Test
    public void testMessageIDGenerated() {
        Message msg = new Message();
        String id = msg.generateMessageID();
        assertNotNull(id);
        assertEquals(10, id.length());
    }

    @Test
    public void testMessageSentOptions() {
        Message msg = new Message();
        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals("Press O to delete the message.", msg.SentMessage(2));
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }
}