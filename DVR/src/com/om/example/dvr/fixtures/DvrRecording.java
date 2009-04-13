package com.om.example.dvr.fixtures;

import java.util.Iterator;
import java.util.List;

import com.om.example.dvr.domain.Program;

public class DvrRecording {
	public void givenDvrCanRecord(int number) {
		CreateSeasonPassFor.getSeasonPassManager().setNumberOfRecorders(number);
	}

	public void whenICreateSeasonPasses(String listOfSeasonPasses) {
		String[] individualSeasonPasses = listOfSeasonPasses.split(",");

		for (String programNameChannel : individualSeasonPasses)
			addOneSeasonPass(programNameChannel);
	}

	private void addOneSeasonPass(String programNameChannel) {
		String[] parts = programNameChannel.split(":");

		String programName = parts[0];
		int channel = Integer.parseInt(parts[1]);

		new CreateSeasonPassFor(programName, channel);
	}

	public boolean thenTheToDoListShouldContain(String listOfEpisodes) {
		List<Program> toDoList = CreateSeasonPassFor.getSeasonPassManager()
				.toDoListContentsFor("");

		String[] episodesSets = listOfEpisodes.split(",");

		for (String episodeSet : episodesSets)
			if (!removeAllFrom(episodeSet, toDoList))
				return false;

		return toDoList.size() == 0;
	}

	private boolean removeAllFrom(String episodeSet, List<Program> toDoList) {
		String programName = extractProgramNameFrom(episodeSet);
		String baseEpisodeName = extractBaseNameFrom(episodeSet);
		int lowerRange = extractLowerRangeFrom(episodeSet);
		int upperRange = extractUpperRangeFrom(episodeSet);

		for (int episodeNumber = lowerRange; episodeNumber <= upperRange; ++episodeNumber)
			if (!remove(programName, baseEpisodeName, episodeNumber, toDoList))
				return false;

		return true;
	}

	private String extractProgramNameFrom(String episodeSet) {
		return episodeSet.split(":")[0];
	}

	private String extractBaseNameFrom(String episodeSet) {
		String[] values = episodeSet.split(":");

		String result = "";
		if (values.length > 1)
			result = values[1];

		return result;
	}

	private int extractLowerRangeFrom(String episodeSet) {
		String[] values = episodeSet.split(":");
		if (values.length > 2) {
			String lowRange = episodeSet.split(":")[2].split("-")[0];
			return Integer.parseInt(lowRange);
		}
		return 0;
	}

	private int extractUpperRangeFrom(String episodeSet) {
		String[] values = episodeSet.split(":");
		if (values.length > 2) {
			String highRange = episodeSet.split(":")[2].split("-")[1];
			return Integer.parseInt(highRange);
		}
		return -1;
	}

	private boolean remove(String programName, String baseEpisodeName,
			int episodeNumber, List<Program> toDoList) {
		String episodeName = String.format("%s%d", baseEpisodeName,
				episodeNumber);

		for (Iterator<Program> iter = toDoList.iterator(); iter.hasNext();) {
			if (matches(iter.next(), programName, episodeName)) {
				iter.remove();
				return true;
			}
		}
		return false;
	}

	private boolean matches(Program current, String programName,
			String episodeName) {
		return programName.equals(current.programName)
				&& episodeName.equals(current.episodeName);
	}
}
