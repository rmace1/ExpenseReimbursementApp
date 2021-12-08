package service;

import dao.RoleDao;
import dao.RoleDaoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class RoleServiceTest {
    RoleService roleService;
    RoleDaoInterface roleDao = Mockito.mock(RoleDao.class);

    @BeforeEach
    void setUp() {
        roleService = new RoleService(roleDao);
    }

    @Test
    void createRole() {
    }

    @Test
    void getRole() {
    }

    @Test
    void getRoles() {
    }

    @Test
    void deleteRole() {
    }

    @Test
    void updateRole() {
    }
}