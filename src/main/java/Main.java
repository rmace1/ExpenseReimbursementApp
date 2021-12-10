import dao.ReimbursementDaoInterface;
import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import models.Reimbursement;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        Javalin server = Javalin.create(config -> {
            //The directory is the location of the static files that are used.
            config.addStaticFiles("/frontend", Location.CLASSPATH);
        }).start(9000);
        new FrontController(server);

        /*
        //http://www.jasypt.org/index.html
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        String encryptedPass = encryptor.encryptPassword("Password#1");
        System.out.println(encryptedPass);
        System.out.println(encryptor.checkPassword("Password#1", encryptedPass));
        */

        /*
        File fi = new File("IMG_0784.jpeg");
        Timestamp ts = new Timestamp(0);
        Reimbursement r = new Reimbursement(1, 5.0, ts, 1, 1, 1, 1);
        r.setReciept( r.toByteArray(fi));
        File fi2 = r.toImage(r.getReciept());
        */

    }
}
