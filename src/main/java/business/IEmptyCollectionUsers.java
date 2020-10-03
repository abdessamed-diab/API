package business;

import business.models.Employee;

import java.time.LocalDate;
import java.util.Set;

public interface IEmptyCollectionUsers extends ICollectionUsers {

    boolean addUser(Employee employee);
    void    setUsers(Set<Employee> employees);
    void    setToday(LocalDate today);

}
