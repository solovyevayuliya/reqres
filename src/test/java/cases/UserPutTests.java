package cases;

import in.reqres.model.UserUpdateResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserPutTests extends BaseTests {

    @Test
    public void updateUser() throws URISyntaxException, IOException, InterruptedException {
        String id = "1";
        String name = "Test";
        String job = UUID.randomUUID().toString();
        var params = Map.of("name", name, "job", job);

        var response = reqresApi.putUser(id, params, UserUpdateResponse.class);
        var responseBody = response.getBody();
        /*
         * Если бы метод POST корректно отрабатывал и создавал пользователя,
         * то можно было бы проверить методом GET /users/{id} созданного пользователя
         * и его редактирование, но проверку добавлять не стала,
         * так как с помощью API нельзя создать
         * пользователя и получить его по id
         */
        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(name, responseBody.getName()),
                () -> assertEquals(job, responseBody.getJob()),
                () -> assertNotNull(responseBody.getUpdatedAt())
        );
    }

    @Test
    public void updateUserName() throws URISyntaxException, IOException, InterruptedException {
        String id = "1";
        String name = "Test";
        var params = Map.of("name", name);

        var response = reqresApi.putUser(id, params, UserUpdateResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertEquals(name, responseBody.getName()),
                () -> assertNull(responseBody.getJob()),
                () -> assertNotNull(responseBody.getUpdatedAt())
        );
    }

    @Test
    public void updateUserJob() throws URISyntaxException, IOException, InterruptedException {
        String id = "1";
        String job = UUID.randomUUID().toString();
        var params = Map.of("job", job);

        var response = reqresApi.putUser(id, params, UserUpdateResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNull(responseBody.getName()),
                () -> assertEquals(job, responseBody.getJob()),
                () -> assertNotNull(responseBody.getUpdatedAt())
        );
    }

    @Test
    public void updateUserNoInfo() throws URISyntaxException, IOException, InterruptedException {
        String id = "1";
        Map<String, String> params = new HashMap<>();

        var response = reqresApi.putUser(id, params, UserUpdateResponse.class);
        var responseBody = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Values are different than expected",
                () -> assertNull(responseBody.getName()),
                () -> assertNull(responseBody.getJob()),
                () -> assertNotNull(responseBody.getUpdatedAt())
        );
    }
}
