package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.UnsupportedDurationField;

final class GJEraDateTimeField extends BaseDateTimeField {
   private static final long serialVersionUID = 4240986525305515528L;
   private final BasicChronology iChronology;

   GJEraDateTimeField(BasicChronology var1) {
      super(DateTimeFieldType.era());
      this.iChronology = var1;
   }

   public boolean isLenient() {
      return false;
   }

   public int get(long var1) {
      return this.iChronology.getYear(var1) <= 0 ? 0 : 1;
   }

   public String getAsText(int var1, Locale var2) {
      return GJLocaleSymbols.forLocale(var2).eraValueToText(var1);
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, 0, 1);
      int var4 = this.get(var1);
      if (var4 != var3) {
         int var5 = this.iChronology.getYear(var1);
         return this.iChronology.setYear(var1, -var5);
      } else {
         return var1;
      }
   }

   public long set(long var1, String var3, Locale var4) {
      return this.set(var1, GJLocaleSymbols.forLocale(var4).eraTextToValue(var3));
   }

   public long roundFloor(long var1) {
      return this.get(var1) == 1 ? this.iChronology.setYear(0L, 1) : Long.MIN_VALUE;
   }

   public long roundCeiling(long var1) {
      return this.get(var1) == 0 ? this.iChronology.setYear(0L, 1) : Long.MAX_VALUE;
   }

   public long roundHalfFloor(long var1) {
      return this.roundFloor(var1);
   }

   public long roundHalfCeiling(long var1) {
      return this.roundFloor(var1);
   }

   public long roundHalfEven(long var1) {
      return this.roundFloor(var1);
   }

   public DurationField getDurationField() {
      return UnsupportedDurationField.getInstance(DurationFieldType.eras());
   }

   public DurationField getRangeDurationField() {
      return null;
   }

   public int getMinimumValue() {
      return 0;
   }

   public int getMaximumValue() {
      return 1;
   }

   public int getMaximumTextLength(Locale var1) {
      return GJLocaleSymbols.forLocale(var1).getEraMaxTextLength();
   }

   private Object readResolve() {
      return this.iChronology.era();
   }
}
