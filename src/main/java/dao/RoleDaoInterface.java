package dao;

import models.UserRole;

import java.util.List;

public interface RoleDaoInterface {
    boolean createRole(String role);
    UserRole getRole(int roleId);
    List<UserRole> getRoles();
    boolean deleteRole(int roleId);
    UserRole updateRole(UserRole role);
}
