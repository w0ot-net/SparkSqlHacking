package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;

public final class UnsupportedDateTimeField extends DateTimeField implements Serializable {
   private static final long serialVersionUID = -1934618396111902255L;
   private static HashMap cCache;
   private final DateTimeFieldType iType;
   private final DurationField iDurationField;

   public static synchronized UnsupportedDateTimeField getInstance(DateTimeFieldType var0, DurationField var1) {
      UnsupportedDateTimeField var2;
      if (cCache == null) {
         cCache = new HashMap(7);
         var2 = null;
      } else {
         var2 = (UnsupportedDateTimeField)cCache.get(var0);
         if (var2 != null && var2.getDurationField() != var1) {
            var2 = null;
         }
      }

      if (var2 == null) {
         var2 = new UnsupportedDateTimeField(var0, var1);
         cCache.put(var0, var2);
      }

      return var2;
   }

   private UnsupportedDateTimeField(DateTimeFieldType var1, DurationField var2) {
      if (var1 != null && var2 != null) {
         this.iType = var1;
         this.iDurationField = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public DateTimeFieldType getType() {
      return this.iType;
   }

   public String getName() {
      return this.iType.getName();
   }

   public boolean isSupported() {
      return false;
   }

   public boolean isLenient() {
      return false;
   }

   public int get(long var1) {
      throw this.unsupported();
   }

   public String getAsText(long var1, Locale var3) {
      throw this.unsupported();
   }

   public String getAsText(long var1) {
      throw this.unsupported();
   }

   public String getAsText(ReadablePartial var1, int var2, Locale var3) {
      throw this.unsupported();
   }

   public String getAsText(ReadablePartial var1, Locale var2) {
      throw this.unsupported();
   }

   public String getAsText(int var1, Locale var2) {
      throw this.unsupported();
   }

   public String getAsShortText(long var1, Locale var3) {
      throw this.unsupported();
   }

   public String getAsShortText(long var1) {
      throw this.unsupported();
   }

   public String getAsShortText(ReadablePartial var1, int var2, Locale var3) {
      throw this.unsupported();
   }

   public String getAsShortText(ReadablePartial var1, Locale var2) {
      throw this.unsupported();
   }

   public String getAsShortText(int var1, Locale var2) {
      throw this.unsupported();
   }

   public long add(long var1, int var3) {
      return this.getDurationField().add(var1, var3);
   }

   public long add(long var1, long var3) {
      return this.getDurationField().add(var1, var3);
   }

   public int[] add(ReadablePartial var1, int var2, int[] var3, int var4) {
      throw this.unsupported();
   }

   public int[] addWrapPartial(ReadablePartial var1, int var2, int[] var3, int var4) {
      throw this.unsupported();
   }

   public long addWrapField(long var1, int var3) {
      throw this.unsupported();
   }

   public int[] addWrapField(ReadablePartial var1, int var2, int[] var3, int var4) {
      throw this.unsupported();
   }

   public int getDifference(long var1, long var3) {
      return this.getDurationField().getDifference(var1, var3);
   }

   public long getDifferenceAsLong(long var1, long var3) {
      return this.getDurationField().getDifferenceAsLong(var1, var3);
   }

   public long set(long var1, int var3) {
      throw this.unsupported();
   }

   public int[] set(ReadablePartial var1, int var2, int[] var3, int var4) {
      throw this.unsupported();
   }

   public long set(long var1, String var3, Locale var4) {
      throw this.unsupported();
   }

   public long set(long var1, String var3) {
      throw this.unsupported();
   }

   public int[] set(ReadablePartial var1, int var2, int[] var3, String var4, Locale var5) {
      throw this.unsupported();
   }

   public DurationField getDurationField() {
      return this.iDurationField;
   }

   public DurationField getRangeDurationField() {
      return null;
   }

   public boolean isLeap(long var1) {
      throw this.unsupported();
   }

   public int getLeapAmount(long var1) {
      throw this.unsupported();
   }

   public DurationField getLeapDurationField() {
      return null;
   }

   public int getMinimumValue() {
      throw this.unsupported();
   }

   public int getMinimumValue(long var1) {
      throw this.unsupported();
   }

   public int getMinimumValue(ReadablePartial var1) {
      throw this.unsupported();
   }

   public int getMinimumValue(ReadablePartial var1, int[] var2) {
      throw this.unsupported();
   }

   public int getMaximumValue() {
      throw this.unsupported();
   }

   public int getMaximumValue(long var1) {
      throw this.unsupported();
   }

   public int getMaximumValue(ReadablePartial var1) {
      throw this.unsupported();
   }

   public int getMaximumValue(ReadablePartial var1, int[] var2) {
      throw this.unsupported();
   }

   public int getMaximumTextLength(Locale var1) {
      throw this.unsupported();
   }

   public int getMaximumShortTextLength(Locale var1) {
      throw this.unsupported();
   }

   public long roundFloor(long var1) {
      throw this.unsupported();
   }

   public long roundCeiling(long var1) {
      throw this.unsupported();
   }

   public long roundHalfFloor(long var1) {
      throw this.unsupported();
   }

   public long roundHalfCeiling(long var1) {
      throw this.unsupported();
   }

   public long roundHalfEven(long var1) {
      throw this.unsupported();
   }

   public long remainder(long var1) {
      throw this.unsupported();
   }

   public String toString() {
      return "UnsupportedDateTimeField";
   }

   private Object readResolve() {
      return getInstance(this.iType, this.iDurationField);
   }

   private UnsupportedOperationException unsupported() {
      return new UnsupportedOperationException(this.iType + " field is unsupported");
   }
}
