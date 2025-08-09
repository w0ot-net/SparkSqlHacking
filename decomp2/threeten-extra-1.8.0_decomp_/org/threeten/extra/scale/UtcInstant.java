package org.threeten.extra.scale;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.JulianFields;
import java.time.temporal.TemporalAccessor;
import org.joda.convert.FromString;
import org.joda.convert.ToString;

public final class UtcInstant implements Comparable, Serializable {
   private static final long serialVersionUID = 2600294095511836210L;
   private final long mjDay;
   private final long nanoOfDay;
   private transient String toString;

   public static UtcInstant ofModifiedJulianDay(long mjDay, long nanoOfDay) {
      UtcRules.system().validateModifiedJulianDay(mjDay, nanoOfDay);
      return new UtcInstant(mjDay, nanoOfDay);
   }

   public static UtcInstant of(Instant instant) {
      return UtcRules.system().convertToUtc(instant);
   }

   public static UtcInstant of(TaiInstant instant) {
      return UtcRules.system().convertToUtc(instant);
   }

   @FromString
   public static UtcInstant parse(CharSequence text) {
      TemporalAccessor parsed = DateTimeFormatter.ISO_INSTANT.parse(text);
      long epochSecond = parsed.getLong(ChronoField.INSTANT_SECONDS);
      long nanoOfSecond = parsed.getLong(ChronoField.NANO_OF_SECOND);
      boolean leap = (Boolean)parsed.query(DateTimeFormatter.parsedLeapSecond());
      long epochDay = Math.floorDiv(epochSecond, 86400L);
      long mjd = epochDay + 40587L;
      long nanoOfDay = Math.floorMod(epochSecond, 86400L) * 1000000000L + nanoOfSecond;
      if (leap) {
         nanoOfDay += 1000000000L;
      }

      return ofModifiedJulianDay(mjd, nanoOfDay);
   }

   private UtcInstant(long mjDay, long nanoOfDay) {
      this.mjDay = mjDay;
      this.nanoOfDay = nanoOfDay;
   }

   public long getModifiedJulianDay() {
      return this.mjDay;
   }

   public UtcInstant withModifiedJulianDay(long mjDay) {
      return ofModifiedJulianDay(mjDay, this.nanoOfDay);
   }

   public long getNanoOfDay() {
      return this.nanoOfDay;
   }

   public UtcInstant withNanoOfDay(long nanoOfDay) {
      return ofModifiedJulianDay(this.mjDay, nanoOfDay);
   }

   public boolean isLeapSecond() {
      return this.nanoOfDay >= 86400000000000L;
   }

   public UtcInstant plus(Duration duration) {
      return of(this.toTaiInstant().plus(duration));
   }

   public UtcInstant minus(Duration duration) {
      return of(this.toTaiInstant().minus(duration));
   }

   public Duration durationUntil(UtcInstant utcInstant) {
      TaiInstant thisTAI = this.toTaiInstant();
      TaiInstant otherTAI = utcInstant.toTaiInstant();
      return thisTAI.durationUntil(otherTAI);
   }

   public Instant toInstant() {
      return UtcRules.system().convertToInstant(this);
   }

   public TaiInstant toTaiInstant() {
      return UtcRules.system().convertToTai(this);
   }

   public int compareTo(UtcInstant otherInstant) {
      int cmp = Long.compare(this.mjDay, otherInstant.mjDay);
      return cmp != 0 ? cmp : Long.compare(this.nanoOfDay, otherInstant.nanoOfDay);
   }

   public boolean isAfter(UtcInstant otherInstant) {
      return this.compareTo(otherInstant) > 0;
   }

   public boolean isBefore(UtcInstant otherInstant) {
      return this.compareTo(otherInstant) < 0;
   }

   public boolean equals(Object otherInstant) {
      if (this == otherInstant) {
         return true;
      } else if (!(otherInstant instanceof UtcInstant)) {
         return false;
      } else {
         UtcInstant other = (UtcInstant)otherInstant;
         return this.mjDay == other.mjDay && this.nanoOfDay == other.nanoOfDay;
      }
   }

   public int hashCode() {
      return (int)(this.mjDay ^ this.mjDay >>> 32) + 51 * (int)(this.nanoOfDay ^ this.nanoOfDay >>> 32);
   }

   @ToString
   public String toString() {
      String currentStringValue = this.toString;
      if (currentStringValue == null) {
         currentStringValue = this.buildToString();
         this.toString = currentStringValue;
      }

      return currentStringValue;
   }

   private String buildToString() {
      LocalDate date = LocalDate.MAX.with(JulianFields.MODIFIED_JULIAN_DAY, this.mjDay);
      StringBuilder buf = new StringBuilder(30);
      int sod = (int)(this.nanoOfDay / 1000000000L);
      int hourValue = sod / 3600;
      int minuteValue = sod / 60 % 60;
      int secondValue = sod % 60;
      int nanoValue = (int)(this.nanoOfDay % 1000000000L);
      if (hourValue == 24) {
         hourValue = 23;
         minuteValue = 59;
         secondValue = 60;
      }

      buf.append(date).append('T').append(hourValue < 10 ? "0" : "").append(hourValue).append(minuteValue < 10 ? ":0" : ":").append(minuteValue).append(secondValue < 10 ? ":0" : ":").append(secondValue);
      if (nanoValue > 0) {
         buf.append('.');
         if (nanoValue % 1000000 == 0) {
            buf.append(Integer.toString(nanoValue / 1000000 + 1000).substring(1));
         } else if (nanoValue % 1000 == 0) {
            buf.append(Integer.toString(nanoValue / 1000 + 1000000).substring(1));
         } else {
            buf.append(Integer.toString(nanoValue + 1000000000).substring(1));
         }
      }

      buf.append('Z');
      return buf.toString();
   }
}
