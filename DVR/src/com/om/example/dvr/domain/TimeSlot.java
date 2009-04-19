package com.om.example.dvr.domain;

import java.util.Date;

import com.om.example.util.DateUtil;

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

   public boolean conflictsInTimeWith(TimeSlot other) {
      return DateUtil.instance().segmentsConflict(startDateTime, durationInMinutes,
            other.startDateTime, other.durationInMinutes);
   }

   @Override
   public boolean equals(Object other) {
      if (!(other instanceof TimeSlot))
         return false;

      TimeSlot rhs = getClass().cast(other);
      return channel == rhs.channel && durationInMinutes == rhs.durationInMinutes
            && startDateTime.equals(rhs.startDateTime);
   }
}