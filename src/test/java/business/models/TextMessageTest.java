package business.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TextMessageTest {



    @Test
    public void testMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            String subject = null;
            String textContent = null;
            new TextMessage(
                    subject,
                    textContent,
                    new Employee("last_name", "first_name", LocalDate.now(), "XXX@XXX.fr")
            );
        });
    }


}
