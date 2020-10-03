package business.models;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Cloneable, Serializable, Comparable<User> {
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private String email;

    public User(String lastName, String firstName, LocalDate dateOfBirth, String email) throws IllegalArgumentException{
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
        if (obj == null || !obj.getClass().isAssignableFrom(User.class)) {
            return false;
        }

        User user = (User) obj;

        return lastName.equalsIgnoreCase(user.lastName)
                && firstName.equalsIgnoreCase(user.firstName)
                && dateOfBirth.equals(user.dateOfBirth)
                && email.equalsIgnoreCase(user.email);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                +": first_name: "+firstName
                +", last_name: "+lastName
                +", email: "+email;
    }

    @Override
    protected User clone() throws CloneNotSupportedException {
        return new User(lastName, firstName, dateOfBirth, email);
    }

    @Override
    public int compareTo(User user) {
        int dateBasedComparison = dateOfBirth.compareTo(user.dateOfBirth);
        if (dateBasedComparison != 0) {
            return dateBasedComparison;
        }

        int firstNameBaseComparison = firstName.toLowerCase().compareTo(user.firstName.toLowerCase());
        if (firstNameBaseComparison != 0) {
            return firstNameBaseComparison;
        }

        return lastName.toLowerCase().compareTo(user.lastName.toLowerCase());
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
