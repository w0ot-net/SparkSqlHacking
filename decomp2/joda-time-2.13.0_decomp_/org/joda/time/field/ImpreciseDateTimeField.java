package org.joda.time.field;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public abstract class ImpreciseDateTimeField extends BaseDateTimeField {
   private static final long serialVersionUID = 7190739608550251860L;
   final long iUnitMillis;
   private final DurationField iDurationField;

   public ImpreciseDateTimeField(DateTimeFieldType var1, long var2) {
      super(var1);
      this.iUnitMillis = var2;
      this.iDurationField = new LinkedDurationField(var1.getDurationType());
   }

   public abstract int get(long var1);

   public abstract long set(long var1, int var3);

   public abstract long add(long var1, int var3);

   public abstract long add(long var1, long var3);

   public int getDifference(long var1, long var3) {
      return FieldUtils.safeToInt(this.getDifferenceAsLong(var1, var3));
   }

   public long getDifferenceAsLong(long var1, long var3) {
      if (var1 < var3) {
         return -this.getDifferenceAsLong(var3, var1);
      } else {
         long var5 = (var1 - var3) / this.iUnitMillis;
         if (this.add(var3, var5) < var1) {
            while(true) {
               ++var5;
               if (this.add(var3, var5) > var1) {
                  --var5;
                  break;
               }
            }
         } else if (this.add(var3, var5) > var1) {
            do {
               --var5;
            } while(this.add(var3, var5) > var1);
         }

         return var5;
      }
   }

   public final DurationField getDurationField() {
      return this.iDurationField;
   }

   public abstract DurationField getRangeDurationField();

   public abstract long roundFloor(long var1);

   protected final long getDurationUnitMillis() {
      return this.iUnitMillis;
   }

   private final class LinkedDurationField extends BaseDurationField {
      private static final long serialVersionUID = -203813474600094134L;

      LinkedDurationField(DurationFieldType var2) {
         super(var2);
      }

      public boolean isPrecise() {
         return false;
      }

      public long getUnitMillis() {
         return ImpreciseDateTimeField.this.iUnitMillis;
      }

      public int getValue(long var1, long var3) {
         return ImpreciseDateTimeField.this.getDifference(var3 + var1, var3);
      }

      public long getValueAsLong(long var1, long var3) {
         return ImpreciseDateTimeField.this.getDifferenceAsLong(var3 + var1, var3);
      }

      public long getMillis(int var1, long var2) {
         return ImpreciseDateTimeField.this.add(var2, var1) - var2;
      }

      public long getMillis(long var1, long var3) {
         return ImpreciseDateTimeField.this.add(var3, var1) - var3;
      }

      public long add(long var1, int var3) {
         return ImpreciseDateTimeField.this.add(var1, var3);
      }

      public long add(long var1, long var3) {
         return ImpreciseDateTimeField.this.add(var1, var3);
      }

      public int getDifference(long var1, long var3) {
         return ImpreciseDateTimeField.this.getDifference(var1, var3);
      }

      public long getDifferenceAsLong(long var1, long var3) {
         return ImpreciseDateTimeField.this.getDifferenceAsLong(var1, var3);
      }
   }
}
