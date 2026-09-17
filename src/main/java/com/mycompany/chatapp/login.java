/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Zanele
 */
public class login {

    // Variables with main Registration & User Profile Fields
    private String firstName;
    private String lastName;
    private String userName;
    private String userPassword;
    private String phoneNumber;

    // Variables for Login Verification Fields
    private String savedUsername;
    private String savedPassword;

    // Declaring varib=ables for custom Status & Tracking Flags
    private boolean isUsernameValid;
    private boolean isLoggedIn;
    private boolean isLoginLimitMet;
    private boolean termsAccepted;
    private int loginAttempts = 0;

    // Methods for fields and requirements
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getSavedUsername() { return savedUsername; }
    public void setSavedUsername(String savedUsername) { this.savedUsername = savedUsername; }

    public String getSavedPassword() { return savedPassword; }
    public void setSavedPassword(String savedPassword) { this.savedPassword = savedPassword; }

    public boolean isTermsAccepted() { return termsAccepted; }
    public void setTermsAccepted(boolean termsAccepted) { this.termsAccepted = termsAccepted; }

    public boolean isLoginLimitMet() { return isLoginLimitMet; }

    // Checking Username Formatting with minimum 8 chars, must contain and underscore)
    public boolean checkUserName() {
        if (userName != null && userName.contains("_") && userName.length() <= 8) {
            isUsernameValid = true;
            return true;
        }
        isUsernameValid = false;
        return false;
    }

    // password check and requirement logic so password
    //the password will at least have 8 chars, uppercase, number, special char
    public boolean checkPasswordComplexity() {
        return userPassword != null &&
               userPassword.length() >= 8 &&
               userPassword.matches(".*[A-Z].*") &&
               userPassword.matches(".*[0-9].*") &&
               userPassword.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }

    // method for strong password checker with > 5 chars, uppercase, number, special char
    public boolean isPasswordStrong() {
        return userPassword != null &&
               userPassword.length() > 5 &&
               userPassword.matches(".*[A-Z].*") &&
               userPassword.matches(".*[0-9].*") &&
               userPassword.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }

    // South African Cell Phone Validation Regex + Required Attribution Comment)
    public boolean checkCellPhoneNumber() {
        // Regex Source: Stack Overflow - South African Mobile International Format (+27 or 27 followed by 8 or 9 digits)
        String saPhoneRegex = "^(\\+27|27)[0-9]{8,9}$";
        return phoneNumber != null && phoneNumber.matches(saPhoneRegex) && phoneNumber.length() <= 11;
    }

    // method to register user
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        } else if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        } else if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        } else {
            return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
        }
    }

    // Login Verification with Unique Limit Feature
    public boolean loginUser() {
        if (isLoginLimitMet) {
            return false;
        }

        if (savedUsername != null && savedPassword != null &&
            savedUsername.equals(userName) && savedPassword.equals(userPassword)) {
            isLoggedIn = true;
            loginAttempts = 0;
            return true;
        } else {
            loginAttempts++;
            if (loginAttempts >= 3) {
                isLoginLimitMet = true;
            }
            isLoggedIn = false;
            return false;
        }
    }

    //  Login Status Messaging
    public String returnLoginStatus() {
        if (isLoginLimitMet) {
            return "Account locked: Maximum login attempts exceeded.";
        }
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}