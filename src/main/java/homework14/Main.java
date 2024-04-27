package homework14;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

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

    public static String minutesToMidnight(LocalDateTime timestamp) {
        LocalDateTime midnight = timestamp
                .withDayOfYear(timestamp.getDayOfYear() + 1)
                .withHour(0)
                .withMinute(0);
        Duration duration = Duration.between(timestamp, midnight);
        long minutes = duration.toMinutes();
        return minutes + " minute" + (minutes == 1 ? "" : "s");
    }

    public static String ageInDays(int year, int month, int day) {
        LocalDate birthday = LocalDate.of(year, month, day);
        long days = ChronoUnit.DAYS.between(birthday, LocalDate.now());
        return "You are " + days + " days old";
    }
}
