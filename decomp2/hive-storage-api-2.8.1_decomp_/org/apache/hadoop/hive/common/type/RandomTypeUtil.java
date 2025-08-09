package org.apache.hadoop.hive.common.type;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomTypeUtil {
   private static final String DECIMAL_CHARS = "0123456789";
   public static final long NANOSECONDS_PER_SECOND;
   public static final long MILLISECONDS_PER_SECOND;
   public static final long NANOSECONDS_PER_MILLISSECOND;
   private static final ThreadLocal DATE_FORMAT;
   public static final int MIN_YEAR = 1900;
   public static final int MAX_YEAR = 3000;
   private static final long MIN_FOUR_DIGIT_YEAR_MILLIS;
   private static final long MAX_FOUR_DIGIT_YEAR_MILLIS;

   public static String getRandString(Random r) {
      return getRandString(r, (String)null, r.nextInt(10));
   }

   public static String getRandString(Random r, String characters, int length) {
      if (characters == null) {
         characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      }

      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < length; ++i) {
         sb.append(characters.charAt(r.nextInt(characters.length())));
      }

      return sb.toString();
   }

   public static byte[] getRandBinary(Random r, int len) {
      byte[] bytes = new byte[len];

      for(int j = 0; j < len; ++j) {
         bytes[j] = Byte.valueOf((byte)r.nextInt());
      }

      return bytes;
   }

   public static HiveDecimal getRandHiveDecimal(Random r) {
      StringBuilder sb = new StringBuilder();
      int precision = 1 + r.nextInt(18);
      int scale = 0 + r.nextInt(precision + 1);
      int integerDigits = precision - scale;
      if (r.nextBoolean()) {
         sb.append("-");
      }

      if (integerDigits == 0) {
         sb.append("0");
      } else {
         sb.append(getRandString(r, "0123456789", integerDigits));
      }

      if (scale != 0) {
         sb.append(".");
         sb.append(getRandString(r, "0123456789", scale));
      }

      return HiveDecimal.create(sb.toString());
   }

   public static Date getRandDate(Random r) {
      String dateStr = String.format("%d-%02d-%02d", 1800 + r.nextInt(500), 1 + r.nextInt(12), 1 + r.nextInt(28));
      Date dateVal = Date.valueOf(dateStr);
      return dateVal;
   }

   private static long parseToMillis(String s) {
      try {
         return ((DateFormat)DATE_FORMAT.get()).parse(s).getTime();
      } catch (ParseException ex) {
         throw new RuntimeException(ex);
      }
   }

   public static Timestamp getRandTimestamp(Random r) {
      return getRandTimestamp(r, 1900, 3000);
   }

   public static Timestamp getRandTimestamp(Random r, int minYear, int maxYear) {
      String optionalNanos = "";
      switch (r.nextInt(4)) {
         case 1:
            optionalNanos = String.format(".%09d", r.nextInt((int)NANOSECONDS_PER_SECOND));
            break;
         case 2:
            optionalNanos = String.format(".%09d", (long)Integer.valueOf(r.nextInt((int)MILLISECONDS_PER_SECOND)) * NANOSECONDS_PER_MILLISSECOND);
            break;
         case 3:
            optionalNanos = String.format(".%09d", r.nextInt((int)NANOSECONDS_PER_MILLISSECOND));
      }

      String timestampStr = String.format("%04d-%02d-%02d %02d:%02d:%02d%s", minYear + r.nextInt(maxYear - minYear + 1), 1 + r.nextInt(12), 1 + r.nextInt(28), 0 + r.nextInt(24), 0 + r.nextInt(60), 0 + r.nextInt(60), optionalNanos);

      try {
         Timestamp timestampVal = Timestamp.valueOf(timestampStr);
         return timestampVal;
      } catch (Exception e) {
         System.err.println("Timestamp string " + timestampStr + " did not parse");
         throw e;
      }
   }

   public static long randomMillis(long minMillis, long maxMillis, Random rand) {
      return minMillis + (long)((double)(maxMillis - minMillis) * rand.nextDouble());
   }

   public static long randomMillis(Random rand) {
      return randomMillis(MIN_FOUR_DIGIT_YEAR_MILLIS, MAX_FOUR_DIGIT_YEAR_MILLIS, rand);
   }

   public static int randomNanos(Random rand, int decimalDigits) {
      int nanos = rand.nextInt((int)NANOSECONDS_PER_SECOND);
      return nanos - nanos % (int)Math.pow((double)10.0F, (double)(9 - decimalDigits));
   }

   public static int randomNanos(Random rand) {
      return randomNanos(rand, 9);
   }

   static {
      NANOSECONDS_PER_SECOND = TimeUnit.SECONDS.toNanos(1L);
      MILLISECONDS_PER_SECOND = TimeUnit.SECONDS.toMillis(1L);
      NANOSECONDS_PER_MILLISSECOND = TimeUnit.MILLISECONDS.toNanos(1L);
      DATE_FORMAT = new ThreadLocal() {
         protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         }
      };
      MIN_FOUR_DIGIT_YEAR_MILLIS = parseToMillis("1900-01-01 00:00:00");
      MAX_FOUR_DIGIT_YEAR_MILLIS = parseToMillis("3000-01-01 00:00:00");
   }
}
