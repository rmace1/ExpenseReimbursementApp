package controllers;

import io.javalin.http.Context;

public class ApiController {

    public static void login(Context context){
        context.sessionAttribute("user-session", 123);
        context.result("Logged in");
    }

    public static void checkSession(Context context){
        int key = 0;
        try {
            key = context.sessionAttribute("user-session");
        }catch(Exception e){

        }
        if(key == 123) {
            context.result("session good");
        }else{
            context.result("session bad, session very very bad");
        }
    }

    public static void logout(Context context){
        context.sessionAttribute("user-session", 0);
        context.result("Logged out");
    }


}
