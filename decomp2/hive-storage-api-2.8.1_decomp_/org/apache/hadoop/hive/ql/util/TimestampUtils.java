package org.apache.hadoop.hive.ql.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveDecimalV1;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;

public class TimestampUtils {
   public static final BigDecimal BILLION_BIG_DECIMAL = BigDecimal.valueOf(1000000000L);

   public static double getDouble(Timestamp ts) {
      long seconds = millisToSeconds(ts.getTime());
      return (double)seconds + (double)ts.getNanos() / (double)1.0E9F;
   }

   public static Timestamp doubleToTimestamp(double f) {
      try {
         long seconds = (long)f;
         BigDecimal bd = new BigDecimal(String.valueOf(f));
         bd = bd.subtract(new BigDecimal(seconds)).multiply(new BigDecimal(1000000000));
         int nanos = bd.intValue();
         long millis = seconds * 1000L;
         if (nanos < 0) {
            millis -= 1000L;
            nanos += 1000000000;
         }

         Timestamp t = new Timestamp(millis);
         t.setNanos(nanos);
         return t;
      } catch (NumberFormatException var9) {
         return null;
      } catch (IllegalArgumentException var10) {
         return null;
      }
   }

   public static Timestamp decimalToTimestamp(HiveDecimal dec) {
      HiveDecimalWritable nanosWritable = new HiveDecimalWritable(dec);
      nanosWritable.mutateFractionPortion();
      nanosWritable.mutateScaleByPowerOfTen(9);
      if (nanosWritable.isSet() && nanosWritable.isInt()) {
         int nanos = nanosWritable.intValue();
         if (nanos < 0) {
            nanos += 1000000000;
         }

         nanosWritable.setFromLong((long)nanos);
         HiveDecimalWritable nanoInstant = new HiveDecimalWritable(dec);
         nanoInstant.mutateScaleByPowerOfTen(9);
         nanoInstant.mutateSubtract(nanosWritable);
         nanoInstant.mutateScaleByPowerOfTen(-9);
         if (nanoInstant.isSet() && nanoInstant.isLong()) {
            long seconds = nanoInstant.longValue();
            Timestamp t = new Timestamp(seconds * 1000L);
            t.setNanos(nanos);
            return t;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public static Timestamp decimalToTimestamp(HiveDecimalWritable decWritable, HiveDecimalWritable scratchDecWritable1, HiveDecimalWritable scratchDecWritable2) {
      scratchDecWritable1.set(decWritable);
      scratchDecWritable1.mutateFractionPortion();
      scratchDecWritable1.mutateScaleByPowerOfTen(9);
      if (scratchDecWritable1.isSet() && scratchDecWritable1.isInt()) {
         int nanos = scratchDecWritable1.intValue();
         if (nanos < 0) {
            nanos += 1000000000;
         }

         scratchDecWritable1.setFromLong((long)nanos);
         scratchDecWritable2.set(decWritable);
         scratchDecWritable2.mutateScaleByPowerOfTen(9);
         scratchDecWritable2.mutateSubtract(scratchDecWritable1);
         scratchDecWritable2.mutateScaleByPowerOfTen(-9);
         if (scratchDecWritable2.isSet() && scratchDecWritable2.isLong()) {
            long seconds = scratchDecWritable2.longValue();
            Timestamp timestamp = new Timestamp(seconds * 1000L);
            timestamp.setNanos(nanos);
            return timestamp;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public static Timestamp decimalToTimestamp(HiveDecimalV1 dec) {
      try {
         BigDecimal nanoInstant = dec.bigDecimalValue().multiply(BILLION_BIG_DECIMAL);
         int nanos = nanoInstant.remainder(BILLION_BIG_DECIMAL).intValue();
         if (nanos < 0) {
            nanos += 1000000000;
         }

         long seconds = nanoInstant.subtract(new BigDecimal(nanos)).divide(BILLION_BIG_DECIMAL).longValue();
         Timestamp t = new Timestamp(seconds * 1000L);
         t.setNanos(nanos);
         return t;
      } catch (NumberFormatException var6) {
         return null;
      } catch (IllegalArgumentException var7) {
         return null;
      }
   }

   public static long millisToSeconds(long millis) {
      return millis >= 0L ? millis / 1000L : (millis - 999L) / 1000L;
   }
}
