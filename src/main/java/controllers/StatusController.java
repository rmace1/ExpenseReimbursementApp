package controllers;

import dao.StatusDao;
import io.javalin.http.Context;
import models.JsonResponse;
import models.ReimbursementStatus;
import org.apache.log4j.Logger;
import service.StatusService;

import java.util.ArrayList;
import java.util.List;

public class StatusController {
    StatusService statusService = new StatusService(new StatusDao());
    Logger log = Logger.getLogger(StatusController.class);

    public void getAllStatuses(Context context) {
        List<ReimbursementStatus> statuses;

        statuses = statusService.getAllStatuses();

        JsonResponse jsonResponse = new JsonResponse(statuses, "", true);


        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void createStatus(Context context) {
        String status = context.formParam("status");

        Boolean created = statusService.createStatus(status);
        JsonResponse jsonResponse = new JsonResponse(null, "", created);

        if(created){
            context.status(200);
        }else{
            jsonResponse.setMessage("Reimbursement status unable to be created.");
            context.status(404);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void getStatusById(Context context) {
        int id = 0;
        try {
            id = Integer.parseInt(context.pathParam("id"));
        }catch (Exception e){
            log.error(e);
        }

        ReimbursementStatus status = statusService.getStatusById(id);
        Boolean successful = status != null;
        JsonResponse jsonResponse = new JsonResponse(status, "", successful);

        if(successful){
            context.status(200);
        }else{
            jsonResponse.setMessage("Reimbursement status with id: " + id + " Not found.");
            context.status(404);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));

    }

    public void updateStatus(Context context) {
        int id = 0;
        String newStatus = "";
        try {
            id = Integer.parseInt(context.pathParam("id"));
            newStatus = context.formParam("status");
        }catch (Exception e){
            log.error(e);
        }
        ReimbursementStatus updatedStatus = new ReimbursementStatus(id, newStatus);
        ReimbursementStatus status = statusService.updateStatus(updatedStatus);
        Boolean successful = status != null;
        JsonResponse jsonResponse = new JsonResponse(status, "", successful);

        if(successful){
            context.status(200);
        }else{
            jsonResponse.setMessage("Reimbursement status with id: " + id + " Not found.");
            context.status(404);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void deleteStatus(Context context) {
        int id = 0;
        JsonResponse jsonResponse = new JsonResponse(null, "", false);

        try{
            id = Integer.parseInt(context.pathParam("id"));
        }catch (Exception e){
            log.error(e);
        }

        Boolean deleted = statusService.deleteStatus(id);
        jsonResponse.setSuccessful(deleted);

        if(deleted){
            context.status(200);
        }else{
            context.status(400);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }
}
