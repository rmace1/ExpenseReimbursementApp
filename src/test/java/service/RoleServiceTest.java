package service;

import dao.RoleDao;
import dao.RoleDaoInterface;
import models.User;
import models.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

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
        Mockito.when(roleDao.createRole("DENIED")).thenReturn(true);

        assertTrue(roleService.createRole("DENIED"));
    }

    @Test
    void createNullRole() {
        String role = null;
        //Mockito.when(roleService.createRole(role)).thenReturn(true);

        assertFalse(roleService.createRole(role));
    }

    @Test
    void createEmptyRole() {
        String role = "";
        Mockito.when(roleDao.createRole(role)).thenReturn(true);

        assertFalse(roleService.createRole(role));
    }

    @Test
    void getRole() {
        UserRole role = new UserRole(1, "MANAGER");

        Mockito.when(roleDao.getRole(1)).thenReturn(role);

        UserRole actualRole = roleService.getRole(1);

        assertEquals(role, actualRole);
    }

    @Test
    void getRole0() {
        UserRole role = new UserRole(0, "MANAGER");

        Mockito.when(roleDao.getRole(role.getId())).thenReturn(role);

        UserRole actualRole = roleService.getRole(role.getId());

        assertEquals(null, actualRole);
    }

    @Test
    void getRoles() {
        List<UserRole> roles = new ArrayList<>();
        roles.add(new UserRole(1, "MANAGER"));
        roles.add(new UserRole(2, "EMPLOYEE"));

        Mockito.when(roleDao.getRoles()).thenReturn(roles);

        List<UserRole> actualRoles = roleService.getRoles();

        assertEquals(roles, actualRoles);
    }

    @Test
    void deleteRole() {
        UserRole updatedRole = new UserRole(1, "MANAGER");

        Mockito.when(roleService.getRole(updatedRole.getId())).thenReturn(updatedRole);
        Mockito.when(roleDao.deleteRole(1)).thenReturn(true);

        assertTrue(roleService.deleteRole(1));
    }

    @Test
    void deleteInvalidRole() {
        int roleId = 1000;
        Mockito.when(roleDao.getRole(roleId)).thenReturn(null);
        Mockito.when(roleDao.deleteRole(roleId)).thenReturn(true);

        assertFalse(roleService.deleteRole(roleId));
    }

    @Test
    void updateRole() {
        UserRole updatedRole = new UserRole(1, "MANAGER");

        Mockito.when(roleService.getRole(updatedRole.getId())).thenReturn(updatedRole);
        Mockito.when(roleDao.updateRole(updatedRole)).thenReturn(updatedRole);

        UserRole actualRole = roleService.updateRole(updatedRole);

        assertEquals(updatedRole, actualRole);
    }

    @Test
    void updateInvalidRole() {
        UserRole updatedRole = new UserRole(1000, "MANAGER");

        Mockito.when(roleDao.getRole(updatedRole.getId())).thenReturn(null);

        UserRole actualRole = roleService.updateRole(updatedRole);

        assertEquals(null, actualRole);
    }
}