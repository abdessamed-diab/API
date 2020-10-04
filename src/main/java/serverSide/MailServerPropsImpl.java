package serverSide;

import business.IMailServerProps;
import serverSide.security.DecryptPassword;

import javax.crypto.BadPaddingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public final class MailServerPropsImpl implements IMailServerProps {
    private Properties props;
    private String serverPropsFileName;

    public static MailServerPropsImpl getInstance () {
        return new MailServerPropsImpl();
    }

    public static MailServerPropsImpl getInstance (String serverPropsFileName) {
        return new MailServerPropsImpl(serverPropsFileName);
    }

    private MailServerPropsImpl() {
        this.serverPropsFileName = "mail/smtp.props";
        loadProperties();
    }

    private MailServerPropsImpl(String serverPropsFileName) {
        this.serverPropsFileName = serverPropsFileName;
        loadProperties();
    }

    @Override
    public Properties getProps() {
        return props;
    }

    @Override
    public String decryptPassword() throws BadPaddingException, IOException, IllegalArgumentException {
        if(props == null) {
            loadProperties();
        }
        return DecryptPassword.decrypt(props.getProperty("pass"));
    }

    private void loadProperties() {
        props = new Properties();
        URL url = ClassLoader.getSystemResource(serverPropsFileName);
        try {
            File file = new File(url.toURI());
            props.load(new FileInputStream(file));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
