package serverSide;

import business.IMessageBuilder;
import business.models.Employee;
import business.models.TextMessage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SmtpMailServerTest {
    private final Employee employee = new Employee("last_name", "first_name",
            LocalDate.now().minusDays(1), "abdessamed.diab@gmail.com");

    @Test
    public void testAddMessage() {
        IMessageBuilder mailServer = SmtpMailServer.getInstance("mail/smtp.props");
        mailServer.addMessage(new TextMessage("abdessamed.diab@gmail.com", "Abdessamed", employee ));

        assertEquals(1, mailServer.getGenericMessages().size());
    }

    @Test
    public void testTransportSendMessages() {
        IMessageBuilder mailServer = SmtpMailServer.getInstance("mail/smtp.props");
        mailServer.addMessage(new TextMessage("abdessamed.diab@gmail.com", "Abdessamed", employee ));

        assertEquals(1, mailServer.transportSendMessages());
    }

    @Test
    public void testMessageBuilderImpl() {
        assertThrows(IllegalArgumentException.class, () -> {
            SmtpMailServer.getInstance("mail/test_smtp_IAException.props");
        });
    }

}
