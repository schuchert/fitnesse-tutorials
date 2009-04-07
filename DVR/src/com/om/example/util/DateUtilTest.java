package com.om.example.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateUtilTest {
   @Test
   public void CanCreateDateTimeFromStrings() throws ParseException {
      Date buildDate = DateUtil.instance().buildDate("1/3/2007", "23:45");
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(buildDate);
      assertEquals(2007, calendar.get(Calendar.YEAR));
      assertEquals(3, calendar.get(Calendar.DATE));
      assertEquals(Calendar.JANUARY, calendar.get(Calendar.MONTH));
      assertEquals(23, calendar.get(Calendar.HOUR_OF_DAY));
      assertEquals(45, calendar.get(Calendar.MINUTE));
      assertEquals(0, calendar.get(Calendar.SECOND));
   }
}
