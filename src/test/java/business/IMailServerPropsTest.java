package business;

import org.junit.jupiter.api.Test;
import serverSide.MailServerPropsImpl;

import java.util.Properties;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class IMailServerPropsTest {

    @Test
    public void testLoadProperties() {
        Properties props = MailServerPropsImpl.Factory.getInstance().getProps();
        assertEquals("smtp.gmail.com", props.getProperty("mail.smtp.host"));

        assertEquals("465", props.getProperty("mail.smtp.port"));

        assertEquals("true", props.getProperty("mail.smtp.auth"));

        assertEquals("abdessamed.trader@gmail.com", props.getProperty("from"));
    }
}
