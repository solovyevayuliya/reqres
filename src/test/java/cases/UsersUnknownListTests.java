package cases;

import in.reqres.model.UserUnknownResponse;
import in.reqres.model.UsersUnknownListResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsersUnknownListTests extends BaseTests {

    @Test
    public void getUsersPageFilter() throws URISyntaxException, IOException, InterruptedException {
        Integer page = 1;
        Integer id = 1;
        var params = Map.of("page", page.toString());

        var response = reqresApi.getListUsersUnknown(params, UsersUnknownListResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(page, responseBody.getPage()),
                () -> assertNotNull(responseBody.getTotal()),
                () -> assertNotNull(responseBody.getPerPage()),
                () -> assertNotNull(responseBody.getTotalPages()),
                () -> assertEquals(id, responseBody.getData().get(0).getId()),
                () -> assertNotNull(responseBody.getData().get(0).getName()),
                () -> assertNotNull(responseBody.getData().get(0).getYear()),
                () -> assertNotNull(responseBody.getData().get(0).getColor()),
                () -> assertNotNull(responseBody.getData().get(0).getPantoneValue()),
                () -> assertNotNull(responseBody.getSupport().getText()),
                () -> assertNotNull(responseBody.getSupport().getUrl())
        );
    }

    @Test
    public void getUsersPerPageFilter() throws URISyntaxException, IOException, InterruptedException {
        Integer perPage = new Random().nextInt(9) + 1;
        Integer page = 1;
        var params = Map.of("per_page", perPage.toString());

        var response = reqresApi.getListUsersUnknown(params, UsersUnknownListResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(page, responseBody.getPage()),
                () -> assertEquals(perPage, responseBody.getPerPage()),
                () -> assertNotNull(responseBody.getTotalPages()),
                () -> assertNotNull(responseBody.getTotal()),
                () -> assertNotNull(responseBody.getData().get(0).getId()),
                () -> assertNotNull(responseBody.getData().get(0).getName()),
                () -> assertNotNull(responseBody.getData().get(0).getYear()),
                () -> assertNotNull(responseBody.getData().get(0).getColor()),
                () -> assertNotNull(responseBody.getData().get(0).getPantoneValue()),
                () -> assertNotNull(responseBody.getSupport().getText()),
                () -> assertNotNull(responseBody.getSupport().getUrl())
        );
    }

    @Test
    public void getUsersIdFilter() throws URISyntaxException, IOException, InterruptedException {
        Integer id = new Random().nextInt(4) + 1;
        var params = Map.of("id", id.toString());

        var response = reqresApi.getListUsersUnknown(params, UserUnknownResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(id, responseBody.getData().getId()),
                () -> assertNotNull(responseBody.getData().getName()),
                () -> assertNotNull(responseBody.getData().getYear()),
                () -> assertNotNull(responseBody.getData().getColor()),
                () -> assertNotNull(responseBody.getData().getPantoneValue()),
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
        var response = reqresApi.getListUsersUnknown(params, UsersUnknownListResponse.class);
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
                () -> assertNotNull(responseBody.getData().get(0).getName()),
                () -> assertNotNull(responseBody.getData().get(0).getYear()),
                () -> assertNotNull(responseBody.getData().get(0).getColor()),
                () -> assertNotNull(responseBody.getData().get(0).getPantoneValue()),
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
        var params = Map.of("delay", delay.toString(), "per_page", perPage.toString(), "page", page.toString());

        var start = LocalDateTime.now();
        var response = reqresApi.getListUsersUnknown(params, UsersUnknownListResponse.class);
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
                () -> assertNotNull(responseBody.getData().get(0).getName()),
                () -> assertNotNull(responseBody.getData().get(0).getYear()),
                () -> assertNotNull(responseBody.getData().get(0).getColor()),
                () -> assertNotNull(responseBody.getData().get(0).getPantoneValue()),
                () -> assertNotNull(responseBody.getSupport().getText()),
                () -> assertNotNull(responseBody.getSupport().getUrl())
        );
    }

    @Test
    public void getUsersNoFilters() throws URISyntaxException, IOException, InterruptedException {
        Integer page = 1;
        Integer id = 1;
        Map<String, String> params = new HashMap<>();

        var response = reqresApi.getListUsersUnknown(params, UsersUnknownListResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(page, responseBody.getPage()),
                () -> assertNotNull(responseBody.getTotal()),
                () -> assertNotNull(responseBody.getPerPage()),
                () -> assertNotNull(responseBody.getTotalPages()),
                () -> assertEquals(id, responseBody.getData().get(0).getId()),
                () -> assertNotNull(responseBody.getData().get(0).getName()),
                () -> assertNotNull(responseBody.getData().get(0).getYear()),
                () -> assertNotNull(responseBody.getData().get(0).getColor()),
                () -> assertNotNull(responseBody.getData().get(0).getPantoneValue()),
                () -> assertNotNull(responseBody.getSupport().getText()),
                () -> assertNotNull(responseBody.getSupport().getUrl())
        );
    }
}
