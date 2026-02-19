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

        urlBox.getChildren().add(textField);
        urlBox.getChildren().add(submitButton);
        urlBox.setAlignment(Pos.CENTER);

        parentBox.getChildren().add(new Label("Wikipedia Revision Finder"));
        parentBox.getChildren().add(urlBox);
        parentBox.getChildren().add(revisionOutputArea);

        submitButton.setOnAction(event -> wikipediaRunAction(textField.getText()));
        revisionOutputArea.setEditable(false);
    }

    @Override
    public void runConsole(Stage primaryStage) {
        Scene scene = new Scene(parentBox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void wikipediaRunAction(String keyPhrase) {
        revisionOutputArea.clear();
        WikipediaConnection connection = new WikipediaConnection();
        PageFormatter formatter = new PageFormatter();

        if(!connection.establishConnectionToWikipedia(keyPhrase, ProgramSettings.MAX_NUMBER_OF_REVISIONS)) {
            revisionOutputArea.appendText("The input given does not go to a Wikipedia page.\n");
            revisionOutputArea.appendText("Additionally, if you know your input is correct, check your network settings.");
            return;
        }

        String connectedJson = connection.readJsonAsString();
        if(!formatter.isValidPage(connectedJson)) {
            revisionOutputArea.appendText("The page connected, however, there was an error parsing the information.");
            return;
        }

        WikipediaPage page = formatter.setupWikipediaPage(connectedJson);
        revisionOutputArea.appendText(page.printPageInformation());
    }
}
