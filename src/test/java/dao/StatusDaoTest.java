package dao;

import models.ReimbursementStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatusDaoTest {
    StatusDaoInterface statusDao;
    @BeforeEach
    void setUp() {
        statusDao = new StatusDao(H2Util.url, H2Util.userName, H2Util.password);
        H2Util.createAll();
    }

    @AfterEach
    void tearDown() {
        H2Util.dropAll();
    }

    @Test
    void createStatus() {
        boolean created = statusDao.createStatus("APPROVED");

        assertTrue(created);
    }

    @Test
    void getStatus() {
        statusDao.createStatus("APPROVED");
        ReimbursementStatus status = statusDao.getStatusById(1);

        assertEquals(status.getStatus(), "APPROVED");
    }

    @Test
    void getAllStatuses() {
        statusDao.createStatus("APPROVED");
        statusDao.createStatus("DENIED");

        List<ReimbursementStatus> statuses = statusDao.getAllStatuses();

        assertEquals(statuses.size(), 2);

    }

    @Test
    void deleteStatus() {
        statusDao.createStatus("APPROVED");
        statusDao.createStatus("DENIED");

        List<ReimbursementStatus> statuses = statusDao.getAllStatuses();

        assertEquals(statuses.size(), 2);

        boolean deleted = statusDao.deleteStatus(1);
        statuses = statusDao.getAllStatuses();

        assertTrue(deleted);
        assertEquals(statuses.size(), 1);
        assertEquals(statuses.get(0).getStatus(), "DENIED");

    }

    @Test
    void updateStatus() {
        statusDao.createStatus("APRROVED");
        ReimbursementStatus updatedStatus = new ReimbursementStatus(1,"APPROVED");

        ReimbursementStatus resultStatus = statusDao.updateStatus(updatedStatus);

        assertEquals(updatedStatus.toString(), resultStatus.toString());
    }
}