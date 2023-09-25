package email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class MailManager {

    private static final ArrayList<Mail> sentMail = new ArrayList<>();

    public static void loadMail() {
        try {

            System.out.println("\nLoading Sent Emails...");
            FileInputStream fileStream = new FileInputStream("SentMail.ser");

            ObjectInputStream os = new ObjectInputStream(fileStream);

            while (fileStream.available() > 0) {
                Mail mail = (Mail) os.readObject();
                sentMail.add(mail);
            }
            fileStream.close();

            System.out.println(sentMail.size() + " mails loaded.");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveMail(Mail email) {
        try {
            email.setSentOn(new Date());
            File f = new File("SentMail.ser");

            FileOutputStream fileStream = new FileOutputStream("SentMail.ser", true);

            ObjectOutputStream os;
            if (f.length() > 0) {
                os = new MyObjectOutputStream(fileStream);
            } else {
                os = new ObjectOutputStream(fileStream);
            }

            os.writeObject(email);
            os.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void findMailByDate(Date date) {

        for (Mail mail : sentMail) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            if (sdf.format(mail.getSentOn()).equals(sdf.format(date))) {
                System.out.println("To : " + mail.getRecipient());
                System.out.println("Subject : " + mail.getSubject());
                System.out.println("Body : " + mail.getContent() + "\n\n");
            }
        }
    }

    public static Properties getCredentials() {
        Properties credentials = new Properties();

        try {
            InputStream input = new FileInputStream("config.properties");

            credentials.load(input);

            System.out.println(credentials.getProperty("email"));

            return credentials;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMail(Mail mail) throws MessagingException {
        String recipient_email = mail.getRecipient();
        String subject = mail.getSubject();
        String content = mail.getContent();

        Properties credentials = getCredentials();

        String username = credentials.getProperty("email");
        String password = credentials.getProperty("password");

        System.out.println(username);

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient_email));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            saveMail(mail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
