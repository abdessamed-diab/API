package business;

import business.models.Employee;

import java.time.format.DateTimeParseException;
import java.util.Set;


public interface ICollectionEmployees {

    /**
     * reads all records of users.csv flat file and convert them to a {@link Set<Employee>}
     * @return {@link Set<Employee>} all Employees.
     */
    Set<Employee> allEmployees() throws DateTimeParseException;

    /**
     * extract only employees with date of birthday is today.
     * @return a set of {@link Set<Employee>} contains only employees with date of birthday is today.
     */
    Set<Employee> findEmployeesDateBirthdayToday();

}
