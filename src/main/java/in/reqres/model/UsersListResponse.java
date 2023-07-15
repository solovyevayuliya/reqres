package in.reqres.model;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsersListResponse {
    private Integer page;
    private Integer perPage;
    private Integer total;
    private Integer totalPages;
    private List<DataUser> data;
    private Support support;

    public Integer getPage() {
        return page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public List<DataUser> getData() {
        return data;
    }

    public Support getSupport() {
        return support;
    }
}
