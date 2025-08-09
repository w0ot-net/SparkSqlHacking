package org.threeten.extra;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjuster;
import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.joda.convert.FromString;
import org.joda.convert.ToString;

public final class LocalDateRange implements Serializable {
   private static final LocalDate MINP1;
   private static final LocalDate MAXM1;
   public static final LocalDateRange ALL;
   private static final long serialVersionUID = 3358656715467L;
   private final LocalDate start;
   private final LocalDate end;

   public static LocalDateRange of(LocalDate startInclusive, LocalDate endExclusive) {
      Objects.requireNonNull(startInclusive, "startInclusive");
      Objects.requireNonNull(endExclusive, "endExclusive");
      return new LocalDateRange(startInclusive, endExclusive);
   }

   public static LocalDateRange ofClosed(LocalDate startInclusive, LocalDate endInclusive) {
      Objects.requireNonNull(startInclusive, "startInclusive");
      Objects.requireNonNull(endInclusive, "endInclusive");
      if (endInclusive.isBefore(startInclusive)) {
         throw new DateTimeException("Start date must be on or before end date");
      } else {
         LocalDate end = endInclusive.equals(LocalDate.MAX) ? LocalDate.MAX : endInclusive.plusDays(1L);
         return new LocalDateRange(startInclusive, end);
      }
   }

   public static LocalDateRange of(LocalDate startInclusive, Period period) {
      Objects.requireNonNull(startInclusive, "startInclusive");
      Objects.requireNonNull(period, "period");
      if (period.isNegative()) {
         throw new DateTimeException("Period must not be zero or negative");
      } else {
         return new LocalDateRange(startInclusive, startInclusive.plus(period));
      }
   }

   public static LocalDateRange ofEmpty(LocalDate date) {
      Objects.requireNonNull(date, "date");
      return new LocalDateRange(date, date);
   }

   public static LocalDateRange ofUnbounded() {
      return ALL;
   }

   public static LocalDateRange ofUnboundedStart(LocalDate endExclusive) {
      return of(LocalDate.MIN, endExclusive);
   }

   public static LocalDateRange ofUnboundedEnd(LocalDate startInclusive) {
      return of(startInclusive, LocalDate.MAX);
   }

   @FromString
   public static LocalDateRange parse(CharSequence text) {
      Objects.requireNonNull(text, "text");

      for(int i = 0; i < text.length(); ++i) {
         if (text.charAt(i) == '/') {
            char firstChar = text.charAt(0);
            if (firstChar != 'P' && firstChar != 'p') {
               LocalDate start = LocalDate.parse(text.subSequence(0, i));
               if (i + 1 < text.length()) {
                  char c = text.charAt(i + 1);
                  if (c == 'P' || c == 'p') {
                     Period duration = Period.parse(text.subSequence(i + 1, text.length()));
                     return of(start, start.plus(duration));
                  }
               }

               LocalDate end = LocalDate.parse(text.subSequence(i + 1, text.length()));
               return of(start, end);
            }

            Period duration = Period.parse(text.subSequence(0, i));
            LocalDate end = LocalDate.parse(text.subSequence(i + 1, text.length()));
            return of(end.minus(duration), end);
         }
      }

      throw new DateTimeParseException("LocalDateRange cannot be parsed, no forward slash found", text, 0);
   }

   private LocalDateRange(LocalDate startInclusive, LocalDate endExclusive) {
      if (endExclusive.isBefore(startInclusive)) {
         throw new DateTimeException("End date must be on or after start date");
      } else if (startInclusive.equals(MAXM1)) {
         throw new DateTimeException("Range must not start at LocalDate.MAX.minusDays(1)");
      } else if (endExclusive.equals(MINP1)) {
         throw new DateTimeException("Range must not end at LocalDate.MIN.plusDays(1)");
      } else if (!endExclusive.equals(LocalDate.MIN) && !startInclusive.equals(LocalDate.MAX)) {
         this.start = startInclusive;
         this.end = endExclusive;
      } else {
         throw new DateTimeException("Empty range must not be at LocalDate.MIN or LocalDate.MAX");
      }
   }

   public LocalDate getStart() {
      return this.start;
   }

   public LocalDate getEnd() {
      return this.end;
   }

   public LocalDate getEndInclusive() {
      return this.isUnboundedEnd() ? LocalDate.MAX : this.end.minusDays(1L);
   }

   public boolean isEmpty() {
      return this.start.equals(this.end);
   }

   public boolean isUnboundedStart() {
      return this.start.equals(LocalDate.MIN);
   }

   public boolean isUnboundedEnd() {
      return this.end.equals(LocalDate.MAX);
   }

   public LocalDateRange withStart(TemporalAdjuster adjuster) {
      return of(this.start.with(adjuster), this.end);
   }

   public LocalDateRange withEnd(TemporalAdjuster adjuster) {
      return of(this.start, this.end.with(adjuster));
   }

   public boolean contains(LocalDate date) {
      Objects.requireNonNull(date, "date");
      return this.start.compareTo(date) <= 0 && (date.compareTo(this.end) < 0 || this.isUnboundedEnd());
   }

   public boolean encloses(LocalDateRange other) {
      Objects.requireNonNull(other, "other");
      return this.start.compareTo(other.start) <= 0 && other.end.compareTo(this.end) <= 0;
   }

   public boolean abuts(LocalDateRange other) {
      Objects.requireNonNull(other, "other");
      return this.end.equals(other.start) ^ this.start.equals(other.end);
   }

   public boolean isConnected(LocalDateRange other) {
      Objects.requireNonNull(other, "other");
      return this.equals(other) || this.start.compareTo(other.end) <= 0 && other.start.compareTo(this.end) <= 0;
   }

   public boolean overlaps(LocalDateRange other) {
      Objects.requireNonNull(other, "other");
      return other.equals(this) || this.start.compareTo(other.end) < 0 && other.start.compareTo(this.end) < 0;
   }

   public LocalDateRange intersection(LocalDateRange other) {
      Objects.requireNonNull(other, "other");
      if (!this.isConnected(other)) {
         throw new DateTimeException("Ranges do not connect: " + this + " and " + other);
      } else {
         int cmpStart = this.start.compareTo(other.start);
         int cmpEnd = this.end.compareTo(other.end);
         if (cmpStart >= 0 && cmpEnd <= 0) {
            return this;
         } else if (cmpStart <= 0 && cmpEnd >= 0) {
            return other;
         } else {
            LocalDate newStart = cmpStart >= 0 ? this.start : other.start;
            LocalDate newEnd = cmpEnd <= 0 ? this.end : other.end;
            return of(newStart, newEnd);
         }
      }
   }

   public LocalDateRange union(LocalDateRange other) {
      Objects.requireNonNull(other, "other");
      if (!this.isConnected(other)) {
         throw new DateTimeException("Ranges do not connect: " + this + " and " + other);
      } else {
         int cmpStart = this.start.compareTo(other.start);
         int cmpEnd = this.end.compareTo(other.end);
         if (cmpStart >= 0 && cmpEnd <= 0) {
            return other;
         } else if (cmpStart <= 0 && cmpEnd >= 0) {
            return this;
         } else {
            LocalDate newStart = cmpStart >= 0 ? other.start : this.start;
            LocalDate newEnd = cmpEnd <= 0 ? other.end : this.end;
            return of(newStart, newEnd);
         }
      }
   }

   public LocalDateRange span(LocalDateRange other) {
      Objects.requireNonNull(other, "other");
      int cmpStart = this.start.compareTo(other.start);
      int cmpEnd = this.end.compareTo(other.end);
      LocalDate newStart = cmpStart >= 0 ? other.start : this.start;
      LocalDate newEnd = cmpEnd <= 0 ? other.end : this.end;
      return of(newStart, newEnd);
   }

   public Stream stream() {
      long count = this.end.toEpochDay() - this.start.toEpochDay() + (long)(this.isUnboundedEnd() ? 1 : 0);
      Spliterator<LocalDate> spliterator = new Spliterators.AbstractSpliterator(count, 17749) {
         private LocalDate current;

         {
            this.current = LocalDateRange.this.start;
         }

         public boolean tryAdvance(Consumer action) {
            if (this.current != null) {
               if (this.current.isBefore(LocalDateRange.this.end)) {
                  action.accept(this.current);
                  this.current = this.current.plusDays(1L);
                  return true;
               }

               if (this.current.equals(LocalDate.MAX)) {
                  action.accept(LocalDate.MAX);
                  this.current = null;
                  return true;
               }
            }

            return false;
         }

         public Comparator getComparator() {
            return null;
         }
      };
      return StreamSupport.stream(spliterator, false);
   }

   public boolean isAfter(LocalDate date) {
      return this.start.compareTo(date) > 0;
   }

   public boolean isBefore(LocalDate date) {
      return this.end.compareTo(date) <= 0 && this.start.compareTo(date) < 0;
   }

   public boolean isAfter(LocalDateRange other) {
      return this.start.compareTo(other.end) >= 0 && !other.equals(this);
   }

   public boolean isBefore(LocalDateRange range) {
      return this.end.compareTo(range.start) <= 0 && !range.equals(this);
   }

   public int lengthInDays() {
      if (!this.isUnboundedStart() && !this.isUnboundedEnd()) {
         long length = this.end.toEpochDay() - this.start.toEpochDay();
         return length > 2147483647L ? Integer.MAX_VALUE : (int)length;
      } else {
         return Integer.MAX_VALUE;
      }
   }

   public Period toPeriod() {
      if (!this.isUnboundedStart() && !this.isUnboundedEnd()) {
         return Period.between(this.start, this.end);
      } else {
         throw new ArithmeticException("Unbounded range cannot be converted to a Period");
      }
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof LocalDateRange)) {
         return false;
      } else {
         LocalDateRange other = (LocalDateRange)obj;
         return this.start.equals(other.start) && this.end.equals(other.end);
      }
   }

   public int hashCode() {
      return this.start.hashCode() ^ this.end.hashCode();
   }

   @ToString
   public String toString() {
      return this.start.toString() + '/' + this.end.toString();
   }

   static {
      MINP1 = LocalDate.MIN.plusDays(1L);
      MAXM1 = LocalDate.MAX.minusDays(1L);
      ALL = new LocalDateRange(LocalDate.MIN, LocalDate.MAX);
   }
}
