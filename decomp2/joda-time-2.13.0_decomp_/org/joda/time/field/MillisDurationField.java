package org.joda.time.field;

import java.io.Serializable;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public final class MillisDurationField extends DurationField implements Serializable {
   private static final long serialVersionUID = 2656707858124633367L;
   public static final DurationField INSTANCE = new MillisDurationField();

   private MillisDurationField() {
   }

   public DurationFieldType getType() {
      return DurationFieldType.millis();
   }

   public String getName() {
      return "millis";
   }

   public boolean isSupported() {
      return true;
   }

   public final boolean isPrecise() {
      return true;
   }

   public final long getUnitMillis() {
      return 1L;
   }

   public int getValue(long var1) {
      return FieldUtils.safeToInt(var1);
   }

   public long getValueAsLong(long var1) {
      return var1;
   }

   public int getValue(long var1, long var3) {
      return FieldUtils.safeToInt(var1);
   }

   public long getValueAsLong(long var1, long var3) {
      return var1;
   }

   public long getMillis(int var1) {
      return (long)var1;
   }

   public long getMillis(long var1) {
      return var1;
   }

   public long getMillis(int var1, long var2) {
      return (long)var1;
   }

   public long getMillis(long var1, long var3) {
      return var1;
   }

   public long add(long var1, int var3) {
      return FieldUtils.safeAdd(var1, (long)var3);
   }

   public long add(long var1, long var3) {
      return FieldUtils.safeAdd(var1, var3);
   }

   public int getDifference(long var1, long var3) {
      return FieldUtils.safeToInt(FieldUtils.safeSubtract(var1, var3));
   }

   public long getDifferenceAsLong(long var1, long var3) {
      return FieldUtils.safeSubtract(var1, var3);
   }

   public int compareTo(DurationField var1) {
      long var2 = var1.getUnitMillis();
      long var4 = this.getUnitMillis();
      if (var4 == var2) {
         return 0;
      } else {
         return var4 < var2 ? -1 : 1;
      }
   }

   public boolean equals(Object var1) {
      if (var1 instanceof MillisDurationField) {
         return this.getUnitMillis() == ((MillisDurationField)var1).getUnitMillis();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (int)this.getUnitMillis();
   }

   public String toString() {
      return "DurationField[millis]";
   }

   private Object readResolve() {
      return INSTANCE;
   }
}
