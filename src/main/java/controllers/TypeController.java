package controllers;

import dao.TypeDao;
import io.javalin.http.Context;
import models.JsonResponse;
import models.ReimbursementStatus;
import models.ReimbursementType;
import org.apache.log4j.Logger;
import service.TypeService;

import java.util.List;

public class TypeController {
    TypeService typeService = new TypeService(new TypeDao());
    Logger log = Logger.getLogger(StatusController.class);

    public void getAllTypes(Context context) {
        List<ReimbursementType> types;

        types = typeService.getAllTypes();

        JsonResponse jsonResponse = new JsonResponse(types, "", true);


        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));    }

    public void createType(Context context) {
        String type = context.formParam("type");

        Boolean created = typeService.createType(type);
        JsonResponse jsonResponse = new JsonResponse(null, "", created);

        if(created){
            context.status(200);
        }else{
            jsonResponse.setMessage("Reimbursement type unable to be created.");
            context.status(404);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void getTypeById(Context context) {
        int id = 0;
        try {
            id = Integer.parseInt(context.pathParam("id"));
        }catch (Exception e){
            log.error(e);
        }

        ReimbursementType type = typeService.getTypeById(id);
        Boolean successful = type != null;
        JsonResponse jsonResponse = new JsonResponse(type, "", successful);

        if(successful){
            context.status(200);
        }else{
            jsonResponse.setMessage("Reimbursement type with id: " + id + " Not found.");
            context.status(404);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void updateType(Context context) {
        int id = 0;
        String newType = "";
        try {
            id = Integer.parseInt(context.pathParam("id"));
            newType = context.formParam("type");
        }catch (Exception e){
            log.error(e);
        }
        ReimbursementType updatedType = new ReimbursementType(id, newType);
        ReimbursementType type = typeService.updateType(updatedType);
        Boolean successful = type != null;
        JsonResponse jsonResponse = new JsonResponse(type, "", successful);

        if(successful){
            context.status(200);
        }else{
            jsonResponse.setMessage("Reimbursement type with id: " + id + " Not found.");
            context.status(404);
        }

        context.contentType("application/json");
        context.result(JsonConverter.convertToJson(jsonResponse));
    }

    public void deleteType(Context context) {
        int id = 0;
        JsonResponse jsonResponse = new JsonResponse(null, "", false);

        try{
            id = Integer.parseInt(context.pathParam("id"));
        }catch (Exception e){
            log.error(e);
        }

        Boolean deleted = typeService.deleteType(id);
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
