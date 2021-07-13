package calculator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DueDateCalculator {

	public static final int WORKHOURS_PER_DAY = 8;
	public static final int WORKHOURS_PER_WEEK = 40;
	public static final int START_OF_WORKDAY = 9;
	public static final int END_OF_WORKDAY = 17;
	public static final int REST_HOURS_INSIDE_WEEK = 16;
	public static final int REST_HOURS_BETWEEN_WEEKS = 16 + 48;

	public static LocalDateTime CalculateDueDate(LocalDateTime errorTicketIn, long turnaround) {
		LocalDateTime errorTicketSolved = errorTicketIn;

		long numberOfWeeks = turnaround / WORKHOURS_PER_WEEK;
		long numberOfDays = turnaround % WORKHOURS_PER_WEEK / WORKHOURS_PER_DAY;
		long numberOfHours = turnaround % WORKHOURS_PER_DAY;

		if (numberOfWeeks > 0) {
			errorTicketSolved = errorTicketIn.plusWeeks(numberOfWeeks);
		}
		if (numberOfDays > 0) {
			for (int i = 0; i < numberOfDays; i++) {
				errorTicketSolved = errorTicketSolved.plusDays(1);
				if (errorTicketSolved.getDayOfWeek() == DayOfWeek.SATURDAY) {
					errorTicketSolved = errorTicketSolved.plusDays(2);
				}
			}
		}
		if (numberOfHours > 0) {
			errorTicketSolved = errorTicketSolved.plusHours(numberOfHours);
		}
		if (errorTicketSolved.getHour() >= END_OF_WORKDAY || errorTicketSolved.getHour() < START_OF_WORKDAY) {
			if (errorTicketSolved.getHour() >= END_OF_WORKDAY
					&& errorTicketSolved.getDayOfWeek().getValue() >= DayOfWeek.MONDAY.getValue()
					&& errorTicketSolved.getDayOfWeek().getValue() <= DayOfWeek.THURSDAY.getValue()
					|| errorTicketSolved.getHour() < START_OF_WORKDAY
							&& errorTicketSolved.getDayOfWeek() == DayOfWeek.FRIDAY) {
				errorTicketSolved = errorTicketSolved.plusHours(REST_HOURS_INSIDE_WEEK);
			} else {
				errorTicketSolved = errorTicketSolved.plusHours(REST_HOURS_BETWEEN_WEEKS);
			}
		}

		return errorTicketSolved;
	}

}
