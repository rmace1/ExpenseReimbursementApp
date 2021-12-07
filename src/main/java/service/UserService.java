package service;

import dao.UserDaoInterface;
import models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {
    UserDaoInterface userDao;

    public UserService(UserDaoInterface userDao){this.userDao = userDao;}

    public boolean createUser(User user) {
        return userDao.createUser(user);
    }

     public User getUser(int userId) {
        return userDao.getUser(userId);
     }

     public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
     }

     public User updateUser(User updatedUser){
        return userDao.updateUser(updatedUser);
     }
}
