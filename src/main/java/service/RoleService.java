package service;

import dao.RoleDaoInterface;
import models.UserRole;

import java.util.List;

public class RoleService {
    RoleDaoInterface roleDao;

    public RoleService(RoleDaoInterface roleDao) {this.roleDao = roleDao;}

    public boolean createRole(String role){
        if(role != null && role != "") {
            return roleDao.createRole(role);
        }else{
            return false;
        }
    }

    public UserRole getRole(int roleId){
        return roleDao.getRole(roleId);
    }

    public List<UserRole> getRoles(){
        return roleDao.getRoles();
    }

    public boolean deleteRole(int roleId){
        return roleDao.deleteRole(roleId);
    }

    public UserRole updateRole(UserRole role){
        return roleDao.updateRole(role);
    }
}
