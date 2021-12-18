package service;

import dao.*;
import models.*;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ReimbursementService {
    ReimbursementDaoInterface reimbDao;
    UserDaoInterface userDao;
    TypeDaoInterface typeDao;
    StatusDaoInterface statusDao;
    Logger log = Logger.getLogger(ReimbursementService.class);


    public ReimbursementService(ReimbursementDaoInterface reimbDao, UserDaoInterface userDao,
                                TypeDaoInterface typeDao, StatusDaoInterface statusDao) {

        this.reimbDao = reimbDao;
        this.userDao = userDao;
        this.typeDao = typeDao;
        this.statusDao = statusDao;
    }

    public byte[] toByteArray(File file){
        byte[] b = null;
        try {
            b = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public File toImage(byte[] bytes){
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(bImage, "jpeg", new File("output123.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File fi = new File("output.jpeg");

        return fi;
    }

    public boolean createNewReimbursementTicket(Reimbursement newTicket){
        //needs to check if user/status/type id exist
        User user;
        ReimbursementType type;
        ReimbursementStatus status;

        user = userDao.getUser(newTicket.getAuthor());

        if(newTicket.getTypeId() > 0) {
            type = typeDao.getType(newTicket.getTypeId());
        }else {
            type = typeDao.getTypeByName(newTicket.getType());
        }

        if(newTicket.getTypeId() > 0) {
            status = statusDao.getStatusById(newTicket.getStatusId());
        }else {
            status = statusDao.getStatusByName("PENDING");
        }

        try {
            newTicket.setStatusId(status.getId());
            newTicket.setStatus(status.getStatus());
            newTicket.setTypeId(type.getId());
        }catch (Exception e){

        }
        if(user == null || type == null){
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

    //todo: add in user validation
    public List<Reimbursement> getAllTicketsByUser(int userId){

        return reimbDao.getAllTicketsByUser(userId);
    }

    //todo: add in type validation
    public List<Reimbursement> getAllTicketsByType(int typeId){

        return reimbDao.getAllTicketsByType(typeId);
    }

    //todo: add in status validation
    public List<Reimbursement> getAllTicketsByStatus(int statusId){

        return reimbDao.getAllTicketsByStatus(statusId);
    }

    //todo: check if ticket is real
    public boolean deleteTicket(int ticketId){

        return reimbDao.deleteTicket(ticketId);
    }

    //todo: check if ticket is real
    public Reimbursement updateTicket(Reimbursement updatedTicket){
        Reimbursement ticket = getOneTicket(updatedTicket.getId());
        if(ticket == null){
            return null;
        }

        if(updatedTicket.getAmount() > 0.00){ticket.setAmount(updatedTicket.getAmount());}
        if(updatedTicket.getStatusId() > 0){ticket.setStatusId(updatedTicket.getStatusId());}
        if(updatedTicket.getTypeId() > 0){ticket.setTypeId(updatedTicket.getTypeId());}

        return reimbDao.updateTicket(ticket);
    }

    //todo: check if ticket is real
    public boolean approveTicket(int ticketId, int resolverId){
        User user = userDao.getUser(resolverId);
        if(user != null && user.getRole().equals("MANAGER")) {
            log.info("Ticket: " + ticketId + " approved by userID: " + resolverId);
            return reimbDao.approveTicket(ticketId, resolverId);
        }else{
            log.error("User ID: " + resolverId + " not found or is not a MANAGER.");
            return false;
        }
    }

    //todo: check if ticket is real
    public boolean denyTicket(int ticketId, int resolverId){
        User user = userDao.getUser(resolverId);
        if(user != null && user.getRole().equals("MANAGER")) {
            log.info("Ticket: " + ticketId + " denied by userID: " + resolverId);
            return reimbDao.denyTicket(ticketId, resolverId);
        }else{
            log.error("User ID: " + resolverId + " not found or is not a MANAGER.");
            return false;
        }
    }
}
