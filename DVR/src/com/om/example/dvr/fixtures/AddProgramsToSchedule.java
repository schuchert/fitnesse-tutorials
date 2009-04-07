package com.om.example.dvr.fixtures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.om.example.dvr.domain.ConflictingProgramException;
import com.om.example.dvr.domain.Program;
import com.om.example.dvr.domain.Schedule;

public class AddProgramsToSchedule {
   static SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy|h:mm");
   private static Schedule schedule = new Schedule();
   private int channel;
   private String date;
   private String startTime;
   private int minutes;
   private String programName;
   private String episodeName;
   private String lastId;

   public static Schedule getSchedule() {
      return schedule;
   }

   public void setName(String name) {
      this.programName = name;
   }

   public void setEpisode(String name) {
      this.episodeName = name;
   }

   public void setChannel(int channel) {
      this.channel = channel;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public void setStartTime(String startTime) {
      this.startTime = startTime;
   }

   public void setMinutes(int minutes) {
      this.minutes = minutes;
   }

   public boolean created() {
      try {
         Program p = schedule.addProgram(programName, episodeName, channel,
               buildStartDateTime(), minutes);
         lastId = p.getId();
      } catch (ConflictingProgramException e) {
         lastId = "n/a";
         return false;
      }
      return true;
   }

   public String lastId() {
      return lastId;
   }

   private Date buildStartDateTime() {
      try {
         String dateTime = String.format("%s|%s", date, startTime);
         return dateFormat.parse(dateTime);
      } catch (ParseException e) {
         throw new RuntimeException("Unable to prase date/time", e);
      }
   }
}
