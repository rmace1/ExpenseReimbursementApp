package dao;

import models.ReimbursementType;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

public class TypeDao implements TypeDaoInterface{
    TypeDaoInterface dao;
    private java.lang.String url;
    private java.lang.String userName;
    private java.lang.String password;
    Logger log = Logger.getLogger(ReimbursementDao.class);

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
        return null;
    }

    @Override
    public List<ReimbursementType> getAllTypes() {
        return null;
    }

    @Override
    public boolean deleteType(int typeId) {
        return false;
    }

    @Override
    public ReimbursementType updateType(ReimbursementType updatedType) {
        return null;
    }
}
