package service;

import dao.RoleDao;
import dao.RoleDaoInterface;
import dao.UserDao;
import dao.UserDaoInterface;
import models.User;
import models.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService;
    UserDaoInterface userDao = Mockito.mock(UserDao.class);
    RoleDaoInterface roleDao = Mockito.mock(RoleDao.class);

    @BeforeEach
    void setUp() {
        userService = new UserService(userDao, roleDao);
    }

    @Test
    void createValidUser() {
        User user = new User(1,"rmace", "password","richard", "mace"
                ,"rmace@revnet.net", 1);
        UserRole role = new UserRole(1, "LODGING");
        Mockito.when(roleDao.getRole(user.getRoleId())).thenReturn(role);
        Mockito.when(userService.createUser(user)).thenReturn(true);


        assertTrue(userService.createUser(user));
    }

    @Test
    void createInvalidUser() {
        User user = new User(1,"rmace", "password","richard", "mace"
                ,"rmace@revnet.net", 10000);
        UserRole role = new UserRole(1, "LODGING");
        //Mockito.when(roleDao.getRole(user.getRoleId())).thenReturn(new UserRole(1, "DENIED"));
        //Mockito.when(userService.createUser(user)).thenReturn(true);


        assertFalse(userService.createUser(user));
    }


    @Test
    void getUser() {
        User user = new User(1,"rmace", "password","richard", "mace"
                ,"rmace@revnet.net", 1);
        Mockito.when(userService.getUser(1)).thenReturn(user);

        User actualUser = userService.getUser(1);

        assertEquals(user, actualUser);
    }

    @Test
    void deleteUser() {
        User user = new User(1,"rmace", "password","richard", "mace"
                ,"rmace@revnet.net", 1);
        UserRole role = new UserRole(1, "LODGING");
        Mockito.when(userService.deleteUser(1)).thenReturn(true);


        assertTrue(userService.deleteUser(1));
    }

    @Test
    void updateUser() {
        User updatedUser = new User(1,"rmace", "password","Ricard", "Mase"
                ,"rmase@revnet.net", 1);
        Mockito.when(userService.updateUser(updatedUser)).thenReturn(updatedUser);

        User actualUser = userService.updateUser(updatedUser);

        assertEquals(updatedUser, actualUser);
    }
}