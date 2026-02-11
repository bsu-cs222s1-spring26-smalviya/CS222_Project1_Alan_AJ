import java.util.Scanner;

public class WikipediaInputConsole {

    public WikipediaInputConsole() {

    }

    public String getSearchNameInput() {
        Scanner inputScanner = new Scanner(System.in);

        boolean running = true;
        while(running) {
            System.out.print("Enter a name: ");
            String input = inputScanner.nextLine();

            if(input != null) {

            }
        }
        return "";
    }
}
