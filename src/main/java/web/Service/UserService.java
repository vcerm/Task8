package web.Service;

import web.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void removeUserById(int id);

    void updateUser(User user);

    void addUser(User user);

    User getUserById(int id);
}
