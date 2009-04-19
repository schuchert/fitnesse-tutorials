package com.om.example.dvr.fixtures;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.om.example.dvr.domain.Program;
import com.om.example.dvr.domain.TimeSlot;
import com.om.example.util.DateUtil;

public class ProgramGuideProgramCellsParser {

   private static final int MINS_PER_CHAR = 15;
   private final Date buildDate;
   private int channel;

   public ProgramGuideProgramCellsParser(Date buildDate) {
      this.buildDate = buildDate;
   }

   public List<Program> parse(String programsInCells) {
      List<Program> result = new LinkedList<Program>();

      String programs = buildSingleStringFromCells(programsInCells);

      int index = 0;
      while ((index = startIndexOfNextProgram(index, programs)) < programs.length()) {
         String nextProgramName = calculateNextProgramName(index, programs);
         index += addNextProgram(nextProgramName, index, result);
      }

      return result;
   }

   public void setChannel(int channel) {
      this.channel = channel;
   }

   private String buildSingleStringFromCells(String programsInCells) {
      String programs = programsInCells.replaceAll("\\|\\|", "|    |");
      programs = programs.replaceAll("\\|", "");
      return programs;
   }

   private Program buildProgram(int index, int duration, String name) {
      Date nextDate = calculateNextDate(buildDate, index * MINS_PER_CHAR);
      TimeSlot timeSlot = new TimeSlot(channel, nextDate, duration);
      return new Program(name, "E1", timeSlot);
   }

   private int addNextProgram(String programName, int index, List<Program> result) {
      if (programName != null) {
         int length = programName.length();
         result.add(buildProgram(index, length * MINS_PER_CHAR, programName));
         return length;
      }
      return 0;
   }

   private int startIndexOfNextProgram(int index, String string) {
      while (index < string.length() && !Character.isLetter(string.charAt(index)))
         ++index;
      return index;
   }

   private String calculateNextProgramName(int index, String string) {
      int lastIndex = index;

      while (lastIndex < string.length() && charactesSameAt(string, index, lastIndex))
         ++lastIndex;

      return string.substring(index, lastIndex);
   }

   private boolean charactesSameAt(String string, int startIndex, int endIndex) {
      return string.charAt(endIndex) == string.charAt(startIndex);
   }

   private Date calculateNextDate(Date fromDate, int lengthInMinutes) {
      return DateUtil.instance().addMinutesTo(fromDate, lengthInMinutes);
   }
}
