package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.RoleDao;
import dao.UserDao;
import io.javalin.http.Context;
import models.JsonResponse;
import models.UserRole;
import org.apache.log4j.Logger;
import service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class RoleController {
    RoleService roleService = new RoleService(new RoleDao());
    Logger log = Logger.getLogger(RoleController.class);


    public void getAllRoles(Context context) {
        List<UserRole> userRoles = new ArrayList<>();
        userRoles = roleService.getRoles();

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setObject(userRoles);

        context.contentType("application/json");
        context.status(200);
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void createRole(Context context) {
        String newRole = context.formParam("role");

        Boolean created = roleService.createRole(newRole);

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setSuccessful(created);
        jsonResponse.setMessage("Role: " + newRole + " created " + created + ".");

        if(created){
            context.status(200);
        }else{
            log.error("Role: " + newRole + " unable to be created.");
            context.status(400);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));

    }

    public void getRoleById(Context context) {
        int roleId = 0;

        try {
            roleId = Integer.parseInt(context.pathParam("id"));
        }catch (Exception e){
            log.error(e);
        }

        UserRole role = roleService.getRole(roleId);
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setObject(role);

        if(role != null){
            jsonResponse.setSuccessful(true);
            context.status(200);
        }else{
            log.error("Role: " + roleId + " unable to be retrieved.");
            context.status(400);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void updateRole(Context context) {
        int roleId = 0;
        String role = "";

        try{
            roleId = Integer.parseInt(context.pathParam("id"));
            role = context.formParam("role");
        }catch (Exception e){
            log.error(e);
        }

        UserRole newRole = new UserRole(roleId, role);

        UserRole updatedRole = roleService.updateRole(newRole);

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setObject(updatedRole);

        if(role != null){
            jsonResponse.setSuccessful(true);
            context.status(200);
        }else{
            log.error("Role: " + roleId + " unable to be updated.");
            context.status(400);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));

    }

    public void deleteRole(Context context) {
        int roleId = 0;

        try{
            roleId = Integer.parseInt(context.pathParam("id"));
        }catch (Exception e){
            log.error(e);
        }

        Boolean deleted = roleService.deleteRole(roleId);

        JsonResponse jsonResponse = new JsonResponse();

        if(deleted){
            jsonResponse.setSuccessful(true);
            context.status(200);
        }else{
            log.error("Role: " + roleId + " unable to be deleted.");
            context.status(400);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }
}
