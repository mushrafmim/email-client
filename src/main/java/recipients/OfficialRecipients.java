package recipients;

public class OfficialRecipients extends Recipient {
    private String designation;

    public OfficialRecipients(String name, String email, String designation) {
        super(name, email);

        this.designation = designation;
    }

}
