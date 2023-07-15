package in.reqres.model;

public class UserRegisterResponse {
    private Integer id;
    private String token;
    private String error;

    public String getError() {
        return error;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
