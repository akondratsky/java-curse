package homework14;

import org.joda.time.DateTimeUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MainTests {
    @AfterEach
    public void setUp() {
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void numberOfDayesPassedIsGreatherThanCycleLength() {
        LocalDate last = LocalDate.of(2007, 1, 1);
        LocalDate today = LocalDate.of(2007, 1, 30);
        int cycleLength = 28;
        Assertions.assertTrue(Main.isPeriodLate(last, today, cycleLength));
    }

    @Test
    public void numberOfDayesPassedIsLessThanCycleLength() {
        LocalDate last = LocalDate.of(2007, 1, 21);
        LocalDate today = LocalDate.of(2007, 2, 2);
        int cycleLength = 30;
        Assertions.assertFalse(Main.isPeriodLate(last, today, cycleLength));
    }

    @Test
    public void returnsCorrectNumberOfUnluckyDaysIn2015() {
        int unluckyDays = Main.unluckyDays(2015);
        Assertions.assertEquals(3, unluckyDays);
    }

    @Test
    public void returnsCorrectNumberOfUnluckyDaysIn1986() {
        int unluckyDays = Main.unluckyDays(1986);
        Assertions.assertEquals(1, unluckyDays);
    }

    @Test
    public void minutesToMidnightReturns840MinutesFor10am() {
        LocalDateTime timestamp = LocalDateTime.of(2015, 1, 1, 10, 0);
        Assertions.assertEquals("840 minutes", Main.minutesToMidnight(timestamp));
    }

    @Test
    public void minutesToMidnightReturns1MinutesFor23h50m() {
        LocalDateTime timestamp = LocalDateTime.of(2015, 1, 1, 23, 59);
        Assertions.assertEquals("1 minute", Main.minutesToMidnight(timestamp));
    }

    @Disabled("Until I know how to mock dates")
    @Test
    public void ageInTheDaysReturnsNumberOfDaysInYears() {
        DateTimeUtils.setCurrentMillisFixed(1714239693439L); // Sat Apr 27 2024 19:41:33 GMT+0200
        Assertions.assertEquals("You are 12972 days old", Main.ageInDays(1988, 10, 21));
    }
}
