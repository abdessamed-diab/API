package business.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {



    @Test
    public void testMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            String subject = null;
            String textContent = null;
            new Message(subject, textContent);
        });
    }

    @Test
    public void testAddAnchorToTextContent() {
        Message message = new Message();
        message.addAnchorToTextContent();
        assertEquals(Message.anchorPoint, message.getTextContent().toString());
    }

    @Test
    public void testReplaceAnchorOfTextContent() {
        Message message = new Message("Happy new year!", "hello how are you M: ");
        message.addAnchorToTextContent();
        message.replaceAnchorOfTextContent("DIAB");

        assertEquals("hello how are you M: DIAB", message.getTextContent().toString());
    }


}
