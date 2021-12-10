package dao;

import models.ReimbursementStatus;
import models.UserRole;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RoleDao implements RoleDaoInterface{
    RoleDaoInterface dao;
    private java.lang.String url;
    private java.lang.String userName;
    private java.lang.String password;
    Logger log = Logger.getLogger(RoleDao.class);

    public RoleDao(){
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

    public RoleDao(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean createRole(String role) {
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "INSERT INTO ers_user_roles VALUES (DEFAULT, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, role);

            boolean created = (ps.executeUpdate() > 0);
            log.info("New role (" + role + ") created successfully.");
            return created;
        }catch(Exception e){
            log.error(e);
        }
        return false;
    }

    @Override
    public UserRole getRole(int roleId) {
        UserRole role = null;

        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT * FROM ers_user_roles WHERE ers_user_role_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, roleId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                role = new UserRole(rs.getInt(1), rs.getString(2));
            }

            log.info("Role retrieved.");
            return role;
        }catch(Exception e){
            log.error(e);
        }
        return role;
    }

    @Override
    public List<UserRole> getRoles() {
        List<UserRole> roles = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT * FROM ers_user_roles ORDER BY ers_user_role_id ASC;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                roles.add(new UserRole(rs.getInt(1), rs.getString(2)));
            }
            log.info("Statuses retrieved.");
            return roles;
        }catch(Exception e){
            log.error(e);
        }
        return roles;
    }

    @Override
    public boolean deleteRole(int roleId) {
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "DELETE FROM ers_user_roles WHERE ers_user_role_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, roleId);

            boolean deleted = (ps.executeUpdate() > 0);
            log.info("Role deleted: " + deleted);
            return deleted;
        }catch (Exception e){
            log.error(e);
        }
        return false;
    }

    @Override
    public UserRole updateRole(UserRole role) {
        UserRole updatedRole = null;
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "UPDATE ers_user_roles SET user_role = ? WHERE ers_user_role_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, role.getRole());
            ps.setInt(2, role.getId());

            boolean updated = (ps.executeUpdate() > 0);
            log.info("User role updated: " + updated);

            updatedRole = getRole(role.getId());

            return updatedRole;
        }catch (Exception e){
            log.error(e);
        }
        return updatedRole;
    }
}
