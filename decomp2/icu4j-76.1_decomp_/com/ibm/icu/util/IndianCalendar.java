package com.ibm.icu.util;

import java.util.Date;
import java.util.Locale;

public class IndianCalendar extends Calendar {
   private static final long serialVersionUID = 3617859668165014834L;
   public static final int CHAITRA = 0;
   public static final int VAISAKHA = 1;
   public static final int JYAISTHA = 2;
   public static final int ASADHA = 3;
   public static final int SRAVANA = 4;
   public static final int BHADRA = 5;
   public static final int ASVINA = 6;
   public static final int KARTIKA = 7;
   public static final int AGRAHAYANA = 8;
   public static final int PAUSA = 9;
   public static final int MAGHA = 10;
   public static final int PHALGUNA = 11;
   public static final int IE = 0;
   private static final int INDIAN_ERA_START = 78;
   private static final int INDIAN_YEAR_START = 80;
   private static final int[][] LIMITS = new int[][]{{0, 0, 0, 0}, {-5000000, -5000000, 5000000, 5000000}, {0, 0, 11, 11}, {1, 1, 52, 53}, new int[0], {1, 1, 30, 31}, {1, 1, 365, 366}, new int[0], {-1, -1, 5, 5}, new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], {-5000000, -5000000, 5000000, 5000000}, new int[0], {-5000000, -5000000, 5000000, 5000000}, new int[0], new int[0], new int[0], {0, 0, 11, 11}};

   public IndianCalendar() {
      this(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
   }

   public IndianCalendar(TimeZone zone) {
      this(zone, ULocale.getDefault(ULocale.Category.FORMAT));
   }

   public IndianCalendar(Locale aLocale) {
      this(TimeZone.forLocaleOrDefault(aLocale), aLocale);
   }

   public IndianCalendar(ULocale locale) {
      this(TimeZone.forULocaleOrDefault(locale), locale);
   }

   public IndianCalendar(TimeZone zone, Locale aLocale) {
      super(zone, aLocale);
      this.setTimeInMillis(System.currentTimeMillis());
   }

   public IndianCalendar(TimeZone zone, ULocale locale) {
      super(zone, locale);
      this.setTimeInMillis(System.currentTimeMillis());
   }

   public IndianCalendar(Date date) {
      super(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
      this.setTime(date);
   }

   public IndianCalendar(int year, int month, int date) {
      super(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
      this.set(1, year);
      this.set(2, month);
      this.set(5, date);
   }

   public IndianCalendar(int year, int month, int date, int hour, int minute, int second) {
      super(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
      this.set(1, year);
      this.set(2, month);
      this.set(5, date);
      this.set(11, hour);
      this.set(12, minute);
      this.set(13, second);
   }

   protected int handleGetExtendedYear() {
      int year;
      if (this.newerField(19, 1) == 19) {
         year = this.internalGet(19, 1);
      } else {
         year = this.internalGet(1, 1);
      }

      return year;
   }

   protected int handleGetYearLength(int extendedYear) {
      return super.handleGetYearLength(extendedYear);
   }

   protected int handleGetMonthLength(int extendedYear, int month) {
      if (month < 0 || month > 11) {
         int[] remainder = new int[1];
         extendedYear += floorDivide(month, 12, remainder);
         month = remainder[0];
      }

      if (isGregorianLeapYear(extendedYear + 78) && month == 0) {
         return 31;
      } else {
         return month >= 1 && month <= 5 ? 31 : 30;
      }
   }

   protected void handleComputeFields(int julianDay) {
      this.computeGregorianFields(julianDay);
      int gregorianYear = this.getGregorianYear();
      int IndianYear = gregorianYear - 78;
      double jdAtStartOfGregYear = this.gregorianToJD(gregorianYear, 0, 1);
      int yday = (int)((double)julianDay - jdAtStartOfGregYear);
      int leapMonth;
      if (yday < 80) {
         --IndianYear;
         leapMonth = isGregorianLeapYear(gregorianYear - 1) ? 31 : 30;
         yday += leapMonth + 155 + 90 + 10;
      } else {
         leapMonth = isGregorianLeapYear(gregorianYear) ? 31 : 30;
         yday -= 80;
      }

      int IndianMonth;
      int IndianDayOfMonth;
      if (yday < leapMonth) {
         IndianMonth = 0;
         IndianDayOfMonth = yday + 1;
      } else {
         int mday = yday - leapMonth;
         if (mday < 155) {
            IndianMonth = mday / 31 + 1;
            IndianDayOfMonth = mday % 31 + 1;
         } else {
            mday -= 155;
            IndianMonth = mday / 30 + 6;
            IndianDayOfMonth = mday % 30 + 1;
         }
      }

      this.internalSet(0, 0);
      this.internalSet(19, IndianYear);
      this.internalSet(1, IndianYear);
      this.internalSet(2, IndianMonth);
      this.internalSet(23, IndianMonth);
      this.internalSet(5, IndianDayOfMonth);
      this.internalSet(6, yday + 1);
   }

   protected int handleGetLimit(int field, int limitType) {
      return LIMITS[field][limitType];
   }

   protected int handleComputeMonthStart(int year, int month, boolean useMonth) {
      if (month < 0 || month > 11) {
         year += month / 12;
         month %= 12;
      }

      int imonth = month + 1;
      double jd = this.IndianToJD(year, imonth, 1);
      return (int)jd;
   }

   private double IndianToJD(int year, int month, int date) {
      int gyear = year + 78;
      int leapMonth;
      double start;
      if (isGregorianLeapYear(gyear)) {
         leapMonth = 31;
         start = this.gregorianToJD(gyear, 2, 21);
      } else {
         leapMonth = 30;
         start = this.gregorianToJD(gyear, 2, 22);
      }

      double jd;
      if (month == 1) {
         jd = start + (double)(date - 1);
      } else {
         jd = start + (double)leapMonth;
         int m = month - 2;
         m = Math.min(m, 5);
         jd += (double)(m * 31);
         if (month >= 8) {
            m = month - 7;
            jd += (double)(m * 30);
         }

         jd += (double)(date - 1);
      }

      return jd;
   }

   private double gregorianToJD(int year, int month, int date) {
      return (double)(this.computeGregorianMonthStart(year, month) + date) - (double)0.5F;
   }

   public String getType() {
      return "indian";
   }
}
