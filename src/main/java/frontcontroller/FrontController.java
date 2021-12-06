package frontcontroller;

import io.javalin.Javalin;
import io.javalin.core.security.AccessManager;

public class FrontController {
    public FrontController(Javalin app){


        new Dispatcher(app);
    }
}
