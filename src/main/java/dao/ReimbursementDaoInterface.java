package dao;

import models.Reimbursement;

import java.util.List;

public interface ReimbursementDaoInterface {

    Reimbursement getOneTicket(int ticketId);
    List<Reimbursement> getAllTickets();
    List<Reimbursement> getAllTicketsByUser(int userId);
    List<Reimbursement> getAllTicketsByType(int typeId);
    List<Reimbursement> getAllTicketsByStatus(int statusId);
    boolean createNewTicket(Reimbursement newTicket);
    boolean deleteTicket(int ticketId);
    boolean approveTicket(int ticketId, int resolverId);
    boolean denyTicket(int ticketId, int resolverId);
    Reimbursement updateTicket(Reimbursement updatedTicket);

}
