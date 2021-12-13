package controllers;

import io.javalin.http.Context;
import models.JsonResponse;
import models.User;

public class ApiController {
    public static void login(Context context){
        User user = context.bodyAsClass(User.class);
        System.out.println(user.toString());
        //todo: get user based on username (either string or have them sign in using their employee id

        JsonResponse jsonResponse = new JsonResponse(user, "Logged in", true);
        context.sessionAttribute("user-session", user.getUserName());
        context.json(JsonConverter.convertToJson(jsonResponse));
    }

    public static void checkSession(Context context){
        User user = null;
        //System.out.println(user.toString());
        String key = "";
        try {
            //user = context.bodyAsClass(User.class);
            key = context.sessionAttribute("user-session");
        }catch(Exception e){

        }
       //System.out.println(user.toString());
        JsonResponse jsonResponse = new JsonResponse(null, "", false);
        if(key == "rmace") {
            jsonResponse.setSuccessful(true);
            jsonResponse.setMessage("Session is good.");
        }else{
            jsonResponse.setSuccessful(false);
            jsonResponse.setMessage("Session bad, session very very bad.");
        }
        context.json(JsonConverter.convertToJson(jsonResponse));
    }

    public static void logout(Context context){
        context.sessionAttribute("user-session", 0);
        context.result("Logged out");
    }


}
