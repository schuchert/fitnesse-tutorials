package com.om.example.dvr.fixtures;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.om.example.dvr.domain.Program;
import com.om.example.util.DateUtil;

public class ProgramGuideRowParser {
   ProgramGuideProgramCellsParser parser;

   public ProgramGuideRowParser(String date, String startTime) throws ParseException {
      Date startDateTime = DateUtil.instance().buildDate(date, startTime);
      parser = new ProgramGuideProgramCellsParser(startDateTime);
   }

   public List<Program> parse(List<String> cells) {
      int channel = Integer.parseInt(cells.get(0));
      cells.remove(0);
      parser.setChannel(channel);
      return parser.parse(rowAsString(cells));
   }

   private String rowAsString(List<String> row) {
      StringBuffer buffer = new StringBuffer();

      buffer.append("|");
      for (String current : row)
         buffer.append(String.format("%s|", current));

      return buffer.toString();
   }
}
