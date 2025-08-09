package org.apache.commons.lang3.time;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.LongRange;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.function.FailableBiConsumer;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableRunnable;
import org.apache.commons.lang3.math.NumberUtils;

public class DurationUtils {
   static final LongRange LONG_TO_INT_RANGE;

   public static void accept(FailableBiConsumer consumer, Duration duration) throws Throwable {
      if (consumer != null && duration != null) {
         consumer.accept(duration.toMillis(), getNanosOfMilli(duration));
      }

   }

   /** @deprecated */
   @Deprecated
   public static int getNanosOfMiili(Duration duration) {
      return getNanosOfMilli(duration);
   }

   public static int getNanosOfMilli(Duration duration) {
      return zeroIfNull(duration).getNano() % 1000000;
   }

   public static boolean isPositive(Duration duration) {
      return !duration.isNegative() && !duration.isZero();
   }

   private static Instant now(FailableConsumer nowConsumer) throws Throwable {
      Instant start = Instant.now();
      nowConsumer.accept(start);
      return start;
   }

   public static Duration of(FailableConsumer consumer) throws Throwable {
      Objects.requireNonNull(consumer);
      return since(now(consumer::accept));
   }

   public static Duration of(FailableRunnable runnable) throws Throwable {
      return of((FailableConsumer)((start) -> runnable.run()));
   }

   public static Duration since(Temporal startInclusive) {
      return Duration.between(startInclusive, Instant.now());
   }

   static ChronoUnit toChronoUnit(TimeUnit timeUnit) {
      switch ((TimeUnit)Objects.requireNonNull(timeUnit)) {
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
            throw new IllegalArgumentException(timeUnit.toString());
      }
   }

   public static Duration toDuration(long amount, TimeUnit timeUnit) {
      return Duration.of(amount, toChronoUnit(timeUnit));
   }

   public static int toMillisInt(Duration duration) {
      Objects.requireNonNull(duration, "duration");
      return ((Long)LONG_TO_INT_RANGE.fit(duration.toMillis())).intValue();
   }

   public static Duration zeroIfNull(Duration duration) {
      return (Duration)ObjectUtils.defaultIfNull(duration, Duration.ZERO);
   }

   static {
      LONG_TO_INT_RANGE = LongRange.of(NumberUtils.LONG_INT_MIN_VALUE, NumberUtils.LONG_INT_MAX_VALUE);
   }
}
