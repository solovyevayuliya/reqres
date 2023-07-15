package in.reqres.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {
    private DataUser data;
    private Support support;

    public DataUser getData() {
        return data;
    }

    public Support getSupport() {
        return support;
    }
}
