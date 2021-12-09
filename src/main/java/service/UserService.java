package service;

import dao.RoleDaoInterface;
import dao.UserDaoInterface;
import models.User;
import models.UserRole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {
    UserDaoInterface userDao;
    RoleDaoInterface roleDao;

    public UserService(UserDaoInterface userDao, RoleDaoInterface roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    //needs UserRole check
    public boolean createUser(User user) {
        UserRole role = roleDao.getRole(user.getRoleId());
        if( role != null) {
            return userDao.createUser(user);
        }else {
            return false;
        }
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
