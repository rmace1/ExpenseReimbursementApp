package dao;

import models.ReimbursementType;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TypeDao implements TypeDaoInterface{
    TypeDaoInterface dao;
    private java.lang.String url;
    private java.lang.String userName;
    private java.lang.String password;
    Logger log = Logger.getLogger(TypeDao.class);

    public TypeDao(){
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

    public TypeDao(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean createType(String type) {
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "INSERT INTO ers_reimbursement_type VALUES (DEFAULT, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type);

            boolean created = (ps.executeUpdate() > 0);
            log.info("Type was created: " + created);
            return created;
        }catch (Exception e){
            log.error(e);
        }
        return false;
    }

    @Override
    public ReimbursementType getType(int typeId) {
        ReimbursementType type = null;
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT * FROM ers_reimbursement_type WHERE reimb_type_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, typeId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                type = new ReimbursementType(rs.getInt(1), rs.getString(2));
            }

            log.info("Type retrieved.");
            return type;
        }catch(Exception e){
            log.error(e);
        }
        return type;
    }

    public ReimbursementType getTypeByName(String typeName){
        ReimbursementType type = null;
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT * FROM ers_reimbursement_type WHERE reimb_type = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, typeName);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                type = new ReimbursementType(rs.getInt(1), rs.getString(2));
            }

            log.info("Type retrieved.");
            return type;
        }catch(Exception e){
            log.error(e);
        }
        return type;
    }


    @Override
    public List<ReimbursementType> getAllTypes() {
        List<ReimbursementType> types = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "SELECT * FROM ers_reimbursement_type ORDER BY reimb_type_id ASC;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                types.add(new ReimbursementType(rs.getInt(1), rs.getString(2)));
            }

            log.info("Types retrieved.");
            return types;
        }catch(Exception e){
            log.error(e);
        }
        return types;
    }

    @Override
    public boolean deleteType(int typeId) {
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "DELETE FROM ers_reimbursement_type WHERE reimb_type_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, typeId);

            boolean deleted = (ps.executeUpdate() > 0);

            log.info("Type deleted: " + deleted);
            return deleted;
        }catch(Exception e){
            log.error(e);
        }
        return false;
    }

    @Override
    public ReimbursementType updateType(ReimbursementType updatedType) {
        ReimbursementType type = null;
        try(Connection conn = DriverManager.getConnection(url, userName, password)){
            String sql = "UPDATE ers_reimbursement_type SET reimb_type = ? WHERE reimb_type_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, updatedType.getType());
            ps.setInt(2, updatedType.getId());

            boolean updated = (ps.executeUpdate() > 0);
            log.info("Type updated: " + updated);

            type =  getType(updatedType.getId());
            return type;
        }catch(Exception e){
            log.error(e);
        }
        return type;
    }
}
