package dao;

import models.User;

import java.util.List;

public interface UserDaoInterface {
    boolean createUser(User user);
    User getUser(int userId);
    List<User> getAllUsers();
    boolean deleteUser(int userId);
    User updateUser(User updatedUser);
    User getUserByName(String userName);
}
