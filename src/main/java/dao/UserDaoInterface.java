package dao;

import models.User;

public interface UserDaoInterface {
    boolean createUser(User user);
    User getUser(int userId);
    boolean deleteUser(int userId);
    User updateUser(User updatedUser);
}
