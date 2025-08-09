package net.razorvine.pickle.objects;

import java.io.Serializable;
import java.util.Calendar;

public class Time implements Serializable {
   private static final long serialVersionUID = 2325820650757621315L;
   public final int hours;
   public final int minutes;
   public final int seconds;
   public final int microseconds;

   public Time(int h, int m, int s, int microsecs) {
      this.hours = h;
      this.minutes = m;
      this.seconds = s;
      this.microseconds = microsecs;
   }

   public Time(long milliseconds) {
      Calendar cal = Calendar.getInstance();
      cal.setTimeInMillis(milliseconds);
      this.hours = cal.get(11);
      this.minutes = cal.get(12);
      this.seconds = cal.get(13);
      this.microseconds = cal.get(14) * 1000;
   }

   public String toString() {
      return String.format("Time: %d hours, %d minutes, %d seconds, %d microseconds", this.hours, this.minutes, this.seconds, this.microseconds);
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + this.hours;
      result = 31 * result + this.microseconds;
      result = 31 * result + this.minutes;
      result = 31 * result + this.seconds;
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof Time)) {
         return false;
      } else {
         Time other = (Time)obj;
         return this.hours == other.hours && this.minutes == other.minutes && this.seconds == other.seconds && this.microseconds == other.microseconds;
      }
   }
}
