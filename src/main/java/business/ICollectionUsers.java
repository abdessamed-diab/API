package business;

import business.models.Employee;

import java.util.Set;

public interface ICollectionUsers {

    Set<Employee> allUsers();

    Set<Employee> findUsersBornToday();

}
