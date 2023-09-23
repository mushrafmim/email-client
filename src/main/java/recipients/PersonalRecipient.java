package recipients;

import email.Mail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonalRecipient extends Recipient implements Wishable {

    private String nickName;
    private Date birthDate;

    private final int lastWishSentYear = 2019;

    public PersonalRecipient(String name, String nickName, String email, String birthDate) throws ParseException {
        super(name, email);
        this.nickName = nickName;
        this.birthDate = new SimpleDateFormat("yyyy/MM/dd").parse(birthDate);

    }


    @Override
    public Mail getGreetingMail() {

        return new Mail(
                this.getEmail(),
                "Birthday Wish",
                "Hugs and love on your birthday.\nMohamed Mushraf."

        );
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
