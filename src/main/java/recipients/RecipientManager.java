package recipients;

import email.MailManager;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RecipientManager {

    private static final ArrayList<Recipient> recipientsList = new ArrayList<Recipient>();


    public static Recipient createRecipient(String[] recipientDetails) throws ParseException {

        Recipient recipient;

        switch (recipientDetails[0]) {
            case "Official":
                recipient = new OfficialRecipients(
                        recipientDetails[1],
                        recipientDetails[2],
                        recipientDetails[3]
                );
                break;
            case "Office_friend":
                recipient = new OfficialFriendRecipients(
                        recipientDetails[1],
                        recipientDetails[2],
                        recipientDetails[3],
                        recipientDetails[4]
                );
                break;
            case "Personal":
                recipient = new PersonalRecipient(
                        recipientDetails[1],
                        recipientDetails[2],
                        recipientDetails[3],
                        recipientDetails[4]
                );
                break;
            default:
                throw new RuntimeException();
        }

        addRecipient(recipient);

        return recipient;
    }

    public static void newRecipient(String[] recipientDetails) throws IOException, ParseException {
        FileWriter recipientsFile = new FileWriter("clientList.txt", true);

        createRecipient(recipientDetails);

        recipientsFile.write(String.join(",", recipientDetails) + "\n");

        recipientsFile.close();
    }

    public static void loadRecipients() throws IOException, ParseException {
        FileReader recipientsFile = new FileReader("clientList.txt");

        BufferedReader bufferedReader = new BufferedReader(recipientsFile);

        String line;

        while ((line=bufferedReader.readLine()) != null) {
            String[] recipientDetails = line.split(",");

            createRecipient(recipientDetails);

        }
    }

    public static ArrayList<Wishable> findRecipientByBirthday(Date birthDate) {

        ArrayList<Wishable> birthdayRecipients = new ArrayList<>();
        for (Recipient recipient : recipientsList) {
            if (recipient instanceof OfficialFriendRecipients || recipient instanceof PersonalRecipient) {

                Wishable wishableRecipient = (Wishable) recipient;
                if (
                        birthDate.getDate() == wishableRecipient.getBirthDate().getDate() &&
                                birthDate.getMonth() == wishableRecipient.getBirthDate().getMonth()
                ) {
                    System.out.println("Adding");
                    birthdayRecipients.add(wishableRecipient);
                }
            }
        }
        return birthdayRecipients;
    }

    public static int getRecipientsCount() {
        return recipientsList.size();
    }

    private static void addRecipient(Recipient recipient) {
        recipientsList.add(recipient);
    }


    public static void sendBirthdayWish() throws MessagingException {
        ArrayList<Wishable> birthdayRecipients = findRecipientByBirthday(new Date());

        for (Wishable birthdayRecipient: birthdayRecipients) {

            MailManager.sendMail(birthdayRecipient.getGreetingMail());
        }
    }

    public static ArrayList<Wishable> findRecipientsByBirthdate(Date date) {
        ArrayList<Wishable> birthdayRecipients = new ArrayList<>();

        for (Recipient recipient : recipientsList) {
            if (recipient instanceof OfficialFriendRecipients || recipient instanceof PersonalRecipient) {

                Wishable wishableRecipient = (Wishable) recipient;
                if (
                        date.getDate() == wishableRecipient.getBirthDate().getDate() &&
                                date.getMonth() == wishableRecipient.getBirthDate().getMonth() &&
                                date.getYear() == wishableRecipient.getBirthDate().getYear()
                ) {
                    birthdayRecipients.add(wishableRecipient);
                }
            }
        }
        return birthdayRecipients;
    }

    public static void printRecipientsOfBirthday(Date date) {
        ArrayList<Wishable> birthdayRecipients = findRecipientsByBirthdate(date);

        for (Wishable birthdayRecipient:
             birthdayRecipients) {
            Recipient recipient = (Recipient) birthdayRecipient;

            System.out.println(recipient.getName() + " has birthday on " + date);
        }

    }
}
