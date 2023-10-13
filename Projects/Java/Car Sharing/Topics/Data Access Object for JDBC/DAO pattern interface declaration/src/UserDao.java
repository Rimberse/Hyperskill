public interface UserDao {
    public void add(User user);
    public User get(int id);
    public void update(User user);
    public void delete(int id);
}
