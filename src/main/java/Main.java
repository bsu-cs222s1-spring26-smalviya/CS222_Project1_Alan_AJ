public class Main {
    static void main(String[] args) {
        WikipediaInputConsole console = new WikipediaInputConsole();
        WikipediaConnection connection = new WikipediaConnection();

        String searchName = console.getSearchNameInput();

        connection.establishConnectionToWikipedia(searchName);
        System.out.println(connection.readJsonAsString());
    }
}
