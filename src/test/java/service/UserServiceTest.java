package service;

import dao.UserDao;
import dao.UserDaoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService;
    UserDaoInterface userDao = Mockito.mock(UserDao.class);

    @BeforeEach
    void setUp() {
        userService = new UserService(userDao);
    }

    @Test
    void createUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}