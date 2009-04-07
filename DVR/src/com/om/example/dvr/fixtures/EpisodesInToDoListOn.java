package com.om.example.dvr.fixtures;

import java.text.ParseException;
import java.util.List;

import com.om.example.dvr.domain.Program;
import com.om.example.util.DateUtil;
import com.om.query.QueryResultBuilder;
import com.om.query.domain.QueryResult;

public class EpisodesInToDoListOn {
   private final String date;

   public EpisodesInToDoListOn(String date) {
      this.date = date;
   }

   public List<Object> query() throws ParseException {
      List<Program> programs = CreateSeasonPassFor.getSeasonPassManager()
            .toDoListContentsOn(DateUtil.instance().formatDate(date));
      QueryResultBuilder builder = new QueryResultBuilder(Program.class);
      builder.register("timeSlot", new TimeSlotPropertyHandler());
      QueryResult result = builder.build(programs);
      return result.render();
   }
}
