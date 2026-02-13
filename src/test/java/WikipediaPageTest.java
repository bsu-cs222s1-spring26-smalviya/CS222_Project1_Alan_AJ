import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WikipediaPageTest {

    // Test for a page with no revisions
    @Test
    void testNoRevisions() {
        PageRevision[] revisions = {};
        WikipediaPage page = new WikipediaPage("Java", revisions);

        String result = page.printPageInformation();

        // Verify that the correct error message is returned
        assertEquals("No revisions to show!", result);
    }

    // Test for a page with revisions
    @Test
    void testRevisions() {
        PageRevision[] revisions = {
                new PageRevision("Citation bot", "2026-01-21T03:03:12Z"),
                new PageRevision("Smasongarrison", "2026-01-10T02:49:23Z"),
        };

        WikipediaPage page = new WikipediaPage("Babbage", revisions);

        String expected =
                "Babbage\n" +
                        "1)  [2026-01-21T03:03:12Z]  Citation bot\n" +
                        "2)  [2026-01-10T02:49:23Z]  Smasongarrison";

        // Verify that that expected string matches the returned string
        assertEquals(expected, page.printPageInformation());
    }
}