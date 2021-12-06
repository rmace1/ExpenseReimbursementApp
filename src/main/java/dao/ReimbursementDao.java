package dao;

import models.Reimbursement;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            java.lang.String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticketId);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                reimbursement = new Reimbursement(rs.getInt(1), rs.getInt(2),
                        rs.getTimestamp(3), rs.getTimestamp(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10));
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
        return null;
    }

    @Override
    public List<Reimbursement> getAllTicketsByUser(int userId){
        List<Reimbursement> tickets = null;
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            //id, amount, submitted timestamp, resolved timestamp, description, receipt, author, resolver, statusId, typeId
            java.lang.String sql = "SELECT * FROM ers_reimbursement";
            PreparedStatement ps = conn.prepareStatement(sql);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                tickets.add(new Reimbursement(rs.getInt(1), rs.getInt(2),
                        rs.getTimestamp(3), rs.getTimestamp(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10)));
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
        List<Reimbursement> tickets = null;
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            //id, amount, submitted timestamp, resolved timestamp, description, receipt, author, resolver, statusId, typeId
            java.lang.String sql = "SELECT * FROM ers_reimbursement WHERE reimb_type_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, typeId);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                tickets.add(new Reimbursement(rs.getInt(1), rs.getInt(2),
                        rs.getTimestamp(3), rs.getTimestamp(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10)));
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
        List<Reimbursement> tickets = null;
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            //id, amount, submitted timestamp, resolved timestamp, description, receipt, author, resolver, statusId, typeId
            java.lang.String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, statusId);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                tickets.add(new Reimbursement(rs.getInt(1), rs.getInt(2),
                        rs.getTimestamp(3), rs.getTimestamp(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10)));
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
        return false;
    }

    @Override
    public Reimbursement updateTicket(Reimbursement updatedTicket) {
        return null;
    }

}
