package serverSide;

import business.IEmptyCollectionEmployees;
import business.models.Employee;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class EmptyCollectionEmployeesImpl extends CollectionEmployeesImpl implements IEmptyCollectionEmployees {

    public EmptyCollectionEmployeesImpl() {
        super();
        super.employees = new TreeSet<>();
    }

    @Override
    public synchronized Set<Employee> allEmployees() {
        return super.employees;
    }

    @Override
    public Set<Employee> findEmployeesBornToday() {
        return !super.employees.isEmpty() ? super.findEmployeesBornToday() : super.employees;
    }

    @Override
    public void setToday(LocalDate today) {
        super.today = today;
    }

    public boolean addEmployee(Employee employee) {
        return super.employees.add(employee);
    }

    public synchronized void  setEmployees(Set<Employee> employees) {
        super.employees.clear();
        super.employees.addAll(employees);
    }
}
