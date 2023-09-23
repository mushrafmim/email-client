package email;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutputStream extends ObjectOutputStream {
    MyObjectOutputStream() throws IOException {
        super();
    }

    MyObjectOutputStream(OutputStream os) throws IOException {
        super(os);
    }

    public void writeStreamHeader() throws IOException {
        return;
    }
}
