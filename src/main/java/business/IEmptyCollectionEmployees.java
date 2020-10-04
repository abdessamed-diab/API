package business;

import business.models.Employee;

import java.time.LocalDate;
import java.util.Set;

public interface IEmptyCollectionEmployees {

    boolean addEmployee(Employee employee);
    void    setEmployees(Set<Employee> employees);
    void    setToday(LocalDate today);

}
