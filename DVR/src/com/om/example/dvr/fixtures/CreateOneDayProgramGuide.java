package com.om.example.dvr.fixtures;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import com.om.example.dvr.domain.Program;

public class CreateOneDayProgramGuide {
   private ProgramGuideRowParser parser;

   public CreateOneDayProgramGuide(String startTime, String date) throws ParseException {
      parser = new ProgramGuideRowParser(date, startTime);
   }

   public List<?> doTable(List<List<String>> table) {
      table.remove(0);

      for (List<String> row : table)
         handleOneRow(row);

      return Collections.emptyList();
   }

   private void handleOneRow(List<String> row) {
      List<Program> programs = parser.parse(row);

      for (Program program : programs)
         AddProgramsToSchedule.getSchedule().addProgram(program);
   }
}
