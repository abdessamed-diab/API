package business;

import business.models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serverSide.EmptyCollectionEmployeesImpl;
import serverSide.MessageBuilderImpl;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MailSenderTest {
    private EmptyCollectionEmployeesImpl collectionUsers;

    @BeforeEach
    public void init() {
        collectionUsers = new EmptyCollectionEmployeesImpl();
    }

    @Test
    public void testSend() {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        for (int i=0; i <2 ; i++) {
            collectionUsers.addEmployee(new Employee("DIAB"+i, "Abdessamed"+i, today, "abdessamed.diab@gmail.com"));
        }
        MailSender mailSender = new MailSender(collectionUsers, MessageBuilderImpl.getInstance(""));
        assertEquals(2, mailSender.send());
    }

    @Test
    public void testSendThrowIllegalArgException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MailSender(collectionUsers,
                    MessageBuilderImpl.getInstance("mail/test_smtp_IAException.props"));
        });
    }

}
