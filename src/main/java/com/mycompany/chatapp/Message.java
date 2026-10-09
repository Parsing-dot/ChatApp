/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;

public class ChatApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        login userLogin = new login();

        System.out.println("/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/`~/");
        System.out.println("    WELCOME TO THE REGISTRATION SYSTEM    ");
        System.out.println("/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/`~/");

        // 1. Capture Registration Details
        System.out.print("Enter First Name: ");
        userLogin.setFirstName(scanner.nextLine());

        System.out.print("Enter Last Name: ");
        userLogin.setLastName(scanner.nextLine());

        System.out.print("Do you accept the Terms and Conditions? (yes/no): ");
        if (!scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("You must accept terms to register. Exiting program.");
            scanner.close();
            return;
        }

        boolean isRegistered = false;
        while (!isRegistered) {
            System.out.println("\n--- REGISTRATION ---");
            System.out.print("Enter Username: ");
            userLogin.setUserName(scanner.nextLine());

            System.out.print("Enter Password: ");
            userLogin.setUserPassword(scanner.nextLine());

            System.out.print("Enter South African Cell Phone Number (e.g. 2783123456): ");
            userLogin.setPhoneNumber(scanner.nextLine());

            System.out.println("\n" + userLogin.registerUser());
            if (userLogin.checkUserName() && userLogin.checkPasswordComplexity() && userLogin.checkCellPhoneNumber()) {
                isRegistered = true;
            } else {
                System.out.println("Please try registering again with valid details.");
            }
        }

        // 2. Login Flow
        System.out.println("\n=========================================");
        System.out.println("               LOGIN SYSTEM              ");
        System.out.println("=========================================");

        boolean isLoggedIn = false;
        while (!userLogin.isLoginLimitMet() && !isLoggedIn) {
            System.out.println("\n--- LOGIN ---");
            System.out.print("Enter Username: ");
            userLogin.setSavedUsername(scanner.nextLine());

            System.out.print("Enter Password: ");
            userLogin.setSavedPassword(scanner.nextLine());

            isLoggedIn = userLogin.loginUser();
            System.out.println("\n" + userLogin.returnLoginStatus());
        }

        // 3. QuickChat Application Loop (Requires Successful Login)
        if (isLoggedIn) {
            System.out.println("\nWelcome to QuickChat.");
            boolean running = true;
            int totalSentAccumulator = 0;

            while (running) {
                System.out.println("\n--- MENU ---");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");

                String inputChoice = scanner.nextLine();
                int option = 0;
                try {
                    option = Integer.parseInt(inputChoice);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numeric option.");
                    continue;
                }

                switch (option) {
                    case 1:
                        System.out.print("How many messages do you wish to enter?: ");
                        int numMessages = Integer.parseInt(scanner.nextLine());

                        for (int i = 0; i < numMessages; i++) {
                            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");

                            // Recipient Validation
                            String recipient = "";
                            Message msgValidator = new Message();
                            while (true) {
                                System.out.print("Enter Recipient Cell Number: ");
                                recipient = scanner.nextLine();
                                String recStatus = msgValidator.checkRecipientCell(recipient);
                                System.out.println(recStatus);
                                if (recStatus.equals("Cell phone number successfully captured.")) {
                                    break;
                                }
                            }

                            // Message Length Validation
                            String text = "";
                            while (true) {
                                System.out.print("Enter Message (Max 250 chars): ");
                                text = scanner.nextLine();
                                String msgStatus = msgValidator.validateMessageLength(text);
                                System.out.println(msgStatus);
                                if (msgStatus.equals("Message ready to send.")) {
                                    break;
                                }
                            }

                            // Construct Message
                            Message msg = new Message(i + 1, recipient, text);

                            // Send/Store/Disregard Options
                            System.out.println("\nChoose Action:");
                            System.out.println("1) Store Message in a JSON file / Send");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message to send later");
                            System.out.print("Select: ");
                            int actionChoice = Integer.parseInt(scanner.nextLine());

                            String actionResult = msg.SentMessage(actionChoice);
                            System.out.println(actionResult);

                            if (actionChoice == 1) {
                                totalSentAccumulator++;
                            }

                            // Display Full Message Details
                            System.out.println("\n--- MESSAGE DETAILS ---");
                            System.out.println(msg.printMessages());
                        }

                        System.out.println("\nTotal messages sent in this session: " + totalSentAccumulator);
                        break;

                    case 2:
                        System.out.println("Coming Soon.");
                        break;

                    case 3:
                        running = false;
                        System.out.println("Thank you for using QuickChat. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid menu option. Please choose 1, 2, or 3.");
                        break;
                }
            }
        }

        scanner.close();
    }
}