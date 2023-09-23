package recipients;

import email.Mail;

import java.util.Date;

public interface Wishable {

    Mail getGreetingMail();

    Date getBirthDate();
}
