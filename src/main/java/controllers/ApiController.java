package controllers;

import dao.RoleDao;
import dao.UserDao;
import io.javalin.http.Context;
import models.JsonResponse;
import models.User;
import models.UserDTO;
import service.UserService;

public class ApiController {
    static UserService userService = new UserService(new UserDao(), new RoleDao());
    public static void login(Context context){
        User user = context.bodyAsClass(User.class);
        User employee = null;
        Boolean loggedIn = false;

        if(user != null) {
            employee = userService.getUserByName(user);
            loggedIn = (employee != null);
        }

        /*
        * if user.password == employee.password continue, else return jsonResponse with false
        * */
        UserDTO userDto = null;
        if(employee != null){
            userDto = new UserDTO(employee);
            context.sessionAttribute("user-session", userDto);

        }else{

        }

        JsonResponse jsonResponse = new JsonResponse(userDto, "Login Attempted.", loggedIn);
        context.json(JsonConverter.convertToJson(jsonResponse));
    }

    public static void checkSession(Context context){
        User user = null;

        UserDTO key = null;
        try {
            key = context.sessionAttribute("user-session");
        }catch(Exception e){

        }

        JsonResponse jsonResponse = new JsonResponse(key, "", false);
        if(key != null) {
            jsonResponse.setSuccessful(true);
            jsonResponse.setMessage("Session is good.");
        }else{
            jsonResponse.setSuccessful(false);
            jsonResponse.setMessage("Session bad, session very very bad.");
        }
        context.json(JsonConverter.convertToJson(jsonResponse));
    }

    public static void logout(Context context){
        context.sessionAttribute("user-session", null);
        context.result("Logged out");
    }


}
