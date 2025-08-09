package com.ibm.icu.util;

import com.ibm.icu.impl.CalendarAstronomer;
import com.ibm.icu.impl.CalendarCache;
import com.ibm.icu.impl.CalendarUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.Locale;
import java.util.function.IntConsumer;

public class IslamicCalendar extends Calendar {
   private static final long serialVersionUID = -6253365474073869325L;
   public static final int MUHARRAM = 0;
   public static final int SAFAR = 1;
   public static final int RABI_1 = 2;
   public static final int RABI_2 = 3;
   public static final int JUMADA_1 = 4;
   public static final int JUMADA_2 = 5;
   public static final int RAJAB = 6;
   public static final int SHABAN = 7;
   public static final int RAMADAN = 8;
   public static final int SHAWWAL = 9;
   public static final int DHU_AL_QIDAH = 10;
   public static final int DHU_AL_HIJJAH = 11;
   private static final long HIJRA_MILLIS = -42521587200000L;
   private static final long CIVIL_EPOCH = 1948440L;
   private static final long ASTRONOMICAL_EPOCH = 1948439L;
   private static Algorithm ISLAMIC_ALGORITHM = new IslamicAlgorithm();
   private static Algorithm CIVIL_ALGORITHM = new CivilAlgorithm();
   private static Algorithm TBLA_ALGORITHM = new TBLAAlgorithm();
   private static Algorithm UMALQURA_ALGORITHM = new UmalquraAlgorithm();
   private static final int[][] LIMITS = new int[][]{{0, 0, 0, 0}, {1, 1, 5000000, 5000000}, {0, 0, 11, 11}, {1, 1, 50, 51}, new int[0], {1, 1, 29, 30}, {1, 1, 354, 355}, new int[0], {-1, -1, 5, 5}, new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], {1, 1, 5000000, 5000000}, new int[0], {1, 1, 5000000, 5000000}, new int[0], new int[0], new int[0], {0, 0, 11, 11}};
   private static final int[] UMALQURA_MONTHLENGTH = new int[]{2730, 3412, 3785, 1748, 1770, 876, 2733, 1365, 1705, 1938, 2985, 1492, 2778, 1372, 3373, 1685, 1866, 2900, 2922, 1453, 1198, 2639, 1303, 1675, 1701, 2773, 726, 2395, 1181, 2637, 3366, 3477, 1452, 2486, 698, 2651, 1323, 2709, 1738, 2793, 756, 2422, 694, 2390, 2762, 2980, 3026, 1497, 732, 2413, 1357, 2725, 2898, 2981, 1460, 2486, 1367, 663, 1355, 1699, 1874, 2917, 1386, 2731, 1323, 3221, 3402, 3493, 1482, 2774, 2391, 1195, 2379, 2725, 2898, 2922, 1397, 630, 2231, 1115, 1365, 1449, 1460, 2522, 1245, 622, 2358, 2730, 3412, 3506, 1493, 730, 2395, 1195, 2645, 2889, 2916, 2929, 1460, 2741, 2645, 3365, 3730, 3785, 1748, 2793, 2411, 1195, 2707, 3401, 3492, 3506, 2745, 1210, 2651, 1323, 2709, 2858, 2901, 1372, 1213, 573, 2333, 2709, 2890, 2906, 1389, 694, 2363, 1179, 1621, 1705, 1876, 2922, 1388, 2733, 1365, 2857, 2962, 2985, 1492, 2778, 1370, 2731, 1429, 1865, 1892, 2986, 1461, 694, 2646, 3661, 2853, 2898, 2922, 1453, 686, 2351, 1175, 1611, 1701, 1708, 2774, 1373, 1181, 2637, 3350, 3477, 1450, 1461, 730, 2395, 1197, 1429, 1738, 1764, 2794, 1269, 694, 2390, 2730, 2900, 3026, 1497, 746, 2413, 1197, 2709, 2890, 2981, 1458, 2485, 1238, 2711, 1351, 1683, 1865, 2901, 1386, 2667, 1323, 2699, 3398, 3491, 1482, 2774, 1243, 619, 2379, 2725, 2898, 2921, 1397, 374, 2231, 603, 1323, 1381, 1460, 2522, 1261, 365, 2230, 2726, 3410, 3497, 1492, 2778, 2395, 1195, 1619, 1833, 1890, 2985, 1458, 2741, 1365, 2853, 3474, 3785, 1746, 2793, 1387, 1195, 2645, 3369, 3412, 3498, 2485, 1210, 2619, 1179, 2637, 2730, 2773, 730, 2397, 1118, 2606, 3226, 3413, 1714, 1721, 1210, 2653, 1325, 2709, 2898, 2984, 2996, 1465, 730, 2394, 2890, 3492, 3793, 1768, 2922, 1389, 1333, 1685, 3402, 3496, 3540, 1754, 1371, 669, 1579, 2837, 2890, 2965, 1450, 2734, 2350, 3215, 1319, 1685, 1706, 2774, 1373, 669};
   private static final int UMALQURA_YEAR_START = 1300;
   private static final int UMALQURA_YEAR_END = 1600;
   private static final byte[] UMALQURA_YEAR_START_ESTIMATE_FIX = new byte[]{0, 0, -1, 0, -1, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, -1, -1, 0, 0, 0, 1, 0, 0, -1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 1, 1, 0, 0, -1, 0, 1, 0, 1, 1, 0, 0, -1, 0, 1, 0, 0, 0, -1, 0, 1, 0, 1, 0, 0, 0, -1, 0, 0, 0, 0, -1, -1, 0, -1, 0, 1, 0, 0, 0, -1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, -1, -1, 0, 0, 0, 1, 0, 0, -1, -1, 0, -1, 0, 0, -1, -1, 0, -1, 0, -1, 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, -1, 0, 1, 0, 1, 1, 0, 0, -1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, -1, 0, 1, 0, 0, -1, -1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, -1, 0, 0, 0, 1, 1, 0, 0, -1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, -1, 0, 0, 0, 1, 0, 0, 0, -1, 0, 0, 0, 0, 0, -1, 0, -1, 0, 1, 0, 0, 0, -1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, -1, 0, 0, 0, 0, 1, 0, 0, 0, -1, 0, 0, 0, 0, -1, -1, 0, -1, 0, 1, 0, 0, -1, -1, 0, 0, 1, 1, 0, 0, -1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};
   private static CalendarCache cache = new CalendarCache();
   private boolean civil;
   private CalculationType cType;
   private transient Algorithm algorithm;

   public IslamicCalendar() {
      this(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
   }

   public IslamicCalendar(TimeZone zone) {
      this(zone, ULocale.getDefault(ULocale.Category.FORMAT));
   }

   public IslamicCalendar(Locale aLocale) {
      this(TimeZone.forLocaleOrDefault(aLocale), aLocale);
   }

   public IslamicCalendar(ULocale locale) {
      this(TimeZone.forULocaleOrDefault(locale), locale);
   }

   public IslamicCalendar(TimeZone zone, Locale aLocale) {
      this(zone, ULocale.forLocale(aLocale));
   }

   public IslamicCalendar(TimeZone zone, ULocale locale) {
      super(zone, locale);
      this.civil = true;
      this.cType = IslamicCalendar.CalculationType.ISLAMIC_CIVIL;
      this.algorithm = CIVIL_ALGORITHM;
      this.setCalcTypeForLocale(locale);
      this.setTimeInMillis(System.currentTimeMillis());
   }

   public IslamicCalendar(Date date) {
      super(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
      this.civil = true;
      this.cType = IslamicCalendar.CalculationType.ISLAMIC_CIVIL;
      this.algorithm = CIVIL_ALGORITHM;
      this.setTime(date);
   }

   public IslamicCalendar(int year, int month, int date) {
      super(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
      this.civil = true;
      this.cType = IslamicCalendar.CalculationType.ISLAMIC_CIVIL;
      this.algorithm = CIVIL_ALGORITHM;
      this.set(1, year);
      this.set(2, month);
      this.set(5, date);
   }

   public IslamicCalendar(int year, int month, int date, int hour, int minute, int second) {
      super(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
      this.civil = true;
      this.cType = IslamicCalendar.CalculationType.ISLAMIC_CIVIL;
      this.algorithm = CIVIL_ALGORITHM;
      this.set(1, year);
      this.set(2, month);
      this.set(5, date);
      this.set(11, hour);
      this.set(12, minute);
      this.set(13, second);
   }

   public void setCivil(boolean beCivil) {
      if (beCivil && this.cType != IslamicCalendar.CalculationType.ISLAMIC_CIVIL) {
         long m = this.getTimeInMillis();
         this.cType = IslamicCalendar.CalculationType.ISLAMIC_CIVIL;
         this.algorithm = CIVIL_ALGORITHM;
         this.clear();
         this.setTimeInMillis(m);
      } else if (!beCivil && this.cType != IslamicCalendar.CalculationType.ISLAMIC) {
         long m = this.getTimeInMillis();
         this.cType = IslamicCalendar.CalculationType.ISLAMIC;
         this.algorithm = ISLAMIC_ALGORITHM;
         this.clear();
         this.setTimeInMillis(m);
      }

      this.civil = this.algorithm.isCivil();
   }

   public boolean isCivil() {
      return this.algorithm.isCivil();
   }

   protected int handleGetLimit(int field, int limitType) {
      return LIMITS[field][limitType];
   }

   private static final boolean civilLeapYear(int year) {
      return (14 + 11 * year) % 30 < 11;
   }

   private long yearStart(int year) {
      return this.algorithm.yearStart(year);
   }

   private static final long trueMonthStart(long month) {
      long start = cache.get(month);
      if (start == CalendarCache.EMPTY) {
         long origin = -42521587200000L + (long)Math.floor((double)month * 29.530588853) * 86400000L;
         double age = moonAge(origin);
         if (moonAge(origin) >= (double)0.0F) {
            do {
               origin -= 86400000L;
               age = moonAge(origin);
            } while(age >= (double)0.0F);
         } else {
            do {
               origin += 86400000L;
               age = moonAge(origin);
            } while(age < (double)0.0F);
         }

         start = (origin - -42521587200000L) / 86400000L + 1L;
         cache.put(month, start);
      }

      return start;
   }

   static final double moonAge(long time) {
      double age = (new CalendarAstronomer(time)).getMoonAge();
      age = age * (double)180.0F / Math.PI;
      if (age > (double)180.0F) {
         age -= (double)360.0F;
      }

      return age;
   }

   protected int handleGetMonthLength(int extendedYear, int month) {
      return this.algorithm.monthLength(extendedYear, month);
   }

   protected int handleGetYearLength(int extendedYear) {
      return this.algorithm.yearLength(extendedYear);
   }

   protected int handleComputeMonthStart(int eyear, int month, boolean useMonth) {
      return (int)(this.algorithm.monthStart(eyear, month) + this.algorithm.epoch() - 1L);
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

   protected void handleComputeFields(int julianDay) {
      this.algorithm.compute((long)julianDay, this.internalGetTimeInMillis(), (year) -> {
         this.internalSet(0, 0);
         this.internalSet(1, year);
         this.internalSet(19, year);
      }, (month) -> {
         this.internalSet(2, month);
         this.internalSet(23, month);
      }, (dayOfMonth) -> this.internalSet(5, dayOfMonth), (dayOfYear) -> this.internalSet(6, dayOfYear));
   }

   public void setCalculationType(CalculationType type) {
      this.cType = type;
      switch (this.cType) {
         case ISLAMIC_UMALQURA:
            this.algorithm = UMALQURA_ALGORITHM;
            break;
         case ISLAMIC:
            this.algorithm = ISLAMIC_ALGORITHM;
            break;
         case ISLAMIC_TBLA:
            this.algorithm = TBLA_ALGORITHM;
            break;
         case ISLAMIC_CIVIL:
         default:
            this.algorithm = CIVIL_ALGORITHM;
      }

      this.civil = this.algorithm.isCivil();
   }

   public CalculationType getCalculationType() {
      return this.algorithm.getType();
   }

   private void setCalcTypeForLocale(ULocale locale) {
      String localeCalType = CalendarUtil.getCalendarType(locale);
      if ("islamic-civil".equals(localeCalType)) {
         this.setCalculationType(IslamicCalendar.CalculationType.ISLAMIC_CIVIL);
      } else if ("islamic-umalqura".equals(localeCalType)) {
         this.setCalculationType(IslamicCalendar.CalculationType.ISLAMIC_UMALQURA);
      } else if ("islamic-tbla".equals(localeCalType)) {
         this.setCalculationType(IslamicCalendar.CalculationType.ISLAMIC_TBLA);
      } else if (localeCalType.startsWith("islamic")) {
         this.setCalculationType(IslamicCalendar.CalculationType.ISLAMIC);
      } else {
         this.setCalculationType(IslamicCalendar.CalculationType.ISLAMIC_CIVIL);
      }

   }

   public String getType() {
      return this.algorithm.getType().bcpType();
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      if (this.cType == null) {
         this.cType = this.civil ? IslamicCalendar.CalculationType.ISLAMIC_CIVIL : IslamicCalendar.CalculationType.ISLAMIC;
      }

      this.setCalculationType(this.cType);
   }

   public boolean inTemporalLeapYear() {
      return this.getActualMaximum(6) == 355;
   }

   private static class IslamicAlgorithm implements Algorithm {
      private IslamicAlgorithm() {
      }

      public boolean isCivil() {
         return false;
      }

      public CalculationType getType() {
         return IslamicCalendar.CalculationType.ISLAMIC;
      }

      public long epoch() {
         return 1948440L;
      }

      public long yearStart(int year) {
         return this.monthStart(year, 0);
      }

      public long monthStart(int year, int month) {
         return IslamicCalendar.trueMonthStart((long)(12 * (year + month / 12 - 1) + month % 12));
      }

      public int monthLength(int year, int month) {
         month += 12 * (year - 1);
         return (int)(IslamicCalendar.trueMonthStart((long)(month + 1)) - IslamicCalendar.trueMonthStart((long)month));
      }

      public int yearLength(int year) {
         int month = 12 * (year - 1);
         return (int)(IslamicCalendar.trueMonthStart((long)(month + 12)) - IslamicCalendar.trueMonthStart((long)month));
      }

      public void compute(long julianDays, long current, IntConsumer yearConsumer, IntConsumer monthConsumer, IntConsumer dayOfMonthConsumer, IntConsumer dayOfYearConsumer) {
         long days = julianDays - this.epoch();
         int month = (int)Math.floor((double)days / 29.530588853);
         long monthStart = (long)Math.floor((double)month * 29.530588853 - (double)1.0F);
         if (days - monthStart >= 25L && IslamicCalendar.moonAge(current) > (double)0.0F) {
            ++month;
         }

         while(IslamicCalendar.trueMonthStart((long)month) > days) {
            --month;
         }

         int year = month >= 0 ? month / 12 + 1 : (month + 1) / 12;
         month = (month % 12 + 12) % 12;
         yearConsumer.accept(year);
         monthConsumer.accept(month);
         dayOfMonthConsumer.accept((int)(days - this.monthStart(year, month)) + 1);
         dayOfYearConsumer.accept((int)(days - this.yearStart(year) + 1L));
      }
   }

   private static class CivilAlgorithm implements Algorithm {
      private CivilAlgorithm() {
      }

      public boolean isCivil() {
         return true;
      }

      public CalculationType getType() {
         return IslamicCalendar.CalculationType.ISLAMIC_CIVIL;
      }

      public long epoch() {
         return 1948440L;
      }

      public long yearStart(int year) {
         return (long)((year - 1) * 354) + (long)Math.floor((double)(3 + 11 * year) / (double)30.0F);
      }

      public long monthStart(int year, int month) {
         return (long)Math.ceil((double)29.5F * (double)(month % 12)) + this.yearStart(year + month / 12);
      }

      public int monthLength(int year, int month) {
         int length = 29;
         if (month % 2 == 0) {
            ++length;
         }

         if (month == 11 && IslamicCalendar.civilLeapYear(year)) {
            ++length;
         }

         return length;
      }

      public int yearLength(int year) {
         return 354 + (IslamicCalendar.civilLeapYear(year) ? 1 : 0);
      }

      public void compute(long julianDays, long current, IntConsumer yearConsumer, IntConsumer monthConsumer, IntConsumer dayOfMonthConsumer, IntConsumer dayOfYearConsumer) {
         long days = julianDays - this.epoch();
         int year = (int)Math.floor((double)(30L * days + 10646L) / (double)10631.0F);
         int month = (int)Math.ceil((double)(days - 29L - this.yearStart(year)) / (double)29.5F);
         month = Math.min(month, 11);
         yearConsumer.accept(year);
         monthConsumer.accept(month);
         dayOfMonthConsumer.accept((int)(days - this.monthStart(year, month)) + 1);
         dayOfYearConsumer.accept((int)(days - this.yearStart(year) + 1L));
      }
   }

   private static class TBLAAlgorithm extends CivilAlgorithm {
      private TBLAAlgorithm() {
      }

      public boolean isCivil() {
         return false;
      }

      public CalculationType getType() {
         return IslamicCalendar.CalculationType.ISLAMIC_TBLA;
      }

      public long epoch() {
         return 1948439L;
      }
   }

   private static class UmalquraAlgorithm implements Algorithm {
      private UmalquraAlgorithm() {
      }

      public boolean isCivil() {
         return false;
      }

      public CalculationType getType() {
         return IslamicCalendar.CalculationType.ISLAMIC_UMALQURA;
      }

      public long epoch() {
         return 1948440L;
      }

      public long yearStart(int year) {
         if (year >= 1300 && year <= 1600) {
            int index = year - 1300;
            int yrStartLinearEstimate = (int)(354.3672 * (double)index + 460322.05 + (double)0.5F);
            return (long)(yrStartLinearEstimate + IslamicCalendar.UMALQURA_YEAR_START_ESTIMATE_FIX[index]);
         } else {
            return IslamicCalendar.CIVIL_ALGORITHM.yearStart(year);
         }
      }

      public long monthStart(int year, int month) {
         year += month / 12;
         month %= 12;
         if (year < 1300) {
            return IslamicCalendar.CIVIL_ALGORITHM.monthStart(year, month);
         } else {
            long ms = this.yearStart(year);

            for(int i = 0; i < month; ++i) {
               ms += (long)this.monthLength(year, i);
            }

            return ms;
         }
      }

      public int monthLength(int year, int month) {
         if (year >= 1300 && year <= 1600) {
            int index = year - 1300;
            int mask = 1 << 11 - month;
            return (IslamicCalendar.UMALQURA_MONTHLENGTH[index] & mask) != 0 ? 30 : 29;
         } else {
            return IslamicCalendar.CIVIL_ALGORITHM.monthLength(year, month);
         }
      }

      public int yearLength(int year) {
         if (year >= 1300 && year <= 1600) {
            int length = 0;

            for(int i = 0; i < 12; ++i) {
               length += this.monthLength(year, i);
            }

            return length;
         } else {
            return IslamicCalendar.CIVIL_ALGORITHM.yearLength(year);
         }
      }

      public void compute(long julianDays, long current, IntConsumer yearConsumer, IntConsumer monthConsumer, IntConsumer dayOfMonthConsumer, IntConsumer dayOfYearConsumer) {
         long days = julianDays - this.epoch();
         if (days < this.yearStart(1300)) {
            IslamicCalendar.CIVIL_ALGORITHM.compute(julianDays, current, yearConsumer, monthConsumer, dayOfMonthConsumer, dayOfYearConsumer);
         } else {
            int year = (int)(((double)days - 460322.55) / 354.3672) + 1300 - 1;
            int month = 0;
            long d = 1L;

            while(d > 0L) {
               ++year;
               d = days - this.yearStart(year) + 1L;
               int yearLength = this.yearLength(year);
               if (d == (long)yearLength) {
                  month = 11;
                  break;
               }

               if (d < (long)yearLength) {
                  int monthLen = this.monthLength(year, month);

                  for(month = 0; d > (long)monthLen; monthLen = this.monthLength(year, month)) {
                     d -= (long)monthLen;
                     ++month;
                  }
                  break;
               }
            }

            yearConsumer.accept(year);
            monthConsumer.accept(month);
            dayOfMonthConsumer.accept((int)(days - this.monthStart(year, month)) + 1);
            dayOfYearConsumer.accept((int)(days - this.yearStart(year) + 1L));
         }
      }
   }

   public static enum CalculationType {
      ISLAMIC("islamic"),
      ISLAMIC_CIVIL("islamic-civil"),
      ISLAMIC_UMALQURA("islamic-umalqura"),
      ISLAMIC_TBLA("islamic-tbla");

      private String bcpType;

      private CalculationType(String bcpType) {
         this.bcpType = bcpType;
      }

      String bcpType() {
         return this.bcpType;
      }
   }

   private interface Algorithm {
      boolean isCivil();

      CalculationType getType();

      long epoch();

      long yearStart(int var1);

      long monthStart(int var1, int var2);

      int monthLength(int var1, int var2);

      int yearLength(int var1);

      void compute(long var1, long var3, IntConsumer var5, IntConsumer var6, IntConsumer var7, IntConsumer var8);
   }
}
