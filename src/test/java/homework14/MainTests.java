package homework14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MainTests {
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

}
