import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class WikipediaInputConsoleTest {

    // Test for inputting a valid word
    @Test
    void testValidInput() {
        String simulatedInput = "Babbage\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        WikipediaInputConsole console = new WikipediaInputConsole();
        String result = console.getSearchNameInput();

        // Verify that the returned string matches the input
        assertEquals("Babbage", result);
    }

    // Test for exiting the program
    @Test
    void testExitInput() {
        String simulatedInput = "exit\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        WikipediaInputConsole console = new WikipediaInputConsole();
        String result = console.getSearchNameInput();

        // Verify that returned string matches the input
        assertEquals("exit", result);
    }

    // Test for inputting an empty string
    @Test
    void testInvalidInput() {
        String simulatedInput = "\nBabbage\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Catch error output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        WikipediaInputConsole console = new WikipediaInputConsole();
        console.getSearchNameInput();

        // Convert error output to string
        String errorOutput = errContent.toString();

        // Verify that the error message was returned
        assertTrue(errorOutput.contains("Input cannot be empty"));
    }
}