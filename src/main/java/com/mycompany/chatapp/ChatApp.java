/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;
import java.util.Scanner;

/**
 *
 * @author Zanele
 */


public class ChatApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        login userLogin = new login();

        System.out.println("/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/`~/");
        System.out.println("   WELCOME TO THE REGISTRATION SYSTEM    ");
        System.out.println("/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/`~/");

        // 1. Capture User Personal Details
        System.out.print("Enter First Name: ");
        userLogin.setFirstName(scanner.nextLine());

        System.out.print("Enter Last Name: ");
        userLogin.setLastName(scanner.nextLine());

        // Terms and Conditions Prompt
        System.out.print("Do you accept the Terms and Conditions? (yes/no): ");
        String termsChoice = scanner.nextLine();
        if (termsChoice.equalsIgnoreCase("yes")) {
            userLogin.setTermsAccepted(true);
        } else {
            userLogin.setTermsAccepted(false);
            System.out.println("You must accept terms to register. Exiting program.");
            scanner.close();
            return;
        }

        // 2. Registration Input Loop
        boolean isRegisteredSuccessfully = false;

        while (!isRegisteredSuccessfully) {
            System.out.println("\n--- REGISTRATION ---");
            System.out.print("Enter Username: ");
            userLogin.setUserName(scanner.nextLine());

            System.out.print("Enter Password: ");
            userLogin.setUserPassword(scanner.nextLine());

            System.out.print("Enter South African Cell Phone Number (e.g. +27831234567): ");
            userLogin.setPhoneNumber(scanner.nextLine());

            // Process Registration
            String registrationResult = userLogin.registerUser();
            System.out.println("\n" + registrationResult);

            // Check if registration conditions were fully met
            if (userLogin.checkUserName() && userLogin.checkPasswordComplexity() && userLogin.checkCellPhoneNumber()) {
                isRegisteredSuccessfully = true;
            } else {
                System.out.println("\nPlease try registering again with valid details.");
            }
        }

        // 3. Login Loop
        System.out.println("\n/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/`~/");
        System.out.println("               LOGIN SYSTEM              ");
        System.out.println("/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/`~/");

        while (!userLogin.isLoginLimitMet()) {
            System.out.println("\n--- LOGIN ---");
            System.out.print("Enter Username: ");
            userLogin.setSavedUsername(scanner.nextLine());

            System.out.print("Enter Password: ");
            userLogin.setSavedPassword(scanner.nextLine());

            // Attempt Login
            boolean loginSuccess = userLogin.loginUser();

            // Print Exact Output Status Message
            System.out.println("\n" + userLogin.returnLoginStatus());

            if (loginSuccess) {
                break;
            }
        }

        scanner.close();
    }
}