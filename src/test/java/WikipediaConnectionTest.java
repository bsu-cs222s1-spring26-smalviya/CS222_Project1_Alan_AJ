import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class WikipediaConnectionTest {

    @Test
    public void wikipediaPageConnectionTest() {
        WikipediaConnection connection = new WikipediaConnection();
        Assertions.assertTrue(connection.establishConnectionToWikipedia("Babbage", 15));
    }

    @Test
    public void checkRevisionTest() {
        PageFormatter formatter = new PageFormatter();
        try {
            Assertions.assertEquals(4, formatter.formatPageRevisions(readSampleFileAsString()).length);
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
