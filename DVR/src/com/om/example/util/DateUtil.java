package com.om.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static DateUtil INSTANCE = new DateUtil();

	private DateUtil() {
	}

	public static DateUtil instance() {
		return INSTANCE;
	}

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"M/d/yyyy");

	public Date formatDate(String date) throws ParseException {
		return dateFormat.parse(date);
	}

	public String formatDate(Date startDateTime) {
		return dateFormat.format(startDateTime);
	}

	static SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");

	public String formatTime(Date startDateTime) {
		return timeFormat.format(startDateTime);
	}

	public String addDaysTo(int days, String nextStartDate)
			throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		Date startingDate = dateFormat.parse(nextStartDate);
		calendar.setTime(startingDate);
		calendar.add(Calendar.DATE, days);
		return dateFormat.format(calendar.getTime());
	}

	public static final SimpleDateFormat dateTimeMergedFormat = new SimpleDateFormat(
			"M/d/yyyy|h:mm");

	public Date buildDate(String date, String startTime) throws ParseException {
		String dateTime = String.format("%s|%s", date, startTime);
		return dateTimeMergedFormat.parse(dateTime);
	}

	public boolean isSameDate(Date startDateTime, Date date) {
		return formatDate(startDateTime).equals(formatDate(date));
	}

	public Date createEndDate(Date startDateTime, int durationInMinutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDateTime);
		calendar.add(Calendar.MINUTE, durationInMinutes);
		return calendar.getTime();
	}

	public boolean segmentsConflict(Date lhs, int lhsDurationMins, Date rhs,
			int rhsDurationMins) {
		Date lhsEnd = createEndDate(lhs, lhsDurationMins);
		Date rhsEnd = createEndDate(rhs, rhsDurationMins);

		return isOnToJustBefore(lhs, rhs, rhsEnd)
				|| isStrictlyWithin(lhsEnd, rhs, rhsEnd)
				|| isOnToJustBefore(rhs, lhs, lhsEnd)
				|| isStrictlyWithin(rhsEnd, lhs, lhsEnd);
	}

	private boolean isOnToJustBefore(Date date, Date rangeBegin, Date rangeEnd) {
		return date.equals(rangeBegin)
				|| (date.after(rangeBegin) && date.before(rangeEnd));
	}

	private boolean isStrictlyWithin(Date date, Date rangeBegin, Date rangeEnd) {
		return date.after(rangeBegin) && date.before(rangeEnd);
	}
}
