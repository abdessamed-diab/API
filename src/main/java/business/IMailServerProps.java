package business;

import javax.crypto.BadPaddingException;
import java.io.IOException;
import java.util.Properties;

public interface IMailServerProps {

    /**
     * load smtp server properties. in this case Gmail account.
     * @return @{@link Properties}
     */
    Properties getProps();

    /**
     * decrypt supplied smtp server password provided in smtp.props.
     * @return decrypted gmail account password
     * @throws BadPaddingException check if provided encrypted password is correct and valid.
     * @throws IOException security decrypt Exception.
     */
    String decryptPassword() throws BadPaddingException, IOException;

}
