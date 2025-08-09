package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.UnsupportedDurationField;

final class BasicSingleEraDateTimeField extends BaseDateTimeField {
   private static final int ERA_VALUE = 1;
   private final String iEraText;

   BasicSingleEraDateTimeField(String var1) {
      super(DateTimeFieldType.era());
      this.iEraText = var1;
   }

   public boolean isLenient() {
      return false;
   }

   public int get(long var1) {
      return 1;
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, 1, 1);
      return var1;
   }

   public long set(long var1, String var3, Locale var4) {
      if (!this.iEraText.equals(var3) && !"1".equals(var3)) {
         throw new IllegalFieldValueException(DateTimeFieldType.era(), var3);
      } else {
         return var1;
      }
   }

   public long roundFloor(long var1) {
      return Long.MIN_VALUE;
   }

   public long roundCeiling(long var1) {
      return Long.MAX_VALUE;
   }

   public long roundHalfFloor(long var1) {
      return Long.MIN_VALUE;
   }

   public long roundHalfCeiling(long var1) {
      return Long.MIN_VALUE;
   }

   public long roundHalfEven(long var1) {
      return Long.MIN_VALUE;
   }

   public DurationField getDurationField() {
      return UnsupportedDurationField.getInstance(DurationFieldType.eras());
   }

   public DurationField getRangeDurationField() {
      return null;
   }

   public int getMinimumValue() {
      return 1;
   }

   public int getMaximumValue() {
      return 1;
   }

   public String getAsText(int var1, Locale var2) {
      return this.iEraText;
   }

   public int getMaximumTextLength(Locale var1) {
      return this.iEraText.length();
   }
}
