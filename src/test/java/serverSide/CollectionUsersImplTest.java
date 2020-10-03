package serverSide;

import business.ICollectionUsers;
import business.IEmptyCollectionUsers;
import business.models.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CollectionUsersImplTest {
    private ICollectionUsers collectionUsers;

    @Test
    public void testConstructorExpectedIllegalArgException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CollectionUsersImpl("testServerSideFlatFiles/users.csvv");
        });
    }

    @Test
    public void testAllUsers() {
        collectionUsers = new CollectionUsersImpl("testServerSideFlatFiles/users_0.csv");
        Set<User> users = collectionUsers.allUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void testAllUsersThrowDateTimeParseException() {
        collectionUsers = new CollectionUsersImpl("testServerSideFlatFiles/users.csv");
        Set<User> users = collectionUsers.allUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void testAllUsersSplitLine() {
        collectionUsers = new CollectionUsersImpl("testServerSideFlatFiles/users_2.csv");
        Set<User> users = collectionUsers.allUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void testAllUsersLoadOnlyFourFields() {
        collectionUsers = new CollectionUsersImpl("testServerSideFlatFiles/users_3.csv");
        Set<User> users = collectionUsers.allUsers();
        assertEquals(1, users.size());
    }

    @Test
    public void testFindUsersBornToday() {
        Set<User> users = new TreeSet<>();
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate notTodayDate = today.minusDays(3);
        for (int i = 0; i < 3 ; i++) {
            User notBornTodayUser = new User("lastname"+i, "firstname"+1, notTodayDate, "adress@example.com");
            users.add(notBornTodayUser);
        }

        for (int i = 3; i < 8 ; i++) {
            User BornTodayUser = new User("lastname"+i, "firstname"+1, today, "adress@example.com");
            users.add(BornTodayUser);
        }

        collectionUsers = new EmptyCollectionUsersImpl();
        ((IEmptyCollectionUsers)collectionUsers).setUsers(users);
        assertEquals(5, collectionUsers.findUsersBornToday().size());
    }

    @Test
    public void testFindUsersBornTodayLeapNoLeapYear() {
        collectionUsers = new EmptyCollectionUsersImpl();
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        LocalDate todayNotLeapYear = LocalDate.of(2021, now.getMonth().getValue(), now.getDayOfMonth());
        LocalDate bornLeapYear = LocalDate.of(1984, now.getMonth().getValue(), now.getDayOfMonth());
        User user = new User("lastname", "firstname", bornLeapYear, "internetAddress@example.fr");
        ((IEmptyCollectionUsers)collectionUsers).addUser(user);
        ((IEmptyCollectionUsers)collectionUsers).setToday(todayNotLeapYear);
        assertEquals(1, collectionUsers.findUsersBornToday().size());
    }

}
