package org.sparkproject.jpmml.model.temporals;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import org.sparkproject.dmg.pmml.DataType;

public class DateTime extends Instant {
   private LocalDateTime dateTime;

   private DateTime() {
      this.dateTime = null;
   }

   public DateTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
      this(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second));
   }

   public DateTime(LocalDateTime dateTime) {
      this.dateTime = null;
      this.setDateTime(dateTime);
   }

   public DataType getDataType() {
      return DataType.DATE_TIME;
   }

   public String toSimpleValue() {
      LocalDateTime dateTime = this.getDateTime();
      return dateTime.toString();
   }

   public String format(String pattern) {
      LocalDateTime dateTime = this.getDateTime();
      return String.format(pattern, dateTime);
   }

   public Date toDate() {
      LocalDateTime dateTime = this.getDateTime();
      return new Date(dateTime.toLocalDate());
   }

   public Time toTime() {
      LocalDateTime dateTime = this.getDateTime();
      return new Time(dateTime.toLocalTime());
   }

   public SecondsSinceDate toSecondsSinceYear(int year) {
      LocalDateTime dateTime = this.getDateTime();
      return new SecondsSinceDate(new Date(year, 1, 1), dateTime);
   }

   public int compareTo(DateTime that) {
      return this.getDateTime().compareTo(that.getDateTime());
   }

   public int hashCode() {
      return this.getDateTime().hashCode();
   }

   public boolean equals(Object object) {
      if (object instanceof DateTime) {
         DateTime that = (DateTime)object;
         return Objects.equals(this.getDateTime(), that.getDateTime());
      } else {
         return false;
      }
   }

   public LocalDateTime getDateTime() {
      return this.dateTime;
   }

   private void setDateTime(LocalDateTime dateTime) {
      this.dateTime = (LocalDateTime)Objects.requireNonNull(dateTime);
   }

   public static DateTime parse(String value) throws DateTimeParseException {
      return new DateTime(LocalDateTime.parse(value));
   }

   public static DateTime valueOf(Object value) {
      if (value instanceof LocalDateTime) {
         LocalDateTime localDateTime = (LocalDateTime)value;
         return new DateTime(localDateTime);
      } else {
         throw new IllegalArgumentException();
      }
   }
}
