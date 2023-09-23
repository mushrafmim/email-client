package email;

import java.io.*;
import java.util.Date;

public class Mail implements Serializable {
    private final String recipient;
    private final String subject;
    private final String content;
    private Date sentOn;

    public Mail(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }


    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public Date getSentOn() {
        return sentOn;
    }
}
