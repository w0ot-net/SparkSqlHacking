package org.joda.time.chrono;

import java.util.Locale;

final class GJMonthOfYearDateTimeField extends BasicMonthOfYearDateTimeField {
   private static final long serialVersionUID = -4748157875845286249L;

   GJMonthOfYearDateTimeField(BasicChronology var1) {
      super(var1, 2);
   }

   public String getAsText(int var1, Locale var2) {
      return GJLocaleSymbols.forLocale(var2).monthOfYearValueToText(var1);
   }

   public String getAsShortText(int var1, Locale var2) {
      return GJLocaleSymbols.forLocale(var2).monthOfYearValueToShortText(var1);
   }

   protected int convertText(String var1, Locale var2) {
      return GJLocaleSymbols.forLocale(var2).monthOfYearTextToValue(var1);
   }

   public int getMaximumTextLength(Locale var1) {
      return GJLocaleSymbols.forLocale(var1).getMonthMaxTextLength();
   }

   public int getMaximumShortTextLength(Locale var1) {
      return GJLocaleSymbols.forLocale(var1).getMonthMaxShortTextLength();
   }
}
