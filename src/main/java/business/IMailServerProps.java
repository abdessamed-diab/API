package business;

import javax.crypto.BadPaddingException;
import java.io.IOException;
import java.util.Properties;

public interface IMailServerProps {

    Properties getProps();

    String decryptPassword() throws BadPaddingException, IOException;

}
