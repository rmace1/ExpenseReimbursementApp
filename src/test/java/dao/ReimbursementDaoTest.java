package dao;

import models.Reimbursement;
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
         created = statusDao.createStatus("PENDING");
         created = roleDao.createRole("EMPLOYEE");
         created = userDao.createUser(new User(1, "rmace", "password", "richard", "mace",
                "rmace@revnet.net", 1));
        Timestamp ts = new Timestamp(0);
        Reimbursement newTicket = new Reimbursement(1, 5.00, ts, 1, 1, 1);
        newTicket.setResolver(1);
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
        Reimbursement newTicket1 = new Reimbursement(2, 5.00, ts, 1, 1, 1);
        newTicket1.setResolver(1);
        Reimbursement newTicket2 = new Reimbursement(3, 5.00, ts, 1, 1, 1);
        newTicket2.setResolver(1);
        boolean created = reimbDao.createNewTicket(newTicket1);
        created = reimbDao.createNewTicket(newTicket2);
        List<Reimbursement> tickets = reimbDao.getAllTickets();

        assertEquals(tickets.size(), 3);
    }

    @Test
    void getAllTicketsByUser() {
        userDao.createUser(new User(2, "rmac", "password1", "rick", "mac",
                "rmac@revnet.net", 1));
        userDao.createUser(new User(3, "rmacce", "password2", "Richard", "Macce",
                "rmacce@revnet.net", 1));
        userDao.createUser(new User(4, "dmase", "password3", "Dick", "Mase",
                "dmase@revnet.net", 1));
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement newTicket1 = new Reimbursement(2, 5.00, ts, 2, 1, 1);
        Reimbursement newTicket2 = new Reimbursement(3, 5.00, ts, 2, 1, 1);
        Reimbursement newTicket3 = new Reimbursement(4, 5.00, ts, 3, 1, 1);
        Reimbursement newTicket4 = new Reimbursement(5, 5.00, ts, 1, 1, 1);
        newTicket1.setResolver(1);
        newTicket2.setResolver(1);
        newTicket3.setResolver(1);
        newTicket4.setResolver(1);

        reimbDao.createNewTicket(newTicket1);
        reimbDao.createNewTicket(newTicket2);
        reimbDao.createNewTicket(newTicket3);
        reimbDao.createNewTicket(newTicket4);
        List<Reimbursement> tickets = reimbDao.getAllTicketsByUser(2);

        assertEquals(tickets.size(), 2);
    }

    @Test
    void getAllTicketsByType() {
        typeDao.createType("LODGING");
        typeDao.createType("TRAVEL");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement newTicket1 = new Reimbursement(2, 5.00, ts, 1, 1, 1);
        Reimbursement newTicket2 = new Reimbursement(3, 5.00, ts, 1, 1, 2);
        Reimbursement newTicket3 = new Reimbursement(4, 5.00, ts, 1, 1, 2);
        Reimbursement newTicket4 = new Reimbursement(5, 5.00, ts, 1, 1, 2);
        newTicket1.setResolver(1);
        newTicket2.setResolver(1);
        newTicket3.setResolver(1);
        newTicket4.setResolver(1);
        reimbDao.createNewTicket(newTicket1);
        reimbDao.createNewTicket(newTicket2);
        reimbDao.createNewTicket(newTicket3);
        reimbDao.createNewTicket(newTicket4);
        List<Reimbursement> tickets = reimbDao.getAllTicketsByType(2);

        assertEquals(tickets.size(), 3);
    }

    @Test
    void getAllTicketsByStatus() {
        statusDao.createStatus("APPROVED");
        statusDao.createStatus("REJECTED");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement newTicket1 = new Reimbursement(2, 5.00, ts, 1, 2, 1);
        Reimbursement newTicket2 = new Reimbursement(3, 5.00, ts, 1, 1, 1);
        Reimbursement newTicket3 = new Reimbursement(4, 5.00, ts, 1, 2, 1);
        Reimbursement newTicket4 = new Reimbursement(5, 5.00, ts, 1, 2, 1);
        newTicket1.setResolver(1);
        newTicket2.setResolver(1);
        newTicket3.setResolver(1);
        newTicket4.setResolver(1);
        reimbDao.createNewTicket(newTicket1);
        reimbDao.createNewTicket(newTicket2);
        reimbDao.createNewTicket(newTicket3);
        reimbDao.createNewTicket(newTicket4);
        List<Reimbursement> tickets = reimbDao.getAllTicketsByStatus(2);

        assertEquals(3, tickets.size());
    }

    @Test
    void createNewTicket() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Reimbursement newTicket1 = new Reimbursement(2, 5.00, ts, 1, 1, 1);
        newTicket1.setResolver(1);
        newTicket1.setDescription("ALL THE MICKEY MOUSE EARS!!!!");
        boolean created = reimbDao.createNewTicket(newTicket1);
        List<Reimbursement> tickets = reimbDao.getAllTickets();

        assertEquals(tickets.size(), 2);

        created = reimbDao.createNewTicket(newTicket1);
        tickets = reimbDao.getAllTickets();

        assertEquals(tickets.size(), 3);
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
        Timestamp ts = new Timestamp(0);
        Reimbursement newTicket = new Reimbursement(2, 5.00, ts, 1, 1, 1);
        newTicket.setResolver(1);

        reimbDao.createNewTicket(newTicket);
        Reimbursement updatedTicket = new Reimbursement(2, 50.00, ts, 1, 1, 1);
        updatedTicket.setResolver(1);
        updatedTicket.setResolved(ts);
        updatedTicket.setType("LODGING");
        updatedTicket.setStatus("PENDING");

        Reimbursement ticket = reimbDao.updateTicket(updatedTicket);

        //Have to set the submitted time due to creation statement not allowing a specified timestamp
        //Didnt want to remove the submitted timestamp from the toString method either.
        ticket.setSubmitted(ts);

        assertEquals(updatedTicket.toString(), ticket.toString());
    }

    @Test
    void approveTicket() {
        statusDao.createStatus("APPROVED");
        statusDao.createStatus("DENIED");

        boolean created = userDao.createUser(new User(2, "rmac", "password", "Richard", "mac",
                "rmac@revnet.net", 1));
        Reimbursement oldTicket = reimbDao.getOneTicket(1);
        boolean approved = reimbDao.approveTicket(1, 2);
        Reimbursement ticket = reimbDao.getOneTicket(1);

        assertTrue(approved);
        assertEquals(ticket.getStatusId(), 2);
        assertEquals(ticket.getStatus(), "APPROVED");

    }

    @Test
    void denyTicket() {
        statusDao.createStatus("APPROVED");
        statusDao.createStatus("DENIED");

        boolean created = userDao.createUser(new User(2, "rmac", "password", "Richard", "mac",
                "rmac@revnet.net", 1));
        boolean denied = reimbDao.denyTicket(1, 2);
        Reimbursement ticket = reimbDao.getOneTicket(1);

        assertTrue(denied);
        assertEquals(ticket.getStatusId(), 3);
        assertEquals(ticket.getStatus(), "DENIED");
    }
}