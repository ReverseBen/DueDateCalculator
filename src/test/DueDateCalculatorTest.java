package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import calculator.DueDateCalculator;

class DueDateCalculatorTest {

	@Test
	void testCalculateDueDateInsideWorkingDay() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 8, 10, 0);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 1);
		assertEquals(errorTime.plusHours(1), dueTime);
	}

	@Test
	void testCalculateDueDateWhenDueTimeEndsJustBeforeEndOfDay() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 6, 10, 59);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 6);
		assertEquals(errorTime.plusHours(6), dueTime);
	}

	@Test
	void testCalculateDueDateAtStartOfDay() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 6, 11, 0);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 6);
		assertEquals(LocalDateTime.of(2021, 7, 7, 9, 0), dueTime);
	}

	@Test
	void testCalculateDueDateOneDayPassed() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 6, 11, 00);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 8);
		assertEquals(errorTime.plusDays(1), dueTime);
	}

	@Test
	void testCalculateDueDateTwoDaysPassed() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 5, 10, 00);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 16);
		assertEquals(errorTime.plusDays(2), dueTime);
	}

	@Test
	void testCalculateDueDateThreeDaysPassed() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 5, 10, 00);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 24);
		assertEquals(errorTime.plusDays(3), dueTime);
	}

	@Test
	void testCalculateDueDateFourDaysPassed() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 5, 10, 00);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 32);
		assertEquals(errorTime.plusDays(4), dueTime);
	}

	@Test
	void testCalculateDueDateOneWeekPassed() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 6, 15, 00);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 40);
		assertEquals(errorTime.plusWeeks(1), dueTime);
	}

	@Test
	void testCalculateDueDateWeekendPassed() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 9, 16, 30);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 2);
		assertEquals(LocalDateTime.of(2021, 7, 12, 10, 30), dueTime);
	}

	@Test
	void testCalculateDueDateAfterWeekendOneDayPassed() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 9, 17, 00);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 8);
		assertEquals(LocalDateTime.of(2021, 7, 13, 9, 00), dueTime);
	}

	@Test
	void testCalculateDueDateAfterWeekendTwoDayPassed() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 9, 10, 00);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 16);
		assertEquals(LocalDateTime.of(2021, 7, 13, 10, 00), dueTime);
	}

	@Test
	void testCalculateDueDateLong() {
		LocalDateTime errorTime = LocalDateTime.of(2021, 7, 8, 12, 00);
		LocalDateTime dueTime = DueDateCalculator.CalculateDueDate(errorTime, 21 * 8 + 6);
		assertEquals(LocalDateTime.of(2021, 8, 9, 10, 00), dueTime);
	}

}
