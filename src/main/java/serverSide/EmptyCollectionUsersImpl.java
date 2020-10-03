package serverSide;

import business.IEmptyCollectionUsers;
import business.models.User;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class EmptyCollectionUsersImpl extends CollectionUsersImpl implements IEmptyCollectionUsers {

    public EmptyCollectionUsersImpl() {
        super();
        super.users = new TreeSet<>();
    }

    @Override
    public synchronized Set<User> allUsers() {
        return super.users;
    }

    @Override
    public Set<User> findUsersBornToday() {
        return !super.users.isEmpty() ? super.findUsersBornToday() : super.users;
    }

    @Override
    public void setToday(LocalDate today) {
        super.today = today;
    }

    public boolean addUser(User user) {
        return super.users.add(user);
    }

    public synchronized void  setUsers(Set<User> users) {
        super.users.clear();
        super.users.addAll(users);
    }
}
