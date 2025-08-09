package org.joda.time.field;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;

public class DelegatedDateTimeField extends DateTimeField implements Serializable {
   private static final long serialVersionUID = -4730164440214502503L;
   private final DateTimeField iField;
   private final DurationField iRangeDurationField;
   private final DateTimeFieldType iType;

   public DelegatedDateTimeField(DateTimeField var1) {
      this(var1, (DateTimeFieldType)null);
   }

   public DelegatedDateTimeField(DateTimeField var1, DateTimeFieldType var2) {
      this(var1, (DurationField)null, var2);
   }

   public DelegatedDateTimeField(DateTimeField var1, DurationField var2, DateTimeFieldType var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("The field must not be null");
      } else {
         this.iField = var1;
         this.iRangeDurationField = var2;
         this.iType = var3 == null ? var1.getType() : var3;
      }
   }

   public final DateTimeField getWrappedField() {
      return this.iField;
   }

   public DateTimeFieldType getType() {
      return this.iType;
   }

   public String getName() {
      return this.iType.getName();
   }

   public boolean isSupported() {
      return this.iField.isSupported();
   }

   public boolean isLenient() {
      return this.iField.isLenient();
   }

   public int get(long var1) {
      return this.iField.get(var1);
   }

   public String getAsText(long var1, Locale var3) {
      return this.iField.getAsText(var1, var3);
   }

   public String getAsText(long var1) {
      return this.iField.getAsText(var1);
   }

   public String getAsText(ReadablePartial var1, int var2, Locale var3) {
      return this.iField.getAsText(var1, var2, var3);
   }

   public String getAsText(ReadablePartial var1, Locale var2) {
      return this.iField.getAsText(var1, var2);
   }

   public String getAsText(int var1, Locale var2) {
      return this.iField.getAsText(var1, var2);
   }

   public String getAsShortText(long var1, Locale var3) {
      return this.iField.getAsShortText(var1, var3);
   }

   public String getAsShortText(long var1) {
      return this.iField.getAsShortText(var1);
   }

   public String getAsShortText(ReadablePartial var1, int var2, Locale var3) {
      return this.iField.getAsShortText(var1, var2, var3);
   }

   public String getAsShortText(ReadablePartial var1, Locale var2) {
      return this.iField.getAsShortText(var1, var2);
   }

   public String getAsShortText(int var1, Locale var2) {
      return this.iField.getAsShortText(var1, var2);
   }

   public long add(long var1, int var3) {
      return this.iField.add(var1, var3);
   }

   public long add(long var1, long var3) {
      return this.iField.add(var1, var3);
   }

   public int[] add(ReadablePartial var1, int var2, int[] var3, int var4) {
      return this.iField.add(var1, var2, var3, var4);
   }

   public int[] addWrapPartial(ReadablePartial var1, int var2, int[] var3, int var4) {
      return this.iField.addWrapPartial(var1, var2, var3, var4);
   }

   public long addWrapField(long var1, int var3) {
      return this.iField.addWrapField(var1, var3);
   }

   public int[] addWrapField(ReadablePartial var1, int var2, int[] var3, int var4) {
      return this.iField.addWrapField(var1, var2, var3, var4);
   }

   public int getDifference(long var1, long var3) {
      return this.iField.getDifference(var1, var3);
   }

   public long getDifferenceAsLong(long var1, long var3) {
      return this.iField.getDifferenceAsLong(var1, var3);
   }

   public long set(long var1, int var3) {
      return this.iField.set(var1, var3);
   }

   public long set(long var1, String var3, Locale var4) {
      return this.iField.set(var1, var3, var4);
   }

   public long set(long var1, String var3) {
      return this.iField.set(var1, var3);
   }

   public int[] set(ReadablePartial var1, int var2, int[] var3, int var4) {
      return this.iField.set(var1, var2, var3, var4);
   }

   public int[] set(ReadablePartial var1, int var2, int[] var3, String var4, Locale var5) {
      return this.iField.set(var1, var2, var3, var4, var5);
   }

   public DurationField getDurationField() {
      return this.iField.getDurationField();
   }

   public DurationField getRangeDurationField() {
      return this.iRangeDurationField != null ? this.iRangeDurationField : this.iField.getRangeDurationField();
   }

   public boolean isLeap(long var1) {
      return this.iField.isLeap(var1);
   }

   public int getLeapAmount(long var1) {
      return this.iField.getLeapAmount(var1);
   }

   public DurationField getLeapDurationField() {
      return this.iField.getLeapDurationField();
   }

   public int getMinimumValue() {
      return this.iField.getMinimumValue();
   }

   public int getMinimumValue(long var1) {
      return this.iField.getMinimumValue(var1);
   }

   public int getMinimumValue(ReadablePartial var1) {
      return this.iField.getMinimumValue(var1);
   }

   public int getMinimumValue(ReadablePartial var1, int[] var2) {
      return this.iField.getMinimumValue(var1, var2);
   }

   public int getMaximumValue() {
      return this.iField.getMaximumValue();
   }

   public int getMaximumValue(long var1) {
      return this.iField.getMaximumValue(var1);
   }

   public int getMaximumValue(ReadablePartial var1) {
      return this.iField.getMaximumValue(var1);
   }

   public int getMaximumValue(ReadablePartial var1, int[] var2) {
      return this.iField.getMaximumValue(var1, var2);
   }

   public int getMaximumTextLength(Locale var1) {
      return this.iField.getMaximumTextLength(var1);
   }

   public int getMaximumShortTextLength(Locale var1) {
      return this.iField.getMaximumShortTextLength(var1);
   }

   public long roundFloor(long var1) {
      return this.iField.roundFloor(var1);
   }

   public long roundCeiling(long var1) {
      return this.iField.roundCeiling(var1);
   }

   public long roundHalfFloor(long var1) {
      return this.iField.roundHalfFloor(var1);
   }

   public long roundHalfCeiling(long var1) {
      return this.iField.roundHalfCeiling(var1);
   }

   public long roundHalfEven(long var1) {
      return this.iField.roundHalfEven(var1);
   }

   public long remainder(long var1) {
      return this.iField.remainder(var1);
   }

   public String toString() {
      return "DateTimeField[" + this.getName() + ']';
   }
}
