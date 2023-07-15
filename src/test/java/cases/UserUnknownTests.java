package cases;

import in.reqres.model.UserUnknownResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserUnknownTests extends BaseTests {

    @Test
    public void getUsersUnknown() throws URISyntaxException, IOException, InterruptedException {
        Integer id = 1;
        var response = reqresApi.getUserUnknown(id.toString(), UserUnknownResponse.class);
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
    public void GetSingleUserNotFound() throws URISyntaxException, IOException, InterruptedException {
        Integer id = -1;
        var response = reqresApi.getUserUnknown(id.toString(), UserUnknownResponse.class);

        assertEquals(404, response.getStatusCode());
        assertNull(response.getBody());
    }
}
