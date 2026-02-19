import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WikipediaUIConsole implements ConsoleOptions{

    private Label programTitleLabel;
    private VBox parentBox;
    private HBox urlBox;
    private TextField textField;
    private Button submitButton;

    private TextArea revisionOutputArea;

    public WikipediaUIConsole() {
        parentBox = new VBox();
        urlBox = new HBox(new Label("Enter Wikipedia Search Key: "));
        textField = new TextField();
        submitButton = new Button("Submit");
        revisionOutputArea = new TextArea();
        revisionOutputArea.setPrefWidth(100);
        revisionOutputArea.setPrefHeight(300);

        urlBox.getChildren().add(textField);
        urlBox.getChildren().add(submitButton);
        urlBox.setAlignment(Pos.CENTER);

        parentBox.getChildren().add(urlBox);
        parentBox.getChildren().add(revisionOutputArea);

        submitButton.setOnAction(event -> wikipediaRunAction(textField.getText()));
        revisionOutputArea.setEditable(false);
    }

    @Override
    public void runConsole(Stage primaryStage) {
        primaryStage.setTitle("Wikipedia Revision Finder");
        Scene scene = new Scene(parentBox, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void wikipediaRunAction(String keyPhrase) {
        revisionOutputArea.clear();
        WikipediaConnection connection = new WikipediaConnection();
        PageFormatter formatter = new PageFormatter();

        if(keyPhrase.isEmpty()) {
            errorWindow("Input Error", "The input cannot be empty.");
            return;
        }

        if(!connection.establishConnectionToWikipedia(keyPhrase, ProgramSettings.MAX_NUMBER_OF_REVISIONS)) {
            errorWindow("Connection Error", "The input given does not go to a Wikipedia page.\nAdditionally, if you know your input is correct, check your network settings.");
            return;
        }

        String connectedJson = connection.readJsonAsString();
        if(!formatter.isValidPage(connectedJson)) {
            errorWindow("Parsing Error","The page connected, however, there was an error parsing the information.");
            return;
        }

        WikipediaPage page = formatter.setupWikipediaPage(connectedJson);
        revisionOutputArea.appendText(page.printPageInformation());
    }

    private void errorWindow(String title, String message) {
        Stage stage = new Stage();
        stage.setTitle(title);

        Label label = new Label(message);
        label.setWrapText(true);

        VBox layout = new VBox(15, label);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(400, 150);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
}
