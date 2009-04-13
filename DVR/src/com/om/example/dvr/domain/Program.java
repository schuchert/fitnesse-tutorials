package com.om.example.dvr.domain;

import java.util.Date;

import com.om.example.util.DateUtil;

public class Program {

	public final String programName;
	public final String episodeName;
	public final TimeSlot timeSlot;

	public Program(String programName, String episodeName, TimeSlot timeSlot) {
		this.programName = programName;
		this.episodeName = episodeName;
		this.timeSlot = timeSlot;
	}

	public String getId() {
		return String.format("(%s:%d)", programName, timeSlot.channel);
	}

	public String getProgramName() {
		return programName;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public boolean sameEpisodeAs(Program program) {
		return timeSlot.channel == program.timeSlot.channel
				&& programName.equals(program.programName)
				&& episodeName.equals(program.episodeName);
	}

	public boolean isOn(Date date) {
		return DateUtil.instance().isSameDate(timeSlot.startDateTime, date);
	}

	public boolean hasTimeConflictWith(Program other) {
		return timeSlot.conflictsInTimeWith(other.timeSlot);
	}
}