package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseInterval;
import org.joda.time.field.FieldUtils;

public class MutableInterval extends BaseInterval implements ReadWritableInterval, Cloneable, Serializable {
   private static final long serialVersionUID = -5982824024992428470L;

   public static MutableInterval parse(String var0) {
      return new MutableInterval(var0);
   }

   public MutableInterval() {
      super(0L, 0L, (Chronology)null);
   }

   public MutableInterval(long var1, long var3) {
      super(var1, var3, (Chronology)null);
   }

   public MutableInterval(long var1, long var3, Chronology var5) {
      super(var1, var3, var5);
   }

   public MutableInterval(ReadableInstant var1, ReadableInstant var2) {
      super(var1, var2);
   }

   public MutableInterval(ReadableInstant var1, ReadableDuration var2) {
      super(var1, var2);
   }

   public MutableInterval(ReadableDuration var1, ReadableInstant var2) {
      super(var1, var2);
   }

   public MutableInterval(ReadableInstant var1, ReadablePeriod var2) {
      super(var1, var2);
   }

   public MutableInterval(ReadablePeriod var1, ReadableInstant var2) {
      super(var1, var2);
   }

   public MutableInterval(Object var1) {
      super((Object)var1, (Chronology)null);
   }

   public MutableInterval(Object var1, Chronology var2) {
      super(var1, var2);
   }

   public void setInterval(long var1, long var3) {
      super.setInterval(var1, var3, this.getChronology());
   }

   public void setInterval(ReadableInterval var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Interval must not be null");
      } else {
         long var2 = var1.getStartMillis();
         long var4 = var1.getEndMillis();
         Chronology var6 = var1.getChronology();
         super.setInterval(var2, var4, var6);
      }
   }

   public void setInterval(ReadableInstant var1, ReadableInstant var2) {
      if (var1 == null && var2 == null) {
         long var8 = DateTimeUtils.currentTimeMillis();
         this.setInterval(var8, var8);
      } else {
         long var3 = DateTimeUtils.getInstantMillis(var1);
         long var5 = DateTimeUtils.getInstantMillis(var2);
         Chronology var7 = DateTimeUtils.getInstantChronology(var1);
         super.setInterval(var3, var5, var7);
      }

   }

   public void setChronology(Chronology var1) {
      super.setInterval(this.getStartMillis(), this.getEndMillis(), var1);
   }

   public void setStartMillis(long var1) {
      super.setInterval(var1, this.getEndMillis(), this.getChronology());
   }

   public void setStart(ReadableInstant var1) {
      long var2 = DateTimeUtils.getInstantMillis(var1);
      super.setInterval(var2, this.getEndMillis(), this.getChronology());
   }

   public void setEndMillis(long var1) {
      super.setInterval(this.getStartMillis(), var1, this.getChronology());
   }

   public void setEnd(ReadableInstant var1) {
      long var2 = DateTimeUtils.getInstantMillis(var1);
      super.setInterval(this.getStartMillis(), var2, this.getChronology());
   }

   public void setDurationAfterStart(long var1) {
      this.setEndMillis(FieldUtils.safeAdd(this.getStartMillis(), var1));
   }

   public void setDurationBeforeEnd(long var1) {
      this.setStartMillis(FieldUtils.safeAdd(this.getEndMillis(), -var1));
   }

   public void setDurationAfterStart(ReadableDuration var1) {
      long var2 = DateTimeUtils.getDurationMillis(var1);
      this.setEndMillis(FieldUtils.safeAdd(this.getStartMillis(), var2));
   }

   public void setDurationBeforeEnd(ReadableDuration var1) {
      long var2 = DateTimeUtils.getDurationMillis(var1);
      this.setStartMillis(FieldUtils.safeAdd(this.getEndMillis(), -var2));
   }

   public void setPeriodAfterStart(ReadablePeriod var1) {
      if (var1 == null) {
         this.setEndMillis(this.getStartMillis());
      } else {
         this.setEndMillis(this.getChronology().add(var1, this.getStartMillis(), 1));
      }

   }

   public void setPeriodBeforeEnd(ReadablePeriod var1) {
      if (var1 == null) {
         this.setStartMillis(this.getEndMillis());
      } else {
         this.setStartMillis(this.getChronology().add(var1, this.getEndMillis(), -1));
      }

   }

   public MutableInterval copy() {
      return (MutableInterval)this.clone();
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError("Clone error");
      }
   }
}
