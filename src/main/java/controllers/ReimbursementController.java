package controllers;

import dao.ReimbursementDao;
import dao.StatusDao;
import dao.TypeDao;
import dao.UserDao;
import io.javalin.http.Context;
import models.Reimbursement;
import service.ReimbursementService;

public class ReimbursementController {
    public ReimbursementService reimbService = new ReimbursementService(new ReimbursementDao(), new UserDao(), new TypeDao(), new StatusDao());

    public ReimbursementController(){


    }

    public void createReimbursementTicket(Context context){
        Reimbursement ticket = new Reimbursement();
        reimbService.createNewReimbursementTicket(ticket);

    }

    public void getOneTicket(Context context){

    }

    public void getAllTickets(Context context){
        context.redirect("frontend/test.html");
    }

    public void getAllTicketsByType(Context context){

    }

    public void getAllTicketsByStatus(Context context){

    }

    public void deleteTicket(Context context){

    }

    public void updateTicket(Context context){

    }
}
