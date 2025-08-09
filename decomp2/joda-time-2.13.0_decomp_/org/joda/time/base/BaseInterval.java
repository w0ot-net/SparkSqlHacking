package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.MutableInterval;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadableInterval;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.IntervalConverter;
import org.joda.time.field.FieldUtils;

public abstract class BaseInterval extends AbstractInterval implements ReadableInterval, Serializable {
   private static final long serialVersionUID = 576586928732749278L;
   private volatile Chronology iChronology;
   private volatile long iStartMillis;
   private volatile long iEndMillis;

   protected BaseInterval(long var1, long var3, Chronology var5) {
      this.iChronology = DateTimeUtils.getChronology(var5);
      this.checkInterval(var1, var3);
      this.iStartMillis = var1;
      this.iEndMillis = var3;
   }

   protected BaseInterval(ReadableInstant var1, ReadableInstant var2) {
      if (var1 == null && var2 == null) {
         this.iStartMillis = this.iEndMillis = DateTimeUtils.currentTimeMillis();
         this.iChronology = ISOChronology.getInstance();
      } else {
         this.iChronology = DateTimeUtils.getInstantChronology(var1);
         this.iStartMillis = DateTimeUtils.getInstantMillis(var1);
         this.iEndMillis = DateTimeUtils.getInstantMillis(var2);
         this.checkInterval(this.iStartMillis, this.iEndMillis);
      }

   }

   protected BaseInterval(ReadableInstant var1, ReadableDuration var2) {
      this.iChronology = DateTimeUtils.getInstantChronology(var1);
      this.iStartMillis = DateTimeUtils.getInstantMillis(var1);
      long var3 = DateTimeUtils.getDurationMillis(var2);
      this.iEndMillis = FieldUtils.safeAdd(this.iStartMillis, var3);
      this.checkInterval(this.iStartMillis, this.iEndMillis);
   }

   protected BaseInterval(ReadableDuration var1, ReadableInstant var2) {
      this.iChronology = DateTimeUtils.getInstantChronology(var2);
      this.iEndMillis = DateTimeUtils.getInstantMillis(var2);
      long var3 = DateTimeUtils.getDurationMillis(var1);
      this.iStartMillis = FieldUtils.safeAdd(this.iEndMillis, -var3);
      this.checkInterval(this.iStartMillis, this.iEndMillis);
   }

   protected BaseInterval(ReadableInstant var1, ReadablePeriod var2) {
      Chronology var3 = DateTimeUtils.getInstantChronology(var1);
      this.iChronology = var3;
      this.iStartMillis = DateTimeUtils.getInstantMillis(var1);
      if (var2 == null) {
         this.iEndMillis = this.iStartMillis;
      } else {
         this.iEndMillis = var3.add(var2, this.iStartMillis, 1);
      }

      this.checkInterval(this.iStartMillis, this.iEndMillis);
   }

   protected BaseInterval(ReadablePeriod var1, ReadableInstant var2) {
      Chronology var3 = DateTimeUtils.getInstantChronology(var2);
      this.iChronology = var3;
      this.iEndMillis = DateTimeUtils.getInstantMillis(var2);
      if (var1 == null) {
         this.iStartMillis = this.iEndMillis;
      } else {
         this.iStartMillis = var3.add(var1, this.iEndMillis, -1);
      }

      this.checkInterval(this.iStartMillis, this.iEndMillis);
   }

   protected BaseInterval(Object var1, Chronology var2) {
      IntervalConverter var3 = ConverterManager.getInstance().getIntervalConverter(var1);
      if (var3.isReadableInterval(var1, var2)) {
         ReadableInterval var4 = (ReadableInterval)var1;
         this.iChronology = var2 != null ? var2 : var4.getChronology();
         this.iStartMillis = var4.getStartMillis();
         this.iEndMillis = var4.getEndMillis();
      } else if (this instanceof ReadWritableInterval) {
         var3.setInto((ReadWritableInterval)this, var1, var2);
      } else {
         MutableInterval var5 = new MutableInterval();
         var3.setInto(var5, var1, var2);
         this.iChronology = var5.getChronology();
         this.iStartMillis = var5.getStartMillis();
         this.iEndMillis = var5.getEndMillis();
      }

      this.checkInterval(this.iStartMillis, this.iEndMillis);
   }

   public Chronology getChronology() {
      return this.iChronology;
   }

   public long getStartMillis() {
      return this.iStartMillis;
   }

   public long getEndMillis() {
      return this.iEndMillis;
   }

   protected void setInterval(long var1, long var3, Chronology var5) {
      this.checkInterval(var1, var3);
      this.iStartMillis = var1;
      this.iEndMillis = var3;
      this.iChronology = DateTimeUtils.getChronology(var5);
   }
}
