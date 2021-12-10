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

    //404,
    public void getOneTicket(Context context){
        context.result("Get one ticket method.");

    }

    public void getAllTickets(Context context){
        context.result("Get all tickets method.");
    }

    //may not need
    public void getAllTicketsByType(Context context){

    }

    //may not need
    public void getAllTicketsByStatus(Context context){
        context.result("Get all tickets by status method.");
    }

    public void deleteTicket(Context context){

    }

    public void updateTicket(Context context){

    }

    public void resolveTicket(Context context){

    }

    //may not need
    public void denyTicket(Context context){

    }
}
