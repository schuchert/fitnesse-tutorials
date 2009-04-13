package com.om.example.dvr.fixtures;

import java.util.List;

import com.om.example.dvr.domain.Program;
import com.om.query.QueryResultBuilder;
import com.om.query.domain.QueryResult;

public class EpisodesInToDoList {
	private final String programId;

	public EpisodesInToDoList() {
		programId = "";
	}

	public EpisodesInToDoList(String programId) {
		this.programId = programId;
	}

	public List<Object> query() {
		List<Program> programs = CreateSeasonPassFor.getSeasonPassManager()
				.toDoListContentsFor(programId);
		QueryResultBuilder builder = new QueryResultBuilder(Program.class);
		builder.register("timeSlot", new TimeSlotPropertyHandler());
		QueryResult result = builder.build(programs);
		return result.render();
	}
}
