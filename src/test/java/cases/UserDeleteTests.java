package cases;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Метод создания пользователей не работает,
 * поэтому не удалось добавить проверку на создание пользователя
 * для тестирования удаления.
 */
public class UserDeleteTests extends BaseTests {

    @Test
    public void deleteUser() throws URISyntaxException, IOException, InterruptedException {
        var id = "1";
        var response = reqresApi.deleteUserUnknown(id);

        assertEquals(204, response.getStatusCode());
        assertNull(response.getBody());
    }
}
