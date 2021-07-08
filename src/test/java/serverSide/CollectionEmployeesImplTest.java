package serverSide;

import business.ICollectionEmployees;
import business.IEmptyCollectionEmployees;
import business.models.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CollectionEmployeesImplTest {
    private ICollectionEmployees collectionUsers;

    @Test
    public void testConstructorExpectedIllegalArgException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CollectionEmployeesImpl("testServerSideFlatFiles/users.csvv");
        });
    }

    @Test
    public void testAllEmployees() {
        collectionUsers = new CollectionEmployeesImpl("testServerSideFlatFiles/users_0.csv");
        Set<Employee> employees = collectionUsers.allEmployees();
        assertEquals(2, employees.size());
    }

    @Test
    public void testAllEmployeesThrowDateTimeParseException() {
        collectionUsers = new CollectionEmployeesImpl("testServerSideFlatFiles/users.csv");
        assertThrows(DateTimeParseException.class, () -> collectionUsers.allEmployees());
    }

    @Test
    public void testAllEmployeesSplitLine() {
        collectionUsers = new CollectionEmployeesImpl("testServerSideFlatFiles/users_2.csv");
        Set<Employee> employees = collectionUsers.allEmployees();
        assertEquals(2, employees.size());
    }

    @Test
    public void testAllEmployeesLoadOnlyFourFields() {
        collectionUsers = new CollectionEmployeesImpl("testServerSideFlatFiles/users_3.csv");
        Set<Employee> employees = collectionUsers.allEmployees();
        assertEquals(1, employees.size());
    }

    @Test
    public void testFindEmployeesDateBirthdayToday() {
        Set<Employee> employees = new TreeSet<>();
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate notTodayDate = today.minusDays(3);
        for (int i = 0; i < 3 ; i++) {
            Employee notBornTodayEmployee = new Employee("lastname"+i, "firstname"+1, notTodayDate, "adress@example.com");
            employees.add(notBornTodayEmployee);
        }

        for (int i = 3; i < 8 ; i++) {
            Employee bornTodayEmployee = new Employee("lastname"+i, "firstname"+1, today, "adress@example.com");
            employees.add(bornTodayEmployee);
        }

        collectionUsers = new EmptyCollectionEmployeesImpl();
        ((IEmptyCollectionEmployees)collectionUsers).setEmployees(employees);
        assertEquals(5, collectionUsers.findEmployeesDateBirthdayToday().size());
    }

    @Test
    public void testFindEmployeesDateBirthdayTodayLeapNoLeapYear() {
        collectionUsers = new EmptyCollectionEmployeesImpl();
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        LocalDate todayNotLeapYear = LocalDate.of(2021, now.getMonth().getValue(), now.getDayOfMonth());
        LocalDate bornLeapYear = LocalDate.of(1984, now.getMonth().getValue(), now.getDayOfMonth());
        Employee employee = new Employee("lastname", "firstname", bornLeapYear, "internetAddress@example.fr");
        ((IEmptyCollectionEmployees)collectionUsers).addEmployee(employee);
        ((IEmptyCollectionEmployees)collectionUsers).setToday(todayNotLeapYear);
        assertEquals(1, collectionUsers.findEmployeesDateBirthdayToday().size());
    }

}
