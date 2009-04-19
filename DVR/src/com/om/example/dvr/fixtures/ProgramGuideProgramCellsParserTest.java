package com.om.example.dvr.fixtures;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.om.example.dvr.domain.Program;
import com.om.example.util.DateUtil;

public class ProgramGuideProgramCellsParserTest {
   private ProgramGuideProgramCellsParser parser;

   @Before
   public void init() throws ParseException {
      parser = new ProgramGuideProgramCellsParser(DateUtil.instance().buildDate(
            "4/8/2008", "1:00"));
      parser.setChannel(204);
   }

   @Test
   public void emptyRowGeneratesNoPrograms() {
      List<Program> result = parser.parse("");
      assertEquals(0, result.size());
   }

   @Test
   public void oneOneHourProgram() throws ParseException {
      List<Program> result = parser.parse("|aaaa|");

      assertEquals(1, result.size());

      Program expected = buildProgram("4/8/2008", "1:00", "aaaa", 204, 60);
      assertEquals(expected, result.get(0));
   }

   @Test
   public void oneThirtyMinuteProgram() throws ParseException {
      List<Program> result = parser.parse("|aa|");

      assertEquals(1, result.size());

      Program expected = buildProgram("4/8/2008", "1:00", "aa", 204, 30);
      assertEquals(expected, result.get(0));
   }

   @Test
   public void twoThirtyMinuteProgramsInSameCell() throws ParseException {
      List<Program> result = parser.parse("|aabb|");

      assertEquals(2, result.size());

      Program expected0 = buildProgram("4/8/2008", "1:00", "aa", 204, 30);
      assertEquals(expected0, result.get(0));

      Program expected1 = buildProgram("4/8/2008", "1:30", "bb", 204, 30);
      assertEquals(expected1, result.get(1));
   }

   @Test
   public void underscoredIgnored() throws ParseException {
      List<Program> result = parser.parse("|__aa|");

      assertEquals(1, result.size());

      Program expected = buildProgram("4/8/2008", "1:30", "aa", 204, 30);
      assertEquals(expected, result.get(0));
   }

   @Test
   public void emptyCellsHandeledCorrectly() throws ParseException {
      List<Program> result = parser.parse("||__aa|");

      assertEquals(1, result.size());

      Program expected = buildProgram("4/8/2008", "2:30", "aa", 204, 30);
      assertEquals(expected, result.get(0));
   }

   @Test
   public void verifyComplexParse() throws ParseException {
      List<Program> result = parser.parse("||__aa|BBcc||FFgg|__  |");
      assertEquals(5, result.size());

      Program expectedLastProgram = buildProgram("4/8/2008", "5:30", "gg", 204, 30);
      assertEquals(expectedLastProgram, result.get(4));
   }

   private Program buildProgram(String date, String time, String name, int channel,
         int duration) throws ParseException {
      return ProgramBuilderUtil.buildProgram(date, time, name, channel, duration);
   }
}
