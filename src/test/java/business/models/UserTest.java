package business.models;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;
    private static final LocalDate date = LocalDate.of(1989, 12, 2);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @BeforeEach
    public void init() {
        user = new User("diab", "abdessamed", date, "abdessamed.diab@adservio.fr");
    }

    @Test
    public void testEquals() {
        User user2 = new User("DIAB", "Abdessamed", date,
                "abdessamed.diab@adservio.fr");
        assertEquals(user, user2);

        user2 = new User("DIAB", "Abdessamed", date,
                "abdessamed.diab@adservio.com");
        assertNotEquals(user, user2);

        user2 = new User("DIAB", "Abdessamed", LocalDate.of(1989, 12, 3),
                "abdessamed.diab@adservio.fr");
        assertNotEquals(user, user2);
    }

    @Test
    @Ignore
    public void testCompareTo() {
        User user2 = new User("DIAB", "Abdessamed", date, "abdessamed.diab@adservio.fr");
        assertEquals(0 , user2.compareTo(user));

        user2 = new User("DIAB", "Abdessamed", LocalDate.of(1989, 12, 3),
                "abdessamed.diab@adservio.fr");
        assertEquals(1 , user2.compareTo(user));

        user2 = new User("DIAB", "Abdessamed", LocalDate.of(1989, 12, 1),
                "abdessamed.diab@adservio.fr");
        assertEquals(-1 , user2.compareTo(user));
    }

    @Test
    @Ignore
    public void testClone() {
        try {
            User user2 = user.clone();
            assertEquals(user, user2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
