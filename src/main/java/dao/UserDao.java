package dao;

import models.ReimbursementType;
import models.User;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDao implements UserDaoInterface {
    UserDaoInterface dao;
    private java.lang.String url;
    private java.lang.String userName;
    private java.lang.String password;
    Logger log = Logger.getLogger(UserDao.class);

    public UserDao(){
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

    public UserDao(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean createUser(User user) {
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "INSERT INTO ers_users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setInt(6, user.getRoleId());

            boolean created = (ps.executeUpdate() > 0);
            log.info("User created: " + created);
            return created;
        }catch(Exception e){
            log.error(e);
        }
        return false;
    }

    @Override
    public User getUser(int userId) {
        User user = null;
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT * FROM ers_users ers JOIN ers_user_roles eur " +
                    "ON ers.user_role_id = eur.ers_user_role_id WHERE ers_users_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                user.setRole(rs.getString(9));
            }

            log.info("User retrieved.");
            return user;
        }catch(Exception e){
            log.error(e);
        }
        return user;
    }

    public User getUserByName(String name){
        User user = null;
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT * FROM ers_users ers JOIN ers_user_roles eur " +
                    "ON ers.user_role_id = eur.ers_user_role_id WHERE ers_username = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                user.setRole(rs.getString(9));
            }

            log.info("User retrieved.");
            return user;
        }catch(Exception e){
            log.error(e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT * FROM ers_users ORDER BY ers_users_id ASC;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                users.add( new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7)));
            }

            log.info("Users retrieved.");
            return users;
        }catch(Exception e){
            log.error(e);
        }
        return users;

    }


    @Override
    public boolean deleteUser(int userId) {
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "DELETE FROM ers_users WHERE ers_users_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            boolean deleted = (ps.executeUpdate() > 0);
            log.info("User deleted: " + deleted);
            return deleted;
        }catch(Exception e){
            log.error(e);
        }
        return false;
    }

    @Override
    public User updateUser(User updatedUser) {
        User user = null;
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "UPDATE ers_users SET ers_username = ?, ers_password =?, user_first_name = ?," +
                    "user_last_name = ?, user_email = ?, user_role_id = ? WHERE ers_users_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, updatedUser.getUserName());
            ps.setString(2, updatedUser.getPassword());
            ps.setString(3, updatedUser.getFirstName());
            ps.setString(4, updatedUser.getLastName());
            ps.setString(5, updatedUser.getEmail());
            ps.setInt(6, updatedUser.getRoleId());
            ps.setInt(7, updatedUser.getId());

            boolean updated = (ps.executeUpdate() > 0);
            log.info("User updated: " + updated);

            user = getUser(updatedUser.getId());

            return user;
        }catch(Exception e){
            log.error(e);
        }
        return user;
    }
}
