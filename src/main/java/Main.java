import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        WikipediaUIConsole uiConsole = new WikipediaUIConsole();
        uiConsole.runConsole(primaryStage);
    }
}