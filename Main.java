package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import backend.Definitions;
import backend.ProjectTable;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private static FileInputStream fin;
	private static FileOutputStream fout;
	private static ObjectInputStream oin;
	private static ObjectOutputStream oout;
	@Override
	
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("EffortLogConsole.fxml"));
			
			Scene scene = new Scene(root);
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					try {						
						fout = new FileOutputStream("tbl");
						oout = new ObjectOutputStream(fout);
						oout.writeObject(UserData.table);
						fout.close();
						oout.close();
						primaryStage.close();
					} catch (Exception e) {
						e.printStackTrace();
						primaryStage.close();
					}
				}
			});
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		loadData();
		launch(args);
	}
	private static void loadData() {
		try {			
			fin = new FileInputStream("tbl");
			oin = new ObjectInputStream(fin);
			UserData.table = (ProjectTable) oin.readObject();
			fin.close();
			oin.close();
		} catch (Exception e) {
			UserData.table = new ProjectTable();
			loadDefaultValues(UserData.table);
		}
	}
	
	private static void loadDefaultValues(ProjectTable table) {
		// ADD DEFAULT PROJECTS
		table.addProject("Business Project");
		table.addProject("Developoment Project");
		
		// ADD ALL DEFAULT LIFE CYCLE STEPS
		table.getDefinitions().addLifeCycleStep("Problem Understanding",2,1);
		table.getDefinitions().addLifeCycleStep("Conceptual Design Plan",1,3);
		table.getDefinitions().addLifeCycleStep("Requirements",2,1);
		table.getDefinitions().addLifeCycleStep("Conceptual Design",2,1);
		table.getDefinitions().addLifeCycleStep("Conceptual Design Review",2,1);
		table.getDefinitions().addLifeCycleStep("Detailed Design Plan",1,4);
		table.getDefinitions().addLifeCycleStep("Detailed Design/Prototype",2,2);
		table.getDefinitions().addLifeCycleStep("Detailed Design Review",2,2);
		table.getDefinitions().addLifeCycleStep("Implementation Plan",1,5);
		table.getDefinitions().addLifeCycleStep("Test Case Generation",2,3);
		table.getDefinitions().addLifeCycleStep("Solution Specification",2,4);
		table.getDefinitions().addLifeCycleStep("Solution Review",2,4);
		table.getDefinitions().addLifeCycleStep("Solution Implementation",2,4);
		table.getDefinitions().addLifeCycleStep("Unit/System Testing",2,4);
		table.getDefinitions().addLifeCycleStep("Reflection",2,4);
		table.getDefinitions().addLifeCycleStep("Repository Update",2,4);
		table.getDefinitions().addLifeCycleStep("Planning",1,1);
		table.getDefinitions().addLifeCycleStep("Information Gathering",2,1);
		table.getDefinitions().addLifeCycleStep("Information Understanding",2,1);
		table.getDefinitions().addLifeCycleStep("Verifying",2,1);
		table.getDefinitions().addLifeCycleStep("Outlining",2,6);
		table.getDefinitions().addLifeCycleStep("Drafting",2,7);
		table.getDefinitions().addLifeCycleStep("Finalizing",2,8);
		table.getDefinitions().addLifeCycleStep("Team Meeting",2,1);
		table.getDefinitions().addLifeCycleStep("Coach Meeting",2,1);
		table.getDefinitions().addLifeCycleStep("StakeHolder Meeting",2,1);
		
		// ADD DEFAULT LIFE CYCLE STEPS TO THE DEFAULT PROJECTS
		table.getDefinitions().addProjectLifeCycleStep(0,16);
		table.getDefinitions().addProjectLifeCycleStep(0,17);
		table.getDefinitions().addProjectLifeCycleStep(0,18);
		table.getDefinitions().addProjectLifeCycleStep(0,19);
		table.getDefinitions().addProjectLifeCycleStep(0,20);
		table.getDefinitions().addProjectLifeCycleStep(0,21);
		table.getDefinitions().addProjectLifeCycleStep(0,22);
		table.getDefinitions().addProjectLifeCycleStep(0,23);
		table.getDefinitions().addProjectLifeCycleStep(0,24);
		table.getDefinitions().addProjectLifeCycleStep(0,25);
		table.getDefinitions().addProjectLifeCycleStep(1,0);
		table.getDefinitions().addProjectLifeCycleStep(1,1);
		table.getDefinitions().addProjectLifeCycleStep(1,2);
		table.getDefinitions().addProjectLifeCycleStep(1,3);
		table.getDefinitions().addProjectLifeCycleStep(1,4);
		table.getDefinitions().addProjectLifeCycleStep(1,5);
		table.getDefinitions().addProjectLifeCycleStep(1,6);
		table.getDefinitions().addProjectLifeCycleStep(1,7);
		table.getDefinitions().addProjectLifeCycleStep(1,8);
		table.getDefinitions().addProjectLifeCycleStep(1,9);
		table.getDefinitions().addProjectLifeCycleStep(1,10);
		table.getDefinitions().addProjectLifeCycleStep(1,11);
		table.getDefinitions().addProjectLifeCycleStep(1,12);
		table.getDefinitions().addProjectLifeCycleStep(1,13);
		table.getDefinitions().addProjectLifeCycleStep(1,14);
		table.getDefinitions().addProjectLifeCycleStep(1,15);
		
		// ADD DEFAULT PLAN CATEGORIES
		table.getDefinitions().addPlan("Project Plan");
		table.getDefinitions().addPlan("Risk Management Plan");
		table.getDefinitions().addPlan("Conceptual Design Plan");
		table.getDefinitions().addPlan("Detailed Design Plan");
		table.getDefinitions().addPlan("Implementation Plan");
		
		// ADD DEFAULT DELIVERABLE CATEGORIES
		table.getDefinitions().addDeliverable("Conceptual Design");
		table.getDefinitions().addDeliverable("Detailed Design");
		table.getDefinitions().addDeliverable("Test Cases");
		table.getDefinitions().addDeliverable("Solution");
		table.getDefinitions().addDeliverable("Reflection");
		table.getDefinitions().addDeliverable("Outline");
		table.getDefinitions().addDeliverable("Draft");
		table.getDefinitions().addDeliverable("Report");

		//don't actually need these, these were only needed for the excel version
		//table.getDefinitions().addDeliverable("User Defined");
		//table.getDefinitions().addDeliverable("Other");
		
		// ADD DEFAULT INTERRUPTION CATEGORIES
		table.getDefinitions().addInterruption("Break");
		table.getDefinitions().addInterruption("Phone");
		table.getDefinitions().addInterruption("Teammate");
		table.getDefinitions().addInterruption("Visitor");
		table.getDefinitions().addInterruption("Other");
		
		// ADD ALL DEFAULT DEFECT CATEGORIES
		table.getDefinitions().addDefectCategory("Not Specified");
		table.getDefinitions().addDefectCategory("Documentation");
		table.getDefinitions().addDefectCategory("Syntax");
		table.getDefinitions().addDefectCategory("Build, Package");
		table.getDefinitions().addDefectCategory("Assignment");
		table.getDefinitions().addDefectCategory("Interface");
		table.getDefinitions().addDefectCategory("Checking");
		table.getDefinitions().addDefectCategory("Data");
		table.getDefinitions().addDefectCategory("Function");
		table.getDefinitions().addDefectCategory("System");
		table.getDefinitions().addDefectCategory("Environment");
	}
}
