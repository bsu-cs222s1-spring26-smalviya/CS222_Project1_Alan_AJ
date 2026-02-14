import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class JSONValueFinderTest {

    @Test
    public void checkIfValueExistsTest() {
        try {
            WikipediaConnection connection = new WikipediaConnection();
            connection.establishConnectionToWikipedia("Zappa", 15);

            String sampleJson = readSampleFileAsString();
            String connectedJson = connection.readJsonAsString();

            boolean keyExistsTest1 = new JSONValueFinder(sampleJson).keyExists("query");
            boolean keyExistsTest2 = new JSONValueFinder(sampleJson).keyExists("bfegf");

            boolean keyExistsTest3 = new JSONValueFinder(connectedJson).keyExists("continue");
            boolean keyExistsTest4 = new JSONValueFinder(connectedJson).keyExists("bfegf");

            Assertions.assertTrue(keyExistsTest1);
            Assertions.assertFalse(keyExistsTest2);

            Assertions.assertTrue(keyExistsTest3);
            Assertions.assertFalse(keyExistsTest4);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readSampleFileAsString() throws NullPointerException, IOException {
        try (InputStream sampleFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("sample.json")) {
            if (sampleFile == null) {
                throw new IllegalStateException("sample.json not found on classpath");
            }
            return new String(Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
        }
    }

}
