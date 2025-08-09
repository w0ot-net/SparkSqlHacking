package org.sparkproject.jpmml.model.temporals;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import org.sparkproject.dmg.pmml.DataType;

public class Time extends Instant {
   private LocalTime time;

   private Time() {
      this.time = null;
   }

   public Time(int hour, int minute, int second) {
      this(LocalTime.of(hour, minute, second));
   }

   public Time(LocalTime time) {
      this.time = null;
      this.setTime(time);
   }

   public DataType getDataType() {
      return DataType.TIME;
   }

   public String toSimpleValue() {
      LocalTime time = this.getTime();
      return time.toString();
   }

   public String format(String pattern) {
      LocalTime time = this.getTime();
      return String.format(pattern, time);
   }

   public SecondsSinceMidnight toSecondsSinceMidnight() {
      LocalTime time = this.getTime();
      return new SecondsSinceMidnight((long)time.toSecondOfDay());
   }

   public int compareTo(Time that) {
      return this.getTime().compareTo(that.getTime());
   }

   public int hashCode() {
      return this.getTime().hashCode();
   }

   public boolean equals(Object object) {
      if (object instanceof Time) {
         Time that = (Time)object;
         return Objects.equals(this.getTime(), that.getTime());
      } else {
         return false;
      }
   }

   public LocalTime getTime() {
      return this.time;
   }

   private void setTime(LocalTime time) {
      this.time = (LocalTime)Objects.requireNonNull(time);
   }

   public static Time parse(String value) throws DateTimeParseException {
      return new Time(LocalTime.parse(value));
   }

   public static Time valueOf(Object value) {
      if (value instanceof LocalTime) {
         LocalTime localTime = (LocalTime)value;
         return new Time(localTime);
      } else if (value instanceof LocalDateTime) {
         LocalDateTime localDateTime = (LocalDateTime)value;
         return new Time(localDateTime.toLocalTime());
      } else {
         throw new IllegalArgumentException();
      }
   }
}
