package backend;

import java.io.Serializable;

public class Defect implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String defectName;
	private String details;
	private int lifeCycleInjected;
	private int lifeCycleRemoved;
	private int defectCategory;
	private boolean isOpen;
	private Defect fix;
	
	public Defect() {
		defectName = "";
		lifeCycleInjected = -1;
		lifeCycleRemoved = -1;
		defectCategory = -1;
		isOpen = false;
		fix = null;
	}
	
	public String getDefectName() {
		return defectName;
	}
	public String getDetails() {
		return details;
	}
	public int getLifeCycleInjected() {
		return lifeCycleInjected;
	}
	public int getLifeCycleRemoved() {
		return lifeCycleRemoved;
	}
	public int getDefectCategory() {
		return defectCategory;
	}
	public boolean getIsOpen() {
		return isOpen;
	}
	public Defect getFix() {
		return fix;
	}
	
	public void setDefectName(String defectName) {
		this.defectName = defectName;
	}
	public void setLifeCycleInjected(int lifeCycleInjected) {
		this.lifeCycleInjected = lifeCycleInjected;
	}
	public void setLifeCycleRemoved(int lifeCycleRemoved) {
		this.lifeCycleRemoved = lifeCycleRemoved;
	}
	public void setDefectCategory(int defectCategory) {
		this.defectCategory = defectCategory;
	}
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public void setFix(Defect fix) {
		this.fix = fix;
	}
	
	public boolean equals(Defect defect) {
		if (defect != null) {
			return (defectName.equals(defect.getDefectName()) && lifeCycleInjected == defect.getLifeCycleInjected() && lifeCycleRemoved == defect.getLifeCycleRemoved() && defectCategory == defect.getDefectCategory() && !(isOpen ^ defect.getIsOpen()) && fix == defect.getFix());
		} else {
			return false;
		}
	}
	public void copy(Defect defect) {
		defectName = defect.getDefectName();
		lifeCycleInjected = defect.getLifeCycleInjected();
		lifeCycleRemoved = defect.getLifeCycleRemoved();
		defectCategory = defect.getDefectCategory();
		isOpen = defect.getIsOpen();
		fix = defect.getFix();
	}
}
