package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.lang.Integer;


public class DefinitionsConsoleController {


    @FXML private GridPane bottomGridPane;
    @FXML private BorderPane centerPane;
    @FXML private VBox gridPane3;
    @FXML private BorderPane majorPane;
    @FXML private VBox topBar;
    @FXML private Label topLabel;
    @FXML private Button effortLogButton;



    public void initialize(){
        //set up project tab
        setUpProjectComboBox();
        //set up Life Cycle Steps tab
        setUpLifeCycleComboBox();
        //set up Plans tab
        setUpPlansComboBox();
        //set up interrupt tab
        setUpInterruptComboBox();
        //set up deliverable tab
        setUpDeliverableComboBox();
        //set up defect category tab
        setUpDefectCategoryComboBox();
    }




    //--------------------------------------------------------------------------------
    //---------------For The Projects Tab---------------
    @FXML private Button project_LCaddButton;
    @FXML private ComboBox<String> project_LCcombo;
    @FXML private Button project_LCdeleteButton;
    @FXML private TextField project_LCtext;
    @FXML private Button project_applyButton;
    @FXML private ComboBox<String> project_combo;
    @FXML private Button project_createButton;
    @FXML private Button project_deleteButton;
    @FXML private TextField project_projectNameText;

    @FXML
    //when the "delete project" button is pushed
    //the selected project will be deleted.
    void removeProjectFromProjectsList(ActionEvent event) {
        String name = project_combo.getValue();
        if(name != "None selected") {
            int idx = UserData.table.getDefinitions().projectIndexOf(name);
            UserData.table.getDefinitions().removeProject(idx);
            setUpProjectComboBox();
        }
    }

    @FXML
    //we want to get the value from the text box and add this value
    //to the list of available projects
    void addProjectToProjectsList(ActionEvent event) {
        String name = project_projectNameText.getText();
        //TODO: deal with duplicates
        UserData.table.getDefinitions().addProject(name);
        setUpProjectComboBox();
        project_projectNameText.clear();
    }

    @FXML
    //we want to get the value from the text box and change the name
    //of whatever project is selected to that new value
    void editProjectName(ActionEvent event) {
        String oldName = project_combo.getValue();
        String newName = project_projectNameText.getText();
        if(oldName != "None selected"){
            int idx = UserData.table.getDefinitions().projectIndexOf(oldName);
            UserData.table.getDefinitions().setProject(idx, newName);
            setUpProjectComboBox();
            project_projectNameText.clear();
        }
    }

    @FXML
    //based on the project selected, when the "delete step" button is
    //pushed, we want to delete that life cycle step from the given project
    void deleteLifeCycleStepFromProject(ActionEvent event) {
        String projectName = project_combo.getValue();
        String cycleStep = project_LCcombo.getValue();
        if(projectName != "None selected" && cycleStep != "None selected") {
            int step = Integer.parseInt(project_LCcombo.getValue());
            int projIdx = UserData.table.getDefinitions().projectIndexOf(projectName);
            int stepIdx = UserData.table.getDefinitions().projectLifeCycleStepIndexOf(projIdx, step);
            UserData.table.getDefinitions().removeProjectLifeCycleStep(projIdx,stepIdx);
            getLifeCycleStepsForProject();
        }
    }

    @FXML
    //based on the project selected, when the "add step" button is
    //pushed, we want to add that life cycle step to the given project
    void addLifeCycleStepToProject(ActionEvent event) {
        String projectName = project_combo.getValue();
        if(projectName != "None selected") {
            int idx = UserData.table.getDefinitions().projectIndexOf(projectName);
            //TODO: ensure the user inputs an int
            int step = Integer.parseInt(project_LCtext.getText());
            UserData.table.getDefinitions().addProjectLifeCycleStep(idx, step);

            //need to reset the comboBox and textBox for
            //life cycle steps with the new addition
            getLifeCycleStepsForProject();
            project_LCtext.clear();
        }
    }


    @FXML
    //get the life cycle steps for a project selected in the project combo box
    //just a helper function
    void getLifeCycleStepsForProject(){
        //first we clear the combo box form previous selection
        project_LCcombo.getItems().clear();

        //get the name of the selected project
        String name = project_combo.getValue();
        if(name != "None selected"){
            int idx = 0;
            ArrayList<String> projs = UserData.table.getDefinitions().projects;

            //find the index of the selected project
            for(int ii = 0; ii < projs.size(); ii++){
                if(projs.get(ii) == name){
                    idx = ii;
                }
            }

            //list of all the projects and their life cycle steps
            ArrayList<ArrayList<Integer>> projSteps = UserData.table.getDefinitions().projectLifeCycleSteps;

            //set up the life cycle combo box
            project_LCcombo.getItems().add("None selected");
            for (int ii = 0; ii < projSteps.get(idx).size(); ii++) {
                project_LCcombo.getItems().add(Integer.toString(projSteps.get(idx).get(ii)));
            }
            project_LCcombo.getSelectionModel().selectFirst();
        }
    }

    @FXML
    void setUpProjectComboBox(){
        project_combo.getItems().clear();
        ArrayList<String> projs = UserData.table.getDefinitions().projects;
        project_combo.getItems().add("None selected");
        for (int i = 0; i < projs.size(); i++) {
            project_combo.getItems().add(projs.get(i));
        }
        project_combo.getSelectionModel().selectFirst();
    }



    //--------------------------------------------------------------------------------
    //---------------For The Life Cycle Steps Tab---------------
    @FXML private Button cycle_applyButton;
    @FXML private TextField cycle_categoryText;
    @FXML private ComboBox<String> cycle_combo;
    @FXML private Button cycle_createButton;
    @FXML private Button cycle_deleteButton;
    @FXML private TextField cycle_deliverableText;
    @FXML private TextField cycle_stepText;

    @FXML
    //remove a life cycle step from the list based on current selection
    void removeStepFromCycleList(ActionEvent event) {
        String stepName = cycle_combo.getValue();
        if(stepName != "None selected") {
            int idx = UserData.table.getDefinitions().lifeCycleStepIndexOf(stepName);
            UserData.table.getDefinitions().removeLifeCycleStep(idx);
            setUpLifeCycleComboBox();
        }
    }

    @FXML
    //based on the selected value from the combo box, we can make changes
    //to all the individual parts of a life cycle step
    void editStepInCycleList(ActionEvent event) {
        String selected = cycle_combo.getValue();
        String stepName = cycle_stepText.getText();
        String category = cycle_categoryText.getText();
        String deliver = cycle_deliverableText.getText();
        if(selected != "None selected"){
            int idx = UserData.table.getDefinitions().lifeCycleStepIndexOf(selected);

            //if the project name text box has a value
            if(!stepName.isEmpty()){
                UserData.table.getDefinitions().setLifeCycleStep(idx, stepName);

            }
            //if the EC text box has a value
            if(!category.isEmpty()){
                int cat = Integer.parseInt(category);
                UserData.table.getDefinitions().setLifeCycleStepEC(idx, cat);
            }
            //if the deliverable text box has a value
            if(!deliver.isEmpty()){
                int del = Integer.parseInt(deliver);
                UserData.table.getDefinitions().setLifeCycleStepD(idx, del);
            }
            clearCycleStepTextBoxes();
            setUpLifeCycleComboBox();
        }
    }

    @FXML
    //add a new choose-able life cycle step based on the given input
    void addNewStepToCycleList(ActionEvent event) {
        String stepName = cycle_stepText.getText();
        String category = cycle_categoryText.getText();
        String deliver = cycle_deliverableText.getText();
        if(stepName != "" && category != "" && deliver != ""){
            int cat = Integer.parseInt(category);
            int del = Integer.parseInt(deliver);
            UserData.table.getDefinitions().addLifeCycleStep(stepName, cat, del);

            //add to list and clear text boxes
            setUpLifeCycleComboBox();
            clearCycleStepTextBoxes();
        }
    }

    @FXML
    void clearCycleStepTextBoxes(){
        cycle_categoryText.clear();
        cycle_stepText.clear();
        cycle_deliverableText.clear();
    }

    @FXML
    void setUpLifeCycleComboBox(){
        cycle_combo.getItems().clear();
        ArrayList<String> steps = UserData.table.getDefinitions().lifeCycleSteps;
        cycle_combo.getItems().add("None selected");
        for (int i = 0; i < steps.size(); i++) {
            cycle_combo.getItems().add(steps.get(i));
        }
        cycle_combo.getSelectionModel().selectFirst();
    }

    //--------------------------------------------------------------------------------
    //---------------For The Plans Tab---------------
    @FXML private Button plans_applyButton;
    @FXML private Button plans_createButton;
    @FXML private Button plans_deleteButton;
    @FXML private ComboBox<String> plans_combo;
    @FXML private TextField plans_text;


    @FXML
    void removePlanFromPlansList(ActionEvent event) {
        String name = plans_combo.getValue();
        if(name != "None selected"){
            int idx = UserData.table.getDefinitions().planIndexOf(name);
            UserData.table.getDefinitions().removePlan(idx);
            setUpPlansComboBox();
        }
    }

    @FXML
    void addPlanToPlansList(ActionEvent event) {
        String name = plans_text.getText();
        if(!name.isEmpty()){
            UserData.table.getDefinitions().addPlan(name);
            setUpPlansComboBox();
            plans_text.clear();
        }
    }

    @FXML
    void editPlanInPlansList(ActionEvent event) {
        String name = plans_combo.getValue();
        if(name != "None selected"){
            String newName = plans_text.getText();
            if(!newName.isEmpty()){
                int idx = UserData.table.getDefinitions().planIndexOf(name);
                UserData.table.getDefinitions().setPlan(idx, newName);
                plans_text.clear();
                setUpPlansComboBox();
            }
        }
    }

    void setUpPlansComboBox(){
        plans_combo.getItems().clear();
        ArrayList<String> plans = UserData.table.getDefinitions().effortSubCategories[0];
        plans_combo.getItems().add("None selected");
        for (int i = 0; i < plans.size(); i++) {
            plans_combo.getItems().add(plans.get(i));
        }
        plans_combo.getSelectionModel().selectFirst();
    }

    //--------------------------------------------------------------------------------
    //---------------For The Interruptions Tab---------------
    @FXML private Button interrupt_applyButton;
    @FXML private ComboBox<String> interrupt_combo;
    @FXML private Button interrupt_createButton;
    @FXML private Button interrupt_deleteButton;
    @FXML private TextField interrupt_text;

    @FXML
    void removeFromInterruptList(ActionEvent event) {
        String name = interrupt_combo.getValue();
        if(name != "None selected"){
            int idx = UserData.table.getDefinitions().interruptionIndexOf(name);
            UserData.table.getDefinitions().removeInterruption(idx);
            setUpInterruptComboBox();
        }
    }

    @FXML
    void addToInterruptList(ActionEvent event) {
        String name = interrupt_text.getText();
        if(!name.isEmpty()){
            UserData.table.getDefinitions().addInterruption(name);
            setUpInterruptComboBox();
            interrupt_text.clear();
        }
    }

    @FXML
    void editElementInInterruptList(ActionEvent event) {
        String name = interrupt_combo.getValue();
        if(name != "None selected"){
            String newName = interrupt_text.getText();
            if(!newName.isEmpty()){
                int idx = UserData.table.getDefinitions().interruptionIndexOf(name);
                UserData.table.getDefinitions().setInterruption(idx, newName);
                interrupt_text.clear();
                setUpInterruptComboBox();
            }
        }
    }

    void setUpInterruptComboBox(){
        interrupt_combo.getItems().clear();
        ArrayList<String> interrupt = UserData.table.getDefinitions().effortSubCategories[2];
        interrupt_combo.getItems().add("None selected");
        for (int i = 0; i < interrupt.size(); i++) {
            interrupt_combo.getItems().add(interrupt.get(i));
        }
        interrupt_combo.getSelectionModel().selectFirst();
    }

    //--------------------------------------------------------------------------------
    //---------------For The Deliverables Tab---------------
    @FXML private Button deliverable_applyButton;
    @FXML private ComboBox<String> deliverable_combo;
    @FXML private Button deliverable_createButton;
    @FXML private Button deliverable_deleteButton;
    @FXML private TextField deliverable_text;

    @FXML
    void removeFromDeliverableList(ActionEvent event) {
        String name = deliverable_combo.getValue();
        if(name != "None selected"){
            int idx = UserData.table.getDefinitions().deliverableIndexOf(name);
            UserData.table.getDefinitions().removeDeliverable(idx);
            setUpDeliverableComboBox();
        }
    }

    @FXML
    void addToDeliverableList(ActionEvent event) {
        String name = deliverable_text.getText();
        if(!name.isEmpty()){
            UserData.table.getDefinitions().addDeliverable(name);
            setUpDeliverableComboBox();
            deliverable_text.clear();
        }
    }

    @FXML
    void editElementInDeliverableList(ActionEvent event) {
        String name = deliverable_combo.getValue();
        if(name != "None selected"){
            String newName = deliverable_text.getText();
            if(!newName.isEmpty()){
                int idx = UserData.table.getDefinitions().deliverableIndexOf(name);
                UserData.table.getDefinitions().setDeliverable(idx, newName);
                deliverable_text.clear();
                setUpDeliverableComboBox();
            }
        }
    }

    void setUpDeliverableComboBox() {
        deliverable_combo.getItems().clear();
        ArrayList<String> deliverable = UserData.table.getDefinitions().effortSubCategories[1];
        deliverable_combo.getItems().add("None selected");
        for (int i = 0; i < deliverable.size(); i++) {
            deliverable_combo.getItems().add(deliverable.get(i));
        }
        deliverable_combo.getSelectionModel().selectFirst();
    }

    //--------------------------------------------------------------------------------
    //---------------For The Defect Categories Tab---------------
    @FXML private Button defect_applyButton;
    @FXML private ComboBox<String> defect_combo;
    @FXML private Button defect_createButton;
    @FXML private Button defect_deleteButton;
    @FXML private TextField defect_text;

    @FXML
    void removeFromDefectList(ActionEvent event) {
        String name = defect_combo.getValue();
        if(name != "None selected"){
            int idx = UserData.table.getDefinitions().defectCategoryIndexOf(name);
            UserData.table.getDefinitions().removeDefectCategory(idx);
            setUpDefectCategoryComboBox();
        }
    }

    @FXML
    void addToDefectList(ActionEvent event) {
        String name = defect_text.getText();
        if(!name.isEmpty()){
            UserData.table.getDefinitions().addDefectCategory(name);
            setUpDefectCategoryComboBox();
            defect_text.clear();
        }
    }

    @FXML
    void editElementInDefectList(ActionEvent event) {
        String name = defect_combo.getValue();
        if(name != "None selected"){
            String newName = defect_text.getText();
            if(!newName.isEmpty()){
                int idx = UserData.table.getDefinitions().defectCategoryIndexOf(name);
                UserData.table.getDefinitions().setDefectCategory(idx, newName);
                defect_text.clear();
                setUpDefectCategoryComboBox();
            }
        }
    }

    void setUpDefectCategoryComboBox() {
        defect_combo.getItems().clear();
        ArrayList<String> defect = UserData.table.getDefinitions().defectCategories;
        defect_combo.getItems().add("None selected");
        for (int i = 0; i < defect.size(); i++) {
            defect_combo.getItems().add(defect.get(i));
        }
        defect_combo.getSelectionModel().selectFirst();
    }



    //--------------------------------------------------------------------------------
    //---------------Return to Effort Log Console---------------
    @FXML
    //This is essentially the back button, takes us back to the effort logger console
    void sendToConsole(ActionEvent event) throws IOException {
        Parent root;

        root = FXMLLoader.load(getClass().getResource("EffortLogConsole.fxml"));
        Stage stage = (Stage) effortLogButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
