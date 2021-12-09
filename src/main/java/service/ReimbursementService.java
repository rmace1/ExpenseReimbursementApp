package service;

import dao.*;
import models.*;

import java.util.List;

public class ReimbursementService {
    ReimbursementDaoInterface reimbDao;
    UserDaoInterface userDao;
    TypeDaoInterface typeDao;
    StatusDaoInterface statusDao;

    public ReimbursementService(ReimbursementDaoInterface reimbDao, UserDaoInterface userDao,
                                TypeDaoInterface typeDao, StatusDaoInterface statusDao) {
        this.reimbDao = reimbDao;
        this.userDao = userDao;
        this.typeDao = typeDao;
        this.statusDao = statusDao;
    }

    public boolean createNewReimbursementTicket(Reimbursement newTicket){
        //needs to check if user/status/type id exist
        User user;
        ReimbursementType type;
        ReimbursementStatus status;

        user = userDao.getUser(newTicket.getAuthor());
        type = typeDao.getType(newTicket.getTypeId());
        status = statusDao.getStatus(newTicket.getStatusId());

        if(user == null || type == null || status == null){
            return false;
        }else {
            return reimbDao.createNewTicket(newTicket);
        }
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
        User user = userDao.getUser(resolverId);
        if(user != null && user.getRole() == "MANAGER") {
            return reimbDao.approveTicket(ticketId, resolverId);
        }else{
            //todo log error
            return false;
        }
    }

    public boolean denyTicket(int ticketId, int resolverId){
        User user = userDao.getUser(resolverId);
        if(user != null && user.getRole() == "MANAGER") {
            return reimbDao.denyTicket(ticketId, resolverId);
        }else{
            //todo log error
            return false;
        }
    }
}
