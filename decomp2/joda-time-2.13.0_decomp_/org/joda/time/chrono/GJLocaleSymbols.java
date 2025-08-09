package org.joda.time.chrono;

import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.IllegalFieldValueException;

class GJLocaleSymbols {
   private static ConcurrentMap cCache = new ConcurrentHashMap();
   private final String[] iEras;
   private final String[] iDaysOfWeek;
   private final String[] iShortDaysOfWeek;
   private final String[] iMonths;
   private final String[] iShortMonths;
   private final String[] iHalfday;
   private final TreeMap iParseEras;
   private final TreeMap iParseDaysOfWeek;
   private final TreeMap iParseMonths;
   private final int iMaxEraLength;
   private final int iMaxDayOfWeekLength;
   private final int iMaxShortDayOfWeekLength;
   private final int iMaxMonthLength;
   private final int iMaxShortMonthLength;
   private final int iMaxHalfdayLength;

   static GJLocaleSymbols forLocale(Locale var0) {
      if (var0 == null) {
         var0 = Locale.getDefault();
      }

      GJLocaleSymbols var1 = (GJLocaleSymbols)cCache.get(var0);
      if (var1 == null) {
         var1 = new GJLocaleSymbols(var0);
         GJLocaleSymbols var2 = (GJLocaleSymbols)cCache.putIfAbsent(var0, var1);
         if (var2 != null) {
            var1 = var2;
         }
      }

      return var1;
   }

   private static String[] realignMonths(String[] var0) {
      String[] var1 = new String[13];

      for(int var2 = 1; var2 < 13; ++var2) {
         var1[var2] = var0[var2 - 1];
      }

      return var1;
   }

   private static String[] realignDaysOfWeek(String[] var0) {
      String[] var1 = new String[8];

      for(int var2 = 1; var2 < 8; ++var2) {
         var1[var2] = var0[var2 < 7 ? var2 + 1 : 1];
      }

      return var1;
   }

   private static void addSymbols(TreeMap var0, String[] var1, Integer[] var2) {
      int var3 = var1.length;

      while(true) {
         --var3;
         if (var3 < 0) {
            return;
         }

         String var4 = var1[var3];
         if (var4 != null) {
            var0.put(var4, var2[var3]);
         }
      }
   }

   private static void addNumerals(TreeMap var0, int var1, int var2, Integer[] var3) {
      for(int var4 = var1; var4 <= var2; ++var4) {
         var0.put(String.valueOf(var4).intern(), var3[var4]);
      }

   }

   private static int maxLength(String[] var0) {
      int var1 = 0;
      int var2 = var0.length;

      while(true) {
         --var2;
         if (var2 < 0) {
            return var1;
         }

         String var3 = var0[var2];
         if (var3 != null) {
            int var4 = var3.length();
            if (var4 > var1) {
               var1 = var4;
            }
         }
      }
   }

   private GJLocaleSymbols(Locale var1) {
      DateFormatSymbols var2 = DateTimeUtils.getDateFormatSymbols(var1);
      this.iEras = var2.getEras();
      this.iDaysOfWeek = realignDaysOfWeek(var2.getWeekdays());
      this.iShortDaysOfWeek = realignDaysOfWeek(var2.getShortWeekdays());
      this.iMonths = realignMonths(var2.getMonths());
      this.iShortMonths = realignMonths(var2.getShortMonths());
      this.iHalfday = var2.getAmPmStrings();
      Integer[] var3 = new Integer[13];

      for(int var4 = 0; var4 < 13; ++var4) {
         var3[var4] = var4;
      }

      this.iParseEras = new TreeMap(String.CASE_INSENSITIVE_ORDER);
      addSymbols(this.iParseEras, this.iEras, var3);
      if ("en".equals(var1.getLanguage())) {
         this.iParseEras.put("BCE", var3[0]);
         this.iParseEras.put("CE", var3[1]);
      }

      this.iParseDaysOfWeek = new TreeMap(String.CASE_INSENSITIVE_ORDER);
      addSymbols(this.iParseDaysOfWeek, this.iDaysOfWeek, var3);
      addSymbols(this.iParseDaysOfWeek, this.iShortDaysOfWeek, var3);
      addNumerals(this.iParseDaysOfWeek, 1, 7, var3);
      this.iParseMonths = new TreeMap(String.CASE_INSENSITIVE_ORDER);
      addSymbols(this.iParseMonths, this.iMonths, var3);
      addSymbols(this.iParseMonths, this.iShortMonths, var3);
      addNumerals(this.iParseMonths, 1, 12, var3);
      this.iMaxEraLength = maxLength(this.iEras);
      this.iMaxDayOfWeekLength = maxLength(this.iDaysOfWeek);
      this.iMaxShortDayOfWeekLength = maxLength(this.iShortDaysOfWeek);
      this.iMaxMonthLength = maxLength(this.iMonths);
      this.iMaxShortMonthLength = maxLength(this.iShortMonths);
      this.iMaxHalfdayLength = maxLength(this.iHalfday);
   }

   public String eraValueToText(int var1) {
      return this.iEras[var1];
   }

   public int eraTextToValue(String var1) {
      Integer var2 = (Integer)this.iParseEras.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalFieldValueException(DateTimeFieldType.era(), var1);
      }
   }

   public int getEraMaxTextLength() {
      return this.iMaxEraLength;
   }

   public String monthOfYearValueToText(int var1) {
      return this.iMonths[var1];
   }

   public String monthOfYearValueToShortText(int var1) {
      return this.iShortMonths[var1];
   }

   public int monthOfYearTextToValue(String var1) {
      Integer var2 = (Integer)this.iParseMonths.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalFieldValueException(DateTimeFieldType.monthOfYear(), var1);
      }
   }

   public int getMonthMaxTextLength() {
      return this.iMaxMonthLength;
   }

   public int getMonthMaxShortTextLength() {
      return this.iMaxShortMonthLength;
   }

   public String dayOfWeekValueToText(int var1) {
      return this.iDaysOfWeek[var1];
   }

   public String dayOfWeekValueToShortText(int var1) {
      return this.iShortDaysOfWeek[var1];
   }

   public int dayOfWeekTextToValue(String var1) {
      Integer var2 = (Integer)this.iParseDaysOfWeek.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalFieldValueException(DateTimeFieldType.dayOfWeek(), var1);
      }
   }

   public int getDayOfWeekMaxTextLength() {
      return this.iMaxDayOfWeekLength;
   }

   public int getDayOfWeekMaxShortTextLength() {
      return this.iMaxShortDayOfWeekLength;
   }

   public String halfdayValueToText(int var1) {
      return this.iHalfday[var1];
   }

   public int halfdayTextToValue(String var1) {
      String[] var2 = this.iHalfday;
      int var3 = var2.length;

      do {
         --var3;
         if (var3 < 0) {
            throw new IllegalFieldValueException(DateTimeFieldType.halfdayOfDay(), var1);
         }
      } while(!var2[var3].equalsIgnoreCase(var1));

      return var3;
   }

   public int getHalfdayMaxTextLength() {
      return this.iMaxHalfdayLength;
   }
}
