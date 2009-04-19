package com.om.example.dvr.fixtures;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.om.example.dvr.domain.Program;

public class ProgramGuideRowParserTest {
   @Test
   public void verifyChannelSetCorrectly() throws ParseException {
      ProgramGuideRowParser parser = new ProgramGuideRowParser("4/5/2008", "9:00");
      List<Program> programs = parser.parse(buildList("123", "aaaa"));

      assertEquals(1, programs.size());
      Program expected = ProgramBuilderUtil.buildProgram("4/5/2008", "9:00", "aaaa", 123,
            60);
      assertEquals(expected, programs.get(0));
   }

   private List<String> buildList(String... strings) {
      List<String> result = new LinkedList<String>();

      for (String current : strings)
         result.add(current);

      return result;
   }
}
