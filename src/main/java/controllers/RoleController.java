package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.RoleDao;
import io.javalin.http.Context;
import models.UserRole;
import service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class RoleController {
    RoleService roleService = new RoleService(new RoleDao());

    public void getAllRoles(Context context) {
        List<UserRole> userRoles = new ArrayList<>();
        userRoles = roleService.getRoles();
        context.contentType("application/json");
        context.status(200);
        context.result(JsonConverter.convertToJson(userRoles));
    }

    public void createRole(Context context) {
        String newRole = context.formParam("role");

        Boolean created = roleService.createRole(newRole);

    }

    public void getRoleById(Context context) {
    }

    public void updateRole(Context context) {
    }

    public void deleteRole(Context context) {
    }
}
