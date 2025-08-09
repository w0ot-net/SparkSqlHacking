package org.joda.time.field;

import java.io.Serializable;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public abstract class BaseDurationField extends DurationField implements Serializable {
   private static final long serialVersionUID = -2554245107589433218L;
   private final DurationFieldType iType;

   protected BaseDurationField(DurationFieldType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The type must not be null");
      } else {
         this.iType = var1;
      }
   }

   public final DurationFieldType getType() {
      return this.iType;
   }

   public final String getName() {
      return this.iType.getName();
   }

   public final boolean isSupported() {
      return true;
   }

   public int getValue(long var1) {
      return FieldUtils.safeToInt(this.getValueAsLong(var1));
   }

   public long getValueAsLong(long var1) {
      return var1 / this.getUnitMillis();
   }

   public int getValue(long var1, long var3) {
      return FieldUtils.safeToInt(this.getValueAsLong(var1, var3));
   }

   public long getMillis(int var1) {
      return (long)var1 * this.getUnitMillis();
   }

   public long getMillis(long var1) {
      return FieldUtils.safeMultiply(var1, this.getUnitMillis());
   }

   public int getDifference(long var1, long var3) {
      return FieldUtils.safeToInt(this.getDifferenceAsLong(var1, var3));
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

   public String toString() {
      return "DurationField[" + this.getName() + ']';
   }
}
