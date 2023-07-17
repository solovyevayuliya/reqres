package cases;

import in.reqres.model.UserResponse;
import in.reqres.model.UsersListResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class UsersListTests extends BaseTests {

    @Test
    public void getUsersPageFilter() throws URISyntaxException, IOException, InterruptedException {
        Integer page = 1;
        Integer id = 1;
        var params = Map.of("page", page.toString());

        var response = reqresApi.getListUsers(params, UsersListResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(page, responseBody.getPage()),
                () -> assertNotNull(responseBody.getTotal()),
                () -> assertNotNull(responseBody.getPerPage()),
                () -> assertNotNull(responseBody.getTotalPages()),
                () -> assertEquals(id, responseBody.getData().get(0).getId()),
                () -> assertNotNull(responseBody.getData().get(0).getEmail()),
                () -> assertNotNull(responseBody.getData().get(0).getFirstName()),
                () -> assertNotNull(responseBody.getData().get(0).getLastName()),
                () -> assertNotNull(responseBody.getData().get(0).getAvatar()),
                () -> assertNotNull(responseBody.getSupport().getText()),
                () -> assertNotNull(responseBody.getSupport().getUrl())
        );
    }

    @Test
    public void getUsersPerPageFilter() throws URISyntaxException, IOException, InterruptedException {
        Integer perPage = new Random().nextInt(9) + 1;
        Integer page = 1;
        var params = Map.of("per_page", perPage.toString());

        var response = reqresApi.getListUsers(params, UsersListResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(page, responseBody.getPage()),
                () -> assertEquals(perPage, responseBody.getPerPage()),
                () -> assertNotNull(responseBody.getTotalPages()),
                () -> assertNotNull(responseBody.getTotal()),
                () -> assertNotNull(responseBody.getData().get(0).getId()),
                () -> assertNotNull(responseBody.getData().get(0).getEmail()),
                () -> assertNotNull(responseBody.getData().get(0).getFirstName()),
                () -> assertNotNull(responseBody.getData().get(0).getLastName()),
                () -> assertNotNull(responseBody.getData().get(0).getAvatar()),
                () -> assertNotNull(responseBody.getSupport().getText()),
                () -> assertNotNull(responseBody.getSupport().getUrl())
        );
    }

    @Test
    public void getUsersIdFilter() throws URISyntaxException, IOException, InterruptedException {
        Integer id = new Random().nextInt(5) + 1;
        var params = Map.of("id", id.toString());

        var response = reqresApi.getListUsers(params, UserResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(id, responseBody.getData().getId()),
                () -> assertNotNull(responseBody.getData().getEmail()),
                () -> assertNotNull(responseBody.getData().getFirstName()),
                () -> assertNotNull(responseBody.getData().getLastName()),
                () -> assertNotNull(responseBody.getData().getAvatar()),
                () -> assertNotNull(responseBody.getSupport().getText()),
                () -> assertNotNull(responseBody.getSupport().getUrl())
        );
    }

    @Test
    public void getDelayedResponse() throws URISyntaxException, IOException, InterruptedException {
        Integer delay = new Random().nextInt(5) + 3;
        Integer page = 1;
        Integer id = 1;
        var params = Map.of("delay", delay.toString());

        var start = LocalDateTime.now();
        var response = reqresApi.getListUsers(params, UsersListResponse.class);
        var responseBody = response.getBody();
        var end = LocalDateTime.now();
        var between = ChronoUnit.SECONDS.between(start, end);

        assertTrue(between >= delay);
        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(page, responseBody.getPage()),
                () -> assertNotNull(responseBody.getTotal()),
                () -> assertNotNull(responseBody.getPerPage()),
                () -> assertNotNull(responseBody.getTotalPages()),
                () -> assertEquals(id, responseBody.getData().get(0).getId()),
                () -> assertNotNull(responseBody.getData().get(0).getEmail()),
                () -> assertNotNull(responseBody.getData().get(0).getFirstName()),
                () -> assertNotNull(responseBody.getData().get(0).getLastName()),
                () -> assertNotNull(responseBody.getData().get(0).getAvatar()),
                () -> assertNotNull(responseBody.getSupport().getText()),
                () -> assertNotNull(responseBody.getSupport().getUrl())
        );
    }

    @Test
    public void getUsersAllFilters() throws URISyntaxException, IOException, InterruptedException {
        Integer delay = new Random().nextInt(5) + 3;
        Integer perPage = new Random().nextInt(9) + 1;
        Integer page = 1;
        Integer id = 1;
        var params = Map.of("delay", delay.toString(), "per_page", perPage.toString(),
                "page", page.toString());

        var start = LocalDateTime.now();
        var response = reqresApi.getListUsers(params, UsersListResponse.class);
        var responseBody = response.getBody();
        var end = LocalDateTime.now();
        var between = ChronoUnit.SECONDS.between(start, end);

        assertTrue(between >= delay);
        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(page, responseBody.getPage()),
                () -> assertNotNull(responseBody.getTotal()),
                () -> assertEquals(perPage, responseBody.getPerPage()),
                () -> assertNotNull(responseBody.getTotalPages()),
                () -> assertEquals(id, responseBody.getData().get(0).getId()),
                () -> assertNotNull(responseBody.getData().get(0).getEmail()),
                () -> assertNotNull(responseBody.getData().get(0).getFirstName()),
                () -> assertNotNull(responseBody.getData().get(0).getLastName()),
                () -> assertNotNull(responseBody.getData().get(0).getAvatar()),
                () -> assertNotNull(responseBody.getSupport().getText()),
                () -> assertNotNull(responseBody.getSupport().getUrl())
        );
    }

    @Test
    public void getUsersNoFilters() throws URISyntaxException, IOException, InterruptedException {
        Integer page = 1;
        Integer id = 1;
        Map<String, String> params = new HashMap<>();

        var response = reqresApi.getListUsers(params, UsersListResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(page, responseBody.getPage()),
                () -> assertNotNull(responseBody.getTotal()),
                () -> assertNotNull(responseBody.getPerPage()),
                () -> assertNotNull(responseBody.getTotalPages()),
                () -> assertEquals(id, responseBody.getData().get(0).getId()),
                () -> assertNotNull(responseBody.getData().get(0).getEmail()),
                () -> assertNotNull(responseBody.getData().get(0).getFirstName()),
                () -> assertNotNull(responseBody.getData().get(0).getLastName()),
                () -> assertNotNull(responseBody.getData().get(0).getAvatar()),
                () -> assertNotNull(responseBody.getSupport().getText()),
                () -> assertNotNull(responseBody.getSupport().getUrl())
        );
    }
}
