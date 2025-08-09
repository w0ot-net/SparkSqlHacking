package org.joda.time.field;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.ReadablePartial;

public abstract class BaseDateTimeField extends DateTimeField {
   private final DateTimeFieldType iType;

   protected BaseDateTimeField(DateTimeFieldType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The type must not be null");
      } else {
         this.iType = var1;
      }
   }

   public final DateTimeFieldType getType() {
      return this.iType;
   }

   public final String getName() {
      return this.iType.getName();
   }

   public final boolean isSupported() {
      return true;
   }

   public abstract int get(long var1);

   public String getAsText(long var1, Locale var3) {
      return this.getAsText(this.get(var1), var3);
   }

   public final String getAsText(long var1) {
      return this.getAsText(var1, (Locale)null);
   }

   public String getAsText(ReadablePartial var1, int var2, Locale var3) {
      return this.getAsText(var2, var3);
   }

   public final String getAsText(ReadablePartial var1, Locale var2) {
      return this.getAsText(var1, var1.get(this.getType()), var2);
   }

   public String getAsText(int var1, Locale var2) {
      return Integer.toString(var1);
   }

   public String getAsShortText(long var1, Locale var3) {
      return this.getAsShortText(this.get(var1), var3);
   }

   public final String getAsShortText(long var1) {
      return this.getAsShortText(var1, (Locale)null);
   }

   public String getAsShortText(ReadablePartial var1, int var2, Locale var3) {
      return this.getAsShortText(var2, var3);
   }

   public final String getAsShortText(ReadablePartial var1, Locale var2) {
      return this.getAsShortText(var1, var1.get(this.getType()), var2);
   }

   public String getAsShortText(int var1, Locale var2) {
      return this.getAsText(var1, var2);
   }

   public long add(long var1, int var3) {
      return this.getDurationField().add(var1, var3);
   }

   public long add(long var1, long var3) {
      return this.getDurationField().add(var1, var3);
   }

   public int[] add(ReadablePartial var1, int var2, int[] var3, int var4) {
      if (var4 == 0) {
         return var3;
      } else {
         DateTimeField var5;
         for(var5 = null; var4 > 0; var3[var2] = this.getMinimumValue(var1, var3)) {
            int var6 = this.getMaximumValue(var1, var3);
            long var7 = (long)(var3[var2] + var4);
            if (var7 <= (long)var6) {
               var3[var2] = (int)var7;
               break;
            }

            if (var5 == null) {
               if (var2 == 0) {
                  throw new IllegalArgumentException("Maximum value exceeded for add");
               }

               var5 = var1.getField(var2 - 1);
               if (this.getRangeDurationField().getType() != var5.getDurationField().getType()) {
                  throw new IllegalArgumentException("Fields invalid for add");
               }
            }

            var4 -= var6 + 1 - var3[var2];
            var3 = var5.add(var1, var2 - 1, var3, 1);
         }

         while(var4 < 0) {
            int var9 = this.getMinimumValue(var1, var3);
            long var10 = (long)(var3[var2] + var4);
            if (var10 >= (long)var9) {
               var3[var2] = (int)var10;
               break;
            }

            if (var5 == null) {
               if (var2 == 0) {
                  throw new IllegalArgumentException("Maximum value exceeded for add");
               }

               var5 = var1.getField(var2 - 1);
               if (this.getRangeDurationField().getType() != var5.getDurationField().getType()) {
                  throw new IllegalArgumentException("Fields invalid for add");
               }
            }

            var4 -= var9 - 1 - var3[var2];
            var3 = var5.add(var1, var2 - 1, var3, -1);
            var3[var2] = this.getMaximumValue(var1, var3);
         }

         return this.set(var1, var2, var3, var3[var2]);
      }
   }

   public int[] addWrapPartial(ReadablePartial var1, int var2, int[] var3, int var4) {
      if (var4 == 0) {
         return var3;
      } else {
         DateTimeField var5 = null;

         while(var4 > 0) {
            int var6 = this.getMaximumValue(var1, var3);
            long var7 = (long)(var3[var2] + var4);
            if (var7 <= (long)var6) {
               var3[var2] = (int)var7;
               break;
            }

            if (var5 == null) {
               if (var2 == 0) {
                  var4 -= var6 + 1 - var3[var2];
                  var3[var2] = this.getMinimumValue(var1, var3);
                  continue;
               }

               var5 = var1.getField(var2 - 1);
               if (this.getRangeDurationField().getType() != var5.getDurationField().getType()) {
                  throw new IllegalArgumentException("Fields invalid for add");
               }
            }

            var4 -= var6 + 1 - var3[var2];
            var3 = var5.addWrapPartial(var1, var2 - 1, var3, 1);
            var3[var2] = this.getMinimumValue(var1, var3);
         }

         while(var4 < 0) {
            int var9 = this.getMinimumValue(var1, var3);
            long var10 = (long)(var3[var2] + var4);
            if (var10 >= (long)var9) {
               var3[var2] = (int)var10;
               break;
            }

            if (var5 == null) {
               if (var2 == 0) {
                  var4 -= var9 - 1 - var3[var2];
                  var3[var2] = this.getMaximumValue(var1, var3);
                  continue;
               }

               var5 = var1.getField(var2 - 1);
               if (this.getRangeDurationField().getType() != var5.getDurationField().getType()) {
                  throw new IllegalArgumentException("Fields invalid for add");
               }
            }

            var4 -= var9 - 1 - var3[var2];
            var3 = var5.addWrapPartial(var1, var2 - 1, var3, -1);
            var3[var2] = this.getMaximumValue(var1, var3);
         }

         return this.set(var1, var2, var3, var3[var2]);
      }
   }

   public long addWrapField(long var1, int var3) {
      int var4 = this.get(var1);
      int var5 = FieldUtils.getWrappedValue(var4, var3, this.getMinimumValue(var1), this.getMaximumValue(var1));
      return this.set(var1, var5);
   }

   public int[] addWrapField(ReadablePartial var1, int var2, int[] var3, int var4) {
      int var5 = var3[var2];
      int var6 = FieldUtils.getWrappedValue(var5, var4, this.getMinimumValue(var1), this.getMaximumValue(var1));
      return this.set(var1, var2, var3, var6);
   }

   public int getDifference(long var1, long var3) {
      return this.getDurationField().getDifference(var1, var3);
   }

   public long getDifferenceAsLong(long var1, long var3) {
      return this.getDurationField().getDifferenceAsLong(var1, var3);
   }

   public abstract long set(long var1, int var3);

   public int[] set(ReadablePartial var1, int var2, int[] var3, int var4) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var4, this.getMinimumValue(var1, var3), this.getMaximumValue(var1, var3));
      var3[var2] = var4;

      for(int var5 = var2 + 1; var5 < var1.size(); ++var5) {
         DateTimeField var6 = var1.getField(var5);
         if (var3[var5] > var6.getMaximumValue(var1, var3)) {
            var3[var5] = var6.getMaximumValue(var1, var3);
         }

         if (var3[var5] < var6.getMinimumValue(var1, var3)) {
            var3[var5] = var6.getMinimumValue(var1, var3);
         }
      }

      return var3;
   }

   public long set(long var1, String var3, Locale var4) {
      int var5 = this.convertText(var3, var4);
      return this.set(var1, var5);
   }

   public final long set(long var1, String var3) {
      return this.set(var1, var3, (Locale)null);
   }

   public int[] set(ReadablePartial var1, int var2, int[] var3, String var4, Locale var5) {
      int var6 = this.convertText(var4, var5);
      return this.set(var1, var2, var3, var6);
   }

   protected int convertText(String var1, Locale var2) {
      try {
         return Integer.parseInt(var1);
      } catch (NumberFormatException var4) {
         throw new IllegalFieldValueException(this.getType(), var1);
      }
   }

   public abstract DurationField getDurationField();

   public abstract DurationField getRangeDurationField();

   public boolean isLeap(long var1) {
      return false;
   }

   public int getLeapAmount(long var1) {
      return 0;
   }

   public DurationField getLeapDurationField() {
      return null;
   }

   public abstract int getMinimumValue();

   public int getMinimumValue(long var1) {
      return this.getMinimumValue();
   }

   public int getMinimumValue(ReadablePartial var1) {
      return this.getMinimumValue();
   }

   public int getMinimumValue(ReadablePartial var1, int[] var2) {
      return this.getMinimumValue(var1);
   }

   public abstract int getMaximumValue();

   public int getMaximumValue(long var1) {
      return this.getMaximumValue();
   }

   public int getMaximumValue(ReadablePartial var1) {
      return this.getMaximumValue();
   }

   public int getMaximumValue(ReadablePartial var1, int[] var2) {
      return this.getMaximumValue(var1);
   }

   public int getMaximumTextLength(Locale var1) {
      int var2 = this.getMaximumValue();
      if (var2 >= 0) {
         if (var2 < 10) {
            return 1;
         }

         if (var2 < 100) {
            return 2;
         }

         if (var2 < 1000) {
            return 3;
         }
      }

      return Integer.toString(var2).length();
   }

   public int getMaximumShortTextLength(Locale var1) {
      return this.getMaximumTextLength(var1);
   }

   public abstract long roundFloor(long var1);

   public long roundCeiling(long var1) {
      long var3 = this.roundFloor(var1);
      if (var3 != var1) {
         var1 = this.add(var3, 1);
      }

      return var1;
   }

   public long roundHalfFloor(long var1) {
      long var3 = this.roundFloor(var1);
      long var5 = this.roundCeiling(var1);
      long var7 = var1 - var3;
      long var9 = var5 - var1;
      return var7 <= var9 ? var3 : var5;
   }

   public long roundHalfCeiling(long var1) {
      long var3 = this.roundFloor(var1);
      long var5 = this.roundCeiling(var1);
      long var7 = var1 - var3;
      long var9 = var5 - var1;
      return var9 <= var7 ? var5 : var3;
   }

   public long roundHalfEven(long var1) {
      long var3 = this.roundFloor(var1);
      long var5 = this.roundCeiling(var1);
      long var7 = var1 - var3;
      long var9 = var5 - var1;
      if (var7 < var9) {
         return var3;
      } else if (var9 < var7) {
         return var5;
      } else {
         return (this.get(var5) & 1) == 0 ? var5 : var3;
      }
   }

   public long remainder(long var1) {
      return var1 - this.roundFloor(var1);
   }

   public String toString() {
      return "DateTimeField[" + this.getName() + ']';
   }
}
