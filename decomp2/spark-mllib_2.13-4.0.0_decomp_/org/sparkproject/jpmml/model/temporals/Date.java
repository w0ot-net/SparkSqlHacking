package org.sparkproject.jpmml.model.temporals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import org.sparkproject.dmg.pmml.DataType;

public class Date extends Instant {
   private LocalDate date;

   private Date() {
      this.date = null;
   }

   public Date(int year, int month, int dayOfMonth) {
      this(LocalDate.of(year, month, dayOfMonth));
   }

   public Date(LocalDate date) {
      this.date = null;
      this.setDate(date);
   }

   public DataType getDataType() {
      return DataType.DATE;
   }

   public String toSimpleValue() {
      LocalDate date = this.getDate();
      return date.toString();
   }

   public String format(String pattern) {
      LocalDate date = this.getDate();
      return String.format(pattern, date);
   }

   public DaysSinceDate toDaysSinceYear(int year) {
      LocalDate date = this.getDate();
      return new DaysSinceDate(new Date(year, 1, 1), date);
   }

   public int compareTo(Date that) {
      return this.getDate().compareTo(that.getDate());
   }

   public int hashCode() {
      return this.getDate().hashCode();
   }

   public boolean equals(Object object) {
      if (object instanceof Date) {
         Date that = (Date)object;
         return Objects.equals(this.getDate(), that.getDate());
      } else {
         return false;
      }
   }

   public LocalDate getDate() {
      return this.date;
   }

   private void setDate(LocalDate date) {
      this.date = (LocalDate)Objects.requireNonNull(date);
   }

   public static Date parse(String value) throws DateTimeParseException {
      return new Date(LocalDate.parse(value));
   }

   public static Date valueOf(Object value) {
      if (value instanceof LocalDate) {
         LocalDate localDate = (LocalDate)value;
         return new Date(localDate);
      } else if (value instanceof LocalDateTime) {
         LocalDateTime localDateTime = (LocalDateTime)value;
         return new Date(localDateTime.toLocalDate());
      } else {
         throw new IllegalArgumentException();
      }
   }
}
