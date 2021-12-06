package dao;

import models.ReimbursementType;
import models.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import static org.junit.jupiter.api.Assertions.*;

class RoleDaoTest {
    RoleDaoInterface roleDao;
    @BeforeEach
    void setUp() {
        roleDao = new RoleDao(H2Util.url, H2Util.userName, H2Util.password);
        H2Util.createAll();
    }

    @AfterEach
    void tearDown() {
        H2Util.dropAll();
    }

    @Test
    void createRole() {
        boolean created = roleDao.createRole("MANAGER");

        assertTrue(created);
    }

    @Test
    void getRole() {
        roleDao.createRole("MANAGER");

        UserRole role = roleDao.getRole(1);

        assertEquals(role.getRole(), "MANAGER");
    }

    @Test
    void deleteRole() {
        boolean created = roleDao.createRole("MANAGER");
        assertTrue(created);

        boolean deleted = roleDao.deleteRole(1);
        assertTrue(deleted);

    }

    @Test
    void updateRole() {
        UserRole updatedRole = new UserRole(1,"EMPLOYEE");
        UserRole role = roleDao.updateRole(updatedRole);

        assertEquals(updatedRole, role);
    }
}