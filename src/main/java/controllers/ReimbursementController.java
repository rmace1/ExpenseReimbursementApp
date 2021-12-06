package controllers;

import dao.ReimbursementDao;
import dao.ReimbursementDaoInterface;
import io.javalin.http.Context;
import models.Reimbursement;
import service.ReimbursementService;

import java.util.List;

public class ReimbursementController {
    public ReimbursementService reimbService = new ReimbursementService(new ReimbursementDao());

    public ReimbursementController(){


    }

    public void createReimbursementTicket(Context context){
        Reimbursement ticket = new Reimbursement();
        reimbService.createNewReimbursementTicket(ticket);

    }

    public void getOneTicket(Context context){

    }

    public void getAllTickets(Context context){
        context.redirect("./HTML/test.html");
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
