package com.om.example.dvr.domain;

import java.util.Date;

public class TimeSlot {

   public final int channel;
   public final Date startDateTime;
   public final int durationInMinutes;

   public TimeSlot(int channel, Date startDateTime, int durationInMinutes) {
      this.channel = channel;
      this.startDateTime = startDateTime;
      this.durationInMinutes = durationInMinutes;
   }

   public boolean conflictsWith(TimeSlot other) {
      if (channel == other.channel && startDateTime.equals(other.startDateTime))
         return true;
      return false;
   }
}