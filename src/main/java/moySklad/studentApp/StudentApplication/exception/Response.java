package moySklad.studentApp.StudentApplication.exception;

public class Response {

    private String error;

    public Response(String error) {
        this.error = error;
    }

    public Response() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
