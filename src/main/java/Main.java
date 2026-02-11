public class Main {
    static void main(String[] args) {
        WikipediaInputConsole console = new WikipediaInputConsole();
        WikipediaConnection connection = new WikipediaConnection();

        connection.establishConnectionToWikipedia(console.getSearchNameInput());
        System.out.println(connection.readJsonAsString());
    }
}
