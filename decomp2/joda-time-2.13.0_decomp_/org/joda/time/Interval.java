package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseInterval;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Interval extends BaseInterval implements ReadableInterval, Serializable {
   private static final long serialVersionUID = 4922451897541386752L;

   public static Interval parse(String var0) {
      return new Interval(var0);
   }

   public static Interval parseWithOffset(String var0) {
      int var1 = var0.indexOf(47);
      if (var1 < 0) {
         throw new IllegalArgumentException("Format requires a '/' separator: " + var0);
      } else {
         String var2 = var0.substring(0, var1);
         if (var2.length() <= 0) {
            throw new IllegalArgumentException("Format invalid: " + var0);
         } else {
            String var3 = var0.substring(var1 + 1);
            if (var3.length() <= 0) {
               throw new IllegalArgumentException("Format invalid: " + var0);
            } else {
               DateTimeFormatter var4 = ISODateTimeFormat.dateTimeParser().withOffsetParsed();
               PeriodFormatter var5 = ISOPeriodFormat.standard();
               DateTime var6 = null;
               Period var7 = null;
               char var8 = var2.charAt(0);
               if (var8 != 'P' && var8 != 'p') {
                  var6 = var4.parseDateTime(var2);
               } else {
                  var7 = var5.withParseType(PeriodType.standard()).parsePeriod(var2);
               }

               var8 = var3.charAt(0);
               if (var8 != 'P' && var8 != 'p') {
                  DateTime var9 = var4.parseDateTime(var3);
                  return var7 != null ? new Interval(var7, var9) : new Interval(var6, var9);
               } else if (var7 != null) {
                  throw new IllegalArgumentException("Interval composed of two durations: " + var0);
               } else {
                  var7 = var5.withParseType(PeriodType.standard()).parsePeriod(var3);
                  return new Interval(var6, var7);
               }
            }
         }
      }
   }

   public Interval(long var1, long var3) {
      super(var1, var3, (Chronology)null);
   }

   public Interval(long var1, long var3, DateTimeZone var5) {
      super(var1, var3, ISOChronology.getInstance(var5));
   }

   public Interval(long var1, long var3, Chronology var5) {
      super(var1, var3, var5);
   }

   public Interval(ReadableInstant var1, ReadableInstant var2) {
      super(var1, var2);
   }

   public Interval(ReadableInstant var1, ReadableDuration var2) {
      super(var1, var2);
   }

   public Interval(ReadableDuration var1, ReadableInstant var2) {
      super(var1, var2);
   }

   public Interval(ReadableInstant var1, ReadablePeriod var2) {
      super(var1, var2);
   }

   public Interval(ReadablePeriod var1, ReadableInstant var2) {
      super(var1, var2);
   }

   public Interval(Object var1) {
      super((Object)var1, (Chronology)null);
   }

   public Interval(Object var1, Chronology var2) {
      super(var1, var2);
   }

   public Interval toInterval() {
      return this;
   }

   public Interval overlap(ReadableInterval var1) {
      var1 = DateTimeUtils.getReadableInterval(var1);
      if (!this.overlaps(var1)) {
         return null;
      } else {
         long var2 = Math.max(this.getStartMillis(), var1.getStartMillis());
         long var4 = Math.min(this.getEndMillis(), var1.getEndMillis());
         return new Interval(var2, var4, this.getChronology());
      }
   }

   public Interval gap(ReadableInterval var1) {
      var1 = DateTimeUtils.getReadableInterval(var1);
      long var2 = var1.getStartMillis();
      long var4 = var1.getEndMillis();
      long var6 = this.getStartMillis();
      long var8 = this.getEndMillis();
      if (var6 > var4) {
         return new Interval(var4, var6, this.getChronology());
      } else {
         return var2 > var8 ? new Interval(var8, var2, this.getChronology()) : null;
      }
   }

   public boolean abuts(ReadableInterval var1) {
      if (var1 == null) {
         long var2 = DateTimeUtils.currentTimeMillis();
         return this.getStartMillis() == var2 || this.getEndMillis() == var2;
      } else {
         return var1.getEndMillis() == this.getStartMillis() || this.getEndMillis() == var1.getStartMillis();
      }
   }

   public Interval withChronology(Chronology var1) {
      return this.getChronology() == var1 ? this : new Interval(this.getStartMillis(), this.getEndMillis(), var1);
   }

   public Interval withStartMillis(long var1) {
      return var1 == this.getStartMillis() ? this : new Interval(var1, this.getEndMillis(), this.getChronology());
   }

   public Interval withStart(ReadableInstant var1) {
      long var2 = DateTimeUtils.getInstantMillis(var1);
      return this.withStartMillis(var2);
   }

   public Interval withEndMillis(long var1) {
      return var1 == this.getEndMillis() ? this : new Interval(this.getStartMillis(), var1, this.getChronology());
   }

   public Interval withEnd(ReadableInstant var1) {
      long var2 = DateTimeUtils.getInstantMillis(var1);
      return this.withEndMillis(var2);
   }

   public Interval withDurationAfterStart(ReadableDuration var1) {
      long var2 = DateTimeUtils.getDurationMillis(var1);
      if (var2 == this.toDurationMillis()) {
         return this;
      } else {
         Chronology var4 = this.getChronology();
         long var5 = this.getStartMillis();
         long var7 = var4.add(var5, var2, 1);
         return new Interval(var5, var7, var4);
      }
   }

   public Interval withDurationBeforeEnd(ReadableDuration var1) {
      long var2 = DateTimeUtils.getDurationMillis(var1);
      if (var2 == this.toDurationMillis()) {
         return this;
      } else {
         Chronology var4 = this.getChronology();
         long var5 = this.getEndMillis();
         long var7 = var4.add(var5, var2, -1);
         return new Interval(var7, var5, var4);
      }
   }

   public Interval withPeriodAfterStart(ReadablePeriod var1) {
      if (var1 == null) {
         return this.withDurationAfterStart((ReadableDuration)null);
      } else {
         Chronology var2 = this.getChronology();
         long var3 = this.getStartMillis();
         long var5 = var2.add(var1, var3, 1);
         return new Interval(var3, var5, var2);
      }
   }

   public Interval withPeriodBeforeEnd(ReadablePeriod var1) {
      if (var1 == null) {
         return this.withDurationBeforeEnd((ReadableDuration)null);
      } else {
         Chronology var2 = this.getChronology();
         long var3 = this.getEndMillis();
         long var5 = var2.add(var1, var3, -1);
         return new Interval(var5, var3, var2);
      }
   }
}
