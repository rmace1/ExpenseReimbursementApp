package service;

import dao.StatusDao;
import dao.StatusDaoInterface;
import models.Reimbursement;
import models.ReimbursementStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatusServiceTest {
    StatusService statusService;
    StatusDaoInterface statusDao = Mockito.mock(StatusDao.class);

    @BeforeEach
    void setUp() {
        statusService = new StatusService(statusDao);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createStatus() {
        Mockito.when(statusDao.createStatus("LODGING")).thenReturn(true);

        assertTrue(statusService.createStatus("LODGING"));

    }

    @Test
    void getStatus() {
        ReimbursementStatus status = new ReimbursementStatus(1, "LODGING");

        Mockito.when(statusDao.getStatusById(1)).thenReturn(status);

        ReimbursementStatus actualStatus = statusService.getStatusById(1);

        assertEquals(status, actualStatus);
    }

    @Test
    void getAllStatuses() {
        List<ReimbursementStatus> statusList = new ArrayList<>();
        statusList.add(new ReimbursementStatus(1, "LODGING"));
        statusList.add(new ReimbursementStatus(2, "TRAVEL"));

        Mockito.when(statusDao.getAllStatuses()).thenReturn(statusList);

        List<ReimbursementStatus> actualList = statusService.getAllStatuses();

        assertEquals(statusList, actualList);

    }

    @Test
    void deleteStatus() {
        Mockito.when(statusDao.deleteStatus(1)).thenReturn(true);

        assertTrue(statusService.deleteStatus(1));
    }

    @Test
    void updateStatus() {
        ReimbursementStatus updatedStatus = new ReimbursementStatus(1, "LODGING");

        Mockito.when(statusDao.updateStatus(updatedStatus)).thenReturn(updatedStatus);

        ReimbursementStatus actualStatus = statusService.updateStatus(updatedStatus);

        assertEquals(updatedStatus, actualStatus);
    }
}