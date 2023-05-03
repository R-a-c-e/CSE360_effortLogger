package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EffortLogEditorConsoleController {

    @FXML
    private GridPane bottomGridPane;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private BorderPane centerPane;

    @FXML
    private BorderPane centralPane;

    @FXML
    private VBox gridPane1;

    @FXML
    private VBox gridPane2;

    @FXML
    private VBox gridPane3;

    @FXML
    private VBox gridPane4;

    @FXML
    private BorderPane mainPane;

    @FXML
    private VBox topBar;

    @FXML
    private Label topLabel;

    @FXML
    private Button updateButtonClicked;

    
    @FXML
	void sendToConsole(ActionEvent event) throws IOException
	{
		Parent consoleParent = FXMLLoader.load(getClass().getResource("EffortLogConsole.fxml"));
		Scene consoleLogsScene = new Scene(consoleParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(consoleLogsScene);
		window.show();
		
		
	}
}
