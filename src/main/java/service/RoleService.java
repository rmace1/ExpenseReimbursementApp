package service;

import dao.RoleDaoInterface;
import models.UserRole;

import java.util.List;

public class RoleService {
    RoleDaoInterface roleDao;

    public RoleService(RoleDaoInterface roleDao) {this.roleDao = roleDao;}

    public boolean createRole(String role){
        //todo: Add in character limit check on role string
        if(role != null && role != "") {
            return roleDao.createRole(role);
        }else{
            return false;
        }
    }

    public UserRole getRole(int roleId){
        if(roleId > 0) {
            return roleDao.getRole(roleId);
        }else{
            return null;
        }
    }

    public List<UserRole> getRoles(){
        return roleDao.getRoles();
    }

    public boolean deleteRole(int roleId){
        UserRole role = getRole(roleId);
        if(role != null) {
            return roleDao.deleteRole(roleId);
        }else{
            return false;
        }
    }

    public UserRole updateRole(UserRole role){
        UserRole updatedRole = getRole(role.getId());
        if(updatedRole != null) {
            return roleDao.updateRole(role);
        }else{
            return null;
        }
    }
}
