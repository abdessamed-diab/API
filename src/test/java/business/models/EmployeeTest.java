package business.models;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    private Employee employee;
    private static final LocalDate date = LocalDate.of(1989, 12, 2);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @BeforeEach
    public void init() {
        employee = new Employee("diab", "abdessamed", date, "abdessamed.diab@adservio.fr");
    }

    @Test
    public void testEquals() {
        Employee employee2 = new Employee("DIAB", "Abdessamed", date,
                "abdessamed.diab@adservio.fr");
        assertEquals(employee, employee2);

        employee2 = new Employee("DIAB", "Abdessamed", date,
                "abdessamed.diab@adservio.com");
        assertNotEquals(employee, employee2);

        employee2 = new Employee("DIAB", "Abdessamed", LocalDate.of(1989, 12, 3),
                "abdessamed.diab@adservio.fr");
        assertNotEquals(employee, employee2);
    }

    @Test
    @Ignore
    public void testCompareTo() {
        Employee employee2 = new Employee("DIAB", "Abdessamed", date, "abdessamed.diab@adservio.fr");
        assertEquals(0 , employee2.compareTo(employee));

        employee2 = new Employee("DIAB", "Abdessamed", LocalDate.of(1989, 12, 3),
                "abdessamed.diab@adservio.fr");
        assertEquals(1 , employee2.compareTo(employee));

        employee2 = new Employee("DIAB", "Abdessamed", LocalDate.of(1989, 12, 1),
                "abdessamed.diab@adservio.fr");
        assertEquals(-1 , employee2.compareTo(employee));
    }

    @Test
    @Ignore
    public void testClone() {
        try {
            Employee employee2 = employee.clone();
            assertEquals(employee, employee2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
