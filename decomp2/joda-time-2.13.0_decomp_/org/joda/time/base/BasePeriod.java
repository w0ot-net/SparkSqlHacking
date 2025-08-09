package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PeriodConverter;
import org.joda.time.field.FieldUtils;

public abstract class BasePeriod extends AbstractPeriod implements ReadablePeriod, Serializable {
   private static final long serialVersionUID = -2110953284060001145L;
   private static final ReadablePeriod DUMMY_PERIOD = new AbstractPeriod() {
      public int getValue(int var1) {
         return 0;
      }

      public PeriodType getPeriodType() {
         return PeriodType.time();
      }
   };
   private final PeriodType iType;
   private final int[] iValues;

   protected BasePeriod(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, PeriodType var9) {
      var9 = this.checkPeriodType(var9);
      this.iType = var9;
      this.iValues = this.setPeriodInternal(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   protected BasePeriod(long var1, long var3, PeriodType var5, Chronology var6) {
      var5 = this.checkPeriodType(var5);
      var6 = DateTimeUtils.getChronology(var6);
      this.iType = var5;
      this.iValues = var6.get(this, var1, var3);
   }

   protected BasePeriod(ReadableInstant var1, ReadableInstant var2, PeriodType var3) {
      var3 = this.checkPeriodType(var3);
      if (var1 == null && var2 == null) {
         this.iType = var3;
         this.iValues = new int[this.size()];
      } else {
         long var4 = DateTimeUtils.getInstantMillis(var1);
         long var6 = DateTimeUtils.getInstantMillis(var2);
         Chronology var8 = DateTimeUtils.getIntervalChronology(var1, var2);
         this.iType = var3;
         this.iValues = var8.get(this, var4, var6);
      }

   }

   protected BasePeriod(ReadablePartial var1, ReadablePartial var2, PeriodType var3) {
      if (var1 != null && var2 != null) {
         if (var1 instanceof BaseLocal && var2 instanceof BaseLocal && var1.getClass() == var2.getClass()) {
            var3 = this.checkPeriodType(var3);
            long var11 = ((BaseLocal)var1).getLocalMillis();
            long var6 = ((BaseLocal)var2).getLocalMillis();
            Chronology var8 = var1.getChronology();
            var8 = DateTimeUtils.getChronology(var8);
            this.iType = var3;
            this.iValues = var8.get(this, var11, var6);
         } else {
            if (var1.size() != var2.size()) {
               throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
            }

            int var4 = 0;

            for(int var5 = var1.size(); var4 < var5; ++var4) {
               if (var1.getFieldType(var4) != var2.getFieldType(var4)) {
                  throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
               }
            }

            if (!DateTimeUtils.isContiguous(var1)) {
               throw new IllegalArgumentException("ReadablePartial objects must be contiguous");
            }

            this.iType = this.checkPeriodType(var3);
            Chronology var10 = DateTimeUtils.getChronology(var1.getChronology()).withUTC();
            this.iValues = var10.get(this, var10.set(var1, 0L), var10.set(var2, 0L));
         }

      } else {
         throw new IllegalArgumentException("ReadablePartial objects must not be null");
      }
   }

   protected BasePeriod(ReadableInstant var1, ReadableDuration var2, PeriodType var3) {
      var3 = this.checkPeriodType(var3);
      long var4 = DateTimeUtils.getInstantMillis(var1);
      long var6 = DateTimeUtils.getDurationMillis(var2);
      long var8 = FieldUtils.safeAdd(var4, var6);
      Chronology var10 = DateTimeUtils.getInstantChronology(var1);
      this.iType = var3;
      this.iValues = var10.get(this, var4, var8);
   }

   protected BasePeriod(ReadableDuration var1, ReadableInstant var2, PeriodType var3) {
      var3 = this.checkPeriodType(var3);
      long var4 = DateTimeUtils.getDurationMillis(var1);
      long var6 = DateTimeUtils.getInstantMillis(var2);
      long var8 = FieldUtils.safeSubtract(var6, var4);
      Chronology var10 = DateTimeUtils.getInstantChronology(var2);
      this.iType = var3;
      this.iValues = var10.get(this, var8, var6);
   }

   protected BasePeriod(long var1) {
      this.iType = PeriodType.standard();
      int[] var3 = ISOChronology.getInstanceUTC().get(DUMMY_PERIOD, var1);
      this.iValues = new int[8];
      System.arraycopy(var3, 0, this.iValues, 4, 4);
   }

   protected BasePeriod(long var1, PeriodType var3, Chronology var4) {
      var3 = this.checkPeriodType(var3);
      var4 = DateTimeUtils.getChronology(var4);
      this.iType = var3;
      this.iValues = var4.get((ReadablePeriod)this, var1);
   }

   protected BasePeriod(Object var1, PeriodType var2, Chronology var3) {
      PeriodConverter var4 = ConverterManager.getInstance().getPeriodConverter(var1);
      var2 = var2 == null ? var4.getPeriodType(var1) : var2;
      var2 = this.checkPeriodType(var2);
      this.iType = var2;
      if (this instanceof ReadWritablePeriod) {
         this.iValues = new int[this.size()];
         var3 = DateTimeUtils.getChronology(var3);
         var4.setInto((ReadWritablePeriod)this, var1, var3);
      } else {
         this.iValues = (new MutablePeriod(var1, var2, var3)).getValues();
      }

   }

   protected BasePeriod(int[] var1, PeriodType var2) {
      this.iType = var2;
      this.iValues = var1;
   }

   protected PeriodType checkPeriodType(PeriodType var1) {
      return DateTimeUtils.getPeriodType(var1);
   }

   public PeriodType getPeriodType() {
      return this.iType;
   }

   public int getValue(int var1) {
      return this.iValues[var1];
   }

   public Duration toDurationFrom(ReadableInstant var1) {
      long var2 = DateTimeUtils.getInstantMillis(var1);
      Chronology var4 = DateTimeUtils.getInstantChronology(var1);
      long var5 = var4.add(this, var2, 1);
      return new Duration(var2, var5);
   }

   public Duration toDurationTo(ReadableInstant var1) {
      long var2 = DateTimeUtils.getInstantMillis(var1);
      Chronology var4 = DateTimeUtils.getInstantChronology(var1);
      long var5 = var4.add(this, var2, -1);
      return new Duration(var5, var2);
   }

   private void checkAndUpdate(DurationFieldType var1, int[] var2, int var3) {
      int var4 = this.indexOf(var1);
      if (var4 == -1) {
         if (var3 != 0) {
            throw new IllegalArgumentException("Period does not support field '" + var1.getName() + "'");
         }
      } else {
         var2[var4] = var3;
      }

   }

   protected void setPeriod(ReadablePeriod var1) {
      if (var1 == null) {
         this.setValues(new int[this.size()]);
      } else {
         this.setPeriodInternal(var1);
      }

   }

   private void setPeriodInternal(ReadablePeriod var1) {
      int[] var2 = new int[this.size()];
      int var3 = 0;

      for(int var4 = var1.size(); var3 < var4; ++var3) {
         DurationFieldType var5 = var1.getFieldType(var3);
         int var6 = var1.getValue(var3);
         this.checkAndUpdate(var5, var2, var6);
      }

      this.setValues(var2);
   }

   protected void setPeriod(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      int[] var9 = this.setPeriodInternal(var1, var2, var3, var4, var5, var6, var7, var8);
      this.setValues(var9);
   }

   private int[] setPeriodInternal(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      int[] var9 = new int[this.size()];
      this.checkAndUpdate(DurationFieldType.years(), var9, var1);
      this.checkAndUpdate(DurationFieldType.months(), var9, var2);
      this.checkAndUpdate(DurationFieldType.weeks(), var9, var3);
      this.checkAndUpdate(DurationFieldType.days(), var9, var4);
      this.checkAndUpdate(DurationFieldType.hours(), var9, var5);
      this.checkAndUpdate(DurationFieldType.minutes(), var9, var6);
      this.checkAndUpdate(DurationFieldType.seconds(), var9, var7);
      this.checkAndUpdate(DurationFieldType.millis(), var9, var8);
      return var9;
   }

   protected void setField(DurationFieldType var1, int var2) {
      this.setFieldInto(this.iValues, var1, var2);
   }

   protected void setFieldInto(int[] var1, DurationFieldType var2, int var3) {
      int var4 = this.indexOf(var2);
      if (var4 == -1) {
         if (var3 != 0 || var2 == null) {
            throw new IllegalArgumentException("Period does not support field '" + var2 + "'");
         }
      } else {
         var1[var4] = var3;
      }

   }

   protected void addField(DurationFieldType var1, int var2) {
      this.addFieldInto(this.iValues, var1, var2);
   }

   protected void addFieldInto(int[] var1, DurationFieldType var2, int var3) {
      int var4 = this.indexOf(var2);
      if (var4 == -1) {
         if (var3 != 0 || var2 == null) {
            throw new IllegalArgumentException("Period does not support field '" + var2 + "'");
         }
      } else {
         var1[var4] = FieldUtils.safeAdd(var1[var4], var3);
      }

   }

   protected void mergePeriod(ReadablePeriod var1) {
      if (var1 != null) {
         this.setValues(this.mergePeriodInto(this.getValues(), var1));
      }

   }

   protected int[] mergePeriodInto(int[] var1, ReadablePeriod var2) {
      int var3 = 0;

      for(int var4 = var2.size(); var3 < var4; ++var3) {
         DurationFieldType var5 = var2.getFieldType(var3);
         int var6 = var2.getValue(var3);
         this.checkAndUpdate(var5, var1, var6);
      }

      return var1;
   }

   protected void addPeriod(ReadablePeriod var1) {
      if (var1 != null) {
         this.setValues(this.addPeriodInto(this.getValues(), var1));
      }

   }

   protected int[] addPeriodInto(int[] var1, ReadablePeriod var2) {
      int var3 = 0;

      for(int var4 = var2.size(); var3 < var4; ++var3) {
         DurationFieldType var5 = var2.getFieldType(var3);
         int var6 = var2.getValue(var3);
         if (var6 != 0) {
            int var7 = this.indexOf(var5);
            if (var7 == -1) {
               throw new IllegalArgumentException("Period does not support field '" + var5.getName() + "'");
            }

            var1[var7] = FieldUtils.safeAdd(this.getValue(var7), var6);
         }
      }

      return var1;
   }

   protected void setValue(int var1, int var2) {
      this.iValues[var1] = var2;
   }

   protected void setValues(int[] var1) {
      System.arraycopy(var1, 0, this.iValues, 0, this.iValues.length);
   }
}
