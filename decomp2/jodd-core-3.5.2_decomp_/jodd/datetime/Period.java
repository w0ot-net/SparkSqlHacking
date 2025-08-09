package jodd.datetime;

public class Period {
   protected final long days;
   protected final int hours;
   protected final int minutes;
   protected final int seconds;
   protected final int milliseconds;

   public Period(JDateTime jdt1, JDateTime jdt2) {
      if (jdt2.isBefore(jdt1)) {
         JDateTime temp = jdt1;
         jdt1 = jdt2;
         jdt2 = temp;
      }

      long julian2 = (long)jdt2.getJulianDayNumber();
      long julian1 = (long)jdt1.getJulianDayNumber();
      long days = julian2 - julian1;
      int milliseconds = jdt2.getMillisecond() - jdt1.getMillisecond();
      int seconds = jdt2.getSecond() - jdt1.getSecond();
      int minutes = jdt2.getMinute() - jdt1.getMinute();
      int hours = jdt2.getHour() - jdt1.getHour();
      if (milliseconds < 0) {
         --seconds;
         milliseconds += 1000;
      }

      if (seconds < 0) {
         --minutes;
         seconds += 60;
      }

      if (minutes < 0) {
         --hours;
         minutes += 60;
      }

      if (hours < 0) {
         --days;
         hours += 24;
      }

      this.days = days;
      this.hours = hours;
      this.minutes = minutes;
      this.seconds = seconds;
      this.milliseconds = milliseconds;
   }

   public long getDays() {
      return this.days;
   }

   public int getHours() {
      return this.hours;
   }

   public int getMinutes() {
      return this.minutes;
   }

   public int getSeconds() {
      return this.seconds;
   }

   public int getMilliseconds() {
      return this.milliseconds;
   }
}
