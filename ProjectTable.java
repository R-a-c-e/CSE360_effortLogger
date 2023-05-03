package backend;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectTable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// EFFORT LOG TABLE ORGANIZED BY PROJECT
	ArrayList<ArrayList<EffortLog>> table;
	
	// DEFECT LOG TABLE ORGANIZED BY PROJECT
	ArrayList<ArrayList<Defect>> defects;
	
	// DEFINITIONS INSTANCE THAT MAPPS INDEXES TO STRING VALUES
	Definitions definitions;
	
	public ProjectTable() {
		table = new ArrayList<ArrayList<EffortLog>>();
		defects = new ArrayList<ArrayList<Defect>>();
		definitions = new Definitions();
	}
	
	// SETTERS
	public void setTable(ArrayList<ArrayList<EffortLog>> table) {
		this.table = table;
	}
	public void setDefects(ArrayList<ArrayList<Defect>> defects) {
		this.defects = defects;
	}
	public void setDefinitions(Definitions def) {
		definitions = def;
	}
	
	// GETTERS
	public ArrayList<ArrayList<EffortLog>> getTable() {
		return table;
	}
	public ArrayList<ArrayList<Defect>> getDefects() {
		return defects;
	}
	public Definitions getDefinitions() {
		return definitions;
	}
	
	// GETTERS FOR INDIVIDUAL ENTRIES IN TABLE
	public EffortLog getLog(int projIndex, int logIndex) {
		return table.get(projIndex).get(logIndex);
	}
	public Defect getDefect(int projIndex, int defIndex) {
		return defects.get(projIndex).get(defIndex);
	}
	
	// INSERT FUNCTIONS FOR INSERTING INDIVIDUAL ENTRIES INTO TABLE
	public void insertLog(EffortLog log, int index) {
		table.get(index).add(log);
	}
	public void insertLogOrdered(EffortLog log, int index) {
		boolean inserted = false;
		ArrayList<EffortLog> list = table.get(index);
		for (int i = 0; i < list.size(); i++) {
			if (log.getStart() == list.get(i).getStart()) {
				if (log.getEnd() <= list.get(i).getEnd()) {
					list.add(i + 1, log);
					inserted = true;
				}
			} else if (log.getStart() < list.get(i).getStart()) {
				list.add(i + 1,log);
				inserted = true;
			}
		}
		if (!inserted) {
			list.add(log);
		}
	}
	public void insertDefect(Defect defect, int index) {
		defects.get(index).add(defect);
	}
	
	// REMOVE FUNCTIONS FOR REMOVING INDIVIDUAL ENTRIES FROM TABLE
	public void removeLog(int tableIndex, int logIndex) {
		table.get(tableIndex).remove(logIndex);
	}
	public void removeDefect(int tableIndex, int defIndex) {
		defects.get(tableIndex).remove(defIndex);
	}
	
	// SETTERS FOR SETTING INDIVIDUAL ENTRIES IN TABLE
	public void setLog(int tableIndex, int logIndex, EffortLog log) {
		table.get(tableIndex).set(logIndex, log);
	}
	public void setDefect(int tableIndex, int defIndex, Defect def) {
		defects.get(tableIndex).set(defIndex, def);
	}
	
	// FUNCTIONS FOR MANIPULATING PROJECT TABLE CONTAINED IN THE DEFINITIONS CLASS
	//
	// These functions should more often be used instead of the functions found in
	// the Definitions class because these functions update the ArrayList of projects
	// and project life cycle steps found in the Definitions class AND they also
	// update the project table found in this class accordingly.
	public void addProject(String project) {
		definitions.addProject(project);
		table.add(new ArrayList<EffortLog>());
		defects.add(new ArrayList<Defect>());
	}
	public void removeProject(int index) {
		definitions.removeProject(index);
		table.remove(index);
		defects.remove(index);
	}
	public void setProject(int index, String project) {
		definitions.setProject(index, project);
	}
	public int projectIndexOf(String project) {
		return definitions.projectIndexOf(project);
	}
}
