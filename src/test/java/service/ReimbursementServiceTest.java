package service;

import dao.*;
import models.Reimbursement;
import models.ReimbursementStatus;
import models.ReimbursementType;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementServiceTest {
    ReimbursementService reimbService;
    ReimbursementDaoInterface reimbDao = Mockito.mock(ReimbursementDao.class);
    UserDaoInterface userDao = Mockito.mock(UserDao.class);
    StatusDaoInterface statusDao = Mockito.mock(StatusDao.class);
    TypeDaoInterface typeDao = Mockito.mock(TypeDao.class);

    @BeforeEach
    void setUp() {
        reimbService = new ReimbursementService(reimbDao, userDao, typeDao, statusDao);
    }

    @Test
    void createValidNewReimbursementTicket() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);

        Mockito.when(userDao.getUser(ticket.getAuthor())).thenReturn(new User(1, "rmace", "password", "richard", "mace",
                "rmace@revnet.net", 1));
        Mockito.when(typeDao.getType(ticket.getTypeId())).thenReturn(new ReimbursementType(1, "LODGING"));
        Mockito.when(statusDao.getStatusById(ticket.getStatusId())).thenReturn(new ReimbursementStatus(1, "APPROVED"));
        Mockito.when(reimbDao.createNewTicket(ticket)).thenReturn(true);

        assertTrue(reimbService.createNewReimbursementTicket(ticket));
        Mockito.verify(reimbDao, Mockito.times(1)).createNewTicket(ticket);

    }

    @Test
    void createInvalidNewReimbursementTicketUser() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);

        //Mockito.when(userDao.getUser(ticket.getAuthor())).thenReturn(new User(1, "rmace", "password", "richard", "mace",
        //        "rmace@revnet.net", 1));
        Mockito.when(typeDao.getType(ticket.getTypeId())).thenReturn(new ReimbursementType(1, "LODGING"));
        Mockito.when(statusDao.getStatusById(ticket.getStatusId())).thenReturn(new ReimbursementStatus(1, "APPROVED"));
        //Mockito.when(reimbDao.createNewTicket(ticket)).thenReturn(true);

        assertFalse(reimbService.createNewReimbursementTicket(ticket));
    }

    @Test
    void createInvalidNewReimbursementTicketType() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);

        Mockito.when(userDao.getUser(ticket.getAuthor())).thenReturn(new User(1, "rmace", "password", "richard", "mace",
                "rmace@revnet.net", 1));
        //Mockito.when(typeDao.getTypeById(ticket.getTypeId())).thenReturn(new ReimbursementType(1, "LODGING"));
        Mockito.when(statusDao.getStatusById(ticket.getStatusId())).thenReturn(new ReimbursementStatus(1, "APPROVED"));
        //Mockito.when(reimbDao.createNewTicket(ticket)).thenReturn(true);

        assertFalse(reimbService.createNewReimbursementTicket(ticket));
    }

    @Test
    void createInvalidNewReimbursementTicketStatus() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);
        User user = new User(1, "rmace", "password", "richard", "mace",
                "rmace@revnet.net", 1);
        ReimbursementStatus status = new ReimbursementStatus(1, "PENDING");
        Mockito.when(userDao.getUser(ticket.getAuthor())).thenReturn(user);
        //Mockito.when(typeDao.getType(ticket.getTypeId())).thenReturn(new ReimbursementType(1, "LODGING"));
        //Mockito.when(statusDao.getStatusById(ticket.getStatusId())).thenReturn(status);
        //Mockito.when(statusDao.getStatus(ticket.getStatusId())).thenReturn(new ReimbursementStatus(1, "APPROVED"));
        //Mockito.when(reimbDao.createNewTicket(ticket)).thenReturn(true);

        assertFalse(reimbService.createNewReimbursementTicket(ticket));
    }

    @Test
    void getOneTicket() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);

        Mockito.when(reimbDao.getOneTicket(ticket.getId())).thenReturn(ticket);

        Reimbursement actualTicket = reimbService.getOneTicket(ticket.getId());

        assertEquals(ticket, actualTicket);

    }

    @Test
    void getAllTickets() {
        Timestamp ts = new Timestamp(0);
        List<Reimbursement> tickets = new ArrayList<>();
        tickets.add( new Reimbursement(1, 5.00, ts, 1, 1, 1));
        tickets.add( new Reimbursement(2, 10.00, ts, 1, 1, 1));

        Mockito.when(reimbDao.getAllTickets()).thenReturn(tickets);

        List<Reimbursement> actualTickets = reimbService.getAllTickets();

        assertEquals(tickets, actualTickets);

    }

    @Test
    void getAllTicketsByAuthor() {
        Timestamp ts = new Timestamp(0);
        List<Reimbursement> tickets = new ArrayList<>();
        tickets.add( new Reimbursement(1, 5.00, ts, 1, 1, 1));
        tickets.add( new Reimbursement(2, 10.00, ts, 1, 2, 2));

        Mockito.when(reimbDao.getAllTicketsByUser(1)).thenReturn(tickets);

        List<Reimbursement> actualTickets = reimbService.getAllTicketsByUser(1);

        assertEquals(tickets, actualTickets);
    }

    @Test
    void getAllTicketsByType() {
        Timestamp ts = new Timestamp(0);
        List<Reimbursement> tickets = new ArrayList<>();
        tickets.add( new Reimbursement(1, 5.00, ts, 1, 1, 1));
        tickets.add( new Reimbursement(2, 10.00, ts, 2, 2, 1));

        Mockito.when(reimbDao.getAllTicketsByType(1)).thenReturn(tickets);

        List<Reimbursement> actualTickets = reimbService.getAllTicketsByType(1);

        assertEquals(tickets, actualTickets);
    }

    @Test
    void getAllTicketsByStatus() {
        Timestamp ts = new Timestamp(0);
        List<Reimbursement> tickets = new ArrayList<>();
        tickets.add( new Reimbursement(1, 5.00, ts, 1, 1, 1));
        tickets.add( new Reimbursement(2, 10.00, ts, 2, 1, 2));

        Mockito.when(reimbDao.getAllTicketsByStatus(1)).thenReturn(tickets);

        List<Reimbursement> actualTickets = reimbService.getAllTicketsByStatus(1);

        assertEquals(tickets, actualTickets);
    }

    @Test
    void deleteTicket() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);

        Mockito.when(reimbDao.deleteTicket(ticket.getId())).thenReturn(true);

        assertTrue(reimbService.deleteTicket(ticket.getId()));
    }

    @Test
    void updateTicket() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);

    }

    @Test
    void approveTicket() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);
        User user = new User(1, "rmace", "password", "richard", "mace",
                "rmace@revnet.net", 1);
        user.setRole("MANAGER");

        Mockito.when(userDao.getUser(user.getId())).thenReturn(user);
        Mockito.when(reimbDao.approveTicket(ticket.getId(), user.getId())).thenReturn(true);

        assertTrue(reimbService.approveTicket(ticket.getId(), user.getId()));
    }

    @Test
    void approveTicketInvalidUser1() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);
        User user = new User(1, "rmace", "password", "richard", "mace",
                "rmace@revnet.net", 1);
        user.setRole("EMPLOYEE");

        //To test if user is null comment out the Mockito line below.  Mockito returns null by default.
        //Mockito.when(userDao.getUser(user.getId())).thenReturn(user);
        //Mockito.when(reimbDao.approveTicket(ticket.getId(), user.getId())).thenReturn(true);

        assertFalse(reimbService.approveTicket(ticket.getId(), user.getId()));
    }

    @Test
    void approveTicketInvalidUser2() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);
        User user = new User(1, "rmace", "password", "richard", "mace",
                "rmace@revnet.net", 1);
        user.setRole("EMPLOYEE");

        Mockito.when(userDao.getUser(user.getId())).thenReturn(user);
        //Mockito.when(reimbDao.approveTicket(ticket.getId(), user.getId())).thenReturn(true);

        assertFalse(reimbService.approveTicket(ticket.getId(), user.getId()));
    }

    @Test
    void denyTicket() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);
        User user = new User(1, "rmace", "password", "richard", "mace",
                "rmace@revnet.net", 1);
        user.setRole("MANAGER");

        Mockito.when(userDao.getUser(user.getId())).thenReturn(user);
        Mockito.when(reimbDao.denyTicket(ticket.getId(), user.getId())).thenReturn(true);

        assertTrue(reimbService.denyTicket(ticket.getId(), user.getId()));
    }

    @Test
    void denyTicketInvalidUser1() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);
        User user = new User(1, "rmace", "password", "richard", "mace",
                "rmace@revnet.net", 1000);
        user.setRole("EMPLOYEE");

        //To test if user is null comment out the Mockito line below.  Mockito returns null by default.
        Mockito.when(userDao.getUser(user.getId())).thenReturn(user);
        //Mockito.when(reimbDao.denyTicket(ticket.getId(), user.getId())).thenReturn(true);

        assertFalse(reimbService.denyTicket(ticket.getId(), user.getId()));
    }

    @Test
    void denyTicketInvalidUser2() {
        Timestamp ts = new Timestamp(0);
        Reimbursement ticket = new Reimbursement(1, 5.00, ts, 1, 1, 1);
        User user = new User(1, "rmace", "password", "richard", "mace",
                "rmace@revnet.net", 1000);
        user.setRole("EMPLOYEE");

        //To test if user is null comment out the Mockito line below.  Mockito returns null by default.
        //Mockito.when(userDao.getUser(user.getId())).thenReturn(user);
        //Mockito.when(reimbDao.denyTicket(ticket.getId(), user.getId())).thenReturn(true);

        assertFalse(reimbService.denyTicket(ticket.getId(), user.getId()));
    }
}