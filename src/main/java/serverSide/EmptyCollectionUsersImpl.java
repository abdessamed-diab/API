package serverSide;

import business.IEmptyCollectionUsers;
import business.models.Employee;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class EmptyCollectionUsersImpl extends CollectionUsersImpl implements IEmptyCollectionUsers {

    public EmptyCollectionUsersImpl() {
        super();
        super.employees = new TreeSet<>();
    }

    @Override
    public synchronized Set<Employee> allUsers() {
        return super.employees;
    }

    @Override
    public Set<Employee> findUsersBornToday() {
        return !super.employees.isEmpty() ? super.findUsersBornToday() : super.employees;
    }

    @Override
    public void setToday(LocalDate today) {
        super.today = today;
    }

    public boolean addUser(Employee employee) {
        return super.employees.add(employee);
    }

    public synchronized void  setUsers(Set<Employee> employees) {
        super.employees.clear();
        super.employees.addAll(employees);
    }
}
