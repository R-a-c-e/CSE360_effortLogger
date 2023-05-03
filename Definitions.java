package backend;

import java.io.Serializable;
import java.util.ArrayList;

public class Definitions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// LIST OF ALL PROJECTS
	public final ArrayList<String> projects = new ArrayList<String>();
	
	// LIST OF LIFE CYCLE STEPS MAPPED TO EACH PROJECT
	public final ArrayList<ArrayList<Integer>> projectLifeCycleSteps = new ArrayList<ArrayList<Integer>>();
	
	// LIST OF POSSIBLE LIFE CYCLE STEPS
	public final ArrayList<String> lifeCycleSteps = new ArrayList<String>();
	
	// LIST OF EFFORT CATEGORIES FOR EACH LIFE CYCLE STEP
	public final ArrayList<Integer> lifeCycleStepEffortCategories = new ArrayList<Integer>();
	
	// LIST OF EFFORT SUB CATEGORIES FOR EACH LIFE CYCLE STEP
	public final ArrayList<Integer> lifeCycleStepEffortSubCategories = new ArrayList<Integer>();
	
	// LIST OF EFFORT CATEGORIES (THIS CANNOT BE CHANGED)
	public final String[] effortCategories = {
		"Plans",
		"Deliverables",
		"Interruptions",
		"Defects",
		"Others"
	};
	
	// LIST OF EFFORT SUBCATEGORIES MAPPED TO EACH EFFORT CATEGORY
	public final ArrayList<String>[] effortSubCategories = (ArrayList<String>[]) new ArrayList[3];
	
	// LIST OF ALL DEFECT CATEGORIES
	public final ArrayList<String> defectCategories = new ArrayList<String>();
	
	
	public Definitions() {
		
		// ADD ALL DEFAULT EFFORT SUBCATEGORIES
		for (int i = 0; i < 3; i++) {
			effortSubCategories[i] = new ArrayList<String>();
		}
	}
	
	// FUNCTION TO MANIPULATE PROJECT LIST
	public void addProject(String project) {
		projects.add(project);
		projectLifeCycleSteps.add(new ArrayList<Integer>());
	}
	public void removeProject(int index) {
		projects.remove(index);
		projectLifeCycleSteps.remove(index);
	}
	public void setProject(int index, String project) {
		projects.set(index, project);
	}
	public int projectIndexOf(String project) {
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).equals(project)) {
				return i;
			}
		}
		return -1;
	}
	
	// FUNCTIONS TO MANIPULATE PROJECT LIFE CYCLE LISTS
	public void addProjectLifeCycleStep(int projIndex, int step) {
		projectLifeCycleSteps.get(projIndex).add(step);
	}
	public void removeProjectLifeCycleStep(int projIndex, int index) {
		projectLifeCycleSteps.get(projIndex).remove(index);
	}
	public void setProjectLifeCycleStep(int projIndex, int index, int step) {
		projectLifeCycleSteps.get(projIndex).set(index, step);
	}

	//used to get the index of a step for a given project
	public int projectLifeCycleStepIndexOf(int projIndex, int step){
		for(int i = 0; i < projectLifeCycleSteps.get(projIndex).size(); i++){
			if(projectLifeCycleSteps.get(projIndex).get(i) == step){
				return i;
			}
		}
		return -1;
	}
	
	// FUNCTIONS TO MANIPULATE LIFE CYCLE LIST
	public void addLifeCycleStep(String step, int effortCategory, int effortSubCategory) {
		lifeCycleSteps.add(step);
		lifeCycleStepEffortCategories.add(effortCategory);
		lifeCycleStepEffortSubCategories.add(effortSubCategory);
	}
	public void removeLifeCycleStep(int index) {
		lifeCycleSteps.remove(index);
		lifeCycleStepEffortCategories.remove(index);
		lifeCycleStepEffortSubCategories.remove(index);
	}
	public void setLifeCycleStep(int index, String step) {
		lifeCycleSteps.set(index,step);
	}
	public void setLifeCycleStepEC(int index, int ec) {
		lifeCycleStepEffortCategories.set(index, ec);
	}
	public void setLifeCycleStepD(int index, int d) {
		lifeCycleStepEffortSubCategories.set(index, d);
	}
	public int lifeCycleStepIndexOf(String step) {
		for (int i = 0; i < lifeCycleSteps.size(); i++) {
			if (lifeCycleSteps.get(i).equals(step)) {
				return i;
			}
		}
		return -1;
	}
	//public String
	
	// FUNCTIONS TO MANIPULATE PLANS LIST
	public void addPlan(String plan) {
		effortSubCategories[0].add(plan);
	}
	public void removePlan(int index) {
		effortSubCategories[0].remove(index);
	}
	public void setPlan(int index, String plan) {
		effortSubCategories[0].set(index, plan);
	}
	public int planIndexOf(String plan) {
		for (int i = 0; i < effortSubCategories[0].size(); i++) {
			if (effortSubCategories[0].get(i).equals(plan)) {
				return i;
			}
		}
		return -1;
	}
	
	// FUNCTIONS TO MANIPULATE DELIVERABLES LIST
	public void addDeliverable(String deliverable) {
		effortSubCategories[1].add(deliverable);
	}
	public void removeDeliverable(int index) {
		effortSubCategories[1].remove(index);
	}
	public void setDeliverable(int index, String deliverable) {
		effortSubCategories[1].set(index, deliverable);
	}
	public int deliverableIndexOf(String deliverable) {
		for (int i = 0; i < effortSubCategories[1].size(); i++) {
			if (effortSubCategories[1].get(i).equals(deliverable)) {
				return i;
			}
		}
		return -1;
	}
	
	// FUNCTIONS TO MANIPULATE INTERRUPTIONS LIST
	public void addInterruption(String interruption) {
		effortSubCategories[2].add(interruption);
	}
	public void removeInterruption(int index) {
		effortSubCategories[2].remove(index);
	}
	public void setInterruption(int index, String interruption) {
		effortSubCategories[2].set(index, interruption);
	}
	public int interruptionIndexOf(String interruption) {
		for (int i = 0; i < effortSubCategories[2].size(); i++) {
			if (effortSubCategories[2].get(i).equals(interruption)) {
				return i;
			}
		}
		return -1;
	}
	
	// FUNCTIONS TO MANIPULATE DEFECT CATEGORIES LIST
	public void addDefectCategory(String defect) {
		defectCategories.add(defect);
	}
	public void removeDefectCategory(int index) {
		defectCategories.remove(index);
	}
	public void setDefectCategory(int index, String defect) {
		defectCategories.set(index, defect);
	}
	public int defectCategoryIndexOf(String defect) {
		for (int i = 0; i < defectCategories.size(); i++) {
			if (defectCategories.get(i).equals(defect)) {
				return i;
			}
		}
		return -1;
	}
}
