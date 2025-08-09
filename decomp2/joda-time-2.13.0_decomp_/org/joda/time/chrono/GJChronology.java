package org.joda.time.chrono;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.DecoratedDurationField;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class GJChronology extends AssembledChronology {
   private static final long serialVersionUID = -2545574827706931671L;
   static final Instant DEFAULT_CUTOVER = new Instant(-12219292800000L);
   private static final ConcurrentHashMap cCache = new ConcurrentHashMap();
   private JulianChronology iJulianChronology;
   private GregorianChronology iGregorianChronology;
   private Instant iCutoverInstant;
   private long iCutoverMillis;
   private long iGapDuration;

   private static long convertByYear(long var0, Chronology var2, Chronology var3) {
      return var3.getDateTimeMillis(var2.year().get(var0), var2.monthOfYear().get(var0), var2.dayOfMonth().get(var0), var2.millisOfDay().get(var0));
   }

   private static long convertByWeekyear(long var0, Chronology var2, Chronology var3) {
      long var4 = var3.weekyear().set(0L, var2.weekyear().get(var0));
      var4 = var3.weekOfWeekyear().set(var4, var2.weekOfWeekyear().get(var0));
      var4 = var3.dayOfWeek().set(var4, var2.dayOfWeek().get(var0));
      var4 = var3.millisOfDay().set(var4, var2.millisOfDay().get(var0));
      return var4;
   }

   public static GJChronology getInstanceUTC() {
      return getInstance(DateTimeZone.UTC, DEFAULT_CUTOVER, 4);
   }

   public static GJChronology getInstance() {
      return getInstance(DateTimeZone.getDefault(), DEFAULT_CUTOVER, 4);
   }

   public static GJChronology getInstance(DateTimeZone var0) {
      return getInstance(var0, DEFAULT_CUTOVER, 4);
   }

   public static GJChronology getInstance(DateTimeZone var0, ReadableInstant var1) {
      return getInstance(var0, var1, 4);
   }

   public static GJChronology getInstance(DateTimeZone var0, ReadableInstant var1, int var2) {
      var0 = DateTimeUtils.getZone(var0);
      Instant var3;
      if (var1 == null) {
         var3 = DEFAULT_CUTOVER;
      } else {
         var3 = var1.toInstant();
         LocalDate var4 = new LocalDate(var3.getMillis(), GregorianChronology.getInstance(var0));
         if (var4.getYear() <= 0) {
            throw new IllegalArgumentException("Cutover too early. Must be on or after 0001-01-01.");
         }
      }

      GJCacheKey var8 = new GJCacheKey(var0, var3, var2);
      GJChronology var5 = (GJChronology)cCache.get(var8);
      if (var5 == null) {
         if (var0 == DateTimeZone.UTC) {
            var5 = new GJChronology(JulianChronology.getInstance(var0, var2), GregorianChronology.getInstance(var0, var2), var3);
         } else {
            var5 = getInstance(DateTimeZone.UTC, var3, var2);
            var5 = new GJChronology(ZonedChronology.getInstance(var5, var0), var5.iJulianChronology, var5.iGregorianChronology, var5.iCutoverInstant);
         }

         GJChronology var6 = (GJChronology)cCache.putIfAbsent(var8, var5);
         if (var6 != null) {
            var5 = var6;
         }
      }

      return var5;
   }

   public static GJChronology getInstance(DateTimeZone var0, long var1, int var3) {
      Instant var4;
      if (var1 == DEFAULT_CUTOVER.getMillis()) {
         var4 = null;
      } else {
         var4 = new Instant(var1);
      }

      return getInstance(var0, var4, var3);
   }

   private GJChronology(JulianChronology var1, GregorianChronology var2, Instant var3) {
      super((Chronology)null, new Object[]{var1, var2, var3});
   }

   private GJChronology(Chronology var1, JulianChronology var2, GregorianChronology var3, Instant var4) {
      super(var1, new Object[]{var2, var3, var4});
   }

   private Object readResolve() {
      return getInstance(this.getZone(), this.iCutoverInstant, this.getMinimumDaysInFirstWeek());
   }

   public DateTimeZone getZone() {
      Chronology var1;
      return (var1 = this.getBase()) != null ? var1.getZone() : DateTimeZone.UTC;
   }

   public Chronology withUTC() {
      return this.withZone(DateTimeZone.UTC);
   }

   public Chronology withZone(DateTimeZone var1) {
      if (var1 == null) {
         var1 = DateTimeZone.getDefault();
      }

      return var1 == this.getZone() ? this : getInstance(var1, this.iCutoverInstant, this.getMinimumDaysInFirstWeek());
   }

   public long getDateTimeMillis(int var1, int var2, int var3, int var4) throws IllegalArgumentException {
      Chronology var5;
      if ((var5 = this.getBase()) != null) {
         return var5.getDateTimeMillis(var1, var2, var3, var4);
      } else {
         long var6 = this.iGregorianChronology.getDateTimeMillis(var1, var2, var3, var4);
         if (var6 < this.iCutoverMillis) {
            var6 = this.iJulianChronology.getDateTimeMillis(var1, var2, var3, var4);
            if (var6 >= this.iCutoverMillis) {
               throw new IllegalArgumentException("Specified date does not exist");
            }
         }

         return var6;
      }
   }

   public long getDateTimeMillis(int var1, int var2, int var3, int var4, int var5, int var6, int var7) throws IllegalArgumentException {
      Chronology var8;
      if ((var8 = this.getBase()) != null) {
         return var8.getDateTimeMillis(var1, var2, var3, var4, var5, var6, var7);
      } else {
         long var9;
         try {
            var9 = this.iGregorianChronology.getDateTimeMillis(var1, var2, var3, var4, var5, var6, var7);
         } catch (IllegalFieldValueException var12) {
            if (var2 != 2 || var3 != 29) {
               throw var12;
            }

            var9 = this.iGregorianChronology.getDateTimeMillis(var1, var2, 28, var4, var5, var6, var7);
            if (var9 >= this.iCutoverMillis) {
               throw var12;
            }
         }

         if (var9 < this.iCutoverMillis) {
            var9 = this.iJulianChronology.getDateTimeMillis(var1, var2, var3, var4, var5, var6, var7);
            if (var9 >= this.iCutoverMillis) {
               throw new IllegalArgumentException("Specified date does not exist");
            }
         }

         return var9;
      }
   }

   public Instant getGregorianCutover() {
      return this.iCutoverInstant;
   }

   public int getMinimumDaysInFirstWeek() {
      return this.iGregorianChronology.getMinimumDaysInFirstWeek();
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof GJChronology)) {
         return false;
      } else {
         GJChronology var2 = (GJChronology)var1;
         return this.iCutoverMillis == var2.iCutoverMillis && this.getMinimumDaysInFirstWeek() == var2.getMinimumDaysInFirstWeek() && this.getZone().equals(var2.getZone());
      }
   }

   public int hashCode() {
      return "GJ".hashCode() * 11 + this.getZone().hashCode() + this.getMinimumDaysInFirstWeek() + this.iCutoverInstant.hashCode();
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer(60);
      var1.append("GJChronology");
      var1.append('[');
      var1.append(this.getZone().getID());
      if (this.iCutoverMillis != DEFAULT_CUTOVER.getMillis()) {
         var1.append(",cutover=");
         DateTimeFormatter var2;
         if (this.withUTC().dayOfYear().remainder(this.iCutoverMillis) == 0L) {
            var2 = ISODateTimeFormat.date();
         } else {
            var2 = ISODateTimeFormat.dateTime();
         }

         var2.withChronology(this.withUTC()).printTo(var1, this.iCutoverMillis);
      }

      if (this.getMinimumDaysInFirstWeek() != 4) {
         var1.append(",mdfw=");
         var1.append(this.getMinimumDaysInFirstWeek());
      }

      var1.append(']');
      return var1.toString();
   }

   protected void assemble(AssembledChronology.Fields var1) {
      Object[] var2 = this.getParam();
      JulianChronology var3 = (JulianChronology)var2[0];
      GregorianChronology var4 = (GregorianChronology)var2[1];
      Instant var5 = (Instant)var2[2];
      this.iCutoverMillis = var5.getMillis();
      this.iJulianChronology = var3;
      this.iGregorianChronology = var4;
      this.iCutoverInstant = var5;
      if (this.getBase() == null) {
         if (var3.getMinimumDaysInFirstWeek() != var4.getMinimumDaysInFirstWeek()) {
            throw new IllegalArgumentException();
         } else {
            this.iGapDuration = this.iCutoverMillis - this.julianToGregorianByYear(this.iCutoverMillis);
            var1.copyFieldsFrom(var4);
            if (var4.millisOfDay().get(this.iCutoverMillis) == 0) {
               var1.millisOfSecond = new CutoverField(var3.millisOfSecond(), var1.millisOfSecond, this.iCutoverMillis);
               var1.millisOfDay = new CutoverField(var3.millisOfDay(), var1.millisOfDay, this.iCutoverMillis);
               var1.secondOfMinute = new CutoverField(var3.secondOfMinute(), var1.secondOfMinute, this.iCutoverMillis);
               var1.secondOfDay = new CutoverField(var3.secondOfDay(), var1.secondOfDay, this.iCutoverMillis);
               var1.minuteOfHour = new CutoverField(var3.minuteOfHour(), var1.minuteOfHour, this.iCutoverMillis);
               var1.minuteOfDay = new CutoverField(var3.minuteOfDay(), var1.minuteOfDay, this.iCutoverMillis);
               var1.hourOfDay = new CutoverField(var3.hourOfDay(), var1.hourOfDay, this.iCutoverMillis);
               var1.hourOfHalfday = new CutoverField(var3.hourOfHalfday(), var1.hourOfHalfday, this.iCutoverMillis);
               var1.clockhourOfDay = new CutoverField(var3.clockhourOfDay(), var1.clockhourOfDay, this.iCutoverMillis);
               var1.clockhourOfHalfday = new CutoverField(var3.clockhourOfHalfday(), var1.clockhourOfHalfday, this.iCutoverMillis);
               var1.halfdayOfDay = new CutoverField(var3.halfdayOfDay(), var1.halfdayOfDay, this.iCutoverMillis);
            }

            var1.era = new CutoverField(var3.era(), var1.era, this.iCutoverMillis);
            var1.year = new ImpreciseCutoverField(var3.year(), var1.year, this.iCutoverMillis);
            var1.years = var1.year.getDurationField();
            var1.yearOfEra = new ImpreciseCutoverField(var3.yearOfEra(), var1.yearOfEra, var1.years, this.iCutoverMillis);
            var1.centuryOfEra = new ImpreciseCutoverField(var3.centuryOfEra(), var1.centuryOfEra, this.iCutoverMillis);
            var1.centuries = var1.centuryOfEra.getDurationField();
            var1.yearOfCentury = new ImpreciseCutoverField(var3.yearOfCentury(), var1.yearOfCentury, var1.years, var1.centuries, this.iCutoverMillis);
            var1.monthOfYear = new ImpreciseCutoverField(var3.monthOfYear(), var1.monthOfYear, (DurationField)null, var1.years, this.iCutoverMillis);
            var1.months = var1.monthOfYear.getDurationField();
            var1.weekyear = new ImpreciseCutoverField(var3.weekyear(), var1.weekyear, (DurationField)null, this.iCutoverMillis, true);
            var1.weekyears = var1.weekyear.getDurationField();
            var1.weekyearOfCentury = new ImpreciseCutoverField(var3.weekyearOfCentury(), var1.weekyearOfCentury, var1.weekyears, var1.centuries, this.iCutoverMillis);
            long var6 = var4.year().roundCeiling(this.iCutoverMillis);
            var1.dayOfYear = new CutoverField(var3.dayOfYear(), var1.dayOfYear, var1.years, var6, false);
            var6 = var4.weekyear().roundCeiling(this.iCutoverMillis);
            var1.weekOfWeekyear = new CutoverField(var3.weekOfWeekyear(), var1.weekOfWeekyear, var1.weekyears, var6, true);
            CutoverField var9 = new CutoverField(var3.dayOfMonth(), var1.dayOfMonth, this.iCutoverMillis);
            var9.iRangeDurationField = var1.months;
            var1.dayOfMonth = var9;
         }
      }
   }

   long julianToGregorianByYear(long var1) {
      return convertByYear(var1, this.iJulianChronology, this.iGregorianChronology);
   }

   long gregorianToJulianByYear(long var1) {
      return convertByYear(var1, this.iGregorianChronology, this.iJulianChronology);
   }

   long julianToGregorianByWeekyear(long var1) {
      return convertByWeekyear(var1, this.iJulianChronology, this.iGregorianChronology);
   }

   long gregorianToJulianByWeekyear(long var1) {
      return convertByWeekyear(var1, this.iGregorianChronology, this.iJulianChronology);
   }

   private class CutoverField extends BaseDateTimeField {
      private static final long serialVersionUID = 3528501219481026402L;
      final DateTimeField iJulianField;
      final DateTimeField iGregorianField;
      final long iCutover;
      final boolean iConvertByWeekyear;
      protected DurationField iDurationField;
      protected DurationField iRangeDurationField;

      CutoverField(DateTimeField var2, DateTimeField var3, long var4) {
         this(var2, var3, var4, false);
      }

      CutoverField(DateTimeField var2, DateTimeField var3, long var4, boolean var6) {
         this(var2, var3, (DurationField)null, var4, var6);
      }

      CutoverField(DateTimeField var2, DateTimeField var3, DurationField var4, long var5, boolean var7) {
         super(var3.getType());
         this.iJulianField = var2;
         this.iGregorianField = var3;
         this.iCutover = var5;
         this.iConvertByWeekyear = var7;
         this.iDurationField = var3.getDurationField();
         if (var4 == null) {
            var4 = var3.getRangeDurationField();
            if (var4 == null) {
               var4 = var2.getRangeDurationField();
            }
         }

         this.iRangeDurationField = var4;
      }

      public boolean isLenient() {
         return false;
      }

      public int get(long var1) {
         return var1 >= this.iCutover ? this.iGregorianField.get(var1) : this.iJulianField.get(var1);
      }

      public String getAsText(long var1, Locale var3) {
         return var1 >= this.iCutover ? this.iGregorianField.getAsText(var1, var3) : this.iJulianField.getAsText(var1, var3);
      }

      public String getAsText(int var1, Locale var2) {
         return this.iGregorianField.getAsText(var1, var2);
      }

      public String getAsShortText(long var1, Locale var3) {
         return var1 >= this.iCutover ? this.iGregorianField.getAsShortText(var1, var3) : this.iJulianField.getAsShortText(var1, var3);
      }

      public String getAsShortText(int var1, Locale var2) {
         return this.iGregorianField.getAsShortText(var1, var2);
      }

      public long add(long var1, int var3) {
         return this.iGregorianField.add(var1, var3);
      }

      public long add(long var1, long var3) {
         return this.iGregorianField.add(var1, var3);
      }

      public int[] add(ReadablePartial var1, int var2, int[] var3, int var4) {
         if (var4 == 0) {
            return var3;
         } else if (!DateTimeUtils.isContiguous(var1)) {
            return super.add(var1, var2, var3, var4);
         } else {
            long var5 = 0L;
            int var7 = 0;

            for(int var8 = var1.size(); var7 < var8; ++var7) {
               var5 = var1.getFieldType(var7).getField(GJChronology.this).set(var5, var3[var7]);
            }

            var5 = this.add(var5, var4);
            return GJChronology.this.get(var1, var5);
         }
      }

      public int getDifference(long var1, long var3) {
         return this.iGregorianField.getDifference(var1, var3);
      }

      public long getDifferenceAsLong(long var1, long var3) {
         return this.iGregorianField.getDifferenceAsLong(var1, var3);
      }

      public long set(long var1, int var3) {
         if (var1 >= this.iCutover) {
            var1 = this.iGregorianField.set(var1, var3);
            if (var1 < this.iCutover) {
               if (var1 + GJChronology.this.iGapDuration < this.iCutover) {
                  var1 = this.gregorianToJulian(var1);
               }

               if (this.get(var1) != var3) {
                  throw new IllegalFieldValueException(this.iGregorianField.getType(), var3, (Number)null, (Number)null);
               }
            }
         } else {
            var1 = this.iJulianField.set(var1, var3);
            if (var1 >= this.iCutover) {
               if (var1 - GJChronology.this.iGapDuration >= this.iCutover) {
                  var1 = this.julianToGregorian(var1);
               }

               if (this.get(var1) != var3) {
                  throw new IllegalFieldValueException(this.iJulianField.getType(), var3, (Number)null, (Number)null);
               }
            }
         }

         return var1;
      }

      public long set(long var1, String var3, Locale var4) {
         if (var1 >= this.iCutover) {
            var1 = this.iGregorianField.set(var1, var3, var4);
            if (var1 < this.iCutover && var1 + GJChronology.this.iGapDuration < this.iCutover) {
               var1 = this.gregorianToJulian(var1);
            }
         } else {
            var1 = this.iJulianField.set(var1, var3, var4);
            if (var1 >= this.iCutover && var1 - GJChronology.this.iGapDuration >= this.iCutover) {
               var1 = this.julianToGregorian(var1);
            }
         }

         return var1;
      }

      public DurationField getDurationField() {
         return this.iDurationField;
      }

      public DurationField getRangeDurationField() {
         return this.iRangeDurationField;
      }

      public boolean isLeap(long var1) {
         return var1 >= this.iCutover ? this.iGregorianField.isLeap(var1) : this.iJulianField.isLeap(var1);
      }

      public int getLeapAmount(long var1) {
         return var1 >= this.iCutover ? this.iGregorianField.getLeapAmount(var1) : this.iJulianField.getLeapAmount(var1);
      }

      public DurationField getLeapDurationField() {
         return this.iGregorianField.getLeapDurationField();
      }

      public int getMinimumValue() {
         return this.iJulianField.getMinimumValue();
      }

      public int getMinimumValue(ReadablePartial var1) {
         return this.iJulianField.getMinimumValue(var1);
      }

      public int getMinimumValue(ReadablePartial var1, int[] var2) {
         return this.iJulianField.getMinimumValue(var1, var2);
      }

      public int getMinimumValue(long var1) {
         if (var1 < this.iCutover) {
            return this.iJulianField.getMinimumValue(var1);
         } else {
            int var3 = this.iGregorianField.getMinimumValue(var1);
            var1 = this.iGregorianField.set(var1, var3);
            if (var1 < this.iCutover) {
               var3 = this.iGregorianField.get(this.iCutover);
            }

            return var3;
         }
      }

      public int getMaximumValue() {
         return this.iGregorianField.getMaximumValue();
      }

      public int getMaximumValue(long var1) {
         if (var1 >= this.iCutover) {
            return this.iGregorianField.getMaximumValue(var1);
         } else {
            int var3 = this.iJulianField.getMaximumValue(var1);
            var1 = this.iJulianField.set(var1, var3);
            if (var1 >= this.iCutover) {
               var3 = this.iJulianField.get(this.iJulianField.add(this.iCutover, -1));
            }

            return var3;
         }
      }

      public int getMaximumValue(ReadablePartial var1) {
         long var2 = GJChronology.getInstanceUTC().set(var1, 0L);
         return this.getMaximumValue(var2);
      }

      public int getMaximumValue(ReadablePartial var1, int[] var2) {
         GJChronology var3 = GJChronology.getInstanceUTC();
         long var4 = 0L;
         int var6 = 0;

         for(int var7 = var1.size(); var6 < var7; ++var6) {
            DateTimeField var8 = var1.getFieldType(var6).getField(var3);
            if (var2[var6] <= var8.getMaximumValue(var4)) {
               var4 = var8.set(var4, var2[var6]);
            }
         }

         return this.getMaximumValue(var4);
      }

      public long roundFloor(long var1) {
         if (var1 >= this.iCutover) {
            var1 = this.iGregorianField.roundFloor(var1);
            if (var1 < this.iCutover && var1 + GJChronology.this.iGapDuration < this.iCutover) {
               var1 = this.gregorianToJulian(var1);
            }
         } else {
            var1 = this.iJulianField.roundFloor(var1);
         }

         return var1;
      }

      public long roundCeiling(long var1) {
         if (var1 >= this.iCutover) {
            var1 = this.iGregorianField.roundCeiling(var1);
         } else {
            var1 = this.iJulianField.roundCeiling(var1);
            if (var1 >= this.iCutover && var1 - GJChronology.this.iGapDuration >= this.iCutover) {
               var1 = this.julianToGregorian(var1);
            }
         }

         return var1;
      }

      public int getMaximumTextLength(Locale var1) {
         return Math.max(this.iJulianField.getMaximumTextLength(var1), this.iGregorianField.getMaximumTextLength(var1));
      }

      public int getMaximumShortTextLength(Locale var1) {
         return Math.max(this.iJulianField.getMaximumShortTextLength(var1), this.iGregorianField.getMaximumShortTextLength(var1));
      }

      protected long julianToGregorian(long var1) {
         return this.iConvertByWeekyear ? GJChronology.this.julianToGregorianByWeekyear(var1) : GJChronology.this.julianToGregorianByYear(var1);
      }

      protected long gregorianToJulian(long var1) {
         return this.iConvertByWeekyear ? GJChronology.this.gregorianToJulianByWeekyear(var1) : GJChronology.this.gregorianToJulianByYear(var1);
      }
   }

   private final class ImpreciseCutoverField extends CutoverField {
      private static final long serialVersionUID = 3410248757173576441L;

      ImpreciseCutoverField(DateTimeField var2, DateTimeField var3, long var4) {
         this(var2, var3, (DurationField)null, var4, false);
      }

      ImpreciseCutoverField(DateTimeField var2, DateTimeField var3, DurationField var4, long var5) {
         this(var2, var3, var4, var5, false);
      }

      ImpreciseCutoverField(DateTimeField var2, DateTimeField var3, DurationField var4, DurationField var5, long var6) {
         this(var2, var3, var4, var6, false);
         this.iRangeDurationField = var5;
      }

      ImpreciseCutoverField(DateTimeField var2, DateTimeField var3, DurationField var4, long var5, boolean var7) {
         super(var2, var3, var5, var7);
         if (var4 == null) {
            var4 = new LinkedDurationField(this.iDurationField, this);
         }

         this.iDurationField = (DurationField)var4;
      }

      public long add(long var1, int var3) {
         if (var1 >= this.iCutover) {
            var1 = this.iGregorianField.add(var1, var3);
            if (var1 < this.iCutover && var1 + GJChronology.this.iGapDuration < this.iCutover) {
               if (this.iConvertByWeekyear) {
                  int var4 = GJChronology.this.iGregorianChronology.weekyear().get(var1);
                  if (var4 <= 0) {
                     var1 = GJChronology.this.iGregorianChronology.weekyear().add(var1, -1);
                  }
               } else {
                  int var6 = GJChronology.this.iGregorianChronology.year().get(var1);
                  if (var6 <= 0) {
                     var1 = GJChronology.this.iGregorianChronology.year().add(var1, -1);
                  }
               }

               var1 = this.gregorianToJulian(var1);
            }
         } else {
            var1 = this.iJulianField.add(var1, var3);
            if (var1 >= this.iCutover && var1 - GJChronology.this.iGapDuration >= this.iCutover) {
               var1 = this.julianToGregorian(var1);
            }
         }

         return var1;
      }

      public long add(long var1, long var3) {
         if (var1 >= this.iCutover) {
            var1 = this.iGregorianField.add(var1, var3);
            if (var1 < this.iCutover && var1 + GJChronology.this.iGapDuration < this.iCutover) {
               if (this.iConvertByWeekyear) {
                  int var5 = GJChronology.this.iGregorianChronology.weekyear().get(var1);
                  if (var5 <= 0) {
                     var1 = GJChronology.this.iGregorianChronology.weekyear().add(var1, -1);
                  }
               } else {
                  int var7 = GJChronology.this.iGregorianChronology.year().get(var1);
                  if (var7 <= 0) {
                     var1 = GJChronology.this.iGregorianChronology.year().add(var1, -1);
                  }
               }

               var1 = this.gregorianToJulian(var1);
            }
         } else {
            var1 = this.iJulianField.add(var1, var3);
            if (var1 >= this.iCutover && var1 - GJChronology.this.iGapDuration >= this.iCutover) {
               var1 = this.julianToGregorian(var1);
            }
         }

         return var1;
      }

      public int getDifference(long var1, long var3) {
         if (var1 >= this.iCutover) {
            if (var3 >= this.iCutover) {
               return this.iGregorianField.getDifference(var1, var3);
            } else {
               var1 = this.gregorianToJulian(var1);
               return this.iJulianField.getDifference(var1, var3);
            }
         } else if (var3 < this.iCutover) {
            return this.iJulianField.getDifference(var1, var3);
         } else {
            var1 = this.julianToGregorian(var1);
            return this.iGregorianField.getDifference(var1, var3);
         }
      }

      public long getDifferenceAsLong(long var1, long var3) {
         if (var1 >= this.iCutover) {
            if (var3 >= this.iCutover) {
               return this.iGregorianField.getDifferenceAsLong(var1, var3);
            } else {
               var1 = this.gregorianToJulian(var1);
               return this.iJulianField.getDifferenceAsLong(var1, var3);
            }
         } else if (var3 < this.iCutover) {
            return this.iJulianField.getDifferenceAsLong(var1, var3);
         } else {
            var1 = this.julianToGregorian(var1);
            return this.iGregorianField.getDifferenceAsLong(var1, var3);
         }
      }

      public int getMinimumValue(long var1) {
         return var1 >= this.iCutover ? this.iGregorianField.getMinimumValue(var1) : this.iJulianField.getMinimumValue(var1);
      }

      public int getMaximumValue(long var1) {
         return var1 >= this.iCutover ? this.iGregorianField.getMaximumValue(var1) : this.iJulianField.getMaximumValue(var1);
      }
   }

   private static class LinkedDurationField extends DecoratedDurationField {
      private static final long serialVersionUID = 4097975388007713084L;
      private final ImpreciseCutoverField iField;

      LinkedDurationField(DurationField var1, ImpreciseCutoverField var2) {
         super(var1, var1.getType());
         this.iField = var2;
      }

      public long add(long var1, int var3) {
         return this.iField.add(var1, var3);
      }

      public long add(long var1, long var3) {
         return this.iField.add(var1, var3);
      }

      public int getDifference(long var1, long var3) {
         return this.iField.getDifference(var1, var3);
      }

      public long getDifferenceAsLong(long var1, long var3) {
         return this.iField.getDifferenceAsLong(var1, var3);
      }
   }
}
