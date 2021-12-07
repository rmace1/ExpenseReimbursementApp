import dao.ReimbursementDaoInterface;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
    public static void main(String[] args) {
        Javalin server = Javalin.create(config -> {
            //The directory is the location of the static files that are used.
            config.addStaticFiles("/frontend", Location.CLASSPATH);
        }).start(9000);

       /* Reimbursement r = new Reimbursement();
        File fi = new File("IMG_0784.jpeg");
        System.out.println(fi);
        byte[] b = r.toByteArray(fi);
        System.out.println(b);

        File f2 = r.toImage(b);
        System.out.println(f2);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println(ts);
        Reimbursement newTicket = new Reimbursement(1, 5.00, ts, 1, 1, 1, 1);
        newTicket.setReciept(b);
        //newTicket.setDescription("chiken strips");
        newTicket.setResolved(ts);

        ReimbursementDaoInterface ticketDao = new ReimbursementDao();
        boolean success = ticketDao.createNewTicket(newTicket);
        System.out.println(success);

        //Reimbursement reimbursement = ticketDao.getOneTicket();
        //System.out.println(reimbursement.getReciept());
        //reimbursement.toImage(reimbursement.getReciept());*/

    }
}
