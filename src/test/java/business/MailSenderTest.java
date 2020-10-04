package business;

import business.models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serverSide.EmptyCollectionUsersImpl;
import serverSide.MailServerPropsImpl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MailSenderTest {
    private EmptyCollectionUsersImpl collectionUsers;

    @BeforeEach
    public void init() {
        collectionUsers = new EmptyCollectionUsersImpl();
    }

    @Test
    public void testSend() throws GeneralSecurityException, IOException {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        for (int i=0; i <2 ; i++) {
            collectionUsers.addEmployee(new Employee("DIAB"+i, "Abdessamed"+i, today, "abdessamed.diab@gmail.com"));
        }
        MailSender mailSender = new MailSender(collectionUsers, MailServerPropsImpl.getInstance());
        assertEquals(2, mailSender.send());
    }

    @Test
    public void testSendThrowIllegalArgException() {
        MailSender mailSender = new MailSender(collectionUsers,
                MailServerPropsImpl.getInstance("mail/test_smtp_IAException.props"));
        assertThrows(IllegalArgumentException.class, () -> {
            mailSender.send();
        });
    }

}
