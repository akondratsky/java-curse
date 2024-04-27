package homework14;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class Main {
    public static boolean isPeriodLate(LocalDate last, LocalDate today, int cycleLength) {
        Period duration = Period.between(last, today);
        return duration.getDays() > cycleLength;
    }

    public static int unluckyDays(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        int unluckyDays = 0;
        for (int i = 1; i < 13; i++) {
            if (date.withMonth(i).withDayOfMonth(13).getDayOfWeek() == DayOfWeek.FRIDAY) {
                unluckyDays++;
            }
        }
        return unluckyDays;
    }
}
