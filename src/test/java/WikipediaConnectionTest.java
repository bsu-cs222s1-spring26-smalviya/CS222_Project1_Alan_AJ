import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

class WikipediaConnectionTest {

    // Test for entering a single word
    @Test
    void testConnectionToWikipediaWithSingleWord() {
        WikipediaConnection connection = new WikipediaConnection();
        connection.establishConnectionToWikipedia("Babbage");

        String json = connection.readJsonAsString();

        // Verify that what is returned exists, is not empty, and is not an error message
        assertNotNull(json);
        assertFalse(json.isEmpty());
        assertNotEquals("Error Retrieving json", json);
    }

    // Test for entering multiple words
    @Test
    void testConnectionToWikipediaWithMultipleWords() {
        WikipediaConnection connection = new WikipediaConnection();
        connection.establishConnectionToWikipedia("Frank Zappa");

        String json = connection.readJsonAsString();

        // Verify that what is returned exists, contains part of the tested name, and is not an error message
        assertNotNull(json);
        assertTrue(json.contains("Frank") || json.contains("Zappa"));
        assertNotEquals("Error Retrieving json", json);
    }
}