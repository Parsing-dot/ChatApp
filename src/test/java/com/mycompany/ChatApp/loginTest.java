/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Zanele
 */public class loginTest {

    @Test
    public void testCorrectlyFormattedUsername() {
        login user = new login();
        user.setUserName("buhle");//setting example username
        assertTrue(user.checkUserName());
    }

    @Test
    public void testIncorrectlyFormattedUsername() {
        login user = new login();
        user.setUserName("buhle!!!!!!!");
        assertFalse(user.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {
        login user = new login();
        user.setUserPassword("Sinothando_12pp0");//example password to test
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testPasswordFailsComplexity() {
        login user = new login();
        user.setUserPassword("password");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneNumberValid() {
        login user = new login();
        user.setPhoneNumber("2783123456");// example phone number
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneNumberInvalid() {
        login user = new login();
        user.setPhoneNumber("08566553");
        assertFalse(user.checkCellPhoneNumber());
    }

   @Test
    public void testLoginSuccessStatus() {
        login user = new login();
        user.setFirstName("buhle");
        user.setLastName("FirstName");
        user.setUserName("buh_le");
        user.setUserPassword("Sinothando_12pp0");
        
        user.setSavedUsername("buh_le");
        user.setSavedPassword("Sinothando_12pp0");
        
        assertTrue(user.loginUser());
        assertEquals("Welcome Buhle, FirstName it is great to see you again.", user.returnLoginStatus());
    }

    @Test
    public void testLoginFailureStatus() {
        login user = new login();
        user.setUserName("buh_le");
        user.setUserPassword("Sinothando_12pp0");
        
        user.setSavedUsername("buh_le");
        user.setSavedPassword("Sinothando_12pp0");
        
        assertFalse(user.loginUser());
        assertEquals("Username or password incorrect, please try again.", user.returnLoginStatus());
    }
}