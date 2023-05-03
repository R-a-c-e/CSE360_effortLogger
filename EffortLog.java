package backend;

import java.util.Date;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EffortLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int lifeCycleStep;
	private int effortCategory;
	private int effortSubCategory;
	private String details;
	private long start;
	private long end;
	private int defect;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	
	public EffortLog() {
		lifeCycleStep = -1;
		effortCategory = -1;
		effortSubCategory = -1;
		details = "";
		start = -1;
		end = -1;
		defect = -1;
	}

	public void setLifeCycleStep(int lifeCycleStep) {
		this.lifeCycleStep = lifeCycleStep;
	}
	public void setEffortCategory(int effortCategory) {
		this.effortCategory = effortCategory;
	}
	public void setEffortSubCategory(int effortSubCategory) {
		this.effortSubCategory = effortSubCategory;
	}
	public void setDetails(String details) {
	}
	public void setStart(long start) {
		this.start = start;
	}
	public void setStart(String s) {
		try {
		    Date d = DATE_FORMAT.parse(s);
		    start = d.getTime();
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public void setEnd(String s) {
		try {
		    Date d = DATE_FORMAT.parse(s);
		    start = d.getTime();
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}
	public void setDefect(int defect) {
		this.defect = defect;
	}

	public int getLifeCycleStep() {
		return lifeCycleStep;
	}
	public int getEffortCategory() {
		return effortCategory;
	}
	public int getEffortSubCategory() {
		return effortSubCategory;
	}
	public String getDetails() {
		return details;
	}
	public long getStart() {
		return start;
	}
	public String getStartString() {
		Timestamp s = new Timestamp(start);
		return DATE_FORMAT.format(new Date(s.getTime()));
	}
	public long getEnd() {
		return end;
	}
	public String getEndString() {
		Timestamp s = new Timestamp(end);
		return DATE_FORMAT.format(new Date(s.getTime()));
	}
	public int getDefect() {
		return defect;
	}
	
	public boolean equals(EffortLog log) {
		if (log != null) {
			return (lifeCycleStep == log.getLifeCycleStep() && effortCategory == log.getEffortCategory() && effortSubCategory == log.getEffortSubCategory() && details.equals(log.getDetails()) && start == log.getStart() && end == log.getEnd() && defect == log.getDefect());
		} else {
			return false;
		}
	}
	
	public void copy(EffortLog log) {
		lifeCycleStep = log.getLifeCycleStep();
		effortCategory = log.getEffortCategory();
		effortSubCategory = log.getEffortSubCategory();
		details = log.getDetails();
		start = log.getStart();
		end = log.getEnd();
		defect = log.getDefect();
	}
}
