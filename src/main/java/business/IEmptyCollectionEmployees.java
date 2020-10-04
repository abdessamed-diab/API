package business;

import business.models.Employee;

import java.time.LocalDate;
import java.util.Set;

/**
 * this interface at variance of {@link ICollectionEmployees} that needs a flat file to create a {@link Set<Employee>}
 * classes that implement this contract interface don't need to load flat file att all, nor to check if records are fully valid.
 * firstly, it create an empty {@link Set<Employee>}, then it feeds directly employees: {@link Set<Employee>}.
 * this approach can be handy for testing scenarios without loading every time flat file.
 */
public interface IEmptyCollectionEmployees {

    boolean addEmployee(Employee employee);
    void    setEmployees(Set<Employee> employees);

    /**
     * for testing purposes, we need to make sure that today is a leaping year or not.
     * @param today the day we want to set as today.
     */
    void    setToday(LocalDate today);

}
