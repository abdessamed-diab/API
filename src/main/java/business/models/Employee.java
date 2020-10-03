package business.models;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Cloneable, Serializable, Comparable<Employee> {
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private String email;

    public Employee(String lastName, String firstName, LocalDate dateOfBirth, String email) throws IllegalArgumentException{
        if (StringUtils.isEmpty(lastName) || StringUtils.isEmpty(lastName) || dateOfBirth == null || StringUtils.isEmpty(lastName)) {
            throw new IllegalArgumentException("all fields mandatory.");
        }
        this.lastName= lastName;
        this.firstName = firstName;
        this.dateOfBirth= dateOfBirth;
        this.email= email;
    }

    @Override
    public int hashCode() {
        return lastName.hashCode()
                + firstName.hashCode()
                + dateOfBirth.hashCode()
                + email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().isAssignableFrom(Employee.class)) {
            return false;
        }

        Employee employee = (Employee) obj;

        return lastName.equalsIgnoreCase(employee.lastName)
                && firstName.equalsIgnoreCase(employee.firstName)
                && dateOfBirth.equals(employee.dateOfBirth)
                && email.equalsIgnoreCase(employee.email);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                +": first_name: "+firstName
                +", last_name: "+lastName
                +", email: "+email;
    }

    @Override
    protected Employee clone() throws CloneNotSupportedException {
        return new Employee(lastName, firstName, dateOfBirth, email);
    }

    @Override
    public int compareTo(Employee employee) {
        int dateBasedComparison = dateOfBirth.compareTo(employee.dateOfBirth);
        if (dateBasedComparison != 0) {
            return dateBasedComparison;
        }

        int firstNameBaseComparison = firstName.toLowerCase().compareTo(employee.firstName.toLowerCase());
        if (firstNameBaseComparison != 0) {
            return firstNameBaseComparison;
        }

        return lastName.toLowerCase().compareTo(employee.lastName.toLowerCase());
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }
}
