package dao;

import models.ReimbursementStatus;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StatusDao implements StatusDaoInterface {

    StatusDaoInterface dao;
    private java.lang.String url;
    private java.lang.String userName;
    private java.lang.String password;
    Logger log = Logger.getLogger(StatusDao.class);

    public StatusDao(){
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

    public StatusDao(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean createStatus(String status) {
        try (Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "INSERT INTO ers_reimbursement_status VALUES (DEFAULT, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);

            boolean created = (ps.executeUpdate() > 0);
            log.info("Status was created: " + created);
            return created;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public ReimbursementStatus getStatusById(int statusId) {
        ReimbursementStatus status = null;
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT * FROM ers_reimbursement_status WHERE reimb_status_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, statusId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                status = new ReimbursementStatus(rs.getInt(1), rs.getString(2));
            }
            log.info("Status retrieved.");
            return status;
        }catch(Exception e){
            log.error(e);
        }
        return status;
    }

    public ReimbursementStatus getStatusByName(String statusName){
        ReimbursementStatus status = null;
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT * FROM ers_reimbursement_status WHERE reimb_status = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, statusName);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                status = new ReimbursementStatus(rs.getInt(1), rs.getString(2));
            }
            log.info("Status retrieved.");
            return status;
        }catch(Exception e){
            log.error(e);
        }
        return status;
    }


    @Override
    public List<ReimbursementStatus> getAllStatuses() {
        List<ReimbursementStatus> statuses = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT * FROM ers_reimbursement_status ORDER BY reimb_status_id ASC;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                statuses.add(new ReimbursementStatus(rs.getInt(1), rs.getString(2)));
            }
            log.info("Statuses retrieved.");
            return statuses;
        }catch(Exception e){
            log.error(e);
        }
        return statuses;
    }

    @Override
    public boolean deleteStatus(int statusId) {
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "DELETE FROM ers_reimbursement_status WHERE reimb_status_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, statusId);

            boolean deleted = (ps.executeUpdate() > 0);
            log.info("Status deleted: " + deleted);
            return deleted;
        }catch(Exception e){
            log.error(e);
        }
        return false;
    }

    @Override
    public ReimbursementStatus updateStatus(ReimbursementStatus status) {
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "UPDATE ers_reimbursement_status SET reimb_status = ? WHERE reimb_status_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status.getStatus());
            ps.setInt(2, status.getId());

            boolean updated = (ps.executeUpdate() > 0);
            log.info("Status updated: " + updated);

            return getStatusById(status.getId());
        }catch(Exception e){
            log.error(e);
        }

        return null;
    }
}
