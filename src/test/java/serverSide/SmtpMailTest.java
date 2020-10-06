package serverSide;

import business.IMessageBuilder;
import business.models.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageBuilderTest {

    @Test
    public void testAddMessage() {
        IMessageBuilder mailServer = MessageBuilderImpl.getInstance("mail/smtp.props");
        mailServer.addMessage("abdessamed.diab@gmail.com", "Abdessamed",
                new Message("Happy birthday!", "Happy birthday, dear "));

        assertEquals(1, mailServer.getMessages().size());
    }

    @Test
    public void testTransportSendMessages() {
        IMessageBuilder mailServer = MessageBuilderImpl.getInstance("mail/smtp.props");
        mailServer.addMessage("abdessamed.diab@gmail.com", "Abdessamed",
                new Message("Happy birthday!", "Happy birthday, dear "));

        assertEquals(1, mailServer.transportSendMessages());
    }

    @Test
    public void testMessageBuilderImpl() {
        assertThrows(IllegalArgumentException.class, () -> {
            MessageBuilderImpl.getInstance("mail/test_smtp_IAException.props");
        });
    }

}
