package dao;

import models.ReimbursementStatus;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class StatusDao implements StatusDaoInterface {

    StatusDaoInterface dao;
    private java.lang.String url;
    private java.lang.String userName;
    private java.lang.String password;
    Logger log = Logger.getLogger(ReimbursementDao.class);

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
    public ReimbursementStatus getStatus(int statusId) {
        return null;
    }

    @Override
    public List<ReimbursementStatus> getAllStatuses() {
        return null;
    }

    @Override
    public boolean deleteStatus(int statusId) {
        return false;
    }

    @Override
    public ReimbursementStatus updateStatus(ReimbursementStatus status) {
        return null;
    }
}
