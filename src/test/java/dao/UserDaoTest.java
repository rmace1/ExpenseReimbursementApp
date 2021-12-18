package dao;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDaoInterface userDao;
    RoleDaoInterface roleDao;
    @BeforeEach
    void setUp() {
        userDao = new UserDao(H2Util.url, H2Util.userName, H2Util.password);
        roleDao = new RoleDao(H2Util.url, H2Util.userName, H2Util.password);
        H2Util.createAll();
        roleDao.createRole("EMPLOYEE");

    }

    @AfterEach
    void tearDown() {
        H2Util.dropAll();
    }

    @Test
    void createUser() {
        User user = new User(1,"rmace", "password","richard", "mace"
        ,"rmace@revnet.net", 1);
        boolean created = userDao.createUser(user);

        assertTrue(created);
    }

    @Test
    void getUser() {
        User user = new User(1,"rmace", "password","richard", "mace"
                ,"rmace@revnet.net", 1);
        user.setRole("EMPLOYEE");
        boolean created = userDao.createUser(user);

        User user2 = userDao.getUser(1);

        assertEquals(user.toString(), user2.toString());
    }

    @Test
    void deleteUser() {
        User user = new User(1,"rmace", "password","richard", "mace"
                ,"rmace@revnet.net", 1);
        userDao.createUser(user);

        boolean deleted = userDao.deleteUser(1);

        assertTrue(deleted);
    }

    @Test
    void updateUser() {
        User user = new User(1,"rmace", "password","richard", "mace"
                ,"rmace@revnet.net", 1);
        user.setRole("EMPLOYEE");
        boolean created = userDao.createUser(user);
        User updatedUser = new User(1,"rmace", "password","richard", "mace"
                ,"rmace@revature.net", 1);
        updatedUser.setRole("EMPLOYEE");

        User resultUser = userDao.updateUser(updatedUser);

        assertEquals(updatedUser.toString(), resultUser.toString());
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1,"rmace", "password","richard", "mace"
                ,"rmace@revnet.net", 1));

        users.add(new User(2,"rmac", "password","richard", "mac"
                ,"rmac@revnet.net", 1));
        boolean created = userDao.createUser(users.get(0));
        created = userDao.createUser(users.get(1));

        List<User> actualUsers = userDao.getAllUsers();

        //the order returned is ascending by ID so the list order will be reversed
          assertEquals(users.get(0).toString(), actualUsers.get(0).toString());
        assertEquals(users.get(1).toString(), actualUsers.get(1).toString());
        assertEquals(users.size(), actualUsers.size());

    }
}