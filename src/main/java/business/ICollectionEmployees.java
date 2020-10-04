package business;

import business.models.Employee;

import java.util.Set;

public interface ICollectionEmployees {

    Set<Employee> allEmployees();

    Set<Employee> findEmployeesBornToday();

}
