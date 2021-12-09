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
        Mockito.when(roleService.createRole("DENIED")).thenReturn(true);

        assertTrue(roleService.createRole("DENIED"));
    }

    @Test
    void getRole() {
        UserRole role = new UserRole(1, "MANAGER");

        Mockito.when(roleService.getRole(1)).thenReturn(role);

        UserRole actualRole = roleService.getRole(1);

        assertEquals(role, actualRole);
    }

    @Test
    void getRoles() {
        List<UserRole> roles = new ArrayList<>();
        roles.add(new UserRole(1, "MANAGER"));
        roles.add(new UserRole(2, "EMPLOYEE"));

        Mockito.when(roleService.getRoles()).thenReturn(roles);

        List<UserRole> actualRoles = roleService.getRoles();

        assertEquals(roles, actualRoles);
    }

    @Test
    void deleteRole() {
        Mockito.when(roleService.deleteRole(1)).thenReturn(true);

        assertTrue(roleService.deleteRole(1));
    }

    @Test
    void updateRole() {
        UserRole updatedRole = new UserRole(1, "MANAGER");

        Mockito.when(roleService.updateRole(updatedRole)).thenReturn(updatedRole);

        UserRole actualRole = roleService.updateRole(updatedRole);

        assertEquals(updatedRole, actualRole);
    }
}