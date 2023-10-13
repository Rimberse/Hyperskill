import java.util.ArrayList;
import java.util.List;

/* Do not change code below */
public class UserDaoImpl implements UserDao {
    private final List<User> users;

    public UserDaoImpl() {
        users = new ArrayList<>();
    }

    @Override
    public void add(User user) {
        users.add(user);
        System.out.println(user + ", added");
    }

    @Override
    public User get(int id) {
        User found = findById(id);
        if (found == null) {
            System.out.println("User : id " + id + ", not found");
            return null;
        }
        System.out.println(found + ", found");
        return new User(found.getId(), found.getName());

    }

    @Override
    public void update(User user) {
        User found = findById(user.getId());
        if (found != null) {
            found.setName(user.getName());
            System.out.println(found + ", updated");
        } else {
            System.out.println("User " + user.getId() + ", not found");
        }

    }

    @Override
    public void delete(int id) {
        User found = findById(id);
        if (found != null) {
            users.remove(found);
            System.out.println(found + ", deleted");
        } else {
            System.out.println("User " + id + ", not found");
        }

    }

    private User findById(int id) {
        for (User user : users) {
            if (id == user.getId()) {
                return user;
            }
        }
        return null;
    }
}
