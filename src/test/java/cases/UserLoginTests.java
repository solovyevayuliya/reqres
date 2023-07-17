package cases;

import in.reqres.model.UserLoginResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserLoginTests extends BaseTests {

    @Test
    public void userLoginSuccess() throws URISyntaxException, IOException, InterruptedException {
        String email = properties.getProperty("email");
        String password = properties.getProperty("passwordForLogin");
        var params = Map.of("email", email, "password", password);

        var response = reqresApi.postLoginUser(params, UserLoginResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNotNull(responseBody.getToken()),
                () -> assertNull(responseBody.getError())
        );
    }

    @Test
    public void userLoginNoEmail() throws URISyntaxException, IOException, InterruptedException {
        String error = "Missing email or username";
        String password = properties.getProperty("passwordForLogin");
        var params = Map.of("password", password);

        var response = reqresApi.postLoginUser(params, UserLoginResponse.class);
        var responseBody = response.getBody();

        assertEquals(400, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNull(responseBody.getToken()),
                () -> assertEquals(error, responseBody.getError())
        );
    }

    @Test
    public void userLoginNoPassword() throws URISyntaxException, IOException, InterruptedException {
        String error = "Missing password";
        String email = properties.getProperty("email");
        var params = Map.of("email", email);

        var response = reqresApi.postLoginUser(params, UserLoginResponse.class);
        var responseBody = response.getBody();

        assertEquals(400, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNull(responseBody.getToken()),
                () -> assertEquals(error, responseBody.getError())
        );
    }

    @Test
    public void userLoginNoPasswordAndEmail() throws URISyntaxException, IOException, InterruptedException {
        String error = "Missing email or username";
        Map<String, String> params = new HashMap<>();

        var response = reqresApi.postLoginUser(params, UserLoginResponse.class);
        var responseBody = response.getBody();

        assertEquals(400, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNull(responseBody.getToken()),
                () -> assertEquals(error, responseBody.getError())
        );
    }
}
