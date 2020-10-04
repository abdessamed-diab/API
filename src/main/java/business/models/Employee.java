package business.models;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * base entity class, each line declared in the flat CSV file represent an instance of this class.
 */
public class Employee implements Cloneable, Serializable, Comparable<Employee> {
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private String email;

    /**
     * in order to create an Employee, all fields are mandatory, no default constructor is needed.
     * @param lastName first-column declared in the flat file represent the lastName of Employee
     * @param firstName second-column declared in the flat file represent firstName of Employee
     * @param dateOfBirth third-column declared in the flat file represent date of birth
     * @param email last-column represent employee's email address.
     * @throws IllegalArgumentException can't create an instance of this class unless you provide all fields.
     */
    public Employee(String lastName, String firstName, LocalDate dateOfBirth, String email) throws IllegalArgumentException{
        if (StringUtils.isEmpty(lastName) || StringUtils.isEmpty(lastName) || dateOfBirth == null || StringUtils.isEmpty(lastName)) {
            throw new IllegalArgumentException("all fields mandatory.");
        }
        this.lastName= lastName;
        this.firstName = firstName;
        this.dateOfBirth= dateOfBirth;
        this.email= email;
    }

    /**
     * since we are using a {@link java.util.Set<Employee>}, overriding @hashCode method is a great way to ensure consistency.
     * @return hashCode of given instance.
     */
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

    /**
     * cloning some employees can become handy, specially if we want to make copies rapidly without using initializing constructor.
     * @return a copy of given employee.
     * @throws CloneNotSupportedException {@link Employee} class implement already {@link Cloneable} interface.
     */
    @Override
    protected Employee clone() throws CloneNotSupportedException {
        return new Employee(lastName, firstName, dateOfBirth, email);
    }

    /**
     * since we are using a {@link java.util.Set<Employee>}, then we should implement {@link Comparable} interface.
     * @param employee employee we want to compare to.
     * @return -1 if current employee was born before the compared-to employee,
     * 1 if current employee was born after the compared-to employee.
     * if two employees were born at the same day, then compare other fields @firstName and @lastName respectively.
     */
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
