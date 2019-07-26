package com.dapp.outng.common.utils;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class DateUtils {

	public static String getAge(String birthday) {
		String[] birthDate = birthday.split(",");
		LocalDate localBirthDate = new LocalDate(Integer.valueOf(birthDate[0]), Integer.valueOf(birthDate[1]),
				Integer.valueOf(birthDate[2]));
		LocalDate now = new LocalDate();
		Period period = new Period(localBirthDate, now, PeriodType.yearMonthDay());
		return String.valueOf(period.getYears());
	}

}
