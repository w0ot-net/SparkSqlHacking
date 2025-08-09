package org.joda.time.base;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.MutableInterval;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadableInterval;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public abstract class AbstractInterval implements ReadableInterval {
   protected AbstractInterval() {
   }

   protected void checkInterval(long var1, long var3) {
      if (var3 < var1) {
         throw new IllegalArgumentException("The end instant must be greater than the start instant");
      }
   }

   public DateTime getStart() {
      return new DateTime(this.getStartMillis(), this.getChronology());
   }

   public DateTime getEnd() {
      return new DateTime(this.getEndMillis(), this.getChronology());
   }

   public boolean contains(long var1) {
      long var3 = this.getStartMillis();
      long var5 = this.getEndMillis();
      return var1 >= var3 && var1 < var5;
   }

   public boolean containsNow() {
      return this.contains(DateTimeUtils.currentTimeMillis());
   }

   public boolean contains(ReadableInstant var1) {
      return var1 == null ? this.containsNow() : this.contains(var1.getMillis());
   }

   public boolean contains(ReadableInterval var1) {
      if (var1 == null) {
         return this.containsNow();
      } else {
         long var2 = var1.getStartMillis();
         long var4 = var1.getEndMillis();
         long var6 = this.getStartMillis();
         long var8 = this.getEndMillis();
         return var6 <= var2 && var2 < var8 && var4 <= var8;
      }
   }

   public boolean overlaps(ReadableInterval var1) {
      long var2 = this.getStartMillis();
      long var4 = this.getEndMillis();
      if (var1 == null) {
         long var10 = DateTimeUtils.currentTimeMillis();
         return var2 < var10 && var10 < var4;
      } else {
         long var6 = var1.getStartMillis();
         long var8 = var1.getEndMillis();
         return var2 < var8 && var6 < var4;
      }
   }

   public boolean isEqual(ReadableInterval var1) {
      return this.getStartMillis() == var1.getStartMillis() && this.getEndMillis() == var1.getEndMillis();
   }

   public boolean isBefore(long var1) {
      return this.getEndMillis() <= var1;
   }

   public boolean isBeforeNow() {
      return this.isBefore(DateTimeUtils.currentTimeMillis());
   }

   public boolean isBefore(ReadableInstant var1) {
      return var1 == null ? this.isBeforeNow() : this.isBefore(var1.getMillis());
   }

   public boolean isBefore(ReadableInterval var1) {
      return var1 == null ? this.isBeforeNow() : this.isBefore(var1.getStartMillis());
   }

   public boolean isAfter(long var1) {
      return this.getStartMillis() > var1;
   }

   public boolean isAfterNow() {
      return this.isAfter(DateTimeUtils.currentTimeMillis());
   }

   public boolean isAfter(ReadableInstant var1) {
      return var1 == null ? this.isAfterNow() : this.isAfter(var1.getMillis());
   }

   public boolean isAfter(ReadableInterval var1) {
      long var2;
      if (var1 == null) {
         var2 = DateTimeUtils.currentTimeMillis();
      } else {
         var2 = var1.getEndMillis();
      }

      return this.getStartMillis() >= var2;
   }

   public Interval toInterval() {
      return new Interval(this.getStartMillis(), this.getEndMillis(), this.getChronology());
   }

   public MutableInterval toMutableInterval() {
      return new MutableInterval(this.getStartMillis(), this.getEndMillis(), this.getChronology());
   }

   public long toDurationMillis() {
      return FieldUtils.safeSubtract(this.getEndMillis(), this.getStartMillis());
   }

   public Duration toDuration() {
      long var1 = this.toDurationMillis();
      return var1 == 0L ? Duration.ZERO : new Duration(var1);
   }

   public Period toPeriod() {
      return new Period(this.getStartMillis(), this.getEndMillis(), this.getChronology());
   }

   public Period toPeriod(PeriodType var1) {
      return new Period(this.getStartMillis(), this.getEndMillis(), var1, this.getChronology());
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ReadableInterval)) {
         return false;
      } else {
         ReadableInterval var2 = (ReadableInterval)var1;
         return this.getStartMillis() == var2.getStartMillis() && this.getEndMillis() == var2.getEndMillis() && FieldUtils.equals(this.getChronology(), var2.getChronology());
      }
   }

   public int hashCode() {
      long var1 = this.getStartMillis();
      long var3 = this.getEndMillis();
      int var5 = 97;
      var5 = 31 * var5 + (int)(var1 ^ var1 >>> 32);
      var5 = 31 * var5 + (int)(var3 ^ var3 >>> 32);
      var5 = 31 * var5 + this.getChronology().hashCode();
      return var5;
   }

   public String toString() {
      DateTimeFormatter var1 = ISODateTimeFormat.dateTime();
      var1 = var1.withChronology(this.getChronology());
      StringBuffer var2 = new StringBuffer(48);
      var1.printTo(var2, this.getStartMillis());
      var2.append('/');
      var1.printTo(var2, this.getEndMillis());
      return var2.toString();
   }
}
