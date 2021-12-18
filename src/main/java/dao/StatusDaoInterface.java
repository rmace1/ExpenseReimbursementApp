package dao;

import models.ReimbursementStatus;
import service.ReimbursementService;

import java.util.List;

public interface StatusDaoInterface {
    boolean createStatus(String status);
    ReimbursementStatus getStatusById(int statusId);
    List<ReimbursementStatus> getAllStatuses();
    boolean deleteStatus(int statusId);
    ReimbursementStatus updateStatus(ReimbursementStatus status);

    ReimbursementStatus getStatusByName(String status);
}
