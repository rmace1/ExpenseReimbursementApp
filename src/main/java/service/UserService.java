package service;

import dao.RoleDaoInterface;
import dao.UserDaoInterface;
import models.User;
import models.UserRole;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserService {
    UserDaoInterface userDao;
    RoleDaoInterface roleDao;

    public UserService(UserDaoInterface userDao, RoleDaoInterface roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    //todo: character limit username (50), password(50 if not encrypted), first/last name (100), email(100)
    public boolean createUser(User user) {
        UserRole role = roleDao.getRole(user.getRoleId());
        if( role != null){
        //http://www.jasypt.org/index.html
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        String encryptedPass = encryptor.encryptPassword(user.getPassword());
        user.setPassword(encryptedPass);
            //String encryptPass = user.getPassword();
            return userDao.createUser(user);
        }else {
            return false;
        }
    }

    public List<User> getAllUsers(){

        return userDao.getAllUsers();
    }

     public User getUser(int userId) {

        return userDao.getUser(userId);
     }

     public User getUserByName(User user){
        User employee = userDao.getUserByName(user.getUserName());
        if(employee == null){
            return null;
        }
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        boolean passMatches = encryptor.checkPassword(user.getPassword(), employee.getPassword());


        if(passMatches){
            return employee;
        }else {
            return null;
        }
     }

     public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
     }

     public User updateUser(User updatedUser){
        User user = userDao.getUser(updatedUser.getId());
        User user1 = userDao.getUserByName(updatedUser.getUserName());
        if(user == null && user1 == null){
            return null;
        }

        if(user == null && user1 != null){
            user = user1;
        }

        if(updatedUser.getUserName() != null){user.setUserName(updatedUser.getUserName());}
        if(updatedUser.getPassword() != null){
            StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
            String encryptedPass = encryptor.encryptPassword(updatedUser.getPassword());
            user.setPassword(encryptedPass);
        }
        if(updatedUser.getFirstName() != null){user.setFirstName(updatedUser.getFirstName());}
        if(updatedUser.getLastName() != null){user.setLastName(updatedUser.getLastName());}
        if(updatedUser.getEmail() != null){user.setEmail(updatedUser.getEmail());}
        if(updatedUser.getRoleId() != 0){user.setRoleId(updatedUser.getRoleId());}

        return userDao.updateUser(user);
     }
}
