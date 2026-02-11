import java.util.Scanner;

public class WikipediaInputConsole {

    public WikipediaInputConsole() {

    }

    public String getSearchNameInput() {
        Scanner inputScanner = new Scanner(System.in);
        String input = "";
        boolean running = true;
        while(running) {
            System.out.print("Enter a name (Type exit to exit): ");
            input = inputScanner.nextLine();

            if(input == null || input == "") {
                System.err.println("Input cannot be empty.. Quitting program..");
                //System.exit(1);
            }
            else {
                running = false;
            }
        }
        return input;
    }
}
