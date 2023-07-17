package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private final Properties properties;

    public TestProperties() {
        this.properties = new Properties();
        try {
            properties.load(new FileReader("src/test/config.properties"));
        } catch (IOException e) {
            // Пробрасываем исключение, чтобы тесты падали сразу
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String propertyKey) {
        if (properties.containsKey(propertyKey)) {
            return properties.getProperty(propertyKey);
        } else {
            return null;
        }
    }
}
