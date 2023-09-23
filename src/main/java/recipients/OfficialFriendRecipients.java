package recipients;

import email.Mail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OfficialFriendRecipients extends OfficialRecipients implements Wishable {
    Date birthdate;

    public OfficialFriendRecipients(String name, String email, String designation, String birthdate) throws ParseException {
        super(name, email, designation);
        this.birthdate = new SimpleDateFormat("yyyy/MM/dd").parse(birthdate);
    }


    @Override
    public Date getBirthDate() {
        return birthdate;
    }

    @Override
    public Mail getGreetingMail() {

        return new Mail(
                this.getEmail(),
                "Birthday Wish",
                "Wish you a Happy Birthday.\nMohamed Mushraf."
        );
    }

}
