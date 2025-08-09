package org.joda.time;

import java.io.Serializable;
import org.joda.convert.FromString;
import org.joda.time.base.BasePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Period extends BasePeriod implements ReadablePeriod, Serializable {
   public static final Period ZERO = new Period();
   private static final long serialVersionUID = 741052353876488155L;

   @FromString
   public static Period parse(String var0) {
      return parse(var0, ISOPeriodFormat.standard());
   }

   public static Period parse(String var0, PeriodFormatter var1) {
      return var1.parsePeriod(var0);
   }

   public static Period years(int var0) {
      return new Period(new int[]{var0, 0, 0, 0, 0, 0, 0, 0}, PeriodType.standard());
   }

   public static Period months(int var0) {
      return new Period(new int[]{0, var0, 0, 0, 0, 0, 0, 0}, PeriodType.standard());
   }

   public static Period weeks(int var0) {
      return new Period(new int[]{0, 0, var0, 0, 0, 0, 0, 0}, PeriodType.standard());
   }

   public static Period days(int var0) {
      return new Period(new int[]{0, 0, 0, var0, 0, 0, 0, 0}, PeriodType.standard());
   }

   public static Period hours(int var0) {
      return new Period(new int[]{0, 0, 0, 0, var0, 0, 0, 0}, PeriodType.standard());
   }

   public static Period minutes(int var0) {
      return new Period(new int[]{0, 0, 0, 0, 0, var0, 0, 0}, PeriodType.standard());
   }

   public static Period seconds(int var0) {
      return new Period(new int[]{0, 0, 0, 0, 0, 0, var0, 0}, PeriodType.standard());
   }

   public static Period millis(int var0) {
      return new Period(new int[]{0, 0, 0, 0, 0, 0, 0, var0}, PeriodType.standard());
   }

   public static Period fieldDifference(ReadablePartial var0, ReadablePartial var1) {
      if (var0 != null && var1 != null) {
         if (var0.size() != var1.size()) {
            throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
         } else {
            DurationFieldType[] var2 = new DurationFieldType[var0.size()];
            int[] var3 = new int[var0.size()];
            int var4 = 0;

            for(int var5 = var0.size(); var4 < var5; ++var4) {
               if (var0.getFieldType(var4) != var1.getFieldType(var4)) {
                  throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
               }

               var2[var4] = var0.getFieldType(var4).getDurationType();
               if (var4 > 0 && var2[var4 - 1].equals(var2[var4])) {
                  throw new IllegalArgumentException("ReadablePartial objects must not have overlapping fields");
               }

               var3[var4] = var1.getValue(var4) - var0.getValue(var4);
            }

            return new Period(var3, PeriodType.forFields(var2));
         }
      } else {
         throw new IllegalArgumentException("ReadablePartial objects must not be null");
      }
   }

   public Period() {
      super(0L, (PeriodType)null, (Chronology)null);
   }

   public Period(int var1, int var2, int var3, int var4) {
      super(0, 0, 0, 0, var1, var2, var3, var4, PeriodType.standard());
   }

   public Period(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      super(var1, var2, var3, var4, var5, var6, var7, var8, PeriodType.standard());
   }

   public Period(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, PeriodType var9) {
      super(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   public Period(long var1) {
      super(var1);
   }

   public Period(long var1, PeriodType var3) {
      super(var1, var3, (Chronology)null);
   }

   public Period(long var1, Chronology var3) {
      super(var1, (PeriodType)null, var3);
   }

   public Period(long var1, PeriodType var3, Chronology var4) {
      super(var1, var3, var4);
   }

   public Period(long var1, long var3) {
      super(var1, var3, (PeriodType)null, (Chronology)null);
   }

   public Period(long var1, long var3, PeriodType var5) {
      super(var1, var3, var5, (Chronology)null);
   }

   public Period(long var1, long var3, Chronology var5) {
      super(var1, var3, (PeriodType)null, var5);
   }

   public Period(long var1, long var3, PeriodType var5, Chronology var6) {
      super(var1, var3, var5, var6);
   }

   public Period(ReadableInstant var1, ReadableInstant var2) {
      super((ReadableInstant)var1, (ReadableInstant)var2, (PeriodType)null);
   }

   public Period(ReadableInstant var1, ReadableInstant var2, PeriodType var3) {
      super(var1, var2, var3);
   }

   public Period(ReadablePartial var1, ReadablePartial var2) {
      super((ReadablePartial)var1, (ReadablePartial)var2, (PeriodType)null);
   }

   public Period(ReadablePartial var1, ReadablePartial var2, PeriodType var3) {
      super(var1, var2, var3);
   }

   public Period(ReadableInstant var1, ReadableDuration var2) {
      super((ReadableInstant)var1, (ReadableDuration)var2, (PeriodType)null);
   }

   public Period(ReadableInstant var1, ReadableDuration var2, PeriodType var3) {
      super(var1, var2, var3);
   }

   public Period(ReadableDuration var1, ReadableInstant var2) {
      super((ReadableDuration)var1, (ReadableInstant)var2, (PeriodType)null);
   }

   public Period(ReadableDuration var1, ReadableInstant var2, PeriodType var3) {
      super(var1, var2, var3);
   }

   public Period(Object var1) {
      super((Object)var1, (PeriodType)null, (Chronology)null);
   }

   public Period(Object var1, PeriodType var2) {
      super((Object)var1, (PeriodType)var2, (Chronology)null);
   }

   public Period(Object var1, Chronology var2) {
      super((Object)var1, (PeriodType)null, (Chronology)var2);
   }

   public Period(Object var1, PeriodType var2, Chronology var3) {
      super(var1, var2, var3);
   }

   private Period(int[] var1, PeriodType var2) {
      super(var1, var2);
   }

   public Period toPeriod() {
      return this;
   }

   public int getYears() {
      return this.getPeriodType().getIndexedField(this, PeriodType.YEAR_INDEX);
   }

   public int getMonths() {
      return this.getPeriodType().getIndexedField(this, PeriodType.MONTH_INDEX);
   }

   public int getWeeks() {
      return this.getPeriodType().getIndexedField(this, PeriodType.WEEK_INDEX);
   }

   public int getDays() {
      return this.getPeriodType().getIndexedField(this, PeriodType.DAY_INDEX);
   }

   public int getHours() {
      return this.getPeriodType().getIndexedField(this, PeriodType.HOUR_INDEX);
   }

   public int getMinutes() {
      return this.getPeriodType().getIndexedField(this, PeriodType.MINUTE_INDEX);
   }

   public int getSeconds() {
      return this.getPeriodType().getIndexedField(this, PeriodType.SECOND_INDEX);
   }

   public int getMillis() {
      return this.getPeriodType().getIndexedField(this, PeriodType.MILLI_INDEX);
   }

   public Period withPeriodType(PeriodType var1) {
      var1 = DateTimeUtils.getPeriodType(var1);
      return var1.equals(this.getPeriodType()) ? this : new Period(this, var1);
   }

   public Period withFields(ReadablePeriod var1) {
      if (var1 == null) {
         return this;
      } else {
         int[] var2 = this.getValues();
         var2 = super.mergePeriodInto(var2, var1);
         return new Period(var2, this.getPeriodType());
      }
   }

   public Period withField(DurationFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else {
         int[] var3 = this.getValues();
         super.setFieldInto(var3, var1, var2);
         return new Period(var3, this.getPeriodType());
      }
   }

   public Period withFieldAdded(DurationFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else if (var2 == 0) {
         return this;
      } else {
         int[] var3 = this.getValues();
         super.addFieldInto(var3, var1, var2);
         return new Period(var3, this.getPeriodType());
      }
   }

   public Period withYears(int var1) {
      int[] var2 = this.getValues();
      this.getPeriodType().setIndexedField(this, PeriodType.YEAR_INDEX, var2, var1);
      return new Period(var2, this.getPeriodType());
   }

   public Period withMonths(int var1) {
      int[] var2 = this.getValues();
      this.getPeriodType().setIndexedField(this, PeriodType.MONTH_INDEX, var2, var1);
      return new Period(var2, this.getPeriodType());
   }

   public Period withWeeks(int var1) {
      int[] var2 = this.getValues();
      this.getPeriodType().setIndexedField(this, PeriodType.WEEK_INDEX, var2, var1);
      return new Period(var2, this.getPeriodType());
   }

   public Period withDays(int var1) {
      int[] var2 = this.getValues();
      this.getPeriodType().setIndexedField(this, PeriodType.DAY_INDEX, var2, var1);
      return new Period(var2, this.getPeriodType());
   }

   public Period withHours(int var1) {
      int[] var2 = this.getValues();
      this.getPeriodType().setIndexedField(this, PeriodType.HOUR_INDEX, var2, var1);
      return new Period(var2, this.getPeriodType());
   }

   public Period withMinutes(int var1) {
      int[] var2 = this.getValues();
      this.getPeriodType().setIndexedField(this, PeriodType.MINUTE_INDEX, var2, var1);
      return new Period(var2, this.getPeriodType());
   }

   public Period withSeconds(int var1) {
      int[] var2 = this.getValues();
      this.getPeriodType().setIndexedField(this, PeriodType.SECOND_INDEX, var2, var1);
      return new Period(var2, this.getPeriodType());
   }

   public Period withMillis(int var1) {
      int[] var2 = this.getValues();
      this.getPeriodType().setIndexedField(this, PeriodType.MILLI_INDEX, var2, var1);
      return new Period(var2, this.getPeriodType());
   }

   public Period plus(ReadablePeriod var1) {
      if (var1 == null) {
         return this;
      } else {
         int[] var2 = this.getValues();
         this.getPeriodType().addIndexedField(this, PeriodType.YEAR_INDEX, var2, var1.get(DurationFieldType.YEARS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.MONTH_INDEX, var2, var1.get(DurationFieldType.MONTHS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.WEEK_INDEX, var2, var1.get(DurationFieldType.WEEKS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.DAY_INDEX, var2, var1.get(DurationFieldType.DAYS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.HOUR_INDEX, var2, var1.get(DurationFieldType.HOURS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.MINUTE_INDEX, var2, var1.get(DurationFieldType.MINUTES_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.SECOND_INDEX, var2, var1.get(DurationFieldType.SECONDS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.MILLI_INDEX, var2, var1.get(DurationFieldType.MILLIS_TYPE));
         return new Period(var2, this.getPeriodType());
      }
   }

   public Period plusYears(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         int[] var2 = this.getValues();
         this.getPeriodType().addIndexedField(this, PeriodType.YEAR_INDEX, var2, var1);
         return new Period(var2, this.getPeriodType());
      }
   }

   public Period plusMonths(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         int[] var2 = this.getValues();
         this.getPeriodType().addIndexedField(this, PeriodType.MONTH_INDEX, var2, var1);
         return new Period(var2, this.getPeriodType());
      }
   }

   public Period plusWeeks(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         int[] var2 = this.getValues();
         this.getPeriodType().addIndexedField(this, PeriodType.WEEK_INDEX, var2, var1);
         return new Period(var2, this.getPeriodType());
      }
   }

   public Period plusDays(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         int[] var2 = this.getValues();
         this.getPeriodType().addIndexedField(this, PeriodType.DAY_INDEX, var2, var1);
         return new Period(var2, this.getPeriodType());
      }
   }

   public Period plusHours(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         int[] var2 = this.getValues();
         this.getPeriodType().addIndexedField(this, PeriodType.HOUR_INDEX, var2, var1);
         return new Period(var2, this.getPeriodType());
      }
   }

   public Period plusMinutes(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         int[] var2 = this.getValues();
         this.getPeriodType().addIndexedField(this, PeriodType.MINUTE_INDEX, var2, var1);
         return new Period(var2, this.getPeriodType());
      }
   }

   public Period plusSeconds(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         int[] var2 = this.getValues();
         this.getPeriodType().addIndexedField(this, PeriodType.SECOND_INDEX, var2, var1);
         return new Period(var2, this.getPeriodType());
      }
   }

   public Period plusMillis(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         int[] var2 = this.getValues();
         this.getPeriodType().addIndexedField(this, PeriodType.MILLI_INDEX, var2, var1);
         return new Period(var2, this.getPeriodType());
      }
   }

   public Period minus(ReadablePeriod var1) {
      if (var1 == null) {
         return this;
      } else {
         int[] var2 = this.getValues();
         this.getPeriodType().addIndexedField(this, PeriodType.YEAR_INDEX, var2, -var1.get(DurationFieldType.YEARS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.MONTH_INDEX, var2, -var1.get(DurationFieldType.MONTHS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.WEEK_INDEX, var2, -var1.get(DurationFieldType.WEEKS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.DAY_INDEX, var2, -var1.get(DurationFieldType.DAYS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.HOUR_INDEX, var2, -var1.get(DurationFieldType.HOURS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.MINUTE_INDEX, var2, -var1.get(DurationFieldType.MINUTES_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.SECOND_INDEX, var2, -var1.get(DurationFieldType.SECONDS_TYPE));
         this.getPeriodType().addIndexedField(this, PeriodType.MILLI_INDEX, var2, -var1.get(DurationFieldType.MILLIS_TYPE));
         return new Period(var2, this.getPeriodType());
      }
   }

   public Period minusYears(int var1) {
      return this.plusYears(-var1);
   }

   public Period minusMonths(int var1) {
      return this.plusMonths(-var1);
   }

   public Period minusWeeks(int var1) {
      return this.plusWeeks(-var1);
   }

   public Period minusDays(int var1) {
      return this.plusDays(-var1);
   }

   public Period minusHours(int var1) {
      return this.plusHours(-var1);
   }

   public Period minusMinutes(int var1) {
      return this.plusMinutes(-var1);
   }

   public Period minusSeconds(int var1) {
      return this.plusSeconds(-var1);
   }

   public Period minusMillis(int var1) {
      return this.plusMillis(-var1);
   }

   public Period multipliedBy(int var1) {
      if (this != ZERO && var1 != 1) {
         int[] var2 = this.getValues();

         for(int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3] = FieldUtils.safeMultiply(var2[var3], var1);
         }

         return new Period(var2, this.getPeriodType());
      } else {
         return this;
      }
   }

   public Period negated() {
      return this.multipliedBy(-1);
   }

   public Weeks toStandardWeeks() {
      this.checkYearsAndMonths("Weeks");
      long var1 = (long)this.getMillis();
      var1 += (long)this.getSeconds() * 1000L;
      var1 += (long)this.getMinutes() * 60000L;
      var1 += (long)this.getHours() * 3600000L;
      var1 += (long)this.getDays() * 86400000L;
      long var3 = (long)this.getWeeks() + var1 / 604800000L;
      return Weeks.weeks(FieldUtils.safeToInt(var3));
   }

   public Days toStandardDays() {
      this.checkYearsAndMonths("Days");
      long var1 = (long)this.getMillis();
      var1 += (long)this.getSeconds() * 1000L;
      var1 += (long)this.getMinutes() * 60000L;
      var1 += (long)this.getHours() * 3600000L;
      long var3 = var1 / 86400000L;
      var3 = FieldUtils.safeAdd(var3, (long)this.getDays());
      var3 = FieldUtils.safeAdd(var3, (long)this.getWeeks() * 7L);
      return Days.days(FieldUtils.safeToInt(var3));
   }

   public Hours toStandardHours() {
      this.checkYearsAndMonths("Hours");
      long var1 = (long)this.getMillis();
      var1 += (long)this.getSeconds() * 1000L;
      var1 += (long)this.getMinutes() * 60000L;
      long var3 = var1 / 3600000L;
      var3 = FieldUtils.safeAdd(var3, (long)this.getHours());
      var3 = FieldUtils.safeAdd(var3, (long)this.getDays() * 24L);
      var3 = FieldUtils.safeAdd(var3, (long)this.getWeeks() * 168L);
      return Hours.hours(FieldUtils.safeToInt(var3));
   }

   public Minutes toStandardMinutes() {
      this.checkYearsAndMonths("Minutes");
      long var1 = (long)this.getMillis();
      var1 += (long)this.getSeconds() * 1000L;
      long var3 = var1 / 60000L;
      var3 = FieldUtils.safeAdd(var3, (long)this.getMinutes());
      var3 = FieldUtils.safeAdd(var3, (long)this.getHours() * 60L);
      var3 = FieldUtils.safeAdd(var3, (long)this.getDays() * 1440L);
      var3 = FieldUtils.safeAdd(var3, (long)this.getWeeks() * 10080L);
      return Minutes.minutes(FieldUtils.safeToInt(var3));
   }

   public Seconds toStandardSeconds() {
      this.checkYearsAndMonths("Seconds");
      long var1 = (long)(this.getMillis() / 1000);
      var1 = FieldUtils.safeAdd(var1, (long)this.getSeconds());
      var1 = FieldUtils.safeAdd(var1, (long)this.getMinutes() * 60L);
      var1 = FieldUtils.safeAdd(var1, (long)this.getHours() * 3600L);
      var1 = FieldUtils.safeAdd(var1, (long)this.getDays() * 86400L);
      var1 = FieldUtils.safeAdd(var1, (long)this.getWeeks() * 604800L);
      return Seconds.seconds(FieldUtils.safeToInt(var1));
   }

   public Duration toStandardDuration() {
      this.checkYearsAndMonths("Duration");
      long var1 = (long)this.getMillis();
      var1 += (long)this.getSeconds() * 1000L;
      var1 += (long)this.getMinutes() * 60000L;
      var1 += (long)this.getHours() * 3600000L;
      var1 += (long)this.getDays() * 86400000L;
      var1 += (long)this.getWeeks() * 604800000L;
      return new Duration(var1);
   }

   private void checkYearsAndMonths(String var1) {
      if (this.getMonths() != 0) {
         throw new UnsupportedOperationException("Cannot convert to " + var1 + " as this period contains months and months vary in length");
      } else if (this.getYears() != 0) {
         throw new UnsupportedOperationException("Cannot convert to " + var1 + " as this period contains years and years vary in length");
      }
   }

   public Period normalizedStandard() {
      return this.normalizedStandard(PeriodType.standard());
   }

   public Period normalizedStandard(PeriodType var1) {
      var1 = DateTimeUtils.getPeriodType(var1);
      long var2 = (long)this.getMillis();
      var2 += (long)this.getSeconds() * 1000L;
      var2 += (long)this.getMinutes() * 60000L;
      var2 += (long)this.getHours() * 3600000L;
      var2 += (long)this.getDays() * 86400000L;
      var2 += (long)this.getWeeks() * 604800000L;
      Period var4 = new Period(var2, var1, ISOChronology.getInstanceUTC());
      int var5 = this.getYears();
      int var6 = this.getMonths();
      if (var5 != 0 || var6 != 0) {
         long var7 = (long)var5 * 12L + (long)var6;
         if (var1.isSupported(DurationFieldType.YEARS_TYPE)) {
            int var9 = FieldUtils.safeToInt(var7 / 12L);
            var4 = var4.withYears(var9);
            var7 -= (long)var9 * 12L;
         }

         if (var1.isSupported(DurationFieldType.MONTHS_TYPE)) {
            int var16 = FieldUtils.safeToInt(var7);
            var4 = var4.withMonths(var16);
            var7 -= (long)var16;
         }

         if (var7 != 0L) {
            throw new UnsupportedOperationException("Unable to normalize as PeriodType is missing either years or months but period has a month/year amount: " + this.toString());
         }
      }

      return var4;
   }
}
