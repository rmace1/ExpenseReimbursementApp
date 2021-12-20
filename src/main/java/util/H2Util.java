package util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Util {
    public static String url = "jdbc:h2:./h2/db";
    public static String userName = "sa";
    public static String password = "sa";
    static Logger log = Logger.getLogger(H2Util.class);


    public static void createReimbursementTable(){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String sql = "CREATE TABLE IF NOT EXISTS ERS_REIMBURSEMENT(\n" +
                    "REIMB_ID serial PRIMARY KEY,\n" +
                    "REIMB_AMOUNT DOUBLE PRECISION NOT NULL CHECK (reimb_amount > 0.00),\n" +
                    "REIMB_SUBMITTED TIMESTAMP NOT NULL DEFAULT now(),\n" +
                    "REIMB_RESOLVED TIMESTAMP DEFAULT NULL,\n" +
                    "REIMB_DESCRIPTION VARCHAR(250) DEFAULT '',\n" +
                    "REIMB_RECIEPT BYTEA DEFAULT NULL,\n" +
                    "REIMB_AUTHOR INT NOT NULL REFERENCES ERS_USERS(ERS_USERS_ID) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "REIMB_RESOLVER INT REFERENCES ERS_USERS(ERS_USERS_ID) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "REIMB_STATUS_ID INT REFERENCES ERS_REIMBURSEMENT_STATUS(REIMB_STATUS_ID) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "REIMB_TYPE_ID INT REFERENCES ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            log.error(e);
        }

    }

    public static void createUsersTable(){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String sql = "CREATE TABLE IF NOT EXISTS ers_users (\n" +
                    "ERS_USERS_ID SERIAL PRIMARY KEY,\n" +
                    "ERS_USERNAME VARCHAR(50) NOT NULL UNIQUE,\n" +
                    "ERS_PASSWORD VARCHAR(100) NOT NULL,\n" +
                    "USER_FIRST_NAME VARCHAR(100) NOT NULL,\n" +
                    "USER_LAST_NAME VARCHAR(100) NOT NULL,\n" +
                    "USER_EMAIL VARCHAR(100) NOT NULL UNIQUE,\n" +
                    "USER_ROLE_ID INT REFERENCES ERS_USER_ROLES(ERS_USER_ROLE_ID) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            log.error(e);
        }

    }

    public static void createReimbursementStatusTable(){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String sql = "CREATE TABLE IF NOT EXISTS ers_reimbursement_status(\n" +
                    "REIMB_STATUS_ID SERIAL PRIMARY KEY,\n" +
                    "REIMB_STATUS VARCHAR(10) NOT NULL\n" +
                    ");";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            log.error(e);
        }

    }

    public static void createReimbursementTypeTable(){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String sql = "CREATE TABLE IF NOT EXISTS ers_reimbursement_type (\n" +
                    "REIMB_TYPE_ID SERIAL PRIMARY KEY,\n" +
                    "REIMB_TYPE VARCHAR(10) NOT NULL\n" +
                    ");";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            log.error(e);
        }

    }

    public static void createUserRolesTable(){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String sql = "CREATE TABLE IF NOT EXISTS ers_user_roles (\n" +
                    "ERS_USER_ROLE_ID SERIAL PRIMARY KEY,\n" +
                    "USER_ROLE VARCHAR(10) NOT NULL\n" +
                    ");";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            log.error(e);
        }

    }

    public static void dropReimbursementTable(){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String sql = "DROP TABLE IF EXISTS ers_reimbursement;\n";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            log.error(e);
        }

    }

    public static void dropReimbursementStatusTable(){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String sql = "DROP TABLE IF EXISTS ers_reimbursement_status;\n";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            log.error(e);
        }

    }

    public static void dropReimbursementTypeTable(){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String sql = "DROP TABLE IF EXISTS ers_reimbursement_type ;\n";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            log.error(e);
        }

    }

    public static void dropUserRolesTable(){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String sql = "DROP TABLE IF EXISTS ers_user_roles ;\n";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            log.error(e);
        }

    }

    public static void dropUsersTable(){
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String sql = "DROP TABLE IF EXISTS ers_users;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            log.error(e);
        }

    }

    public static void createAll(){
        createUserRolesTable();
        createReimbursementStatusTable();
        createReimbursementTypeTable();
        createUsersTable();
        createReimbursementTable();

    }

    public static void dropAll(){
        dropReimbursementTable();
        dropUsersTable();
        dropReimbursementStatusTable();
        dropReimbursementTypeTable();
        dropUserRolesTable();
    }
}