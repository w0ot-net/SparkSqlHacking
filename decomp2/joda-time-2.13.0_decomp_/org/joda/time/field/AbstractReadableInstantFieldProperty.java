package org.joda.time.field;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationField;
import org.joda.time.Interval;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

public abstract class AbstractReadableInstantFieldProperty implements Serializable {
   private static final long serialVersionUID = 1971226328211649661L;

   public abstract DateTimeField getField();

   public DateTimeFieldType getFieldType() {
      return this.getField().getType();
   }

   public String getName() {
      return this.getField().getName();
   }

   protected abstract long getMillis();

   protected Chronology getChronology() {
      throw new UnsupportedOperationException("The method getChronology() was added in v1.4 and needs to be implemented by subclasses of AbstractReadableInstantFieldProperty");
   }

   public int get() {
      return this.getField().get(this.getMillis());
   }

   public String getAsString() {
      return Integer.toString(this.get());
   }

   public String getAsText() {
      return this.getAsText((Locale)null);
   }

   public String getAsText(Locale var1) {
      return this.getField().getAsText(this.getMillis(), var1);
   }

   public String getAsShortText() {
      return this.getAsShortText((Locale)null);
   }

   public String getAsShortText(Locale var1) {
      return this.getField().getAsShortText(this.getMillis(), var1);
   }

   public int getDifference(ReadableInstant var1) {
      return var1 == null ? this.getField().getDifference(this.getMillis(), DateTimeUtils.currentTimeMillis()) : this.getField().getDifference(this.getMillis(), var1.getMillis());
   }

   public long getDifferenceAsLong(ReadableInstant var1) {
      return var1 == null ? this.getField().getDifferenceAsLong(this.getMillis(), DateTimeUtils.currentTimeMillis()) : this.getField().getDifferenceAsLong(this.getMillis(), var1.getMillis());
   }

   public DurationField getDurationField() {
      return this.getField().getDurationField();
   }

   public DurationField getRangeDurationField() {
      return this.getField().getRangeDurationField();
   }

   public boolean isLeap() {
      return this.getField().isLeap(this.getMillis());
   }

   public int getLeapAmount() {
      return this.getField().getLeapAmount(this.getMillis());
   }

   public DurationField getLeapDurationField() {
      return this.getField().getLeapDurationField();
   }

   public int getMinimumValueOverall() {
      return this.getField().getMinimumValue();
   }

   public int getMinimumValue() {
      return this.getField().getMinimumValue(this.getMillis());
   }

   public int getMaximumValueOverall() {
      return this.getField().getMaximumValue();
   }

   public int getMaximumValue() {
      return this.getField().getMaximumValue(this.getMillis());
   }

   public int getMaximumTextLength(Locale var1) {
      return this.getField().getMaximumTextLength(var1);
   }

   public int getMaximumShortTextLength(Locale var1) {
      return this.getField().getMaximumShortTextLength(var1);
   }

   public long remainder() {
      return this.getField().remainder(this.getMillis());
   }

   public Interval toInterval() {
      DateTimeField var1 = this.getField();
      long var2 = var1.roundFloor(this.getMillis());
      long var4 = var1.add(var2, 1);
      Interval var6 = new Interval(var2, var4, this.getChronology());
      return var6;
   }

   public int compareTo(ReadableInstant var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The instant must not be null");
      } else {
         int var2 = this.get();
         int var3 = var1.get(this.getFieldType());
         if (var2 < var3) {
            return -1;
         } else {
            return var2 > var3 ? 1 : 0;
         }
      }
   }

   public int compareTo(ReadablePartial var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The partial must not be null");
      } else {
         int var2 = this.get();
         int var3 = var1.get(this.getFieldType());
         if (var2 < var3) {
            return -1;
         } else {
            return var2 > var3 ? 1 : 0;
         }
      }
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof AbstractReadableInstantFieldProperty)) {
         return false;
      } else {
         AbstractReadableInstantFieldProperty var2 = (AbstractReadableInstantFieldProperty)var1;
         return this.get() == var2.get() && this.getFieldType().equals(var2.getFieldType()) && FieldUtils.equals(this.getChronology(), var2.getChronology());
      }
   }

   public int hashCode() {
      return this.get() * 17 + this.getFieldType().hashCode() + this.getChronology().hashCode();
   }

   public String toString() {
      return "Property[" + this.getName() + "]";
   }
}
