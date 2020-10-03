package business;

import business.models.User;

import java.time.LocalDate;
import java.util.Set;

public interface IEmptyCollectionUsers extends ICollectionUsers {

    boolean addUser(User user);
    void    setUsers(Set<User> users);
    void    setToday(LocalDate today);

}
