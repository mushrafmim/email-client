//import libraries

import recipients.*;
import email.*;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class EmailClient {

    public static void main(String[] args) throws IOException, ParseException, MessagingException {

        initializeApp();

        // Loading all the recipient from clientList.txt to an arrayList
        RecipientManager.loadRecipients();

//         Sending the wishes to the people who have birthday on the particular day.
        RecipientManager.sendBirthdayWish();

        // Loading the sent emails from SentMail.ser to an array list
        MailManager.loadMail();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application");

        int option = scanner.nextInt();
        scanner.nextLine();



        switch(option) {
            case 1:
                // input format - Official: nimal,nimal@gmail.com,ceo
                // Use a single input to get all the details of a recipient
                // code to add a new recipient
                // store details in clientList.txt file
                // Hint: use methods for reading and writing files
                try {
                    // Taking the input and splitting the words as needed.
                    System.out.println("Enter the new recipient: \nFormats:\n\t1. Official: name, email, designation\n\t2. Office_friend: name, email, designation, birthdate\n\t3. Personal: name, nickname, email, birthdate");
                    String[] recipientDetails = scanner.nextLine().strip().split("(: )|(, )");

                    scanner.close();

                    RecipientManager.newRecipient(recipientDetails);

                } catch (IOException e) {
                    System.out.println(e);
                } catch (ParseException e) {
                    System.out.println("Invalid date format");
                    throw new RuntimeException(e);
                }

                break;
            case 2:
                // input format - email, subject, content
                // code to send an email

                System.out.println("Enter the email to send: (email, subject, content)");
                String[] email = scanner.nextLine().strip().split("(, )");

                Mail newEmail = new Mail(email[0], email[1], email[2]);
                MailManager.sendMail(newEmail);

                break;
            case 3:
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print recipients who have birthdays on the given date

                System.out.println("Enter date to find the birthday recipients: (yyyy/MM/dd)");

                String date = scanner.nextLine().strip();
                Date birthDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);


                RecipientManager.printRecipientsOfBirthday(birthDate);

                break;
            case 4:
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print the details of all the emails sent on the input date
                System.out.println("Enter date to get the sent mails on: (yyyy/MM/dd)");

                String dateInp = scanner.nextLine().strip();
                Date dateToFindEmail = new SimpleDateFormat("yyyy/MM/dd").parse(dateInp);

                MailManager.findMailByDate(dateToFindEmail);
                break;
            case 5:
                // code to print the number of recipient objects in the application
                System.out.println("Recipient Count is " + RecipientManager.getRecipientsCount());
                break;

        }

        // start email client
        // code to create objects for each recipient in clientList.txt
        // use necessary variables, methods and classes

    }

    public static void initializeApp() throws IOException {


        File clientListFile = new File("clientList.txt");

        if (!clientListFile.exists()) {
            clientListFile.createNewFile();
        }

        File sentMailFile = new File("SentMail.ser");

        if (!sentMailFile.exists()) {
            sentMailFile.createNewFile();
        }
    }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)