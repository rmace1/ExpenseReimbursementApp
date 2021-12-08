package service;

import dao.StatusDao;
import dao.StatusDaoInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    }

    @Test
    void getStatus() {
    }

    @Test
    void getAllStatuses() {
    }

    @Test
    void deleteStatus() {
    }

    @Test
    void updateStatus() {
    }
}