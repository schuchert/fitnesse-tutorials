package com.om.example.dvr.fixtures;

import java.text.ParseException;
import java.util.Date;

import com.om.example.dvr.domain.Program;
import com.om.example.dvr.domain.TimeSlot;
import com.om.example.util.DateUtil;

public class ProgramBuilderUtil {
   public static Program buildProgram(String date, String time, String name, int channel,
         int duration) throws ParseException {
      Date expectedStartDateTime = DateUtil.instance().buildDate(date, time);
      return new Program(name, "E1", new TimeSlot(channel, expectedStartDateTime,
            duration));
   }
}