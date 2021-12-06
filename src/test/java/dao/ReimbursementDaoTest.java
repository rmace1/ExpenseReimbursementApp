package dao;

import models.Reimbursement;
import models.ReimbursementType;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementDaoTest {
    ReimbursementDaoInterface reimbDao;
    TypeDaoInterface typeDao;
    StatusDaoInterface statusDao;
    RoleDaoInterface roleDao;
    UserDaoInterface userDao;

    @BeforeEach
    void setUp() {
        reimbDao = new ReimbursementDao(H2Util.url, H2Util.userName, H2Util.password);
        typeDao = new TypeDao(H2Util.url, H2Util.userName, H2Util.password);
        statusDao = new StatusDao(H2Util.url, H2Util.userName, H2Util.password);
        roleDao = new RoleDao(H2Util.url, H2Util.userName, H2Util.password);
        userDao = new UserDao(H2Util.url, H2Util.userName, H2Util.password);
        H2Util.createAll();

        boolean created = typeDao.createType("LODGING");
         created = statusDao.createStatus("Approved");
         created = roleDao.createRole("Peasant");
         created = userDao.createUser(new User(1, "rmace", "password", "richard", "mace",
                "rmace@revnet.net", 1));
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement newTicket = new Reimbursement(1, 5.00, ts, 1, 1, 1, 1);
        created = reimbDao.createNewTicket(newTicket);
    }

    @AfterEach
    void tearDown() {
        H2Util.dropAll();
    }

    @Test
    void getOneTicket() {
        Reimbursement ticket = reimbDao.getOneTicket(1);

        assertTrue(ticket.getId() == 1);
        assertTrue(ticket.getAmount() == 5.00);
    }

    @Test
    void getAllTickets() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement newTicket1 = new Reimbursement(2, 5.00, ts, 1, 1, 1, 1);
        Reimbursement newTicket2 = new Reimbursement(3, 5.00, ts, 1, 1, 1, 1);
        reimbDao.createNewTicket(newTicket1);
        reimbDao.createNewTicket(newTicket2);
        List<Reimbursement> tickets = reimbDao.getAllTickets();

        assertEquals(tickets.size(), 3);
    }

    @Test
    void getAllTicketsByUser() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement newTicket1 = new Reimbursement(2, 5.00, ts, 2, 1, 1, 1);
        Reimbursement newTicket2 = new Reimbursement(3, 5.00, ts, 2, 1, 1, 1);
        Reimbursement newTicket3 = new Reimbursement(4, 5.00, ts, 3, 1, 1, 1);
        Reimbursement newTicket4 = new Reimbursement(5, 5.00, ts, 1, 1, 1, 1);
        reimbDao.createNewTicket(newTicket1);
        reimbDao.createNewTicket(newTicket2);
        reimbDao.createNewTicket(newTicket3);
        reimbDao.createNewTicket(newTicket4);
        List<Reimbursement> tickets = reimbDao.getAllTicketsByUser(2);

        assertEquals(tickets.size(), 2);
    }

    @Test
    void getAllTicketsByType() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement newTicket1 = new Reimbursement(2, 5.00, ts, 2, 1, 1, 1);
        Reimbursement newTicket2 = new Reimbursement(3, 5.00, ts, 2, 1, 1, 2);
        Reimbursement newTicket3 = new Reimbursement(4, 5.00, ts, 3, 1, 1, 2);
        Reimbursement newTicket4 = new Reimbursement(5, 5.00, ts, 1, 1, 1, 2);
        reimbDao.createNewTicket(newTicket1);
        reimbDao.createNewTicket(newTicket2);
        reimbDao.createNewTicket(newTicket3);
        reimbDao.createNewTicket(newTicket4);
        List<Reimbursement> tickets = reimbDao.getAllTicketsByType(2);

        assertEquals(tickets.size(), 3);
    }

    @Test
    void getAllTicketsByStatus() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement newTicket1 = new Reimbursement(2, 5.00, ts, 2, 1, 2, 1);
        Reimbursement newTicket2 = new Reimbursement(3, 5.00, ts, 2, 1, 1, 2);
        Reimbursement newTicket3 = new Reimbursement(4, 5.00, ts, 3, 1, 2, 2);
        Reimbursement newTicket4 = new Reimbursement(5, 5.00, ts, 1, 1, 2, 2);
        reimbDao.createNewTicket(newTicket1);
        reimbDao.createNewTicket(newTicket2);
        reimbDao.createNewTicket(newTicket3);
        reimbDao.createNewTicket(newTicket4);
        List<Reimbursement> tickets = reimbDao.getAllTicketsByStatus(2);

        assertEquals(tickets.size(), 3);
    }

    @Test
    void createNewTicket() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement newTicket1 = new Reimbursement(2, 5.00, ts, 2, 1, 1, 1);
        List<Reimbursement> tickets = reimbDao.getAllTickets();

        assertEquals(tickets.size(), 1);

        reimbDao.createNewTicket(newTicket1);
        tickets = reimbDao.getAllTickets();

        assertEquals(tickets.size(), 2);
    }

    @Test
    void deleteTicket() {
        List<Reimbursement> tickets = reimbDao.getAllTickets();
        boolean deleted = reimbDao.deleteTicket(1);
        assertEquals(tickets.size(), 1);
        assertTrue(deleted);
        tickets = reimbDao.getAllTickets();

        assertEquals(tickets.size(), 0);
    }

    @Test
    void updateTicket() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement updatedTicket = new Reimbursement(1, 50.00, ts, 1, 1, 1, 1);
        Reimbursement ticket = reimbDao.updateTicket(updatedTicket);

        assertTrue(ticket.equals(updatedTicket));
    }
}