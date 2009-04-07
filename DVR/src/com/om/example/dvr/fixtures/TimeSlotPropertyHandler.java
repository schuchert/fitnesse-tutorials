package com.om.example.dvr.fixtures;

import java.util.Date;

import com.om.example.dvr.domain.TimeSlot;
import com.om.example.util.DateUtil;
import com.om.query.domain.ObjectDescription;
import com.om.query.handler.PropertyHandler;
import com.om.reflection.PropertyGetter;

public class TimeSlotPropertyHandler extends PropertyHandler {
   @Override
   public void handle(PropertyGetter propertyGetter, Object targetObject,
         ObjectDescription objectDescription) {
      TimeSlot timeSlot = propertyGetter.getValue(targetObject, TimeSlot.class);

      Date startDateTime = timeSlot.startDateTime;

      objectDescription.addPropertyDescription("date", DateUtil.instance().formatDate(
            startDateTime));
      objectDescription.addPropertyDescription("startTime", DateUtil.instance()
            .formatTime(startDateTime));
   }
}