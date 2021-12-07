package dao;

import models.Reimbursement;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//connects to/from service layer
public class ReimbursementDao implements ReimbursementDaoInterface{
    ReimbursementDaoInterface dao;
    private java.lang.String url;
    private java.lang.String userName;
    private java.lang.String password;
    Logger log = Logger.getLogger(ReimbursementDao.class);

    public ReimbursementDao(){
        Properties prop = new Properties();
        java.lang.String fileName = "config.txt";

        try (FileInputStream fis = new FileInputStream(fileName)) {

            prop.load(fis);
            url = "jdbc:postgresql://" + prop.getProperty("jdbcConnection") + "/" + prop.getProperty("jdbcDbName");
            userName = prop.getProperty("jdbcUserName");
            password = prop.getProperty("jdbcPassword");

        } catch (Exception e) {
            log.error(e);
        }
    }

    public ReimbursementDao(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Reimbursement getOneTicket(int ticketId) {
        Reimbursement reimbursement = null;

        //TODO: join status and type tables to reimbursement
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            //id, amount, submitted timestamp, resolved timestamp, description, receipt, author, resolver, statusId, typeId
            java.lang.String sql = "SELECT er.*, ers.reimb_status, ert.reimb_type FROM ers_reimbursement er \n" +
                    "JOIN ers_reimbursement_status ers ON er.reimb_status_id = ers.reimb_status_id\n" +
                    "JOIN ers_reimbursement_type ert ON er.reimb_type_id = ert.reimb_type_id \n" +
                    "WHERE er.reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticketId);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                reimbursement = new Reimbursement(rs.getInt(1), rs.getInt(2),
                        rs.getTimestamp(3), rs.getTimestamp(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10));
                reimbursement.setStatus(rs.getString(11));
                reimbursement.setType(rs.getString(12));
            }
            log.info("Reimbursement ticket retrieved from database successfully.");
        } catch (Exception e)
        {
            log.error(e);
        }

        return reimbursement;
    }

    @Override
    public List<Reimbursement> getAllTickets() {
        List<Reimbursement> tickets = new ArrayList<>();

        //TODO: join status and type tables to reimbursement
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT er.*, ers.reimb_status, ert.reimb_type FROM ers_reimbursement er \n" +
                    "JOIN ers_reimbursement_status ers ON er.reimb_status_id = ers.reimb_status_id\n" +
                    "JOIN ers_reimbursement_type ert ON er.reimb_type_id = ert.reimb_type_id \n" +
                    "WHERE er.reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement(rs.getInt(1), rs.getInt(2),
                        rs.getTimestamp(3), rs.getTimestamp(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10));
                reimbursement.setStatus(rs.getString(11));
                reimbursement.setType(rs.getString(12));
                tickets.add(reimbursement);
            }
            log.info("Tickets retrieved.");
            return tickets;
        }catch(Exception e){
            log.error(e);
        }
        return tickets;
    }

    @Override
    public List<Reimbursement> getAllTicketsByUser(int userId){
        List<Reimbursement> tickets = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            //id, amount, submitted timestamp, resolved timestamp, description, receipt, author, resolver, statusId, typeId
            java.lang.String sql = "SELECT er.*, ers.reimb_status, ert.reimb_type FROM ers_reimbursement er \n" +
                    "JOIN ers_reimbursement_status ers ON er.reimb_status_id = ers.reimb_status_id\n" +
                    "JOIN ers_reimbursement_type ert ON er.reimb_type_id = ert.reimb_type_id \n" +
                    "WHERE er.reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement(rs.getInt(1), rs.getInt(2),
                        rs.getTimestamp(3), rs.getTimestamp(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10));
                reimbursement.setStatus(rs.getString(11));
                reimbursement.setType(rs.getString(12));
                tickets.add(reimbursement);
            }
            log.info("Reimbursement tickets retrieved from database successfully.");
        } catch (Exception e)
        {
            log.error(e);
        }
        return tickets;
    }

    @Override
    public List<Reimbursement> getAllTicketsByType(int typeId) {
        List<Reimbursement> tickets = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            //id, amount, submitted timestamp, resolved timestamp, description, receipt, author, resolver, statusId, typeId
            java.lang.String sql = "SELECT er.*, ers.reimb_status, ert.reimb_type FROM ers_reimbursement er \n" +
                    "JOIN ers_reimbursement_status ers ON er.reimb_status_id = ers.reimb_status_id\n" +
                    "JOIN ers_reimbursement_type ert ON er.reimb_type_id = ert.reimb_type_id \n" +
                    "WHERE er.reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, typeId);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement(rs.getInt(1), rs.getInt(2),
                        rs.getTimestamp(3), rs.getTimestamp(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10));
                reimbursement.setStatus(rs.getString(11));
                reimbursement.setType(rs.getString(12));
                tickets.add(reimbursement);
            }
            log.info("Reimbursement tickets of type (" + tickets.get(0).getTypeId() + ") retrieved from database successfully.");
        } catch (Exception e)
        {
            log.error(e);
        }
        return tickets;
    }

    @Override
    public List<Reimbursement> getAllTicketsByStatus(int statusId) {
        List<Reimbursement> tickets = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            //id, amount, submitted timestamp, resolved timestamp, description, receipt, author, resolver, statusId, typeId
            java.lang.String sql = "SELECT er.*, ers.reimb_status, ert.reimb_type FROM ers_reimbursement er \n" +
                    "JOIN ers_reimbursement_status ers ON er.reimb_status_id = ers.reimb_status_id\n" +
                    "JOIN ers_reimbursement_type ert ON er.reimb_type_id = ert.reimb_type_id \n" +
                    "WHERE er.reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, statusId);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement(rs.getInt(1), rs.getInt(2),
                        rs.getTimestamp(3), rs.getTimestamp(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10));
                reimbursement.setStatus(rs.getString(11));
                reimbursement.setType(rs.getString(12));
                tickets.add(reimbursement);
            }
            log.info("Reimbursement tickets of status (" + tickets.get(0).getStatusId() + ") retrieved from database successfully.");
        } catch (Exception e)
        {
            log.error(e);
        }
        return tickets;
    }

    @Override
    public boolean createNewTicket(Reimbursement newTicket) {
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            //id, amount, submitted timestamp, resolved timestamp, description, receipt, author, resolver, statusId, typeId
            java.lang.String sql = "INSERT INTO ers_reimbursement VALUES (DEFAULT, ?, DEFAULT, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, newTicket.getAmount());
            ps.setTimestamp(2, null);// newTicket.getResolved());
            ps.setString(3, "");// newTicket.getDescription());
            ps.setBytes(4, null);//newTicket.getReciept());
            ps.setInt(5, newTicket.getAuthor());
            ps.setInt(6, newTicket.getResolver());
            ps.setInt(7, newTicket.getStatusId());
            ps.setInt(8, newTicket.getTypeId());


            return (ps.executeUpdate() > 0);
        } catch (Exception e)
        {
            log.error(e);
        }
        return false;
    }

    @Override
    public boolean deleteTicket(int ticketId) {
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "DELETE FROM ers_reimbursement WHERE reimb_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticketId);

            boolean deleted = (ps.executeUpdate() > 0);
            log.info("Ticket was deleted: " + deleted);
            return deleted;
        }catch (Exception e){
            log.error(e);
        }
        return false;
    }

    @Override
    public boolean approveTicket(int ticketId, int resolverId) {
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "UPDATE ers_reimbursement SET reimb_resolver = ?, reimb_resolved = ?, reimb_status_id = ? WHERE reimb_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, resolverId);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setInt(3, 2);
            ps.setInt(4, ticketId);


            boolean approved = (ps.executeUpdate() > 0);
            log.info("Ticket was approved: " + approved);
            return approved;
        }catch (Exception e){
            log.error(e);
        }
        return false;
    }

    @Override
    public boolean denyTicket(int ticketId, int resolverId) {
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "UPDATE ers_reimbursement SET reimb_resolver = ?, reimb_resolved = ?, reimb_status_id = ? WHERE reimb_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, resolverId);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setInt(3, 3);
            ps.setInt(4, ticketId);


            boolean denied = (ps.executeUpdate() > 0);
            log.info("Ticket was denied: " + denied);
            return denied;
        }catch (Exception e){
            log.error(e);
        }
        return false;
    }

    @Override
    public Reimbursement updateTicket(Reimbursement updatedTicket) {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = null;
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "UPDATE ers_reimbursement SET reimb_amount = ?, reimb_resolved = ?," +
                    "reimb_description = ?, reimb_reciept = ?, reimb_resolver = ?, reimb_status_id = ?," +
                    "reimb_type_id = ? WHERE reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, updatedTicket.getAmount());
            ps.setTimestamp(2, ts);
            ps.setString(3, updatedTicket.getDescription());
            ps.setBytes(4, updatedTicket.getReciept());
            ps.setInt(5, updatedTicket.getResolver());
            ps.setInt(6, updatedTicket.getStatusId());
            ps.setInt(7, updatedTicket.getTypeId());
            ps.setInt(8, updatedTicket.getId());

            boolean updated = (ps.executeUpdate() > 0);
            log.info("Ticket updated: " + updated);

            ticket = getOneTicket(updatedTicket.getId());

            return ticket;
        }catch (Exception e){
            log.error(e);
        }
        return ticket;
    }

}
