package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.PreciseDurationDateTimeField;

final class GJDayOfWeekDateTimeField extends PreciseDurationDateTimeField {
   private static final long serialVersionUID = -3857947176719041436L;
   private final BasicChronology iChronology;

   GJDayOfWeekDateTimeField(BasicChronology var1, DurationField var2) {
      super(DateTimeFieldType.dayOfWeek(), var2);
      this.iChronology = var1;
   }

   public int get(long var1) {
      return this.iChronology.getDayOfWeek(var1);
   }

   public String getAsText(int var1, Locale var2) {
      return GJLocaleSymbols.forLocale(var2).dayOfWeekValueToText(var1);
   }

   public String getAsShortText(int var1, Locale var2) {
      return GJLocaleSymbols.forLocale(var2).dayOfWeekValueToShortText(var1);
   }

   protected int convertText(String var1, Locale var2) {
      return GJLocaleSymbols.forLocale(var2).dayOfWeekTextToValue(var1);
   }

   public DurationField getRangeDurationField() {
      return this.iChronology.weeks();
   }

   public int getMinimumValue() {
      return 1;
   }

   public int getMaximumValue() {
      return 7;
   }

   public int getMaximumTextLength(Locale var1) {
      return GJLocaleSymbols.forLocale(var1).getDayOfWeekMaxTextLength();
   }

   public int getMaximumShortTextLength(Locale var1) {
      return GJLocaleSymbols.forLocale(var1).getDayOfWeekMaxShortTextLength();
   }

   private Object readResolve() {
      return this.iChronology.dayOfWeek();
   }
}
