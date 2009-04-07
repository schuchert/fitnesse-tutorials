package com.om.example.dvr.fixtures;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.om.example.dvr.domain.TimeSlot;
import com.om.query.domain.ObjectDescription;
import com.om.query.handler.PropertyHandler;
import com.om.reflection.PropertyGetter;

public class TimeSlotPropertyHandler extends PropertyHandler {
   static SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
   static SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm");

   @Override
   public void handle(PropertyGetter propertyGetter, Object targetObject,
         ObjectDescription objectDescription) {
      TimeSlot timeSlot = propertyGetter.getValue(targetObject, TimeSlot.class);

      Date startDateTime = timeSlot.startDateTime;

      objectDescription.addPropertyDescription("date", dateFormat.format(startDateTime));
      objectDescription.addPropertyDescription("startTime", timeFormat
            .format(startDateTime));
   }
}
