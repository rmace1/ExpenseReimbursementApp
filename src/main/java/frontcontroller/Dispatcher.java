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

        app.routes(() -> {
            path("/api/session", () -> {
                get(ApiController::checkSession);
                post(ApiController::login);
                delete(ApiController::logout);
            });
            path("/reimbursements", () -> {
                get(reimbController::getAllTickets);  //form param
                post(reimbController::createReimbursementTicket);  //form params
                path("/{id}", () -> {
                    get(reimbController::getOneTicket); //path param for id
                    patch(reimbController::updateTicket); //form params?
                    put(reimbController::resolveTicket); //form param for approve/deny (boolean?);
                    delete(reimbController::deleteTicket); //path param for id
                });
                path("/statuses/{id}", () -> {
                    get(reimbController::getAllTicketsByStatus);
                });
                path("/types/{id}", () -> {
                    get(reimbController::getAllTicketsByType);
                });
                path("/users/{id}", () -> {
                    get(reimbController::getAllTicketsByUser);
                });
            });
            path("/users", () -> {
                get(userController::getAllUsers);
                post(userController::createUser);
                patch(userController::resetPasswordForUser);
                path("/{id}", () -> {
                    get(userController::getUserById);
                    patch(userController::updateUser);
                    delete(userController::deleteUser);
                });
            });
            path("/roles", () -> {
                get(roleController::getAllRoles);
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
