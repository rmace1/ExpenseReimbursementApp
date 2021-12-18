package service;

import dao.StatusDaoInterface;
import models.ReimbursementStatus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatusService {
    StatusDaoInterface statusDao;

    public StatusService(StatusDaoInterface statusDao){this.statusDao = statusDao;}

    //todo: status has 10 character limit
    public boolean createStatus(String status){
        return statusDao.createStatus(status);
    }

    public ReimbursementStatus getStatusById(int statusId) {
        return statusDao.getStatusById(statusId);
    }

    public ReimbursementStatus getStatusByName(String status){return statusDao.getStatusByName(status);}

    public List<ReimbursementStatus> getAllStatuses() {
        return statusDao.getAllStatuses();
    }


    public boolean deleteStatus(int statusId) {
        return statusDao.deleteStatus(statusId);
    }


    public ReimbursementStatus updateStatus(ReimbursementStatus status) {
        return statusDao.updateStatus(status);
    }
}
