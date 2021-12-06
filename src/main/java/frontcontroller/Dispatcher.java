package frontcontroller;

import controllers.ReimbursementController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Dispatcher {
    public Dispatcher(Javalin app) {
        ReimbursementController reimbController = new ReimbursementController();

        app.routes(() -> {
            path("/tickets", () -> {
                get(reimbController::getAllTickets);
                post(reimbController::createReimbursementTicket);
                path("/{id}", () -> {
                    get(reimbController::getOneTicket);
                });
            });
        });

    }
}
