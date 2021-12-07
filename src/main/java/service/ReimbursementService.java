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

    public Reimbursement getOneTicket(int ticketId){

        return reimbDao.getOneTicket(ticketId);
    }

    public List<Reimbursement> getAllTickets(){

        return reimbDao.getAllTickets();
    }

    public List<Reimbursement> getAllTicketsByUser(int userId){

        return reimbDao.getAllTicketsByUser(userId);
    }

    public List<Reimbursement> getAllTicketsByType(int typeId){

        return reimbDao.getAllTicketsByType(typeId);
    }

    public List<Reimbursement> getAllTicketsByStatus(int statusId){

        return reimbDao.getAllTicketsByStatus(statusId);
    }

    public boolean deleteTicket(int ticketId){

        return reimbDao.deleteTicket(ticketId);
    }

    public Reimbursement updateTicket(Reimbursement updatedTicket){

        return reimbDao.updateTicket(updatedTicket);
    }

    public boolean approveTicket(int ticketId, int resolverId){
        return reimbDao.approveTicket(ticketId, resolverId);
    }

    public boolean denyTicket(int ticketId, int resolverId){
        return reimbDao.denyTicket(ticketId, resolverId);
    }
}
