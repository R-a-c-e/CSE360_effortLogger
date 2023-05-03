package application;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import backend.Defect;
import backend.Definitions;
import backend.EffortLog;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class EffortLogConsoleController extends UserData implements Initializable {
	@FXML private BorderPane mainPane;
	@FXML private BorderPane centerPane;
	@FXML private BorderPane centralPane;
	@FXML private VBox topBar;
	@FXML private VBox gridPane1;
	@FXML private VBox gridPane2;
	@FXML private VBox gridPane3;
	@FXML private VBox gridPane4;
	@FXML private VBox gridBox1;
	@FXML private VBox gridBox2;
	@FXML private VBox gridBox3;
	@FXML private VBox gridBox4;
	@FXML private VBox clockControlPane;
	@FXML private VBox clockStatusPane;
	@FXML private VBox detailsPane;
	@FXML private GridPane bottomGridPane;
	@FXML private GridPane centralGridPane;
	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button definitionsButton;
	@FXML private Button button4;
	@FXML private Button clockControlButton;
	@FXML private Label topLabel;
	@FXML private Label clockStatusLabel;
	@FXML private Label projectLabel;
	@FXML private Label lifeCycleStepLabel;
	@FXML private Label effortCategoryLabel;
	@FXML private Label effortSubCategoryLabel;
	@FXML private Label detailsLabel;
	@FXML private Label projectErrorMsg;
	@FXML private Label lifeCycleStepErrorMsg;
	@FXML private Label effortCategoryErrorMsg;
	@FXML private Label effortSubCategoryErrorMsg;
	@FXML private ComboBox<String> projectCombobox;
	@FXML private ComboBox<String> lifeCycleStepCombobox;
	@FXML private ComboBox<String> effortCategoryCombobox;
	@FXML private ComboBox<String> effortSubCategoryCombobox;
	@FXML private TextField detailsField;
	
	private long startTime = -1;
	private long endTime = -1;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// ADD PROJECTS
		ArrayList<String> projs = UserData.table.getDefinitions().projects;
		projectCombobox.getItems().add("None selected");
		for (int i = 0; i < projs.size(); i++) {
			projectCombobox.getItems().add(projs.get(i));
		}
		
		// ADD EFFORT CATEGORIES
		String[] ecs = UserData.table.getDefinitions().effortCategories;
		effortCategoryCombobox.getItems().add("None selected");
		for (int i = 0; i < ecs.length; i++) {
			effortCategoryCombobox.getItems().add(ecs[i]);
		}
		
		/* SET COMBOBOXES TO HAVE FIRST ELEMENT AUTOMATICALLY SELECTED */
		projectCombobox.getSelectionModel().selectFirst();
		effortCategoryCombobox.getSelectionModel().selectFirst();
	}
	
	@FXML
	void toggleClockStatus(MouseEvent event) {
		EffortLog log;
		int projIndex;
		int effCatIndex;
		
		event.consume();
		
		if (projectCombobox.getValue() == null || projectCombobox.getValue().equals("") || projectCombobox.getValue().equals("None selected")) {
			projectErrorMsg.setVisible(true);
			return;
		} else {
			projectErrorMsg.setText("");
			if (lifeCycleStepCombobox.getValue() == null || lifeCycleStepCombobox.getValue().equals("") || lifeCycleStepCombobox.getValue().equals("None selected")) {
				lifeCycleStepErrorMsg.setVisible(true);
				return;
			} else {
				lifeCycleStepErrorMsg.setVisible(false);
				if (effortCategoryCombobox.getValue() != null) {
					switch (effortCategoryCombobox.getValue()) {
						case "":
						case "None selected":
							effortCategoryErrorMsg.setVisible(true);
							return;
						case "Others":
							effortCategoryErrorMsg.setVisible(false);
							break;
						default:
							effortCategoryErrorMsg.setVisible(false);
							if (effortSubCategoryCombobox.getValue() != null) {
								switch (effortSubCategoryCombobox.getValue()) {
									case "":
									case "None Selected":
										effortSubCategoryErrorMsg.setVisible(true);
										return;
									default:
										effortSubCategoryErrorMsg.setVisible(false);
										break;
								}
							} else {
								effortSubCategoryErrorMsg.setVisible(true);
								return;
							}
							break;
					}
				} else {
					effortCategoryErrorMsg.setVisible(true);
					return;
				}
			}
		}
		
		if (startTime < 0) {
			// START THE CLOCK
			startTime = System.currentTimeMillis();
			clockStatusLabel.setText("Clock is Started");
			clockStatusPane.setStyle("-fx-background-color: limegreen");
			clockControlButton.setText("Stop Clock");
		} else {
			// STOP THE CLOCK
			endTime = System.currentTimeMillis();
			clockStatusLabel.setText("Clock is Stopped");
			clockStatusPane.setStyle("-fx-background-color: red");
			clockControlButton.setText("Start Clock");
			
			
			
			projIndex = indexOf(projectCombobox,projectCombobox.getValue()) - 1;
			effCatIndex = indexOf(effortCategoryCombobox,effortCategoryCombobox.getValue()) - 1;
			
			log = new EffortLog();
			log.setEffortCategory(effCatIndex);
			log.setLifeCycleStep(indexOf(lifeCycleStepCombobox,lifeCycleStepCombobox.getValue()) - 1);
			log.setStart(startTime);
			log.setEnd(endTime);
			
			if (detailsField.getText() != null) {
				log.setDetails(detailsField.getText());
			}
			
			switch (effCatIndex) {
				case 3:
					log.setDefect(indexOf(effortSubCategoryCombobox,effortSubCategoryCombobox.getValue()) - 1);
					log.setDetails("");
					break;
				case 4:
					log.setDetails(detailsField.getText());
					break;
				default:
					log.setEffortSubCategory(indexOf(effortSubCategoryCombobox,effortSubCategoryCombobox.getValue()) - 1);
					break;
			}
			
			table.insertLogOrdered(log, projIndex);
			
			startTime = -1;
			endTime = -1;
		}
	}
	
	/* TRIGGERS WHEN A NEW VALUE IS SELECTED FROM THE PROJECT COMBO BOX 
	 * this function auto-fills the life cycle step combo box 
	 * with the life cycle steps that the user has set for this
	 * project */
	@FXML
	void toggleProjectSelected() {
		
		// index of value selected from combo box
		int selectedIndex = indexOf(projectCombobox,projectCombobox.getValue()) - 1;
		
		// grab the application's list of projects
		ArrayList<String> lifeCycleSteps = table.getDefinitions().lifeCycleSteps;
		
		// variable that will be used to hold the list of specific life cycle steps
		// associated with the selected project
		ArrayList<Integer> projLifeCycleSteps;
		
		// IF THE USER SELECTED "None selected" FROM THE PROJECT COMBO BOX
		if (selectedIndex < 0) {
			lifeCycleStepCombobox.getItems().clear();
			
		// OTHERWISE
		} else {
			
			// Get the list of life cycle steps associated with the selected project
			projLifeCycleSteps = table.getDefinitions().projectLifeCycleSteps.get(selectedIndex);
			
			// clear the elements in the life cycle steps combo box
			lifeCycleStepCombobox.getItems().clear();
			
			// Add the "None selected" option
			lifeCycleStepCombobox.getItems().add("None selected");
			
			// Add all the life cycle steps associated with this project to the life cycle step combo box
			for (int i = 0; i < projLifeCycleSteps.size(); i++) {
				lifeCycleStepCombobox.getItems().add(lifeCycleSteps.get(projLifeCycleSteps.get(i)));
			}
		}
		
		// Set the first element in the list as the one that is selected
		lifeCycleStepCombobox.getSelectionModel().selectFirst();
	}
	
	/* TRIGGERS WHEN A NEW VALUE IS SELECTED FROM THE EFFORT CATEGORY COMBO BOX 
	 * This function auto-fills the effort category combo box with the corresponding
	 * sub categories associated with the selected effort category. 
	 * 
	 * However, If the user selects an effort category that requires the user to 
	 * input of details rather than choose an associated effort category, this function 
	 * will instead make the "Other details" field available for the user to input
	 * the corresponding information */
	@FXML 
	void toggleEffortCategorySelected() {
		
		// get the string value that was selected
		String selected = effortCategoryCombobox.getValue();
		
		// get the index of the string value that was selected
		int selectedIndex = indexOf(effortCategoryCombobox,selected) - 1;
		
		// variable that will store the index of the project that is currently
		// selected by the project combo box should it be necessary
		int projIndex;
		
		// Array list that will store the defects associated with a given project
		// should that be necessary
		ArrayList<Defect> defects;
		
		// Array list that will store the sub categories of whatever
		// effort category was selected
		ArrayList<String> effSubCats;
			
		// Remove all items from the effort sub category combo box
		effortSubCategoryCombobox.getItems().clear();
		switch (selectedIndex) {
			// If the user selected the option labeled "None selected"
			case -1:
				// Clear the label for the effort sub category
				effortSubCategoryLabel.setText("");
				
				// Clear the label for the "Other details" field
				detailsLabel.setText("");
				
				// Make the "Other details" field inaccessible
				detailsField.setText("");
				detailsField.setVisible(false);
				
				break;
			// IF THE USER SELECTED "Defects"
			case 3:
				
				// Get the index of the currently selected project
				projIndex = indexOf(projectCombobox,projectCombobox.getValue()) - 1;
				
				// Add the corresponding label the the effort sub category field
				effortSubCategoryLabel.setText("Defects:");
				
				// If there is no project currently selected
				if (projIndex < 0) {
					
					// Clear the label for the "Other details" field
					detailsLabel.setText("");
					
					// Make the "Other details" field inaccessible
					detailsField.setText("");
					detailsField.setVisible(false);
					
				// If the user DOES have a project selected
				} else {
					
					// Get the list of the defects associated with this project
					defects = table.getDefects().get(projIndex);
					
					// Add the "None selected" option
					effortSubCategoryCombobox.getItems().add("None selected");
					
					// Add the name of each defect associated with this project
					// to the effort sub category combo box
					for (Defect defect : defects) {
						effortSubCategoryCombobox.getItems().add(defect.getDefectName());
					}

				}

				// Set the first element in the combo box to be the one that is selected
				effortSubCategoryCombobox.getSelectionModel().selectFirst();
				
				break;
				
			// IF THE USER SELECTED "Other"
			case 4:
				
				// Clear the effort sub category label
				effortSubCategoryLabel.setText("");
				
				// Add the label to the "Other details" field
				detailsLabel.setText("Enter Other details here:");
				
				// Make the "Other details" field accessible to the user
				detailsField.setText("");
				detailsField.setVisible(true);
				break;
				
			// IF THE USER SELECTED "Deliverables", "Plans", or "Interruptions"
			default:
				
				// Clear the label for the "Other details" field
				detailsLabel.setText("");
				
				// Make the "Other details" field inaccessible
				detailsField.setVisible(false);
				
				// Add the appropriate label to the effort sub category field according to
				// the selected effort category
				effortSubCategoryLabel.setText(selected + ":");
				
				// Grab the list of effort sub categories associated with this effort category
				effSubCats = UserData.table.getDefinitions().effortSubCategories[selectedIndex];
				
				// Add the "None selected" option
				effortSubCategoryCombobox.getItems().add("None selected");
				
				// Add all the effort sub categories associated with the selected effort category
				for (String cat : effSubCats) {
					effortSubCategoryCombobox.getItems().add(cat);
				}
				
				effortSubCategoryCombobox.getSelectionModel().selectFirst();
				break;
		}
	}
	
	/* TRIGGERS WHEN A NEW VALUE IS SELECTED FROM THE EFFORT SUB-CATEGORY COMBO BOX 
	 * This function does not auto-populate any fields but, if the user selects an 
	 * effort sub category that requires the user to input custom information, the 
	 * function will make the "Other details" field available to the user so they can
	 * input that information */
	@FXML
	void toggleEffortSubCategorySelected() {
		
		// Get selected string from the combo box
		String selected = effortSubCategoryCombobox.getValue();
		if (selected != null) {
			switch (selected) {
			
				// If the user selected "User Defined" or "Other",
				// make the "Other details" field available to them
				case "User Defined":
				case "Other":
					detailsLabel.setText("Specify details here:");
					detailsField.setVisible(true);
					break;
					
				// If the user did not selected either of the above
				// options, hide the "Other details" field
				default:
					detailsLabel.setText("");
					detailsField.setText("");
					detailsField.setVisible(false);
					break;
			}
		}
	}
	
	/* TRIGGERS WHEN A NEW VALUE IS SELECTED FROM THE LIFE CYCLE STEPS COMBO BOX 
	 * This function, auto-selects the corresponding effort category and effort
	 * sub-category associated with the selected life cycle step */
	@FXML
	void toggleLifeCycleStepSelected() {

		// Initialize a definitions object to make the searching easier
		String selected = lifeCycleStepCombobox.getValue();
		String effSubCat;
		
		Definitions def = UserData.table.getDefinitions();
		
		// Get the index of the element that was selected
		int selectedIndex = indexOf(lifeCycleStepCombobox,lifeCycleStepCombobox.getValue()) - 1;
		
		int projIndex = indexOf(projectCombobox,projectCombobox.getValue()) - 1;
		
		int effCat;
		
		// If the user selected "None selected"
		if (selectedIndex < 0) {
			
			// Set the effort category combo box also to "None selected"
			effortCategoryCombobox.setValue("None selected");
			
			// Clear the data from the effort sub category fields
			effortSubCategoryLabel.setText("");
			effortSubCategoryCombobox.getItems().clear();
			
			// Clear the "Other details" field and make it inaccessible to the user 
			detailsLabel.setText("");
			detailsField.setText("");
			detailsField.setVisible(false);
			
		// If the user selected an actual life cycle step
		} else {
			
			effCat = def.lifeCycleStepEffortCategories.get(selectedIndex);
			
			// Select the effort category associated with this life cycle step from the effort category combo box
			effortCategoryCombobox.setValue(def.effortCategories[effCat]);
			
			switch (effCat) {
				case 3:
					effSubCat = UserData.table.getDefect(projIndex, def.lifeCycleStepEffortSubCategories.get(selectedIndex)).getDefectName();
					effortSubCategoryCombobox.setValue(effSubCat);
					break;
				case 4:
					break;
				default:
					effSubCat = def.effortSubCategories[effCat].get(def.lifeCycleStepEffortSubCategories.get(selectedIndex));
					break;
			}
		}
	}
	
	@FXML
	void sendToEditor(ActionEvent event) throws IOException
	{
		Parent editorParent = FXMLLoader.load(getClass().getResource("EffortLogEditor.fxml"));
		Scene editLogsScene = new Scene(editorParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(editLogsScene);
		window.show();		
	}
	

	private static int indexOf(ComboBox<String> c, String value) {
		int result = -1;
		ObservableList<String> ob = c.getItems();
		int index = 0;
		for (String item : ob) {
			if (item.equals(value)) {
				return index;
			}
			index++;
		}
		return result;
	}


	//TODO: Race added these features into the effortLogCConsole controller
	//TODO: FROM here down
	@FXML
	public void goToDefinitions(ActionEvent event) throws IOException{
		Parent root;

		root = FXMLLoader.load(getClass().getResource("DefinitionsConsole.fxml"));
		Stage stage = (Stage) definitionsButton.getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
