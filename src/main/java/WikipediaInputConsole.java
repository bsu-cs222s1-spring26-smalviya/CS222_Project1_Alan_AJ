import java.util.Scanner;

public class WikipediaInputConsole {

    public void runConsole() {
        WikipediaConnection connection = new WikipediaConnection();
        String searchName = getSearchNameInput();

        if(connection.establishConnectionToWikipedia(searchName, ProgramSettings.MAX_NUMBER_OF_REVISIONS)) {
            PageFormatter formatter = new PageFormatter();
            String connectedJson = connection.readJsonAsString();
            WikipediaPage page = formatter.setupWikipediaPage(connectedJson);
            page.printPageInformation();
        }
    }

    public String getSearchNameInput() {
        Scanner inputScanner = new Scanner(System.in);
        String input = "";

        System.out.print("Enter a name: ");
        input = inputScanner.nextLine();

        if(input.isEmpty()) {
            System.err.println("Input cannot be empty.. Quitting program..");
            System.exit(0);
        }

        return input;
    }
}
