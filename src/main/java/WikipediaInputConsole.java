import java.util.Scanner;

public class WikipediaInputConsole {

    public void runConsole() {
        WikipediaConnection connection = new WikipediaConnection();
        PageFormatter formatter = new PageFormatter();

        String searchName = getSearchNameInput();
        connection.establishConnectionToWikipedia(searchName, ProgramSettings.MAX_NUMBER_OF_REVISIONS);

        String connectedJson = connection.readJsonAsString();
        if(!formatter.isValidPage(connectedJson)) {
            System.err.println("Page not found.. Closing the program..");
            return;
        }

        String title = formatter.formatPageTitle(connectedJson);
        PageRevision[] revisions = formatter.formatPageRevisions(connectedJson);

        WikipediaPage page = new WikipediaPage(title, revisions);
        System.out.println(page.printPageInformation());
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
