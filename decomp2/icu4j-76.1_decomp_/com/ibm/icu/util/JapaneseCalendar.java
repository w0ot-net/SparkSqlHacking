package com.ibm.icu.util;

import com.ibm.icu.impl.CalType;
import com.ibm.icu.impl.EraRules;
import java.util.Date;
import java.util.Locale;

public class JapaneseCalendar extends GregorianCalendar {
   private static final long serialVersionUID = -2977189902603704691L;
   private static final int GREGORIAN_EPOCH = 1970;
   private static final EraRules ERA_RULES;
   public static final int CURRENT_ERA;
   public static final int MEIJI;
   public static final int TAISHO;
   public static final int SHOWA;
   public static final int HEISEI;
   public static final int REIWA;

   public JapaneseCalendar() {
   }

   public JapaneseCalendar(TimeZone zone) {
      super(zone);
   }

   public JapaneseCalendar(Locale aLocale) {
      super(aLocale);
   }

   public JapaneseCalendar(ULocale locale) {
      super(locale);
   }

   public JapaneseCalendar(TimeZone zone, Locale aLocale) {
      super(zone, aLocale);
   }

   public JapaneseCalendar(TimeZone zone, ULocale locale) {
      super(zone, locale);
   }

   public JapaneseCalendar(Date date) {
      this();
      this.setTime(date);
   }

   public JapaneseCalendar(int era, int year, int month, int date) {
      super(year, month, date);
      this.set(0, era);
   }

   public JapaneseCalendar(int year, int month, int date) {
      super(year, month, date);
      this.set(0, CURRENT_ERA);
   }

   public JapaneseCalendar(int year, int month, int date, int hour, int minute, int second) {
      super(year, month, date, hour, minute, second);
      this.set(0, CURRENT_ERA);
   }

   /** @deprecated */
   @Deprecated
   public static boolean enableTentativeEra() {
      boolean includeTentativeEra = false;
      String VAR_NAME = "ICU_ENABLE_TENTATIVE_ERA";
      String eraConf = System.getProperty("ICU_ENABLE_TENTATIVE_ERA");
      if (eraConf == null) {
         eraConf = System.getenv("ICU_ENABLE_TENTATIVE_ERA");
      }

      if (eraConf != null) {
         includeTentativeEra = eraConf.equalsIgnoreCase("true");
      } else {
         String jdkEraConf = System.getProperty("jdk.calendar.japanese.supplemental.era");
         includeTentativeEra = jdkEraConf != null;
      }

      return includeTentativeEra;
   }

   protected int handleGetExtendedYear() {
      int year;
      if (this.newerField(19, 1) == 19 && this.newerField(19, 0) == 19) {
         year = this.internalGet(19, 1970);
      } else {
         year = this.internalGet(1, 1) + ERA_RULES.getStartYear(this.internalGet(0, CURRENT_ERA)) - 1;
      }

      return year;
   }

   protected int getDefaultMonthInYear(int extendedYear) {
      int era = this.internalGet(0, CURRENT_ERA);
      int[] eraStart = ERA_RULES.getStartDate(era, (int[])null);
      return extendedYear == eraStart[0] ? eraStart[1] - 1 : super.getDefaultMonthInYear(extendedYear);
   }

   protected int getDefaultDayInMonth(int extendedYear, int month) {
      int era = this.internalGet(0, CURRENT_ERA);
      int[] eraStart = ERA_RULES.getStartDate(era, (int[])null);
      return extendedYear == eraStart[0] && month == eraStart[1] - 1 ? eraStart[2] : super.getDefaultDayInMonth(extendedYear, month);
   }

   protected void handleComputeFields(int julianDay) {
      super.handleComputeFields(julianDay);
      int year = this.internalGet(19);
      int eraIdx = ERA_RULES.getEraIndex(year, this.internalGet(2) + 1, this.internalGet(5));
      this.internalSet(0, eraIdx);
      this.internalSet(1, year - ERA_RULES.getStartYear(eraIdx) + 1);
   }

   protected int handleGetLimit(int field, int limitType) {
      switch (field) {
         case 0:
            if (limitType != 0 && limitType != 1) {
               return ERA_RULES.getNumberOfEras() - 1;
            }

            return 0;
         case 1:
            switch (limitType) {
               case 0:
               case 1:
                  return 1;
               case 2:
                  return 1;
               case 3:
                  return super.handleGetLimit(field, 3) - ERA_RULES.getStartYear(CURRENT_ERA);
            }
         default:
            return super.handleGetLimit(field, limitType);
      }
   }

   public String getType() {
      return "japanese";
   }

   /** @deprecated */
   @Deprecated
   public boolean haveDefaultCentury() {
      return false;
   }

   public int getActualMaximum(int field) {
      if (field == 1) {
         int era = this.get(0);
         if (era == ERA_RULES.getNumberOfEras() - 1) {
            return this.handleGetLimit(1, 3);
         } else {
            int[] nextEraStart = ERA_RULES.getStartDate(era + 1, (int[])null);
            int nextEraYear = nextEraStart[0];
            int nextEraMonth = nextEraStart[1];
            int nextEraDate = nextEraStart[2];
            int maxYear = nextEraYear - ERA_RULES.getStartYear(era) + 1;
            if (nextEraMonth == 1 && nextEraDate == 1) {
               --maxYear;
            }

            return maxYear;
         }
      } else {
         return super.getActualMaximum(field);
      }
   }

   /** @deprecated */
   @Deprecated
   protected boolean isEra0CountingBackward() {
      return false;
   }

   static {
      ERA_RULES = EraRules.getInstance(CalType.JAPANESE, enableTentativeEra());
      MEIJI = 232;
      TAISHO = 233;
      SHOWA = 234;
      HEISEI = 235;
      REIWA = 236;
      CURRENT_ERA = ERA_RULES.getCurrentEraIndex();
   }
}
