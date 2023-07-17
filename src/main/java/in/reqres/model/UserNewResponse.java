package in.reqres.model;

public class UserNewResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
