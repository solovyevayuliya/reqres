package cases;

import in.reqres.model.UserRegisterResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTests extends BaseTests {

    @Test
    public void userRegisterSuccess() throws URISyntaxException, IOException, InterruptedException {
        String email = properties.getProperty("email");
        String password = properties.getProperty("passwordForRegister");
        var params = Map.of("email", email, "password", password);

        var response = reqresApi.postRegisterUser(params, UserRegisterResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNotNull(responseBody.getId()),
                () -> assertNotNull(responseBody.getToken()),
                () -> assertNull(responseBody.getError())
        );
    }

    @Test
    public void userRegisterNoEmail() throws URISyntaxException, IOException, InterruptedException {
        String error = "Missing email or username";
        String password = properties.getProperty("passwordForRegister");
        var params = Map.of("password", password);

        var response = reqresApi.postRegisterUser(params, UserRegisterResponse.class);
        var responseBody = response.getBody();

        assertEquals(400, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNull(responseBody.getId()),
                () -> assertNull(responseBody.getToken()),
                () -> assertEquals(error, responseBody.getError())
        );
    }

    @Test
    public void userRegisterNoPassword() throws URISyntaxException, IOException, InterruptedException {
        String error = "Missing password";
        String email = properties.getProperty("email");
        var params = Map.of("email", email);

        var response = reqresApi.postRegisterUser(params, UserRegisterResponse.class);
        var responseBody = response.getBody();

        assertEquals(400, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNull(responseBody.getId()),
                () -> assertNull(responseBody.getToken()),
                () -> assertEquals(error, responseBody.getError())
        );
    }

    @Test
    public void userRegisterNoPasswordAndEmail() throws URISyntaxException, IOException, InterruptedException {
        String error = "Missing email or username";
        Map<String, String> params = new HashMap<>();

        var response = reqresApi.postRegisterUser(params, UserRegisterResponse.class);
        var responseBody = response.getBody();

        assertEquals(400, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNull(responseBody.getId()),
                () -> assertNull(responseBody.getToken()),
                () -> assertEquals(error, responseBody.getError())
        );
    }
}
