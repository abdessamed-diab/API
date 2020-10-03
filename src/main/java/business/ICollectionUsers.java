package business;

import business.models.User;

import java.util.Set;

public interface ICollectionUsers {

    Set<User> allUsers();

    Set<User> findUsersBornToday();

}
