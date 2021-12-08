package service;

import dao.ReimbursementDao;
import dao.ReimbursementDaoInterface;
import models.Reimbursement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementServiceTest {
    ReimbursementService reimbService;
    ReimbursementDaoInterface reimbDao = Mockito.mock(ReimbursementDao.class);
    @BeforeEach
    void setUp() {
        reimbService = new ReimbursementService(reimbDao);
    }

    @Test
    void createNewReimbursementTicket() {
    }

    @Test
    void getOneTicket() {
    }

    @Test
    void getAllTickets() {
    }

    @Test
    void getAllTicketsByUser() {
    }

    @Test
    void getAllTicketsByType() {
    }

    @Test
    void getAllTicketsByStatus() {
    }

    @Test
    void deleteTicket() {
    }

    @Test
    void updateTicket() {
    }

    @Test
    void approveTicket() {
    }

    @Test
    void denyTicket() {
    }
}