package frontcontroller;

import controllers.*;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Dispatcher {
    public Dispatcher(Javalin app) {
        ReimbursementController reimbController = new ReimbursementController();
        RoleController roleController = new RoleController();
        StatusController statusController = new StatusController();
        TypeController typeController = new TypeController();
        UserController userController = new UserController();

        app.get("/api/login", context -> {
            context.sessionAttribute("user-session", 123);
            context.result("Logged in");
        });

        app.get("/api/check-session", context -> {
            int key = 0;
            try {
                key = context.sessionAttribute("user-session");
            }catch(Exception e){

            }
            if(key == 123) {
                context.result("session good");
            }else{
                context.result("session not good");
            }
        });

        app.get("/api/logout", context -> {
            context.sessionAttribute("user-session", 0);
            context.result("Logged out");
        });

        app.routes(() -> {
            path("/reimbursements", () -> {
                get(reimbController::getAllTickets);  //form param
                post(reimbController::createReimbursementTicket);  //form params
                path("/{id}", () -> {
                    get(reimbController::getOneTicket); //path param for id
                    patch(reimbController::updateTicket); //form params?
                    put(reimbController::resolveTicket); //form param for approve/deny (boolean?);
                    delete(reimbController::deleteTicket); //path param for id
                });
            });
            path("/users", () -> {
                get(userController::getAllUsers);
                post(userController::createUser);
                path("/{id}", () -> {
                    get(userController::getUserById);
                    patch(userController::updateUser);
                    delete(userController::deleteUser);
                });
            });
            path("/roles", () -> {
                get(roleController::getRoles);
                post(roleController::createRole);
                path("/{id}", () -> {
                   get(roleController::getRoleById);
                   patch(roleController::updateRole);
                   delete(roleController::deleteRole);
                });
            });
            path("/statuses", () -> {
                get(statusController::getAllStatuses);
                post(statusController::createStatus);
                path("/{id}", () -> {
                   get(statusController::getStatusById);
                   patch(statusController::updateStatus);
                   delete(statusController::deleteStatus);
                });
            });
            path("/types", () -> {
                get(typeController::getAllTypes);
                post(typeController::createType);
                path("/{id}", () -> {
                   get(typeController::getTypeById);
                   patch(typeController::updateType);
                   delete(typeController::deleteType);
                });
            });

        });

    }
}
