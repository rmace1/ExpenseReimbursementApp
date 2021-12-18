package controllers;

import dao.RoleDao;
import dao.UserDao;
import io.javalin.http.Context;
import models.JsonResponse;
import models.ReimbursementStatus;
import models.User;
import org.apache.log4j.Logger;
import service.UserService;
import util.Email;

import java.util.List;

public class UserController {
    UserService userService = new UserService(new UserDao(),new RoleDao());
    Logger log = Logger.getLogger(UserController.class);

    public void getAllUsers(Context context) {
        List<User> users;

        users = userService.getAllUsers();

        JsonResponse jsonResponse = new JsonResponse(users, "", true);


        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void createUser(Context context) {
        int id = 0;
        String userName = "";
        String password = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        int roleId = 0;

        try{
            userName = context.formParam("userName");
            password = context.formParam("password");
            firstName = context.formParam("firstName");
            lastName = context.formParam("lastName");
            email = context.formParam("email");
            roleId = Integer.parseInt(context.formParam("roleId"));
        }catch (Exception e){
            log.error(e);
        }

        User user = new User(id, userName, password, firstName, lastName, email, roleId);
        Boolean created = userService.createUser(user);

        JsonResponse jsonResponse = new JsonResponse(null, "", created);

        if(created){
            context.status(200);
        }else{
            context.status(400);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void getUserById(Context context) {
        int id = 0;

        try{
            id = Integer.parseInt(context.pathParam("id"));
        }catch (Exception e){

        }

        User user = userService.getUser(id);

        Boolean successful = (user != null);
        JsonResponse jsonResponse = new JsonResponse(user, "", successful);

        if(successful){
            context.status(200);
        }else{
            jsonResponse.setMessage("User with id: " + id + " Not found.");
            context.status(404);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void updateUser(Context context) {
        int id = 0;
        String userName = "";
        String password = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        int roleId = 0;

        try{
            id = Integer.parseInt(context.pathParam("id"));
        }catch (Exception e){
            log.error(e);
        }
        try{
            userName = context.formParam("userName");
        }catch (Exception e){
            log.error(e);
        }
        try{
                password = context.formParam("password");
        }catch (Exception e){
            log.error(e);
        }
        try{
                firstName = context.formParam("firstName");
        }catch (Exception e){
            log.error(e);
        }
        try{
                lastName = context.formParam("lastName");
        }catch (Exception e){
            log.error(e);
        }
        try{
                email = context.formParam("email");
        }catch (Exception e){
            log.error(e);
        }
        try{
            roleId = Integer.parseInt(context.formParam("roleId"));
        }catch (Exception e){
            log.error(e);
        }

        User user = new User(id, userName, password, firstName, lastName, email, roleId);
        User updatedUser = userService.updateUser(user);
        Boolean created = updatedUser != null;

        JsonResponse jsonResponse = new JsonResponse(updatedUser, "", created);

        if(created){
            context.status(200);
        }else{
            context.status(400);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void deleteUser(Context context) {
        int id = 0;
        JsonResponse jsonResponse = new JsonResponse(null, "", false);

        try{
            id = Integer.parseInt(context.pathParam("id"));
        }catch (Exception e){
            log.error(e);
        }

        Boolean deleted = userService.deleteUser(id);
        jsonResponse.setSuccessful(deleted);

        if(deleted){
            context.status(200);
        }else{
            context.status(400);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void resetPasswordForUser(Context context) {
        String userName = context.formParam("userName");
        User user = new User();
        user.setUserName(userName);

        user.setPassword("password123");

        User employee = userService.updateUser(user);

        Boolean reset = false;
        if(employee != null){
            reset = Email.sendEmail(employee.getEmail(), "Password rest",
                    "Your password has been reset to: \"password123\"");
        }

        JsonResponse jsonResponse = new JsonResponse(employee, "Reset email sent.", reset);
        context.status(200).result(JsonConverter.convertToJson(jsonResponse));
    }
}
