package org.threeten.extra;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import org.joda.convert.FromString;
import org.joda.convert.ToString;

public final class Interval implements Serializable {
   public static final Interval ALL;
   private static final long serialVersionUID = 8375285238652L;
   private final Instant start;
   private final Instant end;

   public static Interval of(Instant startInclusive, Instant endExclusive) {
      Objects.requireNonNull(startInclusive, "startInclusive");
      Objects.requireNonNull(endExclusive, "endExclusive");
      if (endExclusive.isBefore(startInclusive)) {
         throw new DateTimeException("End instant must be equal or after start instant");
      } else {
         return new Interval(startInclusive, endExclusive);
      }
   }

   public static Interval of(Instant startInclusive, Duration duration) {
      Objects.requireNonNull(startInclusive, "startInclusive");
      Objects.requireNonNull(duration, "duration");
      if (duration.isNegative()) {
         throw new DateTimeException("Duration must not be negative");
      } else {
         return new Interval(startInclusive, startInclusive.plus(duration));
      }
   }

   public static Interval of(Duration duration, Instant endExclusive) {
      Objects.requireNonNull(duration, "duration");
      Objects.requireNonNull(endExclusive, "endExclusive");
      if (duration.isNegative()) {
         throw new DateTimeException("Duration must not be negative");
      } else {
         return new Interval(endExclusive.minus(duration), endExclusive);
      }
   }

   public static Interval startingAt(Instant startInclusive) {
      Objects.requireNonNull(startInclusive, "startInclusive");
      return ALL.withStart(startInclusive);
   }

   public static Interval endingAt(Instant endExclusive) {
      Objects.requireNonNull(endExclusive, "endExclusive");
      return ALL.withEnd(endExclusive);
   }

   @FromString
   public static Interval parse(CharSequence text) {
      Objects.requireNonNull(text, "text");

      for(int i = 0; i < text.length(); ++i) {
         if (text.charAt(i) == '/') {
            return parseSplit(text.subSequence(0, i), text.subSequence(i + 1, text.length()));
         }
      }

      throw new DateTimeParseException("Interval cannot be parsed, no forward slash found", text, 0);
   }

   private static Interval parseSplit(CharSequence startStr, CharSequence endStr) {
      char firstChar = startStr.charAt(0);
      if (firstChar != 'P' && firstChar != 'p') {
         OffsetDateTime start;
         try {
            start = OffsetDateTime.parse(startStr);
         } catch (DateTimeParseException var9) {
            return parseStartExtended(startStr, endStr);
         }

         if (endStr.length() > 0) {
            char c = endStr.charAt(0);
            if (c == 'P' || c == 'p') {
               PeriodDuration amount = PeriodDuration.parse(endStr);
               return of(start.toInstant(), start.plus(amount).toInstant());
            }
         }

         return parseEndDateTime(start.toInstant(), start.getOffset(), endStr);
      } else {
         PeriodDuration amount = PeriodDuration.parse(startStr);

         try {
            OffsetDateTime end = OffsetDateTime.parse(endStr);
            return of(end.minus(amount).toInstant(), end.toInstant());
         } catch (DateTimeParseException var10) {
            Instant end = Instant.parse(endStr);
            long move = end.isBefore(Instant.EPOCH) ? 86400000L : -86400000L;
            Instant start = end.plusSeconds(move).atOffset(ZoneOffset.UTC).minus(amount).toInstant().minusSeconds(move);
            return of(start, end);
         }
      }
   }

   private static Interval parseStartExtended(CharSequence startStr, CharSequence endStr) {
      Instant start = Instant.parse(startStr);
      if (endStr.length() > 0) {
         char c = endStr.charAt(0);
         if (c == 'P' || c == 'p') {
            PeriodDuration amount = PeriodDuration.parse(endStr);
            long move = start.isBefore(Instant.EPOCH) ? 86400000L : -86400000L;
            Instant end = start.plusSeconds(move).atOffset(ZoneOffset.UTC).plus(amount).toInstant().minusSeconds(move);
            return of(start, end);
         }
      }

      return parseEndDateTime(start, ZoneOffset.UTC, endStr);
   }

   private static Interval parseEndDateTime(Instant start, ZoneOffset offset, CharSequence endStr) {
      try {
         TemporalAccessor temporal = DateTimeFormatter.ISO_DATE_TIME.parseBest(endStr, OffsetDateTime::from, LocalDateTime::from);
         if (temporal instanceof OffsetDateTime) {
            OffsetDateTime odt = (OffsetDateTime)temporal;
            return of(start, odt.toInstant());
         } else {
            LocalDateTime ldt = (LocalDateTime)temporal;
            return of(start, ldt.toInstant(offset));
         }
      } catch (DateTimeParseException var5) {
         Instant end = Instant.parse(endStr);
         return of(start, end);
      }
   }

   private Interval(Instant startInclusive, Instant endExclusive) {
      this.start = startInclusive;
      this.end = endExclusive;
   }

   public Instant getStart() {
      return this.start;
   }

   public Instant getEnd() {
      return this.end;
   }

   public boolean isEmpty() {
      return this.start.equals(this.end);
   }

   public boolean isUnboundedStart() {
      return this.start.equals(Instant.MIN);
   }

   public boolean isUnboundedEnd() {
      return this.end.equals(Instant.MAX);
   }

   public Interval withStart(Instant start) {
      return of(start, this.end);
   }

   public Interval withEnd(Instant end) {
      return of(this.start, end);
   }

   public boolean encloses(Interval other) {
      Objects.requireNonNull(other, "other");
      return this.start.compareTo(other.start) <= 0 && other.end.compareTo(this.end) <= 0;
   }

   public boolean abuts(Interval other) {
      Objects.requireNonNull(other, "other");
      return this.end.equals(other.start) ^ this.start.equals(other.end);
   }

   public boolean isConnected(Interval other) {
      Objects.requireNonNull(other, "other");
      return this.equals(other) || this.start.compareTo(other.end) <= 0 && other.start.compareTo(this.end) <= 0;
   }

   public boolean overlaps(Interval other) {
      Objects.requireNonNull(other, "other");
      return other.equals(this) || this.start.compareTo(other.end) < 0 && other.start.compareTo(this.end) < 0;
   }

   public Interval intersection(Interval other) {
      Objects.requireNonNull(other, "other");
      if (!this.isConnected(other)) {
         throw new DateTimeException("Intervals do not connect: " + this + " and " + other);
      } else {
         int cmpStart = this.start.compareTo(other.start);
         int cmpEnd = this.end.compareTo(other.end);
         if (cmpStart >= 0 && cmpEnd <= 0) {
            return this;
         } else if (cmpStart <= 0 && cmpEnd >= 0) {
            return other;
         } else {
            Instant newStart = cmpStart >= 0 ? this.start : other.start;
            Instant newEnd = cmpEnd <= 0 ? this.end : other.end;
            return of(newStart, newEnd);
         }
      }
   }

   public Interval union(Interval other) {
      Objects.requireNonNull(other, "other");
      if (!this.isConnected(other)) {
         throw new DateTimeException("Intervals do not connect: " + this + " and " + other);
      } else {
         int cmpStart = this.start.compareTo(other.start);
         int cmpEnd = this.end.compareTo(other.end);
         if (cmpStart >= 0 && cmpEnd <= 0) {
            return other;
         } else if (cmpStart <= 0 && cmpEnd >= 0) {
            return this;
         } else {
            Instant newStart = cmpStart >= 0 ? other.start : this.start;
            Instant newEnd = cmpEnd <= 0 ? other.end : this.end;
            return of(newStart, newEnd);
         }
      }
   }

   public Interval span(Interval other) {
      Objects.requireNonNull(other, "other");
      int cmpStart = this.start.compareTo(other.start);
      int cmpEnd = this.end.compareTo(other.end);
      Instant newStart = cmpStart >= 0 ? other.start : this.start;
      Instant newEnd = cmpEnd <= 0 ? other.end : this.end;
      return of(newStart, newEnd);
   }

   public boolean isAfter(Interval interval) {
      return this.start.compareTo(interval.end) >= 0 && !interval.equals(this);
   }

   public boolean isBefore(Interval interval) {
      return this.end.compareTo(interval.start) <= 0 && !interval.equals(this);
   }

   public boolean startsBefore(Instant instant) {
      Objects.requireNonNull(instant, "instant");
      return this.start.compareTo(instant) < 0;
   }

   public boolean startsAtOrBefore(Instant instant) {
      Objects.requireNonNull(instant, "instant");
      return this.start.compareTo(instant) <= 0;
   }

   public boolean startsAfter(Instant instant) {
      Objects.requireNonNull(instant, "instant");
      return this.start.compareTo(instant) > 0;
   }

   public boolean startsAtOrAfter(Instant instant) {
      Objects.requireNonNull(instant, "instant");
      return this.start.compareTo(instant) >= 0;
   }

   public boolean endsBefore(Instant instant) {
      Objects.requireNonNull(instant, "instant");
      return this.end.compareTo(instant) < 0 && !this.isUnboundedEnd();
   }

   public boolean endsAtOrBefore(Instant instant) {
      Objects.requireNonNull(instant, "instant");
      return this.end.compareTo(instant) <= 0 && !this.isUnboundedEnd();
   }

   public boolean endsAfter(Instant instant) {
      Objects.requireNonNull(instant, "instant");
      return this.end.compareTo(instant) > 0 || this.isUnboundedEnd();
   }

   public boolean endsAtOrAfter(Instant instant) {
      Objects.requireNonNull(instant, "instant");
      return this.end.compareTo(instant) >= 0 || this.isUnboundedEnd();
   }

   public boolean contains(Instant instant) {
      return this.startsAtOrBefore(instant) && this.endsAfter(instant);
   }

   public boolean isAfter(Instant instant) {
      return this.startsAfter(instant);
   }

   public boolean isBefore(Instant instant) {
      return this.endsAtOrBefore(instant) && this.startsBefore(instant);
   }

   public Duration toDuration() {
      return Duration.between(this.start, this.end);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof Interval)) {
         return false;
      } else {
         Interval other = (Interval)obj;
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
      ALL = new Interval(Instant.MIN, Instant.MAX);
   }
}
