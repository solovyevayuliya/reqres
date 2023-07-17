package cases;

import in.reqres.model.UserNewResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserCreateTests extends BaseTests {

    @Test
    public void createUser() throws URISyntaxException, IOException, InterruptedException {
        String name = "Test";
        String job = UUID.randomUUID().toString();
        var params = Map.of("name", name, "job", job);

        var response = reqresApi.postCreateUser(params, UserNewResponse.class);
        var responseBody = response.getBody();

        /*
         * Если бы метод POST корректно отрабатывал и создавал пользователя,
         * то можно было бы проверить методом GET /users/{id} созданного пользователя,
         * но проверку добавлять не стала, так как с помощью API нельзя создать
         * пользователя и получить его по id
         */
        assertEquals(201, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(name, responseBody.getName()),
                () -> assertEquals(job, responseBody.getJob()),
                () -> assertNotNull(responseBody.getId()),
                () -> assertNotNull(responseBody.getCreatedAt())
        );
    }

    @Test
    public void createUserJob() throws URISyntaxException, IOException, InterruptedException {
        String job = UUID.randomUUID().toString();
        var params = Map.of("job", job);

        var response = reqresApi.postCreateUser(params, UserNewResponse.class);
        var responseBody = response.getBody();

        assertEquals(201, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNull(responseBody.getName()),
                () -> assertEquals(job, responseBody.getJob()),
                () -> assertNotNull(responseBody.getId()),
                () -> assertNotNull(responseBody.getCreatedAt())
        );
    }

    @Test
    public void createUserName() throws URISyntaxException, IOException, InterruptedException {
        String name = "Test";
        var params = Map.of("name", name);

        var response = reqresApi.postCreateUser(params, UserNewResponse.class);
        var responseBody = response.getBody();

        assertEquals(201, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(name, responseBody.getName()),
                () -> assertNull(responseBody.getJob()),
                () -> assertNotNull(responseBody.getId()),
                () -> assertNotNull(responseBody.getCreatedAt())
        );
    }

    @Test
    public void createUserNoInfo() throws URISyntaxException, IOException, InterruptedException {
        Map<String, String> params = new HashMap<>();

        var response = reqresApi.postCreateUser(params, UserNewResponse.class);
        var responseBody = response.getBody();

        assertEquals(201, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNull(responseBody.getName()),
                () -> assertNull(responseBody.getJob()),
                () -> assertNotNull(responseBody.getId()),
                () -> assertNotNull(responseBody.getCreatedAt())
        );
    }
}
