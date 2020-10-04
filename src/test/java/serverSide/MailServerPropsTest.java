package serverSide;

import org.junit.jupiter.api.Test;
import serverSide.MailServerPropsImpl;

import java.util.Properties;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class MailServerPropsTest {

    @Test
    public void testLoadProperties() {
        Properties props = MailServerPropsImpl.getInstance().getProps();
        assertEquals("smtp.gmail.com", props.getProperty("mail.smtp.host"));

        assertEquals("465", props.getProperty("mail.smtp.port"));

        assertEquals("true", props.getProperty("mail.smtp.auth"));

        assertEquals("abdessamed.trader@gmail.com", props.getProperty("from"));
    }
}
