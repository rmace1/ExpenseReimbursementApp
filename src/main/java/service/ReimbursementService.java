package service;

import dao.ReimbursementDao;
import dao.ReimbursementDaoInterface;
import models.Reimbursement;

import java.util.List;

public class ReimbursementService {
    ReimbursementDaoInterface reimbDao;

    public ReimbursementService(ReimbursementDaoInterface reimbDao) {
        this.reimbDao = reimbDao;
    }

    public boolean createNewReimbursementTicket(Reimbursement newTicket){


        return reimbDao.createNewTicket(newTicket);

    }

    public Reimbursement getOneTicket(int TicketID){

        return null;
    }

    public List<Reimbursement> getAllTickets(){

        return null;
    }

    public List<Reimbursement> getAllTicketsByUser(int userId){

        return null;
    }

    public List<Reimbursement> getAllTicketsByType(String type){

        return null;
    }

    public List<Reimbursement> getAllTicketsByStatus(String status){

        return null;
    }

    public boolean deleteTicket(int ticketId){

        return false;
    }

    public Reimbursement updateTicket(Reimbursement updatedTicket){

        return null;
    }

}
