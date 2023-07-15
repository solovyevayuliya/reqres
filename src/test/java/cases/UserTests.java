package cases;

import in.reqres.model.UserResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests extends BaseTests {

    @Test
    public void getSingleUserFound() throws URISyntaxException, IOException, InterruptedException {
        Integer id = 1;
        var response = reqresApi.getUser(id.toString(), UserResponse.class);
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
    public void GetUserNonExistent() throws URISyntaxException, IOException, InterruptedException {
        Integer idNegative = -1;
        var response = reqresApi.getUser(idNegative.toString(), UserResponse.class);

        assertEquals(404, response.getStatusCode());
        assertNull(response.getBody());
    }
}
