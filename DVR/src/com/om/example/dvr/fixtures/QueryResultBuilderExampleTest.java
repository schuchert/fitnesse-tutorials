package com.om.example.dvr.fixtures;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.om.example.dvr.domain.Program;
import com.om.example.dvr.domain.Schedule;
import com.om.example.dvr.domain.SeasonPassManager;
import com.om.query.QueryResultBuilder;
import com.om.query.domain.QueryResult;

public class QueryResultBuilderExampleTest {
   private SeasonPassManager seasonPassManager;
   private Schedule schedule;

   private Date createDate(int year, int month, int day, int hour, int min) {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.set(Calendar.YEAR, year);
      calendar.set(Calendar.MONTH, month);
      calendar.set(Calendar.DAY_OF_MONTH, day);
      calendar.set(Calendar.HOUR, hour);
      calendar.set(Calendar.MINUTE, min);

      return calendar.getTime();
   }

   @Before
   public void init() {
      schedule = new Schedule();
      schedule.addProgram("p1", "e1", 7, createDate(2008, 4, 5, 7, 0), 60);
      schedule.addProgram("p2", "e2", 7, createDate(2008, 4, 5, 8, 0), 60);
      seasonPassManager = new SeasonPassManager(schedule);
   }

   @Test
   public void queryResultBuilderCanTranslateToDoListCorrectly() {
      seasonPassManager.createNewSeasonPass("p1", 7);

      QueryResultBuilder builder = new QueryResultBuilder(Program.class);
      builder.register("timeSlot", new TimeSlotPropertyHandler());
      QueryResult result = builder.build(seasonPassManager.toDoListIterator());
      List<Object> renderedObjects = result.render();
      assertEquals(1, renderedObjects.size());
   }
}
