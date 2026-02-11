import java.util.Scanner;

public class WikipediaInputConsole {

    public WikipediaInputConsole() {

    }

    public String getSearchNameInput() {
        Scanner inputScanner = new Scanner(System.in);
        String input = "";
        boolean running = true;
        while(running) {
            System.out.print("Enter a name (Type -1 to exit): ");
            input = inputScanner.nextLine();

            if(input == null || input == "") {
                System.out.println("Input cannot be empty.. Try again.");
            }
            else if(input.equals("-1")) {
                System.out.println("Exiting Program..");
                running = false;
            }
            else {
                running = false;
            }
        }
        return input;
    }
}
