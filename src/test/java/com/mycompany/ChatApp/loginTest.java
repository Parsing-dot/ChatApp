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
        user.setUserName("kyl_1");
        assertTrue(user.checkUserName());
    }

    @Test
    public void testIncorrectlyFormattedUsername() {
        login user = new login();
        user.setUserName("kyle!!!!!!!");
        assertFalse(user.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {
        login user = new login();
        user.setUserPassword("Ch&&sec@ke99!");
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
        user.setPhoneNumber("2783123456");
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
        user.setFirstName("Kyle");
        user.setLastName("FirstName");
        user.setUserName("kyl_1");
        user.setUserPassword("Ch&&sec@ke99!");
        
        user.setSavedUsername("kyl_1");
        user.setSavedPassword("Ch&&sec@ke99!");
        
        assertTrue(user.loginUser());
        assertEquals("Welcome Kyle, FirstName it is great to see you again.", user.returnLoginStatus());
    }

    @Test
    public void testLoginFailureStatus() {
        login user = new login();
        user.setUserName("kyl_1");
        user.setUserPassword("Ch&&sec@ke99!");
        
        user.setSavedUsername("kyl_1");
        user.setSavedPassword("wrongPassword");
        
        assertFalse(user.loginUser());
        assertEquals("Username or password incorrect, please try again.", user.returnLoginStatus());
    }
}