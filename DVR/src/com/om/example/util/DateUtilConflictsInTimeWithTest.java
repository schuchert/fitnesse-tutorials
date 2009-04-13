package com.om.example.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DateUtilConflictsInTimeWithTest {
	private final String message;
	private Date lhsDate;
	private int lhsDuration;
	private Date rhsDate;
	private int rhsDuration;
	private final boolean expected;

	@Parameters
	public static Collection<?> parameters() {
		ArrayList<Object[]> v = new ArrayList<Object[]>();
		v.add(new Object[] { "rhs inside lhs", "1/2/2008", "20:00", 60,
				"1/2/2008", "20:30", 1, true });
		v.add(new Object[] { "unrelated times", "1/2/2008", "20:00", 60,
				"1/2/2008", "10:00", 60, false });
		v.add(new Object[] { "same times", "1/2/2008", "20:00", 60, "1/2/2008",
				"20:00", 60, true });
		v.add(new Object[] { "lhs offset by 30 mins on rhs", "1/2/2008",
				"20:00", 60, "1/2/2008", "20:30", 60, true });
		v.add(new Object[] { "different dates", "1/4/2008", "20:00", 60,
				"1/2/2008", "20:30", 60, false });
		v.add(new Object[] { "lhs just ends before rhs", "1/2/2008", "20:00",
				60, "1/2/2008", "21:00", 60, false });
		return v;
	}

	public DateUtilConflictsInTimeWithTest(String message, String lhsDate,
			String lhsTime, int lhsDuration, String rhsDate, String rhsTime,
			int rhsDuration, boolean expected) throws ParseException {
		this.message = message;
		this.lhsDuration = lhsDuration;
		this.rhsDuration = rhsDuration;
		this.expected = expected;

		this.lhsDate = DateUtil.instance().buildDate(lhsDate, lhsTime);
		this.rhsDate = DateUtil.instance().buildDate(rhsDate, rhsTime);
	}

	@Test
	public void lhsComparedToRhs() {
		assertEquals(message, expected, DateUtil.instance().segmentsConflict(
				lhsDate, lhsDuration, rhsDate, rhsDuration));
	}

	@Test
	public void rhsComparedToLhs() {
		assertEquals(message, expected, DateUtil.instance().segmentsConflict(
				rhsDate, rhsDuration, lhsDate, lhsDuration));
	}
}
