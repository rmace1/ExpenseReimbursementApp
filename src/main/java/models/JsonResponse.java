package models;

public class JsonResponse {
    private Object object;
    private String message;
    private Boolean successful;

    public JsonResponse() {
    }

    public JsonResponse(Object object, String message, Boolean successful) {
        this.object = object;
        this.message = message;
        this.successful = successful;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }
}
