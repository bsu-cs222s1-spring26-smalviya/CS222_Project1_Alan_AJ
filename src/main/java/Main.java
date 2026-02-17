import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    static void main(String[] args) {
        WikipediaInputConsole console = new WikipediaInputConsole();
        console.runConsole();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WikipediaInputConsole uiConsole = new WikipediaInputConsole();
        uiConsole.runConsole();

        VBox parent = new VBox();
        parent.getChildren().add(new Label("Wikipedia Revisions Finder"));

        HBox urlArea = new HBox(new Label("Enter Wikipedia Page Name: "));
        TextField textField = new TextField();
        urlArea.getChildren().add(textField);
        parent.getChildren().add(urlArea);

        Button button = new Button("Search for Revisions!");
        parent.getChildren().add(button);
        button.setOnAction(event -> newActionEvent());
        Scene scene = new Scene(parent, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void newActionEvent() {
        System.out.println("Pressed");
    }
}