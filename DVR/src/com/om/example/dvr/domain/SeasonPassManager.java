package com.om.example.dvr.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SeasonPassManager {
	private final Schedule schedule;
	private List<Program> toDoList = new LinkedList<Program>();
	private int numberOfRecorders = 1;

	public SeasonPassManager(Schedule schedule) {
		this.schedule = schedule;
	}

	public void setNumberOfRecorders(int number) {
		this.numberOfRecorders = number;
	}

	public int sizeOfToDoList() {
		return toDoList.size();
	}

	public Program createNewSeasonPass(String programName, int channel) {
		List<Program> programsFound = schedule.findProgramsNamedOn(programName,
				channel);

		for (Program current : programsFound)
			if (!alreadyInToDoList(current)
					&& !conflictsWithExistingSchedule(current))
				toDoList.add(current);

		if (programsFound.size() > 0)
			return programsFound.get(0);
		return null;
	}

	private boolean conflictsWithExistingSchedule(Program program) {
		int remainingConflicts = numberOfRecorders - 1;

		for (Program current : toDoList)
			if (current.hasTimeConflictWith(program)) {
				--remainingConflicts;
				if (remainingConflicts < 0)
					return true;
			}

		return remainingConflicts < 0;
	}

	private boolean alreadyInToDoList(Program candidate) {
		for (Program current : toDoList)
			if (current.sameEpisodeAs(candidate))
				return true;

		return false;
	}

	public Iterable<?> toDoListIterator() {
		return toDoList;
	}

	public List<Program> toDoListContentsFor(String programId) {
		List<Program> result = new LinkedList<Program>();

		for (Program current : toDoList)
			if (programId.length() == 0 || current.getId().equals(programId))
				result.add(current);

		return result;
	}

	public List<Program> toDoListContentsOn(Date date) {
		List<Program> result = new LinkedList<Program>();

		for (Program current : toDoList)
			if (current.isOn(date))
				result.add(current);

		return result;
	}

	public void clearToDoList() {
		toDoList.clear();
	}
}