package dao;

import models.UserRole;

public interface RoleDaoInterface {
    boolean createRole(String role);
    UserRole getRole(int roleId);
    boolean deleteRole(int roleId);
    UserRole updateRole(UserRole role);
}
