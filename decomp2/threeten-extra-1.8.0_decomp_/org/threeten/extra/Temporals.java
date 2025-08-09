package org.threeten.extra;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParsePosition;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class Temporals {
   private Temporals() {
   }

   public static TemporalAdjuster nextWorkingDay() {
      return Temporals.Adjuster.NEXT_WORKING;
   }

   public static TemporalAdjuster nextWorkingDayOrSame() {
      return Temporals.Adjuster.NEXT_WORKING_OR_SAME;
   }

   public static TemporalAdjuster previousWorkingDay() {
      return Temporals.Adjuster.PREVIOUS_WORKING;
   }

   public static TemporalAdjuster previousWorkingDayOrSame() {
      return Temporals.Adjuster.PREVIOUS_WORKING_OR_SAME;
   }

   public static Object parseFirstMatching(CharSequence text, TemporalQuery query, DateTimeFormatter... formatters) {
      Objects.requireNonNull(text, "text");
      Objects.requireNonNull(query, "query");
      Objects.requireNonNull(formatters, "formatters");
      if (formatters.length == 0) {
         throw new DateTimeParseException("No formatters specified", text, 0);
      } else if (formatters.length == 1) {
         return formatters[0].parse(text, query);
      } else {
         for(DateTimeFormatter formatter : formatters) {
            try {
               ParsePosition pp = new ParsePosition(0);
               formatter.parseUnresolved(text, pp);
               int len = text.length();
               if (pp.getErrorIndex() == -1 && pp.getIndex() == len) {
                  return formatter.parse(text, query);
               }
            } catch (RuntimeException var9) {
            }
         }

         throw new DateTimeParseException("Text '" + text + "' could not be parsed", text, 0);
      }
   }

   public static ChronoUnit chronoUnit(TimeUnit unit) {
      Objects.requireNonNull(unit, "unit");
      switch (unit) {
         case NANOSECONDS:
            return ChronoUnit.NANOS;
         case MICROSECONDS:
            return ChronoUnit.MICROS;
         case MILLISECONDS:
            return ChronoUnit.MILLIS;
         case SECONDS:
            return ChronoUnit.SECONDS;
         case MINUTES:
            return ChronoUnit.MINUTES;
         case HOURS:
            return ChronoUnit.HOURS;
         case DAYS:
            return ChronoUnit.DAYS;
         default:
            throw new IllegalArgumentException("Unknown TimeUnit constant");
      }
   }

   public static TimeUnit timeUnit(ChronoUnit unit) {
      Objects.requireNonNull(unit, "unit");
      switch (unit) {
         case NANOS:
            return TimeUnit.NANOSECONDS;
         case MICROS:
            return TimeUnit.MICROSECONDS;
         case MILLIS:
            return TimeUnit.MILLISECONDS;
         case SECONDS:
            return TimeUnit.SECONDS;
         case MINUTES:
            return TimeUnit.MINUTES;
         case HOURS:
            return TimeUnit.HOURS;
         case DAYS:
            return TimeUnit.DAYS;
         default:
            throw new IllegalArgumentException("ChronoUnit cannot be converted to TimeUnit: " + unit);
      }
   }

   public static long[] convertAmount(long amount, TemporalUnit fromUnit, TemporalUnit toUnit) {
      Objects.requireNonNull(fromUnit, "fromUnit");
      Objects.requireNonNull(toUnit, "toUnit");
      validateUnit(fromUnit);
      validateUnit(toUnit);
      if (fromUnit.equals(toUnit)) {
         return new long[]{amount, 0L};
      } else if (isPrecise(fromUnit) && isPrecise(toUnit)) {
         long fromNanos = fromUnit.getDuration().toNanos();
         long toNanos = toUnit.getDuration().toNanos();
         if (fromNanos > toNanos) {
            long multiple = fromNanos / toNanos;
            return new long[]{Math.multiplyExact(amount, multiple), 0L};
         } else {
            long multiple = toNanos / fromNanos;
            return new long[]{amount / multiple, amount % multiple};
         }
      } else {
         int fromMonthFactor = monthMonthFactor(fromUnit, fromUnit, toUnit);
         int toMonthFactor = monthMonthFactor(toUnit, fromUnit, toUnit);
         if (fromMonthFactor > toMonthFactor) {
            long multiple = (long)(fromMonthFactor / toMonthFactor);
            return new long[]{Math.multiplyExact(amount, multiple), 0L};
         } else {
            long multiple = (long)(toMonthFactor / fromMonthFactor);
            return new long[]{amount / multiple, amount % multiple};
         }
      }
   }

   private static void validateUnit(TemporalUnit unit) {
      if (unit instanceof ChronoUnit) {
         if (unit.equals(ChronoUnit.ERAS) || unit.equals(ChronoUnit.FOREVER)) {
            throw new UnsupportedTemporalTypeException("Unsupported TemporalUnit: " + unit);
         }
      } else if (!unit.equals(IsoFields.QUARTER_YEARS)) {
         throw new UnsupportedTemporalTypeException("Unsupported TemporalUnit: " + unit);
      }

   }

   private static boolean isPrecise(TemporalUnit unit) {
      return unit instanceof ChronoUnit && ((ChronoUnit)unit).compareTo(ChronoUnit.WEEKS) <= 0;
   }

   private static int monthMonthFactor(TemporalUnit unit, TemporalUnit fromUnit, TemporalUnit toUnit) {
      if (unit instanceof ChronoUnit) {
         switch ((ChronoUnit)unit) {
            case MONTHS:
               return 1;
            case YEARS:
               return 12;
            case DECADES:
               return 120;
            case CENTURIES:
               return 1200;
            case MILLENNIA:
               return 12000;
            default:
               throw new DateTimeException(String.format("Unable to convert between units: %s to %s", fromUnit, toUnit));
         }
      } else {
         return 3;
      }
   }

   public static BigDecimal durationToBigDecimalSeconds(Duration duration) {
      return BigDecimal.valueOf(duration.getSeconds()).add(BigDecimal.valueOf((long)duration.getNano(), 9));
   }

   public static Duration durationFromBigDecimalSeconds(BigDecimal seconds) {
      BigInteger nanos = seconds.setScale(9, RoundingMode.UP).max(Temporals.BigDecimalSeconds.MIN).min(Temporals.BigDecimalSeconds.MAX).unscaledValue();
      BigInteger[] secondsNanos = nanos.divideAndRemainder(BigInteger.valueOf(1000000000L));
      return Duration.ofSeconds(secondsNanos[0].longValue(), (long)secondsNanos[1].intValue());
   }

   public static double durationToDoubleSeconds(Duration duration) {
      return duration.getSeconds() < 1000000000L ? (double)duration.toNanos() / (double)1.0E9F : durationToBigDecimalSeconds(duration).doubleValue();
   }

   public static Duration durationFromDoubleSeconds(double seconds) {
      return durationFromBigDecimalSeconds(BigDecimal.valueOf(seconds));
   }

   public static Duration multiply(Duration duration, double multiplicand) {
      if (multiplicand != (double)0.0F && !duration.isZero()) {
         if (multiplicand == (double)1.0F) {
            return duration;
         } else {
            BigDecimal amount = durationToBigDecimalSeconds(duration);
            amount = amount.multiply(BigDecimal.valueOf(multiplicand));
            return durationFromBigDecimalSeconds(amount);
         }
      } else {
         return Duration.ZERO;
      }
   }

   private static enum Adjuster implements TemporalAdjuster {
      NEXT_WORKING {
         public Temporal adjustInto(Temporal temporal) {
            int dow = temporal.get(ChronoField.DAY_OF_WEEK);
            switch (dow) {
               case 5:
                  return temporal.plus(3L, ChronoUnit.DAYS);
               case 6:
                  return temporal.plus(2L, ChronoUnit.DAYS);
               default:
                  return temporal.plus(1L, ChronoUnit.DAYS);
            }
         }
      },
      PREVIOUS_WORKING {
         public Temporal adjustInto(Temporal temporal) {
            int dow = temporal.get(ChronoField.DAY_OF_WEEK);
            switch (dow) {
               case 1:
                  return temporal.minus(3L, ChronoUnit.DAYS);
               case 7:
                  return temporal.minus(2L, ChronoUnit.DAYS);
               default:
                  return temporal.minus(1L, ChronoUnit.DAYS);
            }
         }
      },
      NEXT_WORKING_OR_SAME {
         public Temporal adjustInto(Temporal temporal) {
            int dow = temporal.get(ChronoField.DAY_OF_WEEK);
            switch (dow) {
               case 6:
                  return temporal.plus(2L, ChronoUnit.DAYS);
               case 7:
                  return temporal.plus(1L, ChronoUnit.DAYS);
               default:
                  return temporal;
            }
         }
      },
      PREVIOUS_WORKING_OR_SAME {
         public Temporal adjustInto(Temporal temporal) {
            int dow = temporal.get(ChronoField.DAY_OF_WEEK);
            switch (dow) {
               case 6:
                  return temporal.minus(1L, ChronoUnit.DAYS);
               case 7:
                  return temporal.minus(2L, ChronoUnit.DAYS);
               default:
                  return temporal;
            }
         }
      };

      private Adjuster() {
      }
   }

   private static final class BigDecimalSeconds {
      public static final BigDecimal MIN = BigDecimal.valueOf(Long.MIN_VALUE).add(BigDecimal.valueOf(0L, 9));
      public static final BigDecimal MAX = BigDecimal.valueOf(Long.MAX_VALUE).add(BigDecimal.valueOf(999999999L, 9));
   }
}
