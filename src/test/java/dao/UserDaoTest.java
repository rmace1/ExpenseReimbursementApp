package dao;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDaoInterface userDao;
    @BeforeEach
    void setUp() {
        userDao = new UserDao(H2Util.url, H2Util.userName, H2Util.password);
        H2Util.createAll();
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
        userDao.createUser(user);

        User user2 = userDao.getUser(1);

        assertEquals(user, user2);
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
        userDao.createUser(user);
        User updatedUser = new User(1,"rmace", "password","richard", "mace"
                ,"rmace@revature.net", 1);
        User resultUser = userDao.updateUser(updatedUser);

        assertEquals(updatedUser, resultUser);
    }
}